package Game;

import java.util.Random;


/**
 * This class contains the attributes of the computer cars and the related methods.
 *
 */
public class ComputerCars {
	int x,y,dy,width,height,initial_y;

	
	/**
  * This initializes the attributes of the computer cars.
 * @param initial_y 
 * Initial y-coordinate of the computer_cars
 * @param x 
 * x-coordinate of the computer_cars
 * @param y
 * y-coordinate of the computer_cars
 * @param dy
 * speed of the computer_cars
 * @param width
 * width of the computer_car image
 * @param height
 * height of the computer_car image
 */

	public ComputerCars(int initial_y,int x,int y,int dy,int width,int height){
	this.x=x;
	this.y=y;
	this.dy=dy;
	this.width=width;
	this.height=height;
	this.initial_y=initial_y; 
 }
   /**
     * This method decides the speed of the computer_cars. 
     */
    public void decide_speed(){
    	int maximum_speed[]={1,2,2,3,4,5,6,7,8,9,10};
    	int minimum_speed[]={1,1,2,2,3,3,4,5,5,6,6};
    	Random rand=new Random();
    	this.dy=rand.nextInt(maximum_speed[Game.score/20000+1])+minimum_speed[Game.score/20000+1];
    }
    
    /**
     * This method sets the X,Y coordinates of the computer_cars.
     */
    public void setXY(){
    	if(this.y>600){
    		this.y=this.initial_y;
    		this.decide_speed();
    	}
    	else this.y+=this.dy;
    }
}
