package com.toy.robot.application;

import com.toy.robot.exception.ToyRobotException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class LocationTest {

    @Test
    public void testLocation() throws ToyRobotException {
        Location location = new Location(0, 0, Face.NORTH);

        Location newLocation = location.getNextLocation();
        Assert.assertEquals(newLocation.getXOrdinate(), 0);
        Assert.assertEquals(newLocation.getYOrdinate(), 1);
        Assert.assertEquals(newLocation.getFace(), Face.NORTH);

        newLocation = newLocation.getNextLocation();
        Assert.assertNotEquals(newLocation.getXOrdinate(), 1);
        Assert.assertEquals(newLocation.getYOrdinate(), 1);
        Assert.assertEquals(newLocation.getFace(), Face.NORTH);

    }
}
