import arc.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.FontMetrics;

public class tools{

    public static String title() {
        // Make the title centered, and then return it
        String strTitle = "***** Welcome To BlackJack *****";
        int intWidth = 80;
        return String.format(
            "%" + ((intWidth + strTitle.length()) / 2) + "s",
            strTitle
        );
    }

    public static void logoNBackground() {
        Console con = new Console();
        con.setBackgroundColor(new Color(240, 240, 240));
    }

}
