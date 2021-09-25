package com.toy.robot.application;

import org.junit.Assert;
import org.junit.Test;

public class FaceTest {

    @Test
    public void testFaceRotation(){
        Face direction = Face.EAST;

        direction = direction.leftDirection();
        Assert.assertEquals(direction, Face.NORTH);
    }
}
