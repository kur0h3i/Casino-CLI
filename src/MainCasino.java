

import java.io.IOException;

import casino.*;

public class MainCasino {
    public static void main(String[] args) throws IOException {
        SalaRegistro registro = new SalaRegistro();
        if (registro.mayorEdad()){
            SalaPrincipal principal = new SalaPrincipal(SalaRegistro.jugador);
        }
    }
}
