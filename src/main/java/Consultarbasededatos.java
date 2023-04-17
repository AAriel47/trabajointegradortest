import java.sql.*;

public class Consultarbasededatos {

public Consultarbasededatos() {
}

public void pronosticosql() {
	try{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/pronosticodeportivo","root","");
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from pronostico");
		while(rs.next()) {
		System.out.println("Pronostico: "+rs.getString(1));
		//USO DE LA DB
		}
		con.close();
	}
	catch(Exception e){ System.out.println(e);}
}

public void ganadoressql() {
	
	try{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/pronosticodeportivo","root","");
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from ganadores");
		while(rs.next()) {
		System.out.println("ganadores: "+rs.getString(1));
		//USO DE LA DB
		}
		con.close();
	}
	catch(Exception e){ System.out.println(e);}	
	
	
}

}
