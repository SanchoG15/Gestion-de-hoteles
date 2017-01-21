package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BD {

	private Statement orden;
	private Statement orden2;
	Connection conn;

	// Metodo para crear la conexion y que contiene la direccion de la bd.
	public BD() {
		try {
			Class.forName("org.sqlite.JDBC");

			conn = DriverManager.getConnection("jdbc:sqlite:GestionHotel.sqlite");
			orden = conn.createStatement();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	// Getter del atributo orden.
	public Statement getOrden() {
		return orden;
	}

	// Metodo que cierra la conexion a la base de datos.
	public void cerrar() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Metodo para crear la conexion a la bd solamente.
	public void conectar() {
		// TODO Auto-generated method stub
		try {
			orden = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Corta la conexion.
	public void terminarConsulta(ResultSet rs) {
		// TODO Auto-generated method stub
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Sirve para ejecutar la consulta que queremos hacer.
	public ResultSet ejecutarConsulta(String sql) {
		Statement ord = null;
		try {
			ord = conn.createStatement();
			return ord.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
