/**
	* Clase para la implementación del tablero de juego que estará formado por células.
 	* @author: Eduardo Escobar Alberto
 	* @version: 1.0 08/03/2017
 	* Correo electrónico: eduescal13@gmail.com.
 	* Asignatura: Programación de Aplicaciones Interactivas.
 	* Centro: Universidad de La Laguna.
*/

import java.io.IOException;

public class Tablero {

	// DECLARACION DE ATRIBUTOS.
	int numeroFilas; // Atributo para almacenar el número de filas del tablero.
	int numeroColumnas; // Atributo para almacenar el número de columnas
	Celula matrizTablero[][]; // Matriz que implementa el tablero de juego. Formado por objetos Celula.
	
	// DECLARACIÓN DE CONSTANTES.
	final static int PRIMERA_FILA = 0;
	final static int PRIMERA_COLUMNA = 0;
	final static boolean VIVA = true;
	final static boolean MUERTA = false;
	final static int NO_ACTUAL = -1;
	final static int MINIMO_VECINOS_VIVOS = 2;
	final static int MAXIMO_VECINOS_VIVOS = 3;
	
	/**
 		* Constructor por defecto.
	*/
	public Tablero() {
		this.numeroFilas = 0;
		this.numeroColumnas = 0;
	}
	
	/**
		* Constructor de copia.
		* @param. tableroCopia. Objeto tablero del que realizar la copia.
	*/
	public Tablero(Tablero tableroCopia) {
		this.numeroFilas = tableroCopia.getNumeroFilas();
		this.numeroColumnas = tableroCopia.getNumeroColumnas();
		this.matrizTablero = tableroCopia.getMatrizTablero();
	}
	
	/**
 		* Constructor.
 		* @param ficheroEntrada. Objeto de la clase LecturaFichero para operaciones con el fichero de entrada.
		* @throws IOException 
	*/
	public Tablero(LecturaFichero ficheroEntrada) throws IOException { 
		String lineaLeida = null; // Variable String para almacenar la línea leida.
		int contadorLinea = 0; // Contador para el número de líneas.
		Celula celulaTablero; // Objeto Celula para almacenar las celulas en el tablero.
		lineaLeida = ficheroEntrada.leerLineaFichero(); // Leemos manualmente la primera línea del fichero.
		this.numeroFilas = Integer.parseInt(lineaLeida); // Convertimos dicha línea a entero. Número de filas.
		lineaLeida = ficheroEntrada.leerLineaFichero(); // Leemos manualmente la segunda línea del fichero.
		this.numeroColumnas = Integer.parseInt(lineaLeida); // Convertimos dicha línea a entero. Número de columnas.
		matrizTablero = new Celula[numeroFilas][numeroColumnas]; // Inicializamos la matriz del tablero.
		while ((lineaLeida = ficheroEntrada.leerLineaFichero()) != null) { // Para el resto de líneas. Datos de las células.
			for (int j = 0; j < this.getNumeroColumnas(); j++) {
				celulaTablero = new Celula(lineaLeida.charAt(j));
				insertarCelulaTablero(contadorLinea, j, celulaTablero);
			}
			contadorLinea++; // Incrementamos para pasar a la siguiente linea de la matriz.
		}
		contadorLinea = 0; // Reiniciamos el valor de contadorLinea.
		ficheroEntrada.cerrarFichero();
	}
	
	/**
 		* Método para insertar una célula pasada por parámetro en el tablero.
 		* @param indicadorFila. Indicador de la fila en la que se insertará la célula.
		* @param indicadorColumna. Indicador de la columna en la que se insertará la célula.
		* @param celulaInsertar. Célula a insertar en el tablero. 
	*/
	public void insertarCelulaTablero(int indicadorFila, int indicadorColumna, Celula celulaInsertar) {
		this.getMatrizTablero()[indicadorFila][indicadorColumna] = celulaInsertar;
	}
	
	/**
		* Método para obtener una célula del tablero.
		* @param indicadorFila. Indicador de la fila de la célula.
		* @param indicadorColumna. Indicador de la columna de la célula.
		* @return Célula de la posición indicada.
	*/
	public Celula obtenerCelulaTablero(int indicadorFila, int indicadorColumna) {
		return this.getMatrizTablero()[indicadorFila][indicadorColumna];
	}
	
