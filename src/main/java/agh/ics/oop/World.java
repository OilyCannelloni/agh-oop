package agh.ics.oop;

import java.util.ArrayList;

public class World {
    static void run(ArrayList<MoveDirection> args) {
        //System.out.println(String.join(", ", args));
        Animal myAnimal = new Animal();
        System.out.println(myAnimal);


        for (MoveDirection arg : args) {
            switch (arg) {
                case FORWARD:
                    System.out.println("zwierzak idzie do przodu");
                    myAnimal.move(MoveDirection.FORWARD);
                    break;
                case BACKWARD:
                    System.out.println("zwierzak idzie do tyłu");
                    myAnimal.move(MoveDirection.BACKWARD);
                    break;
                case RIGHT:
                    System.out.println("zwierzak skręca w prawo");
                    myAnimal.move(MoveDirection.RIGHT);
                    break;
                case LEFT:
                    System.out.println("zwierzak skręca w lewo");
                    myAnimal.move(MoveDirection.LEFT);
                    break;
            }
            System.out.println(myAnimal);
        }


    }


    public static void main(String[] args) {
        System.out.println("Start systemu");

        ArrayList <MoveDirection> dirs = OptionsParser.parse(args);

        run(dirs);

        System.out.println("Stop systemu");
    }
}