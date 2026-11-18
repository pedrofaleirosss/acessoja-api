
package br.com.acessoja.util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class SenhaUtil {

    public static String gerarHash(String senha) {

        try {
            byte[] salt = new byte[16];
            new SecureRandom().nextBytes(salt);

            int iteracoes = 600000;

            PBEKeySpec spec = new PBEKeySpec(
                    senha.toCharArray(), salt, iteracoes, 256
            );

            SecretKeyFactory factory =
                    SecretKeyFactory.getInstance(
                            "PBKDF2WithHmacSHA256"
                    );

            byte[] hash = factory.generateSecret(spec)
                    .getEncoded();

            spec.clearPassword();

            return "PBKDF2:" + iteracoes + ":" +
                    Base64.getEncoder().encodeToString(salt) +
                    ":" +
                    Base64.getEncoder().encodeToString(hash);

        } catch (Exception e) {
            throw new IllegalStateException(
                    "Erro ao proteger a senha"
            );
        }
    }
}
