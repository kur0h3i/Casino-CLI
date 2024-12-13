

import java.io.IOException;

import estructuraCasino.*;
import excep.ExcepcionjugadorSinFichas;

public class MainCasino {
    public static void main(String[] args) throws IOException, ExcepcionjugadorSinFichas {
        SalaRegistro registro = new SalaRegistro();
        if (registro.mayorEdad()){
            SalaPrincipal principal = new SalaPrincipal(SalaRegistro.jugador);
        }
    }
}
