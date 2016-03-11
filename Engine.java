import DynamicGameEngine.Exceptions.NoSuchRoomException;
import DynamicGameEngine.Rooms.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;




//TODO
    //Handle key listening with room changes and all that












/**
 * DynamicGameEngine; A user friendly java library that allows users to create their own games
 * in the Java programming language.
 *
 *
 * @author David Lamar
 *
 */
public class Engine extends Canvas implements Runnable{

    //These specify all of the variables that can be changed for customization of the engine
    private int width = 640;
    private int height = 480;
    private String title = "Your Game Title";
    private JFrame window;

    private Thread gameLoop;
    private boolean running = false;

    private double frameRate = 60.0;
    private double updateRate = 60.0;

    private Graphics graphics;
    private BufferStrategy buffStrat;

    private Room currentRoom;
    private ArrayList<Room> loadedRooms = new ArrayList<Room>();

    //The following variables are for development purposes:
    private int frames = 0;
    private int updates = 0;

    public void initialize(){
        window = new JFrame();
        Dimension dim = new Dimension(width, height);

        window.setPreferredSize(dim);
        window.setMaximumSize(dim);
        window.setMinimumSize(dim);
        window.setTitle(title);

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.add(this);
        window.setVisible(true);
        this.start();
    }

    public synchronized void start(){
        gameLoop = new Thread(this);
        gameLoop.start();
        running = true;
    }

    public synchronized void stop(){
        try{
            gameLoop.join();
            running = false;
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        long past = System.nanoTime();
        long present;
        long timer = System.currentTimeMillis();
        double render = 0;
        double update = 0;
        int fr = 0;
        int ur = 0;

        while(running){
            present = System.nanoTime();
            render += (present - past ) / (1000000000 / frameRate);
            update += (present - past ) / (1000000000 / updateRate);
            past = present;

            //Renders to the screen every (frameRate/second)
            if(render >= 1){
                render();
                render = 0;
                fr += 1;
            }

            //Updates the engine every (updateRate/second)
            if(update >= 1){
                update();
                update = 0;
                ur += 1;
            }

            //Timer used for some dev calculations
            if(System.currentTimeMillis() - timer > 1000){
                timer = System.currentTimeMillis();
                frames = fr;
                updates = ur;
                fr = 0;
                ur = 0;
            }
        }

        stop();
    }

    public void render(){
        buffStrat = getBufferStrategy();

        if(buffStrat == null){
            createBufferStrategy(2);
            return;
        }

        graphics = buffStrat.getDrawGraphics();

        try {
            if(currentRoom == null){
                throw new NoSuchRoomException();
            }

            currentRoom.render(graphics);

        } catch (NoSuchRoomException e) {
            e.printStackTrace();
            System.exit(0);
        }


        graphics.dispose();
        buffStrat.show();
    }

    public void update(){
        window.setTitle(title + " " + frames + " " + updates);
        if(currentRoom != null){
            currentRoom.update();
        }
    }


    public void loadRoom(Room r){
        loadedRooms.add(r);
    }

    public void goToRoom(Room r){
        currentRoom = r;
    }

    public int getWindowWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWindowHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }
}
