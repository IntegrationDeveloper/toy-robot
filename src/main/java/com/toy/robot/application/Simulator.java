package com.toy.robot.application;

import com.toy.robot.exception.ToyRobotException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Simulator {

    public static Logger log = LoggerFactory.getLogger(Simulator.class);

    SquareTableTop squareTableTop;
    ToyRobot toyRobot;

    public String simulate(String inputString) throws ToyRobotException {

        String[] commandParams = inputString.split(" ");

        // validate command
        Command command = validateCommand(commandParams);
        // validate PLACE command params
        validatePlaceCommand(squareTableTop);
        //validate command params face and location
        Location robotLocation = validateCommandParams(commandParams);

        return executeCommand(command, robotLocation);
    }

    private boolean placeToyRobot(Location location) throws ToyRobotException {

        // validate location of robot
        validateLocation(location);

        // if position is valid then assign values to fields
        boolean isRobotPlaced = validateLocation(location);
        if (isRobotPlaced) {
            toyRobot.setLocation(location);
        }
        return isRobotPlaced;

    }

    private boolean validateLocation(Location location) throws ToyRobotException {
        if (ObjectUtils.isEmpty(location))
            throw new ToyRobotException("Invalid position object");

        if (ObjectUtils.isEmpty(location.getFace()))
            throw new ToyRobotException("Invalid direction value");

        // validate the position
        if (!squareTableTop.isValidLocation(location))
            return false;

        return true;
    }

    private String executeCommand(Command command, Location robotLocation) {
        String output = null;
        try {
            switch (command) {
                case PLACE:
                    output = String.valueOf(placeToyRobot(robotLocation));
                    break;
                case MOVE:
                    Location newLocation = toyRobot.getLocation().getNextLocation();
                    if (!squareTableTop.isValidLocation(newLocation))
                        output = String.valueOf(false);
                    else
                        output = String.valueOf(toyRobot.move(newLocation));
                    break;
                case LEFT:
                    output = String.valueOf(toyRobot.rotateLeftSide());
                    break;
                case RIGHT:
                    output = String.valueOf(toyRobot.rotateRightSide());
                    break;
                case REPORT:
                    output = report();
                    break;
                default:
                    throw new ToyRobotException("Invalid command");
            }
        } catch (ToyRobotException e) {
            log.error(e.getMessage());
        }
        return output;
    }

    private Location validateCommandParams(String[] commandParams) throws ToyRobotException {
        String[] params;
        int x = 0;
        int y = 0;
        Face commandFacing = null;
        if (Command.valueOf(commandParams[0]).equals(Command.PLACE)) {
            params = commandParams[1].split(",");
            try {
                x = Integer.parseInt(params[0]);
                y = Integer.parseInt(params[1]);
                commandFacing = Face.valueOf(params[2]);
            } catch (Exception e) {
                throw new ToyRobotException("Invalid command");
            }
        }
        return new Location(x, y, commandFacing);
    }

    private void validatePlaceCommand(SquareTableTop squareTableTop) {
        if (ObjectUtils.isEmpty(squareTableTop))
            try {
                throw new ToyRobotException("Invalid squareTableTop object");
            } catch (ToyRobotException e) {
                log.error(e.getMessage());
            }
    }

    private Command validateCommand(String[] commandParams) {
        Command command = Command.valueOf(commandParams[0]);
            if (command == Command.PLACE && commandParams.length < 2) {
                  log.error("Invalid command");
            }
        return command;
    }

    public String report() {
        if (ObjectUtils.isEmpty(toyRobot.getLocation()))
            return null;

        String outString = toyRobot.getLocation().getXOrdinate() + ","
                + toyRobot.getLocation().getYOrdinate() + ","
                + toyRobot.getLocation().getFace().toString();
        log.info(outString);
        return outString;
    }
}
