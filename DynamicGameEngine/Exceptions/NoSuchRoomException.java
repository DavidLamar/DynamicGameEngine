package DynamicGameEngine.Exceptions;

/**
 * Created by David on 3/8/2016.
 */
public class NoSuchRoomException extends Exception{
    public NoSuchRoomException(String message){
        super(message);
    }

    public NoSuchRoomException(){
        super("No room found; set the current or start room.");
    }
}
