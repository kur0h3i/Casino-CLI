package excep;

public class ExcepcionJugadorSinFichas extends Exception {
    public ExcepcionJugadorSinFichas(String mensaje) { 
        super(mensaje);
    }
}