import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class AsteroidsGame extends PApplet {

SpaceShip ship;
Star[] yes;
int width, height;
public void setup() 
{
  //your code here
  width = 500;
  height = 500;
  size(width,height);
  ship = new SpaceShip();
  yes = new Star[50];
  for(int i =0;i<yes.length;i++)
  {
    yes[i] = new Star();
  }
  background(0);
}
public void draw() 
{
  smooth();
  
  //your code here
  background(0);
  for(int j=0;j<yes.length;j++)
  {
    yes[j].show();
  }
  ship.show();
  //ship.accelerate();
  ship.move();
}
public void keyPressed()
{
  fill(255);
  if(keyCode == LEFT)
  {
    ship.rotate(-10);
  }
  else if(keyCode == RIGHT)
  {
    ship.rotate(10);
  }
  if(keyCode == UP)
  {
    ship.accelerate(.2f);
  }
  else if(keyCode == DOWN)
  {
    ship.accelerate(-.2f);
  }
  if(key == 'h')
  {
    hyperspace(ship, yes);
    println('h');
  }
}
public void hyperspace(SpaceShip dog, Star[] ye)
{
  for(int k=0;k<ye.length;k++)
  {
    ye[k].setX((int)(Math.random()*500));
    ye[k].setY((int)(Math.random()*500));
  }
  dog.setDirectionX(0);
  dog.setDirectionY(0);
  dog.setX((int)(Math.random()*500));
  dog.setY((int)(Math.random()*500));
  dog.setPointDirection((int)(Math.random()*360));
  
}
class Star
{
  private int x,y;
  Star()
  {
    x = (int)(Math.random()*500);
    y = (int)(Math.random()*500);
  }
  public void setX(int num)
  {
    x = num;
  }
  public int getX()
  {
    return x;
  }
  public void setY(int num)
  {
    y = num;
  }
  public int getY()
  {
    return y;
  }
  public void show()
  {
    fill(255);
    ellipse(x,y,3,3);
  }
}
class SpaceShip extends Floater  
{   
    SpaceShip()
    {
      corners = 3;
      xCorners = new int[corners];
      yCorners = new int[corners];
      xCorners[0] = -8;
      yCorners[0] = -8;
      xCorners[1] = 16;
      yCorners[1] = 0;
      xCorners[2] = -8;
      yCorners[2] = 8;
      myColor = color(255);
      myCenterX = 250;
      myCenterY = 250;
      myDirectionX = 0;
      myDirectionY = 0;
      myPointDirection = 0;
    }
    public void setX(int x){myCenterX = x;}
    public int getX(){return (int)myCenterX;}
    public void setY(int y){myCenterY = y;}
    public int getY(){return (int)myCenterY;}
    public void setDirectionX(double x){myDirectionX = x;} 
    public double getDirectionX(){return myDirectionX;}
    public void setDirectionY(double y){myDirectionY = y;}
    public double getDirectionY(){return myDirectionY;}
    public void setPointDirection(int degrees){myPointDirection = degrees;} 
    public double getPointDirection(){return myPointDirection;}
}
abstract class Floater //Do NOT modify the Floater class! Make changes in the SpaceShip class 
{   
  protected int corners;  //the number of corners, a triangular floater has 3   
  protected int[] xCorners;   
  protected int[] yCorners;   
  protected int myColor;   
  protected double myCenterX, myCenterY; //holds center coordinates   
  protected double myDirectionX, myDirectionY; //holds x and y coordinates of the vector for direction of travel   
  protected double myPointDirection; //holds current direction the ship is pointing in degrees    
  abstract public void setX(int x);  
  abstract public int getX();   
  abstract public void setY(int y);   
  abstract public int getY();   
  abstract public void setDirectionX(double x);   
  abstract public double getDirectionX();   
  abstract public void setDirectionY(double y);   
  abstract public double getDirectionY();   
  abstract public void setPointDirection(int degrees);   
  abstract public double getPointDirection(); 

  //Accelerates the floater in the direction it is pointing (myPointDirection)   
  public void accelerate (double dAmount)   
  {          
    //convert the current direction the floater is pointing to radians    
    double dRadians =myPointDirection*(Math.PI/180);     
    //change coordinates of direction of travel    
    myDirectionX += ((dAmount) * Math.cos(dRadians));    
    myDirectionY += ((dAmount) * Math.sin(dRadians));       
  }   
  public void rotate (int nDegreesOfRotation)   
  {     
    //rotates the floater by a given number of degrees    
    myPointDirection+=nDegreesOfRotation;   
  }   
  public void move ()   //move the floater in the current direction of travel
  {      
    //change the x and y coordinates by myDirectionX and myDirectionY       
    myCenterX += myDirectionX;    
    myCenterY += myDirectionY;     

    //wrap around screen    
    if(myCenterX >width)
    {     
      myCenterX = 0;    
    }    
    else if (myCenterX<0)
    {     
      myCenterX = width;    
    }    
    if(myCenterY >height)
    {    
      myCenterY = 0;    
    }   
    else if (myCenterY < 0)
    {     
      myCenterY = height;    
    }   
  }   
  public void show ()  //Draws the floater at the current position  
  {             
    fill(myColor);   
    stroke(myColor);    
    //convert degrees to radians for sin and cos         
    double dRadians = myPointDirection*(Math.PI/180);                 
    int xRotatedTranslated, yRotatedTranslated;    
    beginShape();         
    for(int nI = 0; nI < corners; nI++)    
    {     
      //rotate and translate the coordinates of the floater using current direction 
      xRotatedTranslated = (int)((xCorners[nI]* Math.cos(dRadians)) - (yCorners[nI] * Math.sin(dRadians))+myCenterX);     
      yRotatedTranslated = (int)((xCorners[nI]* Math.sin(dRadians)) + (yCorners[nI] * Math.cos(dRadians))+myCenterY);      
      vertex(xRotatedTranslated,yRotatedTranslated);    
    }   
    endShape(CLOSE);  
  }   
} 

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "AsteroidsGame" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
