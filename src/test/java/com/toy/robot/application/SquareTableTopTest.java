package com.toy.robot.application;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class SquareTableTopTest {

    @Test
    public void testIsValidLocation(){
    SquareTableTop squareTableTop = new SquareTableTop(5,5);
    Location location = Mockito.mock(Location.class);
    when(location.getXOrdinate()).thenReturn(-1);
    when(location.getYOrdinate()).thenReturn(-1);
    Assert.assertTrue(squareTableTop.isValidLocation(location));

    when(location.getXOrdinate()).thenReturn(1);
    when(location.getYOrdinate()).thenReturn(1);
    Assert.assertTrue(squareTableTop.isValidLocation(location));
    }
}
