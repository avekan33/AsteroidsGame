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
ArrayList<Asteroid> belt;
//Asteroid test;
int width, height;
public void setup() 
{
  //your code here
  width = 700;
  height = 700;
  size(width,height);
  ship = new SpaceShip();
  belt = new ArrayList<Asteroid>();
  yes = new Star[70];
  for(int i =0;i<yes.length;i++)
  {
    yes[i] = new Star();
  }
  for(int u =0;u<20;u++)
  {
    belt.add(u,new Asteroid());
  }
  /*test = new Asteroid();
  test.setRot(0);
  test.setX(350);
  test.setY(350);*/
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
  //test.show();
  for(int p =0;p<belt.size();p++)
  {
    (belt.get(p)).show();
    if(dist(ship.getX(),ship.getY(),belt.get(p).getX(), belt.get(p).getY()) < 20)
    {
      belt.remove(p);
    }
    else
    {
      (belt.get(p)).move();
    }
  }
  
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
  }
}
public void hyperspace(SpaceShip dog, Star[] ye)
{
  for(int k=0;k<ye.length;k++)
  {
    ye[k].setX((int)(Math.random()*700));
    ye[k].setY((int)(Math.random()*700));
  }
  dog.setDirectionX(0);
  dog.setDirectionY(0);
  dog.setX((int)(Math.random()*700));
  dog.setY((int)(Math.random()*700));
  dog.setPointDirection((int)(Math.random()*360));
  
}
class Star
{
  private int x,y;
  Star()
  {
    x = (int)(Math.random()*700);
    y = (int)(Math.random()*700);
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
class Asteroid extends Floater
{
  private int myRot;
  Asteroid()
  {
    corners = 8;
    xCorners = new int[corners];
    yCorners = new int[corners];
    xCorners[0] = -12;
    yCorners[0] = -4;
    xCorners[1] = -6;
    yCorners[1] = -8;
    xCorners[2] = 0;
    yCorners[2] = -12;
    xCorners[3] = 6;
    yCorners[3] = -10;
    xCorners[4] = 12;
    yCorners[4] = 0;
    xCorners[5] = 10;
    yCorners[5] = 6;
    xCorners[6] = 0;
    yCorners[6] = 12;
    xCorners[7] = -8;
    yCorners[7] = 6;
    myPointDirection = 0;
    myColor = color(255);
    myCenterX = (int)(Math.random()*700);
    myCenterY = (int)(Math.random()*700);
    myDirectionX = (int)(Math.random()*4 - 2);
    if(myDirectionX == 0){myDirectionX++;}
    myDirectionY = (int)(Math.random()*4 - 2);
    if(myDirectionY == 0){myDirectionY++;}
    myRot = (int)(Math.random()*10-5);
    if(myRot == 0){myRot ++;}
  }
  public int getRot(){return myRot;}
  public void setRot(int newb){myRot = newb;}
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
    rotate(myRot);   
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
      myCenterX = 350;
      myCenterY = 350;
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
