package libraries.hashing;

public class FactoryEstrategiaDeHashing {

    public static EstrategiaDeHashing getEstrategiaDeHashing(String nombreEstrategia){
        EstrategiaDeHashing estrategiaDeHashing = null;
        switch (nombreEstrategia){
            case "guava": estrategiaDeHashing = new GuavaGoogleHash();
        }
        return estrategiaDeHashing;
    }

}
