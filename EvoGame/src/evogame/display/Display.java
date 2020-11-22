/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evogame.display;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author christophersisa
 */
public class Display {
    
    private JFrame frame;
    private String title;
    private int width, height;
    private Canvas canvas; 
    
    
    //make display to set title width and height of frame
    public Display(String title, int width, int height){
        this.title = title;//set title
        this.width = width;//set width
        this.height = height;//set height 
        createDisplay(); // creates display
    }
    
    
    private void createDisplay(){
        frame= new JFrame(title);// new instance of jFrame
        frame.setSize(width, height); // size of window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // game closes in the background when program is closed
        frame.setResizable(false); // cant resize window
        frame.setLocationRelativeTo(null); //frame appears in center
        frame.setVisible(true); // make window visable when opened
        
        canvas = new Canvas();//new canvas instance
        canvas.setPreferredSize(new Dimension(width,height));//set size
        canvas.setMaximumSize(new Dimension(width,height));//set min size
        canvas.setMinimumSize(new Dimension(width,height));//set max size
        
        frame.add(canvas);
        frame.pack();

    }
    
    public Canvas getCanvas(){
        return canvas;
    }
    
    
}
