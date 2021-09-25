package com.toy.robot.application;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
public class SquareTableTop {
	protected int rows;
	protected int columns;

	public boolean isValidLocation(Location newLocation) {
		return !(
				newLocation.getXOrdinate() > this.columns || newLocation.getXOrdinate() < 0 ||
						newLocation.getYOrdinate() > this.rows || newLocation.getYOrdinate() < 0
		);
	}
}
