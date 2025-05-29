import arc.*;
public class mainProgram{
	public static void main(String args[]){
		Console con = new Console(1000,800);
		
		//call the background image method
		tools.background(con);
		
		//call the logo image method
		tools.logo(con);
		
		//call the menu options method
		tools.menu(con);
		
	}
}
