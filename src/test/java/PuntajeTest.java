import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class PuntajeTest {
private Puntaje puntaje;

static String datos;
static String datos2="[Alejandro Ariel Uñate 2, Jose Luis Perales 1]";
	@Test
	public void test() {
		puntaje = new Puntaje();
		Puntaje esperado = new Puntaje();
		datos=esperado.puntajes();
		assertTrue(datos, true);
	}

}
