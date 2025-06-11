import arc.*;
import java.awt.Color;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.FontMetrics;
import java.awt.Font;

public class game{
	//global variables
	public static String strPlayerName = "";
	public static BufferedImage[] imgCards = new BufferedImage[52];
	//create decks
	public static int[][] intDeck = new int[52][3];
	public static int[][] intShuffled = new int[52][3];	
	
	
	//main program:
	public static void main(String args[]){
		Console con = new Console(1000,800);
		
		//calls the main menu method
		game.game(con);
		
	}
	
	//mouse click program
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
	

	//secondary programs:
	public static void game(Console con){
		background(con);
		logo(con);
		loadCards(con);
		menu3Buttons(con);
		//wordInput(con);
	}
	
	public static void logo(Console con) {
		//loading the logo
        BufferedImage imgLogo = con.loadImage("/Users/chrislau/Documents/GitHub/ICS3U-CPT-Chris/media/logoFinal.png");
        
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
			"/Users/chrislau/Documents/GitHub/ICS3U-CPT-Chris/media/bg3.jpg"
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
		
		con.repaint();
		
		while (con.currentMouseButton() == 0) {
				con.sleep(10);
			}
		
		while(true){
			
			con.repaint();
			
			if(isClicked(con, 345, 350, intWidth, intHeight)){
				startGame(con);
				break;
			} else if(isClicked(con, 345, 450, intWidth, intHeight)){
				showLeaderboard(con);
				break;
			} else if(isClicked(con, 345, 550, intWidth, intHeight)){
				quitGame(con);
				break;
			}
			con.sleep(2);
		}
		
	}
	
	public static void wordInput(Console con){
		while(true){
			char chrInput = con.getChar();  //captures typed character
			
			//directs input to corresponding methods
			if(chrInput == '1'){
				startGame(con);
				break;
			}else if(chrInput == '2'){
				showLeaderboard(con);
				break;
			}else if(chrInput == '3'){
				quitGame(con);
				break;
			}
			
			//pause program for a bit between loop
			con.sleep(50);
		}
	}
	
	public static void startGame(Console con){
		//clear the screen of any text
		con.clear();
		
		//cover everything up
		BufferedImage imgBackground = con.loadImage("/Users/chrislau/Documents/GitHub/ICS3U-CPT-Chris/media/pokerbg.jpeg");
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
		con.drawString(" Enter name: ", intBoxX+45, intBoxY +10 );
		con.repaint();
		
		//Make "Welcome To BlackJack"
		con.setDrawColor(Color.WHITE);
		con.setDrawFont(new Font("Times New Roman", Font.BOLD, 60));
		con.drawString("WELCOME TO BLACKJACK", 80, 70);
	
		//Make the input area in the created box
		for (int i = 0; i < 16; i++) {
			con.println();
		}
		String strPadding = "                                      ";
		con.print(strPadding);
		String strPlayerName = con.readLine();
		con.repaint();
		
		System.out.println("name: " + strPlayerName);
		
		//run the main logic method
		if(strPlayerName != null){
			blackjack(con);
		}
	}
	
	public static void quitGame(Console con){
		
		//cover up everything and clear
		con.clear();
		con.setDrawColor(Color.BLACK);
		con.fillRect(0, 0, 1000, 800);
		
		//Draw background image
		BufferedImage imgBg = con.loadImage("/Users/chrislau/Documents/GitHub/ICS3U-CPT-Chris/media/backgroundmeme.jpeg");
		con.drawImage(imgBg,0,0);
		
		//Set goodbye text
		con.setDrawFont(new Font("Times New Roman", Font.BOLD, 50));
		con.setDrawColor(Color.WHITE);
		con.drawString("THANKS FOR PLAYING", 225, 200);
		
		con.repaint();
		
		//make sure user reads the message before closing window
		con.sleep(2000);
		con.closeWindow();
		
		//closes the whole program
		System.exit(0);
	}

