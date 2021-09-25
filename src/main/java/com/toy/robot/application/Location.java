package com.toy.robot.application;

import com.toy.robot.exception.ToyRobotException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.util.ObjectUtils;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Location {
    protected int xOrdinate;
    protected int yOrdinate;
    protected Face face;

    public Location(Location location) {
        this.xOrdinate = location.getXOrdinate();
        this.yOrdinate = location.getYOrdinate();
        this.face = location.getFace();
    }

    public void change(int x, int y) {
        this.xOrdinate = this.xOrdinate + x;
        this.yOrdinate = this.xOrdinate + y;
    }

    public Location getNextLocation() throws ToyRobotException {
        if (ObjectUtils.isEmpty(this.face))
            throw new ToyRobotException("Invalid robot direction");

        // new position to validate
        Location newLocation = new Location(this);
        switch (this.face) {
            case NORTH:
                newLocation.change(0, 1);
                break;
            case EAST:
                newLocation.change(1, 0);
                break;
            case SOUTH:
                newLocation.change(0, -1);
                break;
            case WEST:
                newLocation.change(-1, 0);
                break;
        }
        return newLocation;
    }
}

