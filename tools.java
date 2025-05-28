import arc.*;
import java.awt.Color;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.FontMetrics;

public class tools{
	public static int intWidth;
	public static int intHeight;
	public static void logo(Console con) {
		//loading the logo
        BufferedImage imgLogo = con.loadImage("/Users/chrislau/Documents/CS/CPT/bjLogo.png");
        
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
		BufferedImage imgBackground = con.loadImage(
			"/Users/chrislau/Documents/CS/CPT/backgroundfinal.png"
		);
		
		con.drawImage(imgBackground,0,0);
		con.repaint();
		
	}

}
