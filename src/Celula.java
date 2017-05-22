/**
	* Clase para la implementación de células del tablero de juego.
 	* @author: Eduardo Escobar Alberto
 	* @version: 1.0 08/03/2017
 	* Correo electrónico: eduescal13@gmail.com.
 	* Asignatura: Programación de Aplicaciones Interactivas.
 	* Centro: Universidad de La Laguna.
*/

public class Celula {
	
	// DECLARACIÓN DE ATRIBUTOS.
	private boolean estado; // Atributo booleano para determinar el estado. 
	
	// DECLARACIÓN DE CONSTANTES.
	final static boolean VIVA = true;
	final static boolean MUERTA = false;
	final static char CARACTER_VIVA = 'o';
	final static char CARACTER_MUERTA = '*';
	
	/**
	 	* Constructor por defecto. 
	*/ 
	public Celula() {
		estado = VIVA;
	}
	
	/**
 		* Constructor mediante un boolean. 
 		* @param estado. Variable booleana con el estado de la célula.
 	*/ 
	public Celula(boolean estado) {
		this.estado = estado;
	}
	
	/**
		* Constructor mediante un char. 
		* @param estado. Variable char con el estado de la célula.
	*/ 
	public Celula(char estado) {
		if (estado == CARACTER_VIVA) {
			this.estado = VIVA;
		}
		else if (estado == CARACTER_MUERTA) {
			this.estado = MUERTA;
		}
	}

	/**
		* Método toString de una célula. 
	*/ 
	public String toString() { 
		String retorno = null;
		if (this.getEstado() == VIVA) {
			retorno = String.valueOf(CARACTER_VIVA);
		}
		else if (this.getEstado() == MUERTA) {
			retorno = String.valueOf(CARACTER_MUERTA);
		} 
		return retorno;
	}
	
	// MÉTODOS GETTER Y SETTER DE LA CLASE.
	public boolean getEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}	
}
