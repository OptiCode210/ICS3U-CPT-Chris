import arc.*;
public class mainProgram{
	public static void main(String args[]){
		Console con = new Console();
		
		//Make the title centered, and then print it
		String strTitle = "***** Welcome To BlackJack *****";
		int intWidth = 80;
		con.println(
			String.format("%" + ((intWidth + strTitle.length())/2) + "s", strTitle)
			);
		
	}
}
