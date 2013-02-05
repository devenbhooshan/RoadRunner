package Game;

import java.awt.Color;

import javax.swing.JFrame;

/**
 * This is the entry point of the execution.
 * @author <b>Deven Bhooshan</b>
 */
@SuppressWarnings("serial")
public class Main extends JFrame{
	public Main(){
		setTitle("RoadRunner");
		add(new Game());
		setSize(1000,600);
		setLocation(180,100);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Main();
		
	}

}
