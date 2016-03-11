package TrialGame;

import DynamicGameEngine.Engine;
import DynamicGameEngine.Rooms.Room;

/**
 * Created by David on 3/6/2016.
 */
public class TrialGame {
    public static void main(String[] args){
        Engine engine = new Engine();
        engine.setTitle("This is a test");
        engine.setWidth(1000);
        engine.setHeight(200);

        //This is temporary so that I don't get NoSuchRoomException error
        Room room1 = new Room(engine);
        engine.setCurrentRoom(room1);

        engine.initialize();

    }
}
