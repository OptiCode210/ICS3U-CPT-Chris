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
	public static BufferedImage imgDeck;
	public static int intPlayerMoney = 5000;
	public static int intBet = 0;
	//create decks
	public static int[][] intDeck = new int[52][3];
	public static int[][] intShuffled = new int[52][3];	
	//hands
	public static int[][] intPlayer = new int[5][2];
	public static int[][] intDealer = new int[5][2];

	public static void main(String args[]){		//main program:
		Console con = new Console(1000,800);
		
		//calls the main menu method
		//game.game(con);
		blackjack(con);
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
	
	public static void game(Console con){
		background(con);
		logo(con);
		loadCards(con);
		menu3Buttons(con);
		//wordInput(con);
	}
	
	public static void helpMenu(Console con){
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
				startGame(con);
				break;
			} else if(isClicked(con, 345, 450, intWidth, intHeight)){
				showLeaderboard(con);
				break;
			} else if(isClicked(con, 345, 550, intWidth, intHeight)){
				quitGame(con);
				break;
			} else if(isClicked(con, 345, 650, intWidth, intHeight)){
				helpMenu(con);
				break;
			}
			con.sleep(20);
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
		//con.drawImage(imgCards[50],100,100);
	}

	public static void bjBetInterface(Console con){
		//background
			con.clear();
			BufferedImage imgBackground = con.loadImage(
			"/Users/chrislau/Documents/GitHub/ICS3U-CPT-Chris/media/pokertable.jpeg"
			);
			con.drawImage(imgBackground,0,0);
			con.repaint();
			
		//wordings
			con.setDrawColor(new Color(227, 208, 64));
			con.setDrawFont(new Font("Times New Roman", Font.BOLD, 30));
			con.drawString("Amount: ", 50, 50);
			con.drawString("Bet: ", 50, 100);
			con.drawString("Dealer's hand: ", 200, 170);
			con.drawString("Player's hand: ", 200, 450);
		
		//buttons
			String[] arrButtonName = {
			"    Hit", "    Stand","Double Down", "Bet 10%", "Bet 30%", "Bet 60%", "  All In"
			};
			int intX = 650;    // x-position of all buttons
			int intY = 100;    // starting y-position
			int intW = 200;    // button width
			int intH = 50;     // button height
			int intArc = 20;     // corner radius
			int intSpace = 100;     // vertical gap between buttons
			
			Color colButtonGreen = new Color(71, 237, 129);
			
			con.setDrawColor(colButtonGreen);
			for (int intI = 0; intI < arrButtonName.length; intI++) {
				int y = intY + intI * intSpace;
				con.fillRoundRect(intX, y, intW, intH, intArc, intArc);
			}

			con.setDrawColor(Color.BLACK);
			for (int intI = 0; intI < arrButtonName.length; intI++) {
				int y = intY + intI * intSpace;
				// center text at about half the button height (50/2 = 25), tweak +5 for baseline  
				int yText = y + 5;
				con.drawString(arrButtonName[intI], intX + 10, yText);
			}

			con.repaint();
	}
	
	public static void bjRedButtons(Console con,String strbutton, int x, int y, int w, int h){
		con.setDrawColor(Color.RED);
		con.fillRoundRect(x, y, 200, 50, 20, 20);
		con.setDrawColor(Color.BLACK);
		con.drawRoundRect(x, y, 200, 50, 20, 20);
		con.setDrawFont(new Font("Times New Roman", Font.BOLD, 24));
		con.drawString(strbutton, x, y);
		con.repaint();
	}
	
	public static void dealInitialCards(int[][] intShuffled, int[][] intPlayer, int[][] intDealer) {
		//use method 
			//dealInitialCards(intShuffled, intPlayer, intDealer);
		// Player cards
		intPlayer[0][0] = intShuffled[0][0];
		intPlayer[0][1] = intShuffled[0][1];

		intPlayer[1][0] = intShuffled[2][0];
		intPlayer[1][1] = intShuffled[2][1];

		// Dealer cards
		intDealer[0][0] = intShuffled[1][0];
		intDealer[0][1] = intShuffled[1][1];

		intDealer[1][0] = intShuffled[3][0];
		intDealer[1][1] = intShuffled[3][1];

		// Debug prints
		System.out.println("PLAYER CARDS:");
		for (int i = 0; i < 2; i++) {
			System.out.println("Card " + (i+1) + ": Value = " + intPlayer[i][0] + ", Suit = " + intPlayer[i][1]);
		}

		System.out.println("DEALER CARDS:");
		for (int i = 0; i < 2; i++) {
			System.out.println("Card " + (i+1) + ": Value = " + intDealer[i][0] + ", Suit = " + intDealer[i][1]);
		}
	}

	public static int calculateHandValue(int[][] hand ){	
		//call
			//int intPlayerTotal = calculateHandValue(intPlayer);
			//int intDealerTotal = calculateHandValue(intDealer);
			
		int intTotal = 0;
		int intAces = 0; //aces special rule
		
		for (int i = 0; i < hand.length; i++) {		//loop through all cards
			int intVal = hand[i][0]; 	//first column of table is the value
			
			if(intVal == 0){	//empty slot when value is 0
				break;
			}
			if(intVal > 10){	//JQK worth 10
				intVal = 10;
			}
			if(intVal == 1){	//aces are treated as 11s for now and are tracked
				intVal = 11;
				intAces ++;
			}
		
			intTotal += intVal;
		}
		
		//if total is over 21 + hv aces -> downgrade from 11 to 1
		while(intTotal > 21 && intAces > 0){	
			intTotal -= 10;
			intAces --;
		}
		
		return intTotal;
	}
	
	public static int dealerPlay(int[][] intShuffled, int[][] intDealer, int intDeckIndex){		//keep hitting until 17
		
		while(calculateHandValue(intDealer) < 17){	
			boolean drewCard = false;
			for(int i =0; i < intDealer.length; i++){
				if(intDealer[i][0] == 0){ //find first empty card slot
					intDealer[i][0] = intShuffled[intDeckIndex][0];
					intDealer[i][1] = intShuffled[intDeckIndex][1];
					intDeckIndex++;
					drewCard = true;
					break;
				}
			}
			if (!drewCard) {
				System.out.println("Dealer has 5 cards already — cannot hit.");
				break; // avoid infinite loop
			}
		}
		return intDeckIndex;
		
	}
	
	public static String determineWinner(int[][] intPlayer, int[][] intDealer){
		int intPlayerTotal = calculateHandValue(intPlayer);
		int intDealerTotal = calculateHandValue(intDealer);
		
		if(intPlayerTotal > 21) return "Player Loses";
		if(intDealerTotal > 21) return "Player Wins";
		
		//compare totals
		if(intPlayerTotal > intDealerTotal) return "Player Wins";
		if(intPlayerTotal < intDealerTotal) return "Player Loses";
		
		return "Tie";
	}
	
	public static int playerPlay(int[][] intShuffled, int[][] intPlayer, int intDeckIndex, String strAction){
		//use
			// When hit button is clicked:
			//intDeckIndex = playerTurnLogic(intShuffled, intPlayer, intDeckIndex, "hit");

			// When stand button is clicked:
			//playerTurnLogic(intShuffled, intPlayer, intDeckIndex, "stand");

	
		int intPlayerCardIndex = 0;
		
		//find first empty card slot
		 for (int i = 0; i < intPlayer.length; i++) {
			if (intPlayer[i][0] == 0) {
				intPlayerCardIndex = i;
				break;
			}
		}
		
		//hit logic
		if(strAction.equalsIgnoreCase("hit")){
			if(intPlayerCardIndex < 5){
				intPlayer[intPlayerCardIndex][0] = intShuffled[intDeckIndex][0]; // value
				intPlayer[intPlayerCardIndex][1] = intShuffled[intDeckIndex][1]; // suit
				intDeckIndex++;

				// Check for bust
				if (calculateHandValue(intPlayer) > 21) {
					System.out.println("Player busted!");
				}
			}else{
				System.out.println("Player already has 5 cards.");
			}
		}
		
		return intDeckIndex;
	}

	public static boolean isFiveCardSpecialRule(int[][] hand){
		return calculateHandValue(hand) <= 21 && countCards(hand) == 5;
	}
	
	public static boolean isBlackjack(int[][] hand) {
		return calculateHandValue(hand) == 21 && countCards(hand) == 2;
	}

	public static boolean isDoubleDownEligible(int[][] hand) {
		int total = calculateHandValue(hand);
		return (countCards(hand) == 2 && (total == 9 || total == 10 || total == 11));
	}

	public static int calculateWinnings(int[][] intPlayer, int[][] intDealer, int intBet){
		
		int intPlayerTotal = calculateHandValue(intPlayer);
		int intDealerTotal = calculateHandValue(intDealer);
		
		//blackjack win
		if(isBlackjack(intPlayer)){
			System.out.println("Blackjack! 3x payout");
			return intBet *3;
		}
		
		//5 card special rule
		if(isFiveCardSpecialRule(intPlayer)){
			System.out.println("You win via 5 card special rule!");
			return intBet *3;
		}
		
		//bust
		if(intPlayerTotal > 21){
			System.out.println("Busted, bet lost");
			return 0;
		}
		
		//dealer bust
		if (intDealerTotal > 21) {
			System.out.println("Dealer busted! You win 2x your bet.");
			return intBet * 2;
		}
		
		//player has higher total
		if (intPlayerTotal > intDealerTotal) {
			System.out.println("You beat the dealer! You win 2x your bet.");
			return intBet * 2;
		}

		// tie
		if (intPlayerTotal == intDealerTotal) {
			System.out.println("Tie! You get your bet back.");
			return intBet;
		}

		//dealer has higher total
		System.out.println("Dealer wins. You lose your bet.");
		return 0;
	}
	
	public static int countCards(int[][] hand) {
		int count = 0;

		// Loop through the hand and count cards with a non-zero value
		for (int i = 0; i < hand.length; i++) {
			if (hand[i][0] != 0) {
				count++;
			}
		}

		return count;
	}

	public static void blackjack(Console con) {
		// Setup deck and hands
		loadCards(con);
		deckArray(con);
		
		intPlayerMoney = 5000; // starting money
		intBet = 0;
		
		bjBetInterface(con);
		bets(con);
		
		dealInitialCards(intShuffled, intPlayer, intDealer);
		drawInitialDeal(con, intPlayer, intDealer, imgCards);
		if(checkInitialBJ(con)) return;
	}
	
	public static void bets(Console con){
		while(intBet == 0){
			if(isClicked(con, 650, 400, 150, 50)){ //10%
				intBet = Math.max(1, (int)(intPlayerMoney * 0.10));
				System.out.println("10% button clicked: " + intBet);
				bjRedButtons(con,"  Bet 10%" ,650,400,150,50);
				bjRedButtons(con,"  Bet 30%" ,650,500,150,50);
				bjRedButtons(con,"  Bet 60%" ,650,600,150,50);
				bjRedButtons(con,"  All In" ,650,700,150,50);
			}else if(isClicked(con, 650, 500, 150, 50)){  //30%
				intBet = Math.max(1, (int)(intPlayerMoney * 0.30));
				System.out.println("30% button clicked: " + intBet);
				bjRedButtons(con,"  Bet 10%" ,650,400,150,50);
				bjRedButtons(con,"  Bet 30%" ,650,500,150,50);
				bjRedButtons(con,"  Bet 60%" ,650,600,150,50);
				bjRedButtons(con,"  All In" ,650,700,150,50);
			}else if (isClicked(con, 650, 600, 150, 50)) { //50%
				intBet = Math.max(1, (int)(intPlayerMoney * 0.60));
				System.out.println("60% button clicked: " + intBet);
				bjRedButtons(con,"  Bet 10%" ,650,400,150,50);
				bjRedButtons(con,"  Bet 30%" ,650,500,150,50);
				bjRedButtons(con,"  Bet 60%" ,650,600,150,50);
				bjRedButtons(con,"  All In" ,650,700,150,50);
				bjRedButtons(con,"  Bet 30%" ,650,500,150,50);
				bjRedButtons(con,"  Bet 60%" ,650,600,150,50);
				bjRedButtons(con,"  All In" ,650,700,150,50);
			}
			
			con.sleep(20);
		}
		
		con.setDrawFont(new Font("Times New Roman", Font.BOLD, 30));
		con.setDrawColor(Color.WHITE);
		con.drawString(""+intBet, 120, 100);
		con.drawString(""+intPlayerMoney, 190, 50);
		con.repaint();
	}
		
	public static void drawInitialDeal(Console con, int[][] intPlayer, int[][] intDealer, BufferedImage[] imgCards){
		BufferedImage imgBack = con.loadImage("/Users/chrislau/Documents/GitHub/ICS3U-CPT-Chris/media/backofcard.png");

		con.clear();
		
		// Offset settings
		int xOffset = -90; // shift left
		int yOffset = 110;  // shift down

		// Draw dealer 1st card (shifted)
		int valD1 = intDealer[0][0];
		int suitD1 = intDealer[0][1];
		con.drawImage(imgCards[(suitD1 - 1) * 13 + (valD1 - 1)], 200 + xOffset, 150 + yOffset);

		// Draw player 1st card (shifted)
		int valP1 = intPlayer[0][0];
		int suitP1 = intPlayer[0][1];
		con.drawImage(imgCards[(suitP1 - 1) * 13 + (valP1 - 1)], 200 + xOffset, 400 + yOffset);

		con.repaint();
		con.sleep(500);

		// Draw dealer 2nd card (shifted + diagonal drift)
		con.drawImage(imgBack, 270 + xOffset + 10, 150 + yOffset + 10);

		// Draw player 2nd card (shifted + diagonal drift)
		int valP2 = intPlayer[1][0];
		int suitP2 = intPlayer[1][1];
		con.drawImage(imgCards[(suitP2 - 1) * 13 + (valP2 - 1)], 270 + xOffset + 10, 400 + yOffset + 10);

		con.repaint();
	}

	public static boolean checkInitialBJ(Console con){
		 boolean roundEnded = false;

		if (isBlackjack(intPlayer)) {
			intPlayerMoney += intBet * 2; // +2x profit
			roundEnded = true;
			endRoundGraphics(con);
		} else if(isBlackjack(intDealer)){
			int valD2 = intDealer[1][0];
			int suitD2 = intDealer[1][1];
			int cardIndex = (suitD2 - 1) * 13 + (valD2 - 1);
			con.drawImage(imgCards[cardIndex], 270, 150);
			con.repaint();
			con.sleep(500);
			
			if (isBlackjack(intPlayer)) {
				// Tie
				intPlayerMoney += intBet; // return bet
			} else {
				//player loses everything
			}
			roundEnded = true;
		}
		
		return roundEnded;
	}

	public static void endRoundGraphics(Console con){
		con.println("graphics here");
	}

}//class
