package game;

import java.awt.*;
import java.util.Random;

public class Player extends GameObject{

    Random random = new Random();

    public Player(int x, int y, Id id) {
        super(x, y, id);

    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x,y,32,32);
    }
}
