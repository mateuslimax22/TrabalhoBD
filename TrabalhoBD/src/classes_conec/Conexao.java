package classes_conec;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	public static Connection faz_conexao() throws SQLException{
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/trabalhobd?useTimezone=true&serverTimezone=UTC","root","");
		}catch(ClassNotFoundException e) {
			throw new SQLException(e.getException());
			
			
		}
		
		
	}

}
