package libraries.hashing;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class GuavaGoogleHash implements EstrategiaDeHashing{
    @Override
    public String generarHash(String algo) {
        return Hashing.sha256().hashString(algo, StandardCharsets.UTF_8).toString();
    }
}
