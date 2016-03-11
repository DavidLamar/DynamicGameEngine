package Rooms;

import DynamicGameEngine.Engine;
import DynamicGameEngine.Entities.Entity;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by David on 3/6/2016.
 */
public class Room extends KeyAdapter {

    protected int width;
    protected int height;
    protected int name;


    protected int view_x;
    protected int view_y;
    protected int view_width;
    protected int view_height;
    protected double view_scale;
    protected int view_rotation;


    protected Color backgroundColor = Color.black;
    protected Engine e;


    private ArrayList<Entity> entities = new ArrayList<Entity>();



    public Room(Engine e){
        this.e = e;
    }

    public void render(Graphics g){
        g.setColor(backgroundColor);
        g.fillRect(0, 0, e.getWindowWidth(), e.getWindowHeight());
    }

    public void update(){

    }

    public void setBackgroundColor(Color c){
        this.backgroundColor = c;
    }
}