	/**
		* Método para analizar los vecinos y el estado de la célula indicada.
		* @param indicadorFila. Indicador de la fila de la célula a analizar.
		* @param indicadorColumna. Indicador de la columna de la célula a analizar.
		* @return Nuevo estado de la célula, indenpendientemente de que sea el mismo que el inicial.
	*/
	public boolean analizarCelula(int indicadorFila, int indicadorColumna) {
		Celula celulaAnalizar = obtenerCelulaTablero(indicadorFila, indicadorColumna); // Almacenamos la celula a analizar.
		boolean estadoCelula = celulaAnalizar.getEstado(); // Variable que retornará el estado de la célula analizada. Inicializada al actual.
		boolean nuevoEstado = celulaAnalizar.getEstado(); // Variable booleana de retorno.
		int vecinosVivos = numeroVecinosVivos(indicadorFila, indicadorColumna); // Obtenemos el número de vecinos vivos de la célula.
		if (estadoCelula == VIVA) { // Si la célula está viva.
			if ((vecinosVivos < MINIMO_VECINOS_VIVOS) || (vecinosVivos > MAXIMO_VECINOS_VIVOS)) { // Vecinos vivos igual a 2 o 3 exactamente.
				nuevoEstado = MUERTA; // El estado de la célula cambiaría.
			}
			else {
				nuevoEstado = VIVA;
			}
		}
		else if (estadoCelula == MUERTA) { // Si la célula está muerta.
			if (vecinosVivos == MAXIMO_VECINOS_VIVOS) { // Si el número de vecinos es igual a 3 exactamente.
				nuevoEstado = VIVA; // Actualizamos el estado a viva.
			}
			else {
				nuevoEstado = MUERTA;
			}
		}
		return nuevoEstado;
	}
	
	/**
 		* Función que devuelve el número de vecinos vivos de una celula indicada por índices.
		* @param indicadorFila. Indicador de la fila de la célula a comprobar.
		* @param indicadorColumna. Indicador de la columna de la célula a comprobar.
		* @return Número de vecinos vivos.
	*/
	public int numeroVecinosVivos(int indicadorFila, int indicadorColumna) {
		int contadorVecinosVivos = 0;
		if (indicadorFila == PRIMERA_FILA) { // Si la célula a analizar está en la primera fila, solo tendrá vecinos en la misma y en la siguiente.
			if (indicadorColumna == PRIMERA_COLUMNA) { // Si la célula está en la primera columna, solo tiene vecinos en la misma y en la siguiente.
				contadorVecinosVivos += numeroCelulasVivasFila(indicadorFila, indicadorColumna, indicadorColumna + 1, indicadorColumna);
				contadorVecinosVivos += numeroCelulasVivasFila(indicadorFila + 1, indicadorColumna, indicadorColumna + 1, NO_ACTUAL);
			}
			else if (indicadorColumna == (this.getNumeroColumnas() - 1)) { // Si la célula está en la última columna.
				contadorVecinosVivos += numeroCelulasVivasFila(indicadorFila, indicadorColumna - 1, indicadorColumna, indicadorColumna);
				contadorVecinosVivos += numeroCelulasVivasFila(indicadorFila + 1, indicadorColumna - 1, indicadorColumna, NO_ACTUAL);
			}
			else { // Si la célula está en el resto de columnas.
				contadorVecinosVivos += numeroCelulasVivasFila(indicadorFila, indicadorColumna - 1, indicadorColumna + 1, indicadorColumna);
				contadorVecinosVivos += numeroCelulasVivasFila(indicadorFila + 1, indicadorColumna - 1, indicadorColumna + 1, NO_ACTUAL);
			}
		}
		else if (indicadorFila == (this.getNumeroFilas() - 1)) { // Si la fila está en la última fila.
			if (indicadorColumna == PRIMERA_COLUMNA) { // Si la célula está en la primera columna, solo tiene vecinos en la misma y en la siguiente.
				contadorVecinosVivos += numeroCelulasVivasFila(indicadorFila, indicadorColumna, indicadorColumna + 1, indicadorColumna);
				contadorVecinosVivos += numeroCelulasVivasFila(indicadorFila - 1, indicadorColumna, indicadorColumna + 1, NO_ACTUAL);
			}
			else if (indicadorColumna == (this.getNumeroColumnas() - 1)) { // Si la célula está en la última columna.
				contadorVecinosVivos += numeroCelulasVivasFila(indicadorFila, indicadorColumna - 1, indicadorColumna, indicadorColumna);
				contadorVecinosVivos += numeroCelulasVivasFila(indicadorFila - 1, indicadorColumna - 1, indicadorColumna, NO_ACTUAL);
			}
			else { // Si la célula está en el resto de columnas.
				contadorVecinosVivos += numeroCelulasVivasFila(indicadorFila, indicadorColumna - 1, indicadorColumna + 1, indicadorColumna);
				contadorVecinosVivos += numeroCelulasVivasFila(indicadorFila - 1, indicadorColumna - 1, indicadorColumna + 1, NO_ACTUAL);
			}
		}
		else { // Si la célula está en cualquier fila exceptuando las anteriores.
			if (indicadorColumna == PRIMERA_COLUMNA) { // Si la célula está en la primera columna, solo tiene vecinos en la misma y en la siguiente.
				contadorVecinosVivos += numeroCelulasVivasFila(indicadorFila - 1, indicadorColumna, indicadorColumna + 1, NO_ACTUAL);
				contadorVecinosVivos += numeroCelulasVivasFila(indicadorFila, indicadorColumna, indicadorColumna + 1, indicadorColumna);
				contadorVecinosVivos += numeroCelulasVivasFila(indicadorFila + 1, indicadorColumna, indicadorColumna + 1, NO_ACTUAL);
			}
			else if (indicadorColumna == (this.getNumeroColumnas() - 1)) { // Si la célula está en la última columna.
				contadorVecinosVivos += numeroCelulasVivasFila(indicadorFila - 1, indicadorColumna - 1, indicadorColumna, NO_ACTUAL);
				contadorVecinosVivos += numeroCelulasVivasFila(indicadorFila, indicadorColumna - 1, indicadorColumna, indicadorColumna);
				contadorVecinosVivos += numeroCelulasVivasFila(indicadorFila + 1, indicadorColumna - 1, indicadorColumna, NO_ACTUAL);
			}
			else { // Si la célula está en el resto de columnas.
				contadorVecinosVivos += numeroCelulasVivasFila(indicadorFila - 1, indicadorColumna - 1, indicadorColumna + 1, NO_ACTUAL);
				contadorVecinosVivos += numeroCelulasVivasFila(indicadorFila, indicadorColumna - 1, indicadorColumna + 1, indicadorColumna);
				contadorVecinosVivos += numeroCelulasVivasFila(indicadorFila + 1, indicadorColumna - 1, indicadorColumna + 1, NO_ACTUAL);			
			}
		}
		return contadorVecinosVivos;
	}
	
