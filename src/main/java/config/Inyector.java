package config;

import libraries.hashing.EstrategiaDeHashing;
import libraries.hashing.FactoryEstrategiaDeHashing;
import libraries.hashing.GuavaGoogleHash;

public class Inyector {

    private static Inyector instancia;
    private EstrategiaDeHashing estrategiaDeHashing;

    static {
        instancia = null;
    }

    private Inyector() {
        this.estrategiaDeHashing = FactoryEstrategiaDeHashing.
                getEstrategiaDeHashing(Config.biblotecaHash);   //Podriamos leerlo de un archivo
                                                            // de configuracion y levantar la bibloteca necesaria
    }

    public static Inyector getInstance() {
        if (instancia == null) {
            instancia = new Inyector();
        }
        return instancia;
    }

    public EstrategiaDeHashing estrategiaDeHashing(){
        return instancia.estrategiaDeHashing;
    }

}
