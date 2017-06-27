package game;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{

    private static final long serialVersionID = 1550691097822371818L;

    private static final int WIDTH = 640;
    private static final int HEIGHT = WIDTH/12*9;
    private Thread thread;
    private boolean running = false;

    private Random random;
    private Handler handler;

    public Game() {
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));

        new Window(WIDTH,HEIGHT, "Lets Build a Game!",this);

        random = new Random();

        handler.addObject(new Player(WIDTH/2-32,HEIGHT/2-32,Id.Player));
    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double nanoseconds = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running){
            long now = System.nanoTime();
            delta += (now - lastTime) / nanoseconds;
            lastTime = now;
            while (delta >= 1){
                tick();
                delta--;
            }
            if (running)
                render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000){
                timer+= 1000;
                //System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick() {
        handler.tick();
    }

    private void render(){
        BufferStrategy strategy = this.getBufferStrategy();
        if (strategy == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics graphics = strategy.getDrawGraphics();
        graphics.setColor(Color.black);
        graphics.fillRect(0,0,WIDTH,HEIGHT);

        handler.render(graphics);

        graphics.dispose();
        strategy.show();
    }

    public static void main(String[] args) {
        new Game();
    }
}
