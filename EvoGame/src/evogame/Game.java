
package evogame;

import evogame.display.Display;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Game implements Runnable {
    private Display display;
    public int width, height;
    public String title;
   
    private boolean running = false;
    private Thread thread;
    
    private BufferStrategy bs;
    private Graphics g;
    
    
    
    
    
    
    public Game(String title,int width, int height){
        this.width = width;
        this.height = height;
        this.title = title;
    }
    
   
    private void init(){
        display = new Display(title, width, height);
    }
    
    
    private void tick(){
    
    }
    
    
    private void render(){
        bs = display.getCanvas().getBufferStrategy();//set buffer to game canvas
        if(bs==null){
            display.getCanvas().createBufferStrategy(3);// creates buffer strategy
            return;
        }
        g = bs.getDrawGraphics();
        //clear screen
        g.clearRect(0, 0, width, height);
        // draw here! first rec are below others
        
        g.setColor(Color.red);
        g.fillRect(10, 50, 50, 70);

        
        
        
        
        //end drawing 
        bs.show();// shows drawing
        g.dispose();// 
    }
    
    
    public void run(){
        init();
        
        int fps= 60;
        double timePerTick = 1000000000/fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;
            
        
        while(running){
            now =System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;
            
            
            if(delta >= 1){
                tick();
                render();
                ticks++;
                delta--;
            }
            if(timer>= 1000000000){
                System.out.println("ticks and frames per sec: " + ticks);
                ticks= 0;
                timer = 0;
            }
        }
        stop();
    }
    
    
    public synchronized void start(){
        if (running)
            return;
        running = true; 
        thread = new Thread(this);// makes this class into thread
        thread.start();//call run method
    }
    
    
    public synchronized void stop(){
        if(!running)
            return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
