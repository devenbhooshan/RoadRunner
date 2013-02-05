package Game;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


/**
 * In this class my whole logic is written. This class also paint the images of the cars and also the side_panel.
 * @author Deven Bhooshan
 */
@SuppressWarnings("serial")
public class Game extends JPanel implements ActionListener, KeyListener{

	
	ComputerCars computer_cars[]=new ComputerCars[5];
	Image enemy_cars[]=new Image[5];
	Image user_car;
	final int user_image_width=18;
	final int user_image_height=38;
	int x=400,y=485,dx,temp=0;
	boolean in_game=true;
	public static int score=0; // very good use of static
	Timer timer=new Timer(5,this);
	Graphics g;

	public Game(){
	   setBackground(Color.WHITE);
	   ImageIcon user = new ImageIcon(this.getClass().getResource("../images/user.png"));
	   user_car=user.getImage();
       initialise();
	   addKeyListener(this);
       setFocusable(true);
       setFocusTraversalKeysEnabled(true);
       timer.start();
	}
	/**
	 * This method is used to create the ComputerCars class objects and the values
	 * like initial position at y-axis,x-coordinate,y-coordinate,initial speed,width and height of the car,
	 * is passed.
	 */
	public void initialise(){
		in_game=true;
		x=400;
		score=0;
		ImageIcon enemy_cars_icon[]=new ImageIcon[5];
		for(int i=0;i<5;i++)
			enemy_cars_icon[i]=new ImageIcon(this.getClass().getResource("../images/enemy"+(i+1)+".png"));
		for(int i = 0; i < 5; i++) 
			enemy_cars[i]=enemy_cars_icon[i].getImage();	
		computer_cars[0]=new ComputerCars(0,400,0,3,20,38);	//(initial position,x,y,speed,width,height)
		computer_cars[1]=new ComputerCars(-42,400+37,-42,5,20,36);
		computer_cars[2]=new ComputerCars(-82,400+74,-82,2,20,40);
		computer_cars[3]=new ComputerCars(-18,400+111,-18,2,30,63);
		computer_cars[4]=new ComputerCars(-6,400+158,-6,2,38,81);
	}
	
	
	
	public void paint(Graphics g){
		if(in_game){
	                super.paint(g);
	                this.g=g;
	                drawsidepanel(g);
	                g.drawImage(user_car,x,485,null);
	                for(int i=0;i<5;i++)
	                g.drawImage(enemy_cars[i],computer_cars[i].x,computer_cars[i].y,null);
	                
		}
		       else{
                    timer.stop();
                    ImageIcon gameover_icon=new ImageIcon(this.getClass().getResource("../images/game_over.png"));
                    Image gameover=gameover_icon.getImage();
                    g.drawImage(gameover, 420, 300, null);
		           }
	 }
	
	/**
	 * This method is used to draw the side-panel of the screen . The side-panel includes
	 * Score,Time,Level.
	 * 
	 */
	private void drawsidepanel(Graphics g) {
		Font small = new Font("Helvetica", Font.CENTER_BASELINE, 25);
        g.setFont(small);
        g.drawString("Level: "+(score/20000+1),100,260);
		g.drawString("Your Score:", 100, 300);
		ImageIcon road_image=new ImageIcon(this.getClass().getResource("../images/road.png"));
		Image road=road_image.getImage();
		g.drawImage(road, 390, 0, null);
		g.drawString(Integer.toString(score/100), 100, 340);
		g.drawString("Your Time:", 100, 380);
		g.drawString(Integer.toString(score/1000 )+" sec", 100, 420);
		score=score+10;
	}


	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		for(int i=0;i<5;i++)
			computer_cars[i].setXY();
		collision();
		repaint();
	}
	
	
	
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		int c=arg0.getKeyCode();
		
		if(c==KeyEvent.VK_LEFT)
		dx=-10;
		
		else if(c==KeyEvent.VK_RIGHT)
		dx=10;
		
		else if(c==KeyEvent.VK_SPACE)
			{
			if(temp==0){
			temp=1;
	        timer.stop();
			}
			else{
				timer.restart(); temp=0;
			}
			}
		else if(c==KeyEvent.VK_R){
			initialise();
			timer.restart();
		}
		else if(c==KeyEvent.VK_ESCAPE){
			System.exit(-1);
		}
		x+=dx;
		checkbounds(x);
		
	}
	
	
	
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		dx=0;		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
	
	
	
	/** 
	 * This method checks the bounds of the user's x coordinate.
	 * User is not allowed to move its car out of the screen using this method. 
	 * 
	 */
	public void checkbounds(int x){
		if(x+user_image_width>600){
			this.x=600-user_image_width;
			
		}
		if(x<400){
			this.x=400;
		}
		
	}

	/**
	 * This method checks the collision of the cars.
	 * This method creates the objects of the Rectangle class and creates the rectangle using the 
	 * attributes of the user car and the computer cars and checks whether the user car collided with
	 * the computer cars or not.  
	 * 
	 */
	public void collision(){
        Rectangle user=new Rectangle(x,y,user_image_width,user_image_height);
		for(int i=0;i<5;i++){
		Rectangle enemy=new Rectangle(computer_cars[i].x,computer_cars[i].y,computer_cars[i].width,computer_cars[i].height);
		if(user.intersects(enemy)){
			in_game=false;
			break;
		}
		}
	}
	
	
	
	
}
	





