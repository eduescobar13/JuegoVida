/**
	* Clase para la implementación del Juego de la Vida de John Conway's.
 	* @author: Eduardo Escobar Alberto
 	* @version: 1.0 08/03/2017
 	* Correo electrónico: eduescal13@gmail.com.
 	* Asignatura: Programación de Aplicaciones Interactivas.
 	* Centro: Universidad de La Laguna.
*/

import java.io.IOException;

public class JuegoVida {
	
	// DECLARACIÓN DE ATRIBUTOS.
	Tablero tableroJuego; // Atributo que contiene el tablero del juego.
	EscrituraFichero ficheroSalida; // Atributo que se usará para volcar los resultados del juego.
	
	/**
 		* Constructor por defecto.
	*/
	public JuegoVida() {
		this.tableroJuego = new Tablero();
		this.ficheroSalida = new EscrituraFichero();
	}
	
	/**
	 	* Constructor sin salida.
		* @param ficheroEntrada. Objeto de la clase LecturaFichero para operaciones con el fichero de entrada.
		* @throws IOException 
	*/
	public JuegoVida(LecturaFichero ficheroEntrada) throws IOException {
		this.tableroJuego = new Tablero(ficheroEntrada);
	}
	
	/**
	 	* Constructor.
		* @param ficheroEntrada. Objeto de la clase LecturaFichero para operaciones con el fichero de entrada.
		* @param ficheroSalida. Objeto de la clase EscrituraFichero para operaciones con el fichero de entrada.
		* @throws IOException 
	*/
	public JuegoVida(LecturaFichero ficheroEntrada, EscrituraFichero ficheroSalida) throws IOException {
		this.tableroJuego = new Tablero(ficheroEntrada);
		this.ficheroSalida = ficheroSalida;
	}
	
	/**
	 	* Método para volcar el contenido del tablero en el fichero de salida.
	*/
	public void volcarTableroFichero() {
		String lineaFichero;
		for (int i = 0; i < this.getTableroJuego().getNumeroFilas(); i++) {
			lineaFichero = this.getTableroJuego().obtenerCelulaTablero(i, 0).toString();
			for (int j = 1; j < this.getTableroJuego().getNumeroColumnas(); j++) {
				lineaFichero += this.getTableroJuego().obtenerCelulaTablero(i, j).toString();
			}
			getFicheroSalida().escribirLineaFichero(lineaFichero);
			lineaFichero = null;
		}
	}
	
	/**
		* Método principal de la clase. Implementa el desarrollo del juego.
		* @param numeroPasos. Número de pasos a implementar en el juego.
	*/
	public void inicializarFicheroSalida(int numeroPasos, int debug) {
		getFicheroSalida().escribirLineaFichero("UNIVERSIDAD DE LA LAGUNA.");
		getFicheroSalida().escribirLineaFichero("Programación de Aplicaciones Interactivas.");
		getFicheroSalida().escribirLineaFichero("Juego de la Vida de Jonh Conway's.");
		getFicheroSalida().escribirLineaFichero("------------------------------------------------");
		if (debug == 1) {
			getFicheroSalida().escribirLineaFichero("MODO DEBUG. NÚMERO DE PASOS: " + numeroPasos);
			getFicheroSalida().escribirLineaFichero("------------------------------------------------");
		}
		getFicheroSalida().escribirLineaFichero("----------- ESTADO INICIAL -----------");
		getFicheroSalida().escribirLineaFichero("");
		volcarTableroFichero();
		getFicheroSalida().escribirLineaFichero("");
	}
	
	/**
		* Método para finalizar la escritura en el fichero de salida.
		* @throws IOException 
	*/
	public void finalizarFicheroSalida() throws IOException {
		getFicheroSalida().escribirLineaFichero("------------- ESTADO FINAL ------------");
		getFicheroSalida().escribirLineaFichero("");
		volcarTableroFichero();
		getFicheroSalida().escribirLineaFichero("");
		getFicheroSalida().cerrarFichero();
	}
	
	/**
 		* Método principal de la clase. Implementa el desarrollo del juego.
 		* @param numeroPasos. Número de pasos a implementar en el juego.
 		* @throws IOException 
 	*/
	public void comenzarJuego(int numeroPasos, int debug) throws IOException {
		int contadorPasos = 0; // Variable para contabilizar los pasos.
		boolean nuevoEstado = false; // Variable booleana para almacenar el nuevo estado de una célula.
		Celula celulaSalida; // Objeto celula para crear nuevas celulas que almacenar en la matriz auxiliar.
		Celula[][] matrizAuxiliar; // Matriz auxiliar para guardar el estado del nuevo tablero.
		inicializarFicheroSalida(numeroPasos, debug); // Inicializamos el fichero con la información del juego.
		while (contadorPasos < numeroPasos) { // Para el número de pasos pasado por parámetros.
			matrizAuxiliar = new Celula[this.getTableroJuego().getNumeroFilas()][this.getTableroJuego().getNumeroColumnas()]; // Inicializamos la matriz auxiliar.
			for (int i = 0; i < this.getTableroJuego().getNumeroFilas(); i++) {
				for (int j = 0; j < this.getTableroJuego().getNumeroColumnas(); j++) {
					nuevoEstado = this.getTableroJuego().analizarCelula(i, j); // Analizamos cada una de las células.
					celulaSalida = new Celula(nuevoEstado);// Creamos una nueva celula con el estado que nos devuelve analizarCelula.
					matrizAuxiliar[i][j] = celulaSalida; // Guardamos dicha célula en una matriz auxiliar. No sobreescribimos la del tablero.
				}
			}
			this.getTableroJuego().setMatrizTablero(matrizAuxiliar); // Sobreescribimos la matriz de tablero actual con la nueva matriz de salida.
			if (debug == 1) { // Para el modo debug volcamos el paso en el fichero de salida.
				getFicheroSalida().escribirLineaFichero("--------------- PASO " + (contadorPasos + 1) + " ---------------");
				getFicheroSalida().escribirLineaFichero("");
				volcarTableroFichero();
				getFicheroSalida().escribirLineaFichero("");
			}
			contadorPasos++;
		}
		finalizarFicheroSalida();
		contadorPasos = 0; // Reiniciamos el contador de pasos.
	}

	// MÉTODOS GETTER Y SETTER DE LA CLASE.
	public Tablero getTableroJuego() {
		return tableroJuego;
	}

	public void setTableroJuego(Tablero tableroJuego) {
		this.tableroJuego = tableroJuego;
	}

	public EscrituraFichero getFicheroSalida() {
		return ficheroSalida;
	}

	public void setFicheroSalida(EscrituraFichero ficheroSalida) {
		this.ficheroSalida = ficheroSalida;
	}
}
