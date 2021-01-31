/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegamejavafx;


import javafx.application.Application;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import java.util.ArrayList;
/**
 *
 * @author christophersisa
 */
public class SnakeGameJavafx extends Application implements EventHandler<KeyEvent>{

    String direction = "RIGHT";
    String direction1 = "LEFT";
    
   
    double unitWidth=60;
    double unitHeight= 41; // must be odd number
    double recSize = 10;
    double width= unitWidth * recSize;
    double height=  unitHeight * recSize;
    String tempDir = "RIGHT";
    String tempDir1= "LEFT";
    double  speed = 1; //speed 0,1,2,3,ect
    
    private Timeline timeline;
    double color;
    
    
    double rows = height / recSize;
    double columns = width/ recSize;
    double midpoint = (Math.round(rows /2)) *recSize - recSize;
    double x= 0;
    double y = midpoint;
    double x1 = columns * recSize - recSize;
    double y1 = midpoint;
    
    
//    List<Integer> snake= new ArrayList<Integer>();

    ArrayList<Integer> snake1 =new ArrayList<Integer>();
    ArrayList<Integer> snake2 =new ArrayList<Integer>();
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        
    
        
        
        stage.setTitle("2 Player Snake Game ");
        Group root = new Group();
        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Scene scene = new Scene(root, width, height, Color.BLACK);
        scene.setOnKeyPressed(this);
        
        
        
        
        
        AnimationTimer timer = new AnimationTimer(){
            public void handle(long nanotime){
                Color c = Color.hsb(color,1,1);
                color++;
                gc.setFill(c);
                gc.fillRect(x, y, recSize, recSize);
                gc.fillRect(x1, y1, recSize, recSize);
                
                if(checkLocation(x,y)){
                    
                     tempDir = direction;
                     snake1.add((int)x);
                     snake1.add((int)y);
                }
                 continueDirection();

                 if(checkLocation(x1,y1)){
                 
                    tempDir1 = direction1;
                    snake2.add((int)x1);
                    snake2.add((int)y1);
                    
                    if(CheckCollision()){
                     this.stop();
                    }
                    
                    if(snake1.size()>2){
                        System.out.println("x1: " +snake1.get(snake1.size()-2));
                        System.out.println("y1: " +snake1.get(snake1.size()-1));
                        
                 
                        System.out.println("x2: " + snake2.get(snake2.size()-2));
                        System.out.println("y2: " + snake2.get(snake2.size()-1));
                        System.out.println("---------------------------");
                    }
                 }
                 continueDirection1();
                 
                 
                 
                 
            }
        };
        
        timer.start();
        root.getChildren().add(canvas);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
    
    
    
    public boolean checkLocation(double X, double Y){
        double placementx= X%recSize;
        double placementy = Y % recSize;
        if((placementx) == 0 && (placementy) ==0){
            return true;
        }else{
            return false;
        }
    }

    
        public void continueDirection(){
            if(tempDir == "UP"){
                moveUp();
            }else if(tempDir == "LEFT"){
                moveLeft();
             }else if(tempDir == "RIGHT"){
                moveRight();
            }else if(tempDir == "DOWN"){
                moveDown();
            }
        }
        
                
                
        public void continueDirection1(){
            if(tempDir1 == "UP"){
                moveUp1();
            }else if(tempDir1 == "LEFT"){
                moveLeft1();
             }else if(tempDir1 == "RIGHT"){
                moveRight1();
            }else if(tempDir1 == "DOWN"){
                moveDown1();
            }
        }
        
        
    public void moveUp1(){
        y1--;
        y1= y1- speed;
    }
    public void moveRight1(){
        x1++;
        x1= x1 + speed;
    }
    public void moveLeft1(){
        x1--;
        x1 = x1-speed;
    }
    public void moveDown1(){
        y1++;
        y1=y1+speed;
    }
    

    
    public void moveUp(){
        y--;
        y= y- speed;
    }
    public void moveRight(){
        x++;
        x= x + speed;
    }
    public void moveLeft(){
        x--;
        x = x-speed;
    }
    public void moveDown(){
        y++;
        y=y+speed;
    }
    
    

    

       @Override
    public void handle(KeyEvent event){
        switch(event.getCode()){
            case UP:
                if(direction1 != "DOWN")
                    direction1 = "UP";
                break;
            case DOWN:
                if(direction1 != "UP")
                    direction1 = "DOWN";
                break;
            case LEFT:
                if(direction1 != "RIGHT")
                    direction1 = "LEFT";
                break;
            case RIGHT:
                if(direction1 != "LEFT")
                    direction1 = "RIGHT";
                break;
            case SPACE:
                    direction1 = "NOTHING" ;
                break;
            case W:
                if(direction!= "DOWN")
                    direction = "UP";
                break;
            case S:
                if(direction!= "UP")
                    direction = "DOWN";
                break;
            case A:
                if(direction!= "RIGHT")
                    direction = "LEFT";
                break;
            case D:
                if(direction!= "LEFT")
                    direction = "RIGHT";
                break;
        }
    }
     
    //if snakes collides function returns true 
    public boolean CheckCollision(){
        
        double snake1HeadX = snake1.get(snake1.size()-2);
        double snake1HeadY = snake1.get(snake1.size()-1);
        double snake2HeadX = snake2.get(snake1.size()-2);
        double snake2HeadY = snake2.get(snake1.size()-1);
        boolean trueSoFar = false;
        double commonYindex ;
        
        //checks collision of snake 1 hitting snake 2
         for (int i = 0; i < snake2.size(); i=i+2){ 
                if(snake1HeadX == snake2.get(i)){
                    trueSoFar = true;
                    commonYindex = i+1;
                    if (snake1HeadY == snake2.get((int)commonYindex)){
                        return true;
                    }else{
                        trueSoFar = false;
                    }
                }
        }
         
        //checks collision between snake 2 hitting snake 1
        for (int i = 0; i < snake1.size(); i=i+2){ 
                if(snake2HeadX == snake1.get(i)){
                    trueSoFar = true;
                    commonYindex = i+1;
                    if (snake2HeadY == snake1.get((int)commonYindex)){
                        return true;
                    }
                }
        }
        
        
        //checks snake 1 collision with itself 
        if(snake1.size()>2){
            for (int i = 0; i < snake1.size()-2; i=i+2){ 
                if(snake1HeadX == snake1.get(i)){
                    commonYindex = i+1;
                    if (snake1HeadY == snake1.get((int)commonYindex))
                           return true;//
                }
           }
        } 
        
        
        //checks snake 2 collision with itself 
         if(snake2.size()>2){
            for (int i = 0; i < snake2.size()-2; i=i+2){ 
                if(snake2HeadX == snake2.get(i)){
                    commonYindex = i+1;
                    if (snake2HeadY == snake2.get((int)commonYindex))
                           return true;//
                }
           }
        }        
                
        
        
        if(snake1HeadX < 0 || snake1HeadX > width)
            return true;
        if(snake1HeadY < 0 || snake1HeadY >height)
            return true;
        if(snake2HeadX < 0 || snake2HeadX > width)
            return true;
        if(snake2HeadY < 0 || snake2HeadY >height)
            return true;

        
         
        return false;
    }

    
}
