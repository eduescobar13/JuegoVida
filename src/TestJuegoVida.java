/**
	* Clase para la implementación de los test de la clase JuegoVida.
 	* @author: Eduardo Escobar Alberto
 	* @version: 1.0 08/03/2017
 	* Correo electrónico: alu0100825985@ull.edu.es
 	* Asignatura: Programación de Aplicaciones Interactivas.
 	* Centro: Universidad de La Laguna.
*/

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;
 
public class TestJuegoVida {
 	
	// DECLARACIÓN DE ATRIBUTOS.
	protected static JuegoVida juegoTest; 
	
	// DECLARACIÓN DE CONSTANTES.
	final static int NUMERO_COLUMNAS = 30;
	final static int NUMERO_FILAS = 10;
	final static boolean VIVA = true;
	final static boolean MUERTA = false;
	final static int NO_ACTUAL = -1;
	
	@BeforeClass
	public static void setUpClass() throws IOException { // Método que se ejecuta antes de los test de la clase para asignar variables.
		LecturaFichero ficheroEntradaTest = new LecturaFichero("input.txt");
		juegoTest = new JuegoVida(ficheroEntradaTest); // Inicializamos el objeto juegoTest.
	}
	
	@Test
	public void inicializacionAnalizador() { // Test para comprobar la correcta creación del objeto juegoTest.
		assertNotNull(juegoTest); // Comprobamos que el objeto juegoTest se ha creado correctamente.
	}
	
	@Test
	public void comprobarCreacionTablero() { // Test para comprobar el correcto funcionamiento de descomponerLinea.
		assertEquals(NUMERO_FILAS, juegoTest.getTableroJuego().getNumeroFilas());
		assertEquals(NUMERO_COLUMNAS, juegoTest.getTableroJuego().getNumeroColumnas());
	}
	
	@Test
	public void comprobarEstadoCelula() { // Test para comprobar la correcta inicialización de la células.
		assertTrue(juegoTest.getTableroJuego().obtenerCelulaTablero(2, 13).getEstado());
		assertTrue(juegoTest.getTableroJuego().obtenerCelulaTablero(4, 23).getEstado());
		assertTrue(juegoTest.getTableroJuego().obtenerCelulaTablero(7, 12).getEstado());
		assertFalse(juegoTest.getTableroJuego().obtenerCelulaTablero(1, 7).getEstado());
		assertFalse(juegoTest.getTableroJuego().obtenerCelulaTablero(5, 16).getEstado());
		assertFalse(juegoTest.getTableroJuego().obtenerCelulaTablero(9, 0).getEstado());
	}
	
 	@Test
	public void comprobarCelulasVivasFila() { // Test para comprobar el correcto funciónamiento de celulasVivasFila.
 		assertEquals(5, juegoTest.getTableroJuego().numeroCelulasVivasFila(4, 2, 28, 13));
 		assertEquals(6, juegoTest.getTableroJuego().numeroCelulasVivasFila(4, 2, 28, NO_ACTUAL));
 		assertEquals(0, juegoTest.getTableroJuego().numeroCelulasVivasFila(1, 0, 29, NO_ACTUAL));
 		assertEquals(3, juegoTest.getTableroJuego().numeroCelulasVivasFila(2, 7, 25, 8));
 	}

 	@Test
	public void comprobarVecinosVivos() { // Test para comprobar el correcto funciónamiento de numeroVecinosVivos.
 		assertEquals(1, juegoTest.getTableroJuego().numeroVecinosVivos(2, 13));
 		assertEquals(4, juegoTest.getTableroJuego().numeroVecinosVivos(4, 22));
 		assertEquals(2, juegoTest.getTableroJuego().numeroVecinosVivos(4, 5));
 		assertEquals(4, juegoTest.getTableroJuego().numeroVecinosVivos(7, 13));
 		assertEquals(0, juegoTest.getTableroJuego().numeroVecinosVivos(7, 25));
 	}
 	
 	@Test
 	public void comprobarAnalizarCelula() { // Test para comprobar el correcto funcionamiento de analizarCelula.
		boolean nuevoEstado1, nuevoEstado2;
 		assertTrue(juegoTest.getTableroJuego().obtenerCelulaTablero(3, 21).getEstado());
 		assertFalse(juegoTest.getTableroJuego().obtenerCelulaTablero(3, 12).getEstado());
 		nuevoEstado1 = juegoTest.getTableroJuego().analizarCelula(3, 21);
 		nuevoEstado2 = juegoTest.getTableroJuego().analizarCelula(3, 12);
 		assertFalse(nuevoEstado1);
 		assertTrue(nuevoEstado2);
 	}
}