	public static void showLeaderboard(Console con){
		//drawing background image
		con.clear();
		BufferedImage imgBackground = con.loadImage(
			"/Users/chrislau/Documents/GitHub/ICS3U-CPT-Chris/media/leaderboard.png"
		);
		con.drawImage(imgBackground,0,0);
		con.repaint();
		
		//create the table titles
		con.setDrawColor(new Color(227, 208, 64));
		con.setDrawFont(new Font("Times New Roman", Font.BOLD, 50));
		con.drawString("Name", 275, 45);
		con.drawString("Amount", 615, 45);
		
		//load the leaderboard file
		TextInputFile txtLB = new TextInputFile("leaderboard.txt");
		
		//create arrays to store values
		String[] names = new String[100];
		int[] money = new int[100];
		int intCount = 0;
		
		while(!txtLB.eof()){
			String strLine = txtLB.readLine();
			if (strLine == null || strLine.trim().equals("")) continue;
			
			//split the line
			String[] parts = strLine.split("-");
			
			//assign the splitted values to their own array
			String strName = parts[0].trim();
			int intMoney = Integer.parseInt(parts[1].trim());
			
			//saves value into array
			names[intCount] = strName;
			money[intCount] = intMoney;
			intCount++;
			
		}
		txtLB.close();
		
		//bubble sort the 2 arrays
		for(int i = 0; i<intCount; i++){
			for(int j = i + 1; j < intCount; j++){
				if(money[j] > money[i]){
					//switch values of money array
					int intTMoney = money[i];
					money[i] = money[j];
					money[j] = intTMoney;
					
					//switch values of names array
					String strTName = names[i];
					names[i] = names[j];
					names[j] = strTName;
					
				}
			}
		}
		
		
		//test if the sorting worked
		System.out.println("test is below: ");
		System.out.println("count = " + intCount);
		for(int x = 0; x < intCount; x++){
			System.out.println(money[x] + " " + names[x]);
		}
		
		
		//print the top 5
		con.setDrawColor(Color.WHITE);
		for(int i = 0; i < 8; i++){
			int DY = 170 + (i*70); //increments of 70 pixels
			if (intCount > i) {
				con.drawString(names[i], 280, DY);
				con.drawString("$ " + money[i], 620, DY);
				con.repaint();
			}
		}
		
		con.repaint();
		
		//create back button
		con.setDrawColor(Color.RED);
		con.fillRoundRect(820, 720, 150, 50, 20, 20);
		con.setDrawColor(Color.BLACK);
		con.drawRoundRect(820, 720, 150, 50, 20, 20);
		con.setDrawFont(new Font("Times New Roman", Font.BOLD, 24));
		con.setDrawColor(Color.WHITE);
		con.drawString("e - Back", 855, 725);
		con.repaint();
		
		while(true){
			char chrInput = con.getChar();  //captures typed character
			
			//directs input to corresponding methods
			if(chrInput == 'e'){
				game(con);
				break;
			}
			
			//pause program for a bit between loop
			con.sleep(50);
		}
	}
	
	public static void deckArray(Console con){
		
		//fill deck values
		int intIndex = 0;
		for(int intSuit = 1; intSuit <= 4; intSuit++){
			for(int intValue = 1; intValue <= 13; intValue++){
			
				//column 0: card value
				intDeck[intIndex][0] = intValue;
				//column 1: suits
				intDeck[intIndex][1] = intSuit;
				//column 2: randint
				intDeck[intIndex][2] = (int)(Math.random()*100) +1;
				intIndex ++;
				
			}
		}
		
		/*
		//array test
		for(int intI = 0; intI < 52; intI++){
			int intVal   = intDeck[intI][0];
			int intSuitV = intDeck[intI][1];
			int intKey   = intDeck[intI][2];
			System.out.println(
				" value: " + intVal +
				", suit: "  + intSuitV +
				", key: "   + intKey
			);
		}
		*/
		
		//fill shuffled deck values
		for(int i = 0; i < 52; i ++){
			intShuffled[i][0] = intDeck[i][0];
			intShuffled[i][1] = intDeck[i][1];
			intShuffled[i][2] = intDeck[i][2];
			
		}
		
		//bubble sort array to create random cards
		for(int i = 0;  i < 52; i++){
			for(int j = 0; j < 51 - i; j++){
				if(intShuffled[j][2] > intShuffled[j + 1][2]){
					int[] temp = intShuffled[j];
					intShuffled[j] = intShuffled[j + 1];
					intShuffled[j+1] = temp;
 				}
			}
		}
		
		/*
		//test if the array is sorted
		System.out.println();
		System.out.println("Shuffled deck: ");
		for(int i=0; i<52; i++){
			int val = intShuffled[i][0];
			int suit = intShuffled[i][1];
			int order = intShuffled[i][2];
			System.out.println("Value: " + val + " Suit: " + suit + " Order: " + order);
		}
		*/
		
	}
	
	public static void loadCards(Console con){
		String folderPath = "/Users/chrislau/Documents/GitHub/ICS3U-CPT-Chris/media/cards";
		String filePath;
		//for loop to load cards
		for(int i = 0; i < 52; i++){
			filePath = folderPath + "/c" + i + ".png";
			imgCards[i] = con.loadImage(filePath);
		}
		
		//try out printing cards
		//con.drawImage(imgCards[22],100,100);
	}
	
