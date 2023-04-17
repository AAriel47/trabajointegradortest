

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Participantes {
	static int[] punval = new int[3];
	static String pungan;
	static int pungan1;
	static int adic=0;

	
	static boolean entrar=false;
	static String reducir;
	static String contenido1;
	static String contenido2;
	static String ganadores;
	static String perdedores;
	static int rep;
	static String repnom;
	static String repnom2;
	static String nombregana;
	static String valores;
	static String [] valor;
	static String variospronos;
	static String redupronos;
	
	static List<String> resuoriginal = new ArrayList<String>();
	static List<String> comprobar =new ArrayList<String>();

	static List<Object> resultadofina = new ArrayList<Object>();
	static List<Object>resultadofinal = new ArrayList<Object>();


	public Participantes(){

	}
		
		/*static Path fechasPar = Paths.get("C:\\Users\\lucas\\OneDrive\\Escritorio\\Curso Java\\fechas.csv");
		static Path partidos = Paths.get("C:\\Users\\lucas\\OneDrive\\Escritorio\\Curso Java\\zonas.csv");
		static Path resultados = Paths.get("C:\\Users\\lucas\\OneDrive\\Escritorio\\Curso Java\\resultado.csv");
		static Path reducida = Paths.get("C:\\Users\\lucas\\OneDrive\\Escritorio\\Curso Java\\resullista.csv");
		static Path pronosreducido = Paths.get("C:\\Users\\lucas\\OneDrive\\Escritorio\\Curso Java\\pronosredu.csv");
		static Path ganador = Paths.get("C:\\Users\\lucas\\OneDrive\\Escritorio\\Curso Java\\ganadoresfinales.csv");
		*/
		
	static Path reducida = Paths.get("resullista.csv");
	static Path pronosreducido = Paths.get("pronosredu.csv");
	static Path ganador = Paths.get("ganadoresfinales.csv");
	
	public void mostrar() {
		try {
			if (!Files.exists(pronosreducido)) {
				System.out.println("NO SE CARGARON PRONOSTICOS TODAVÍA");
			}else {
				for (String leerlineas : Files.readAllLines(pronosreducido)) {
				variospronos = String.valueOf(leerlineas);
				}
				if (null==variospronos) {
					System.out.println("NO SE CARGARON PRONOSTICOS TODAVÍA");
				}else {
					for (String leerlista : Files.readAllLines(ganador)) {
					valores = String.valueOf(leerlista);
					}
					String [] varios = variospronos.split(",");
					System.out.println("Pronosticos realizados");
					for(int i=0;i<varios.length;i++) {
						System.out.print(varios[i]+" ");
						if(i%2!=0) {
							System.out.println(" ");
						}
	
					}
				//******************************************
					
					try{
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/pronosticodeportivo","root","");
						Statement stmt=con.createStatement();
						stmt.executeUpdate("DELETE FROM pronostico");
						String valor = varios[0];
						for (int i =1; i<varios.length;i++) {
							valor = valor + varios[i];
							String mens="INSERT INTO pronostico (pronos) values (\"" + valor+"\")";
							stmt.executeUpdate(mens);
							i++;
							valor="";
							if(i==varios.length) {
								break;
							}
							valor = valor + varios[i];
						}
						//ResultSet rs=stmt.executeQuery("select * from pronostico");
						//while(rs.next()) {
						//System.out.println("ID:"+rs.getString(1));
						//USO DE LA DB
						//}
						con.close();
					}
					catch(Exception e){ System.out.println(e);}
					
					
					
				//**************************************
				
					if ((!Files.exists(reducida))|| (!Files.exists(pronosreducido))){
						System.out.println("AUN NO SE GRABARON LOS PRONOSTICOS NI LOS RESULTADOS DE LOS PARTIDOS");
					}else {
						try {
								for (String leerlista : Files.readAllLines(reducida)) {
								reducir = String.valueOf(leerlista);
								}
								
								for (String leerlista1 : Files.readAllLines(pronosreducido)) {
									redupronos = String.valueOf(leerlista1);
								}
				
						}
						catch(IOException e) {
							System.err.print(e);
						}
						//EN ESTA ZONA SE COMPARA LOS DATOS DE LOS PRONOSTICOS RESUMIDOS(CON CAMPO CLAVE) Y LOS RESULTADOS RESUMIDOS (CON CAMPO CLAVE)
						String lista[]=reducir.split(",");
						for (int re=0;re<lista.length;re++) {
							resuoriginal.add(lista[re]);
						}

						String[] lista1 = redupronos.split(",");
						for (int con=0;con<lista1.length;con++) {
							comprobar.add(lista1[con]);
						}
						for(int e = 0; e<resuoriginal.size();e++) {
							contenido1=resuoriginal.get(e).toString();
							for(int i = 0;i<comprobar.size();i++) {
								contenido2=comprobar.get(i).toString();
								if (contenido1.equals(contenido2)) {
									i++;
									ganadores=contenido1+" " +comprobar.get(i).toString()+" GANADOR";
									resultadofina.add(ganadores);
								}
							}
						}
					}
					
					for(int e = 0; e<comprobar.size();e++) {
						contenido1=comprobar.get(e).toString();
						for(int i = 0;i<resuoriginal.size();i++) {
							contenido2=resuoriginal.get(i).toString();
							if (contenido1.equals(contenido2)) {
								entrar=false;
								break;
							}else {
								entrar=true;
							}
						}
						e++;
						if (entrar) {
							perdedores=contenido1+" "+comprobar.get(e).toString()+" PERDEDOR = 0";
							resultadofinal.add(perdedores);
						}
					}
							
					
					
					System.out.println(" ");
					System.out.println("GANADORES: ");
					
					for(int i=0;i<resultadofina.size();i++) {
						System.out.println(resultadofina.get(i));
					}					
					
					
					System.out.println(" ");
					System.out.println("PERDEDORES: ");
					for(int a = 0; a<resultadofinal.size();a++) {
						System.out.println(resultadofinal.get(a));
					}
					System.out.println(" ");

				//****************************************
					valores = valores.substring(1, valores.length()-1);
					valor = valores.split(",");
					
					List<String> datosgana =new ArrayList<String>();
					List<String> datosgana2 =new ArrayList<String>();

					
					for (int i=0;i<valor.length;i++) {
						datosgana.add(valor[i].toString());
					}					
					//-------------------------------
					try{
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/pronosticodeportivo","root","");
						Statement stmt=con.createStatement();
						ResultSet st = stmt.executeQuery("select * from configuracion");
						int s = 1;
						while(st.next()) {
							if(s==1) {
								punval[0] = st.getInt(3);
							}else if(s==4) {
								punval[1] = st.getInt(3);
							}else if(s==5) {
								punval[2] = st.getInt(3);
							}
							s++;
						}
						//
						//
						con.close();
					}
					catch(Exception ep){ System.out.println(ep);}
					
					//----------------------------------
					System.out.println("PARTICIPANTE - PUNTOS: ");
					for(int e = 0;e<datosgana.size();e++) {
						//System.out.println(datosgana.get(e));
						
						pungan = datosgana.get(e).substring(datosgana.get(e).length()-1, datosgana.get(e).length());
						pungan1 = Integer.parseInt(pungan);
						int respun = punval[0];
						if (pungan1==2) {
							adic = punval[1];
						}else if(pungan1==6) {
							adic = punval[2];
						}
						System.out.println(datosgana.get(e)+" Puntos obtenidos "+((pungan1*respun)+adic));
						String grarel = datosgana.get(e)+" Puntos obtenidos "+((pungan1*respun)+adic);
						adic=0;
						datosgana2.add(grarel);

					}
					//*********************************************
					
					try{
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/pronosticodeportivo","root","");
						Statement stmt=con.createStatement();
						stmt.executeUpdate("DELETE FROM ganadores");
						String valor;// = datosgana.get(0);
						for (int i =0; i<datosgana2.size();i++) {
							valor = datosgana2.get(i);
							String mens="INSERT INTO ganadores (nombres) values (\"" + valor+"\")";
							stmt.executeUpdate(mens);
							if(i==varios.length) {
								break;
							}
						}
						con.close();
					}
					catch(Exception e){ System.out.println(e);}					
					
					//*********************************************
				}
			}
		}
		catch(IOException e) {
			System.err.print(e);
		}
	}
}	
	
