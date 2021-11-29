package agh.ics.oop;

import java.util.ArrayList;

public class OptionsParser {
    public static ArrayList<MoveDirection> parse(String[] args){
        ArrayList <MoveDirection> dirs = new ArrayList<>();
        for (String arg : args) {
            switch(arg) {
                case "f":
                case "forward":
                    dirs.add(MoveDirection.FORWARD);
                    break;
                case "b":
                case "backward":
                    dirs.add(MoveDirection.BACKWARD);
                    break;
                case "l":
                case "left":
                    dirs.add(MoveDirection.LEFT);
                    break;
                case "r":
                case "right":
                    dirs.add(MoveDirection.RIGHT);
                    break;
                default:
                    throw new IllegalArgumentException(arg + " is not legal move specification");
            }
        }
        return dirs;
    }
}
