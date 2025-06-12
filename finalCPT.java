import arc.*;
import java.awt.Color;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.FontMetrics;
import java.awt.Font;

public class finalCPT{
	public static String strPlayerName = "";
	public static BufferedImage[] imgCards = new BufferedImage[52];
	public static BufferedImage imgDeck;
	public static int[][] intDeck = new int[52][3];
	public static int[][] intShuffled = new int[52][3];
	
	public static void main(String args[]){
		Console con = new Console(1000,800);
		
		mainMenu(con);
		
	}
	
	public static void mainMenu(Console con){
		//bg
			BufferedImage imgBackground = con.loadImage(
				"/Users/chrislau/Documents/GitHub/ICS3U-CPT-Chris/media/bg3.jpg"
			);
			
			//drawing the background
			con.drawImage(imgBackground,0,0);
			con.repaint();
			
		//logo
			BufferedImage imgLogo = con.loadImage("/Users/chrislau/Documents/GitHub/ICS3U-CPT-Chris/media/logoFinal.png");
			
			//centering the logo to the middle of the console
			int intCWidth = imgLogo.getWidth();
			int intCHeight = imgLogo.getHeight();
			
			int intWinHeight = 800;
			int intWinLength = 1000;
			
			int intX = (intWinLength - intCWidth) /2;
			int intY = 10;
			
			//draw the image
			con.drawImage(imgLogo,intX,intY); 
			con.repaint();
			
		
			
		//3 buttons
			int intWidth = 300;
			int intHeight = 60;
			
			//set font
			con.setDrawFont(new Font("Times New Roman", Font.BOLD,35));

		
			//First button: Play Game (1)
			con.setDrawColor(new Color(32,145,94));	//Dark Green
			con.fillRoundRect(345,350, intWidth, intHeight ,50,50);
			con.setDrawColor(Color.WHITE);
			con.drawRoundRect(345, 350, intWidth, intHeight, 50, 50);
			con.setDrawColor(Color.WHITE);
			con.drawString("   Play Game", 390, 350);
			
			//Second Button: View Leaderboard (2)
			con.setDrawColor(new Color(31, 46, 130)); // Blue
			con.fillRoundRect(345, 450, intWidth, intHeight, 50, 50);
			con.setDrawColor(Color.WHITE);
			con.drawRoundRect(345, 450, intWidth, intHeight, 50, 50);
			con.setDrawColor(Color.WHITE);
			con.drawString("   Leaderboard", 370, 450);
			
			//Third Button: Quit (3) 
			con.setDrawColor(new Color(150, 20, 30)); // Red
			con.fillRoundRect(345, 550, intWidth, intHeight, 50, 50);
			con.setDrawColor(Color.WHITE);
			con.drawRoundRect(345, 550, intWidth, intHeight, 50, 50);
			con.setDrawColor(Color.WHITE);
			con.drawString("   Quit", 435, 550);
			
			//make secret button image
			con.setDrawColor(new Color(220, 126, 235)); // Red
			con.fillRoundRect(345, 650, intWidth, intHeight, 50, 50);
			con.setDrawColor(Color.WHITE);
			con.drawRoundRect(345, 650, intWidth, intHeight, 50, 50);
			con.setDrawColor(Color.WHITE);
			con.drawString("   Help", 435, 650);
			con.repaint();
			
			while (con.currentMouseButton() == 0) {
					con.sleep(10);
				}
			
			while(true){
				con.repaint();
				if(isClicked(con, 345, 350, intWidth, intHeight)){
					//startGame(con);
					break;
				} else if(isClicked(con, 345, 450, intWidth, intHeight)){
					//showLeaderboard(con);
					break;
				} else if(isClicked(con, 345, 550, intWidth, intHeight)){
					//quitGame(con);
					break;
				} else if(isClicked(con, 345, 650, intWidth, intHeight)){
					//helpMenu(con);
					break;
				}
				con.sleep(20);
			}
		
		//help button
				BufferedImage imgBackground = con.loadImage("/Users/chrislau/Documents/GitHub/ICS3U-CPT-Chris/media/helpBGfinal.jpeg"
			);
			con.drawImage(imgBackground,0,0);
			con.repaint();
			
			//title
			con.setDrawColor(new Color(255,255,255));
			con.setDrawFont(new Font("Times New Roman", Font.BOLD, 40));
			con.drawString("How to play this game", 450, 70);
			
			//array for instructions
			String [] arrGameplay ={
					"- Place your bet using the on-screen",
					"  controls before each deal begins.",
					"- Click \"Hit\" to draw a new card or",
					"  \"Stand\" to end your turn safely.",
					"- If your cards total over 21, you",
					"  bust and lose your wager immediately.",
					"- After you stand, dealer reveals",
					"  cards and draws until 17 or higher.",
					"- Aces count as 1 or 11, whichever."
			};
			
			int intY = 150;
			for(int i = 0; i< arrGameplay.length; i++){
				con.setDrawColor(Color.WHITE);
				con.setDrawFont(new Font("Times New Roman", Font.BOLD, 30));
				con.drawString(arrGameplay[i], 450, intY);
				intY += 60;
			
			}
			
			con.setDrawColor(new Color(212, 175, 55));
			con.setDrawFont(new Font("Times New Roman", Font.BOLD, 20));
			con.drawString("For more help email:", 450, 700);
			con.drawString("210chrislau@gmail.com", 450, 730);
			
			con.repaint();
	}
	
	public static boolean isClicked(Console con, int x, int y, int w, int h){
		 if (con.currentMouseButton() == 1) {
			int mx = con.currentMouseX();
			int my = con.currentMouseY();
			// check bounds
			if (mx >= x && mx < x + w && my >= y && my < y + h) {
				// debounce: wait until they lift the button
				while (con.currentMouseButton() == 1) {
					con.sleep(10);
				}
				return true;
			}
		}
		return false;
	}
}
