package Entities;

import java.awt.*;


/**
 * Created by David on 3/6/2016.
 */
public abstract class Entity {

    protected int x;
    protected int y;
    protected boolean solid = true;
    protected boolean visible = true;
    protected boolean controllable = false;



    public abstract void render(Graphics g);
    public abstract void update();



    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isSolid() {
        return solid;
    }

    public void setSolid(boolean solid) {
        this.solid = solid;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isControllable() {
        return controllable;
    }

    public void setControllable(boolean controllable) {
        this.controllable = controllable;
    }
}