	public static void bjBetInterface(Console con){
		//draw background image
		con.clear();
		BufferedImage imgBackground = con.loadImage(
			"/Users/chrislau/Documents/GitHub/ICS3U-CPT-Chris/media/pokertable.jpeg"
		);
		con.drawImage(imgBackground,0,0);
		
		//draw strings
		con.setDrawColor(new Color(255,215,0));
		con.setDrawFont(new Font("Times New Roman", Font.BOLD, 30));
		con.drawString("BLACKJACK", 410, 40);
		con.repaint();
		
		con.setDrawColor(new Color(255,255,255));
		con.setDrawFont(new Font("Times New Roman", Font.BOLD, 30));
		con.drawString("Your Amount: ", 70, 100);
		con.repaint();
		
		con.setDrawColor(new Color(255,255,255));
		con.setDrawFont(new Font("Times New Roman", Font.BOLD, 30));
		con.drawString("Your Bet: ",70, 150);
		con.repaint();
		
		con.setDrawColor(new Color(255,255,255));
		con.setDrawFont(new Font("Times New Roman", Font.BOLD, 30));
		con.drawString("Dealer's Hand", 120, 220);
		con.repaint();
		
		con.setDrawColor(new Color(255,255,255));
		con.setDrawFont(new Font("Times New Roman", Font.BOLD, 30));
		con.drawString("Your Hand", 650, 220);
		con.repaint();
		
		//draw image of card deck
		BufferedImage imgDeck = con.loadImage("/Users/chrislau/Documents/GitHub/ICS3U-CPT-Chris/media/backofcard.png");
		con.drawImage(imgDeck, 170,600);
		con.repaint();

		// Bet 10% button
		con.setDrawColor(new Color(71,237,129));
		con.fillRoundRect(600, 620, 150, 50, 20, 20);
		con.setDrawColor(Color.BLACK);
		con.drawString("Bet 10%", 620, 620);
		con.repaint();

		// Bet 30% button
		con.setDrawColor(new Color(71,237,129));
		con.fillRoundRect(600, 690, 150, 50, 20, 20);
		con.setDrawColor(Color.BLACK);
		con.drawString("Bet 30%", 620, 690);
		con.repaint();

		//Bet 50%
		con.setDrawColor(new Color(71,237,129));
		con.fillRoundRect(780, 620, 150, 50, 20, 20);
		con.setDrawColor(Color.BLACK);
		con.drawString("Bet 50%", 790, 620);
		con.repaint();
		
		//Bet 80%
		con.setDrawColor(new Color(71,237,129));
		con.fillRoundRect(780, 690, 150, 50, 20, 20);
		con.setDrawColor(Color.BLACK);
		con.drawString("Bet 80%", 790, 690);
		con.repaint();
	}
	
	public static void bjBetRedInterface(Console con){
		// Bet 10% button
		con.setDrawColor(Color.RED);
		con.fillRoundRect(600, 620, 150, 50, 20, 20);
		con.setDrawColor(Color.BLACK);
		con.drawString("Bet 10%", 620, 620);
		con.repaint();

		// Bet 30% button
		con.setDrawColor(Color.RED);
		con.fillRoundRect(600, 690, 150, 50, 20, 20);
		con.setDrawColor(Color.BLACK);
		con.drawString("Bet 30%", 620, 690);
		con.repaint();

		//Bet 50%
		con.setDrawColor(Color.RED);
		con.fillRoundRect(780, 620, 150, 50, 20, 20);
		con.setDrawColor(Color.BLACK);
		con.drawString("Bet 50%", 790, 620);
		con.repaint();
		
		//Bet 80%
		con.setDrawColor(Color.RED);
		con.fillRoundRect(780, 690, 150, 50, 20, 20);
		con.setDrawColor(Color.BLACK);
		con.drawString("Bet 80%", 790, 690);
		con.repaint();
	}
	
	public static void blackjack(Console con){
		//create array for both the player and the dealer's hand
		int[][] arrPlayer = new int[5][3];
		int[][] arrDealer = new int[5][3];
		
		//set balance
		int intStartBalance = 5000;
		int intGameBalance = intStartBalance;
	
		//set boolean value to see if player wants to play again
		boolean booQuit = false;
		
		//load bet interface
		bjBetInterface(con);
		
		//booQuit == false && intGameBalance > 0
		while(true){ //change it when real game
			//print amount
			con.setDrawColor(Color.WHITE);
			con.drawString(""+ intGameBalance, 270, 100);
			con.repaint();
			
			con.clear();
			con.sleep(30); //free up system resources
			deckArray(con);
			
			//reset deal counts
			int intCIndex = 0;
			int intPCardCount = 0; //player card count
			int intDCardCount = 0; //dealer card count
			int intBet = 0;
			
			//record bet
			while(intBet == 0){
				if(isClicked(con, 600, 620, 150, 50)){ //10%
					intBet = Math.max(1, (int)(intGameBalance * 0.10));
					System.out.println("10% button clicked: " + intBet);
				}else if(isClicked(con, 600, 690, 150, 50)){  //30%
					intBet = Math.max(1, (int)(intGameBalance * 0.30));
					System.out.println("30% button clicked: " + intBet);
				}else if (isClicked(con, 780, 620, 150, 50)) { //50%
					intBet = Math.max(1, (int)(intGameBalance * 0.50));
					System.out.println("50% button clicked: " + intBet);
				}else if (isClicked(con, 780, 690, 150, 50)) {  //80%
					intBet = Math.max(1, (int)(intGameBalance * 0.80));
					System.out.println("80% button clicked: " + intBet);
				}
				con.sleep(20);
			}
			
			//print amounts
			intGameBalance -= intBet; //deduct bet from amount
			bjBetInterface(con);
			con.setDrawColor(Color.WHITE);
			con.drawString(""+ intGameBalance, 270, 100);
			con.drawString("" + intBet, 205, 150);
		    con.repaint();
			bjBetRedInterface(con); //buttons become red after you click it
			
			break;
			
		}
	}
	
}
