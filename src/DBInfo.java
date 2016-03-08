import java.sql.Connection;
import java.sql.DriverManager;

public class DBInfo 
{
	static Connection con;
	static
	{
		String url="jdbc:mysql://localhost:3306/yourbank";
		String uname="root";
		String pass="rat";
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");			//load the driver
			con=DriverManager.getConnection(url, uname, pass);	//establishing a connection
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		//Driver loaded and connection established
	}
}
