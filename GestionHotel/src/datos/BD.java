package datos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BD {
		
	private Statement orden;
	Connection conn ;
	public BD() {
		try {
			Class.forName("org.sqlite.JDBC");
		
        conn = DriverManager.getConnection("jdbc:sqlite:GestionHotel.sqlite");
        orden = conn.createStatement();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	public Statement getOrden() {
		return orden;
	}
	public void cerrar(){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
