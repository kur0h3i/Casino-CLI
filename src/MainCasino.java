

import java.io.IOException;

import estructuraCasino.*;
import excep.ExcepcionJugadorMenorEdad;
import excep.ExcepcionJugadorSinFichas;

public class MainCasino {
    public static void main(String[] args) throws IOException, ExcepcionJugadorSinFichas, ExcepcionJugadorMenorEdad {
        SalaRegistro registro = new SalaRegistro();
        if (registro.mayorEdad()){
            SalaPrincipal principal = new SalaPrincipal(SalaRegistro.jugador);
        }
    }
}
