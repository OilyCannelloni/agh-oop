package agh.ics.oop;

import java.util.ArrayList;

public class World {
    static void run(ArrayList<Direction> args) {
        //System.out.println(String.join(", ", args));

        for (Direction arg: args) {
            switch (arg) {
                case FORWARD:
                    System.out.println("zwierzak idzie do przodu");
                    break;
                case BACKWARD:
                    System.out.println("zwierzak idzie do tyłu");
                    break;
                case RIGHT:
                    System.out.println("zwierzak idzie w prawo");
                    break;
                case LEFT:
                    System.out.println("zwierzak idzie w lewo");
                    break;
            }
        }


    }


    public static void main(String[] args) {
        System.out.println("Start systemu");
        ArrayList <Direction> dirs = new ArrayList<>();

        for (String arg : args) {
            switch(arg) {
                case "f":
                    dirs.add(Direction.FORWARD);
                    break;
                case "b":
                    dirs.add(Direction.BACKWARD);
                    break;
                case "l":
                    dirs.add(Direction.LEFT);
                    break;
                case "r":
                    dirs.add(Direction.RIGHT);
                    break;
            }
        }

        run(dirs);
        System.out.println("Stop systemu");
    }
}