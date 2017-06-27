package game;

import java.awt.*;
import java.util.LinkedList;

public class Handler {

    LinkedList<GameObject> objects = new LinkedList<GameObject>();

    public void tick(){
        for (int i = 0; i < objects.size() ; i++) {
            GameObject temp = objects.get(i);
            temp.tick();
        }
    }

    public void render(Graphics g){
        for (int i = 0; i < objects.size(); i++) {
            GameObject temp = objects.get(i);
            temp.render(g);
        }
    }

    public void addObject(GameObject object){
        this.objects.add(object);
    }

    public void removeObject(GameObject object){
        this.objects.remove(object);
    }
}
