import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;


public class Browser extends User {
	Browser(String name,String password,String role){
		super(name,password,role);
	}
	
	public void showMenu(){
		new BrFrame();
	}
}
