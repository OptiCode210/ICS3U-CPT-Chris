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
		//load the image
		BufferedImage imgBackground = con.loadImage(
			"/Users/chrislau/Documents/CS/CPT/bg3.jpg"
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
		con.setDrawColor(new Color(255, 255, 255)); // White
		con.fillRoundRect(intX, intY, intW, intH, 50, 50);
		con.setDrawColor(Color.BLACK);
		con.drawRoundRect(intX, intY, intW, intH, 50, 50);
		con.setDrawFont(new Font("Times New Roman", Font.BOLD, 30));
		con.setDrawColor(Color.GRAY);
		con.drawString("Enter 1 / 2 / 3:", 403, intY + 8);
		con.setTextColor(Color.BLACK);
		int intInput = 0;
		
		//While loop to cycle when inputting error values
		while (true) {
				con.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" + "                                         "
				);	
				intInput = con.readInt();

				if (intInput == 1 || intInput == 2 || intInput == 3) {
					break;
				} else {
					con.println("Invalid input. Try again.");
					con.sleep(1000);
				}
			}

			// Call the corresponding method
			if (intInput == 1) {
				startGame(con);
			} else if (intInput == 2) {
				showLeaderboard(con);
			} else if (intInput == 3) {
				quitGame(con);
			}
    }
	
	public static void startGame(Console con){
		//clear the screen of any text
		con.clear();
		
		//cover everything up
		BufferedImage imgBackground = con.loadImage("/Users/chrislau/Documents/CS/CPT/pokerbg.jpeg");
		con.drawImage(imgBackground,0,0);
		con.repaint();
		
		//Set box coordinates and dimensions
		int intBoxX = 350;
		int intBoxY = 300;
		int intBoxW = 300;
		int intBoxH = 150;
		
		//create the input box
		con.setDrawColor(new Color(77, 219, 115)); // Light green
		con.fillRoundRect(intBoxX, intBoxY, intBoxW, intBoxH, 40, 40);
		con.setDrawColor(Color.BLACK);
		con.drawRoundRect(intBoxX, intBoxY, intBoxW, intBoxH, 40, 40);
		
		con.setDrawFont(new Font("Times New Roman", Font.BOLD, 40));
		con.setDrawColor(Color.BLACK);
		con.drawString("Enter name: ", intBoxX+45, intBoxY +10 );
		con.repaint();
	
		//Make the input area in the created box
		for (int i = 0; i < 16; i++) {
			con.println();
		}
		String strPadding = "                                      ";
		con.print(strPadding);
		String strName = con.readLine();
		
		//run the main logic method
		tools.blackjack(con);
	}
	
	public static void blackjack(Console con){
	
	}
	
	public static void showLeaderboard(Console con){
		
	}
	
	public static void quitGame(Console con){
	
		//make a goodbye message
		con.setDrawColor(Color.BLACK);
		con.fillRect(0, 0, 1000, 800);
		
		con.setDrawFont(new Font("Times New Roman", Font.BOLD, 50));
		con.setDrawColor(Color.WHITE);
		con.drawString("THANKS FOR PLAYING", 200, 350);
		
		con.repaint();
		
		//make sure user reads the message before closing window
		con.sleep(2000);
		con.closeWindow();
		
		//closes the whole program
		System.exit(0);
	}
}
