
import java.sql.*;

public class Configurar {
static int ids;
static int puntos;


public Configurar(int ids, int punto) {
	this.ids = ids;
	this.puntos = punto;
}
public Configurar() {
	
}
public  void configuracion(){
	try{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/pronosticodeportivo","root","");
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from configuracion");
		while(rs.next()) {
		System.out.println("ID:"+" "+rs.getInt(1)+" - "+"Detalle:"+" "+rs.getString(2)+" - "+"Puntos:"+" "+rs.getString(3));
		//USO DE LA DB
		}
		con.close();
	}
	catch(Exception e){ System.out.println(e);}
}

public void actualizar() {
	try{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/pronosticodeportivo","root","");
		Statement stmt=con.createStatement();
		String mens="UPDATE configuracion SET punto =" + this.puntos+" WHERE id ="+this.ids;
		stmt.executeUpdate(mens);
		ResultSet rs=stmt.executeQuery("select * from configuracion");
		while(rs.next()) {
		System.out.println("ID:"+" "+rs.getInt(1)+" - "+"Detalle:"+" "+rs.getString(2)+" - "+"Puntos:"+" "+rs.getString(3));
		//USO DE LA DB
		}
		con.close();
	}
	catch(Exception e){ System.out.println(e);}
	

	
}
}