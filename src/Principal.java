import java.io.IOException;

public class Principal {

	public static void main(String[] args) throws IOException {
		int numeroPasos = Integer.parseInt(args[0]);
		int debug = Integer.parseInt(args[3]);
		LecturaFichero ficheroEntrada = new LecturaFichero(args[1]);
		EscrituraFichero ficheroSalida = new EscrituraFichero(args[2]);
		JuegoVida juego = new JuegoVida(ficheroEntrada, ficheroSalida);
		juego.comenzarJuego(numeroPasos, debug);
		ficheroSalida.cerrarFichero();
	}

}