	/**
	 	* Función que devuelve el número de células vivas en una determinada fila con un rango parametrizado por variables.
		* @param fila. Indicador de la fila en la que obtener las células vivas.
		* @param columnaInicio. Indicador de la columna en la que se comienza el análisis.
		* @param columnaFin. Indicador de la columna en la que finaliza el análisis.
		* @param noAnalizar. Indicador de la columna que no se analizará.
		* @return Número de células vivas en la fila.
	*/
	public int numeroCelulasVivasFila(int fila, int columnaInicio, int columnaFin, int noAnalizar) {
		int contadorVecinosVivos = 0; // Variable para almacenar el número de vecinos vivos.
		for (int i = columnaInicio; i <= columnaFin; i++) { // Bucle for dentro del rango indicado.
			if (i != noAnalizar) { // Evitamos la célula indicada.
				if (obtenerCelulaTablero(fila, i).getEstado() == VIVA) { // Si la célula está viva.
					contadorVecinosVivos++; // Incrementamos la variable de vecinos vivos.
				}
			}
		}	
		return contadorVecinosVivos;
	}
	
	// MÉTODOS GETTER Y SETTER DE LA CLASE.
	public int getNumeroFilas() {
		return numeroFilas;
	}
	
	public void setNumeroFilas(int numeroFilas) {
		this.numeroFilas = numeroFilas;
	}
	
	public int getNumeroColumnas() {
		return numeroColumnas;
	}
	
	public void setNumeroColumnas(int numeroColumnas) {
		this.numeroColumnas = numeroColumnas;
	}

	public Celula[][] getMatrizTablero() {
		return matrizTablero;
	}

	public void setMatrizTablero(Celula[][] matrizTablero) {
		this.matrizTablero = matrizTablero;
	}
}
