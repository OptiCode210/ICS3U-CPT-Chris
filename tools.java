import arc.*;
import java.awt.Color;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.FontMetrics;
import java.awt.Font;

public class tools{
	public static int intWidth;
	public static int intHeight;
	public static int intInput;
	public static void logo(Console con) {
		//loading the logo
        BufferedImage imgLogo = con.loadImage("/Users/chrislau/Documents/CS/CPT/logoFinal.png");
        
        //centering the logo to the middle of the console
        int intWidth = imgLogo.getWidth();
        int intHeight = imgLogo.getHeight();
        
        int intWinHeight = 800;
        int intWinLength = 1000;
        
        int intX = (intWinLength - intWidth) /2;
        int intY = 10;
        
        //draw the image
        con.drawImage(imgLogo,intX,intY); 
        con.repaint();
    }
	
	public static void background(Console con){
		//loading the image
		BufferedImage imgBackground = con.loadImage(
			"/Users/chrislau/Documents/CS/CPT/backgroundfinal.png"
		);
		
		//drawing the background
		con.drawImage(imgBackground,0,0);
		con.repaint();
		
	}
	
	public static void menu3Buttons(Console con){
		int intWidth = 300;
		int intHeight = 60;
		
		//set font
		con.setDrawFont(new Font("Times New Roman", Font.BOLD,35));

	
		//First button: Play Game (1)
		con.setDrawColor(new Color(32,145,94));	//Dark Green
		con.fillRoundRect(345,350, intWidth, intHeight ,50,50);
		con.setDrawColor(Color.BLACK);
		con.drawRoundRect(345, 350, intWidth, intHeight, 50, 50);
		con.setDrawColor(Color.WHITE);
		con.drawString("1 - Play Game", 390, 350);
		
		//Second Button: View Leaderboard (2)
		con.setDrawColor(new Color(31, 46, 130)); // Blue
		con.fillRoundRect(345, 450, intWidth, intHeight, 50, 50);
		con.setDrawColor(Color.BLACK);
		con.drawRoundRect(345, 450, intWidth, intHeight, 50, 50);
		con.setDrawColor(Color.WHITE);
		con.drawString("2 - Leaderboard", 370, 450);
		
		//Third Button: Quit (3)
		con.setDrawColor(new Color(150, 20, 30)); // Red
		con.fillRoundRect(345, 550, intWidth, intHeight, 50, 50);
		con.setDrawColor(Color.BLACK);
		con.drawRoundRect(345, 550, intWidth, intHeight, 50, 50);
		con.setDrawColor(Color.WHITE);
		con.drawString("3 - Quit", 435, 550);
		
		con.repaint();
		
	}
	
	public static void inputButton(Console con){
		int intX = 345;
		int intY = 640;
		int intW = 300;
		int intH = 120; // Increased height

		// Draw input box
		con.setDrawColor(new Color(255, 253, 158)); // Yellow
		con.fillRoundRect(intX, intY, intW, intH, 50, 50);
		con.setDrawColor(Color.BLACK);
		con.drawRoundRect(intX, intY, intW, intH, 50, 50);
		con.setDrawFont(new Font("Times New Roman", Font.BOLD, 30));
		con.setDrawColor(Color.GRAY);
		con.drawString("Enter 1 / 2 / 3:", 403, intY + 8);
		
		//Set the text cursor to the middle of the screen
		con.setTextColor(Color.BLACK);
		while (true) {
			con.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" + "                                         "
				);	
			int intInput = con.readInt();
			
			if (intInput != 1 || intInput != 2 || intInput != 3){
				con.println("                                  Invalid choice.");
				con.sleep(500);
				con.clear();
			}
			else break;
		}
			 
		if (intInput == 1) {
			startGame(con);
		} else if (intInput == 2) {
			showLeaderboard(con);
		} else if (intInput == 3) {
			quitGame(con);
		}
    }
	
	public static void startGame(Console con){
		//cover everything up
		con.setDrawColor(Color.WHITE);
		con.fillRect(0, 0, 1000, 800);
		con.repaint();
		
		//create dimensions for the name input box
		int intBoxX = 350;
		int intBoxY = 300;
		int intBoxW = 300;
		int intBoxH = 100;
		
		con.setDrawColor(new Color(200,255,255)); //Light blue
		con.fillRoundRect(intBoxX, intBoxY, intBoxW, intBoxH, 40,40);
		con.setDrawColor(Color.BLACK);
		
	}
	
	public static void showLeaderboard(Console con){
		
	}
	
	public static void quitGame(Console con){
	
	}
}
