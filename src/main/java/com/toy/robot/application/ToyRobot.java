package com.toy.robot.application;

import com.toy.robot.exception.ToyRobotException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.util.ObjectUtils;

@RequiredArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class ToyRobot {

    private Location location;
    public boolean movePosition() throws ToyRobotException {
        return move(location.getNextLocation());
    }

    public boolean move(Location newLocation) {
        if (newLocation == null)
            return false;

        // change position
        this.location = newLocation;
        return true;
    }
    
    public boolean rotateLeftSide() {
        if (ObjectUtils.isEmpty(this.location.face))
            return false;

        this.location.face = this.location.face.leftDirection();
        return true;
    }

    public boolean rotateRightSide() {
        if (ObjectUtils.isEmpty(this.location.face))
            return false;

        this.location.face = this.location.face.rightDirection();
        return true;
    }

}
