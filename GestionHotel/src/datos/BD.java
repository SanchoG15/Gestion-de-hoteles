package datos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BD {
		
	private Statement orden;
	
	public BD() {
		try {
			Class.forName("org.sqlite.JDBC");
		
        Connection conn = DriverManager.getConnection("jdbc:sqlite:GestionHotel.sqlite");
        orden = conn.createStatement();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	public Statement getOrden() {
		return orden;
	}
	
}
