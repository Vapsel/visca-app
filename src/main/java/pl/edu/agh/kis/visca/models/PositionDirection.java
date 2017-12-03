package pl.edu.agh.kis.visca.models;

import lombok.Getter;

@Getter
public enum PositionDirection {
    UP("up"),
    LEFT("left"),
    RIGHT("right"),
    DOWN("down");

    private String direction;

    PositionDirection(String direction) {
        this.direction = direction;
    }
}
