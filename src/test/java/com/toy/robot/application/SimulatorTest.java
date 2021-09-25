package com.toy.robot.application;

import com.toy.robot.exception.ToyRobotException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimulatorTest {

    final int ROWS = 5;
    final int COLUMNS = 5;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void testValidateExceptions() throws ToyRobotException {
        SquareTableTop squareTableTop = new SquareTableTop(COLUMNS, ROWS);
        ToyRobot toyRobot = new ToyRobot();
        Simulator simulator = new Simulator(squareTableTop, toyRobot);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            simulator.simulate("PLACE00NORTH");
        });
        String expectedMessage = "No enum constant com.toy.robot.application.Command.PLACE00NORTH";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

    }
    @Test
    public void testSimulate() throws ToyRobotException {
        SquareTableTop squareTableTop = new SquareTableTop(COLUMNS, ROWS);
        ToyRobot toyRobot = new ToyRobot();
        Simulator simulator = new Simulator(squareTableTop, toyRobot);
        Assert.assertEquals("true", simulator.simulate("PLACE 0,0,NORTH"));
        Assert.assertEquals("true", simulator.simulate("MOVE"));
        Assert.assertEquals("true", simulator.simulate("MOVE"));
        Assert.assertEquals("0,1,NORTH", simulator.simulate("REPORT"));
    }
}
