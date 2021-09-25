package com.toy.robot.application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;


@RequiredArgsConstructor
@AllArgsConstructor
public enum Face {
    NORTH(0), EAST(1), SOUTH(2), WEST(3);
    private static Map<Integer, Face> map = new HashMap<Integer, Face>();

    static {
        for (Face directionEnum : Face.values()) {
            map.put(directionEnum.directionIndex, directionEnum);
        }
    }

    private int directionIndex;



    public static Face valueOf(int directionNum) {
        return map.get(directionNum);
    }

    public Face leftDirection() {
        return rotate(-1);
    }

    public Face rightDirection() {
        return rotate(1);
    }

    private Face rotate(int step) {

        int newIndex = (this.directionIndex + step) < 0 ?
                map.size() - 1 :
                (this.directionIndex + step) % map.size();

        return Face.valueOf(newIndex);
    }


}
