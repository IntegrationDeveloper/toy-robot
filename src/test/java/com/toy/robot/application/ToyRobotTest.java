package com.toy.robot.application;


import com.toy.robot.exception.ToyRobotException;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class ToyRobotTest {

    @Test
    public void testRobotMove() throws ToyRobotException {
        ToyRobot robot = new ToyRobot(new Location(0, 0, Face.NORTH));
        robot.movePosition();
        robot.movePosition();
        robot.rotateRightSide();
        robot.movePosition();
        robot.movePosition();
        robot.movePosition();
        robot.rotateRightSide();
        robot.movePosition();
        robot.movePosition();
        robot.movePosition();
        Assert.assertEquals(3, robot.getLocation().getXOrdinate());
        Assert.assertEquals(2, robot.getLocation().getYOrdinate());
        Assert.assertEquals(Face.SOUTH, robot.getLocation().getFace());
    }

}
