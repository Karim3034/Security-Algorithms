import Security.MonoalphabeticCipher;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class MonoalphabeticCipherTest {

    @Test
    public void testEncryption() {
        String key = "QWERTYUIOPASDFGHJKLZXCVBNM";
        String plaintext = "HELLO WORLD";
        String expectedCiphertext = "ITSSG VGKSR";

        String actualCiphertext = MonoalphabeticCipher.encrypt(plaintext, key);
        assertEquals(expectedCiphertext, actualCiphertext);
    }

    @Test
    public void testDecryption() {
        String key = "QWERTYUIOPASDFGHJKLZXCVBNM";
        String ciphertext = "ITSSG VGKSR";
        String expectedPlaintext = "HELLO WORLD";

        String actualPlaintext = MonoalphabeticCipher.decrypt(ciphertext, key);
        assertEquals(expectedPlaintext, actualPlaintext);
    }

    @Test
    public void testFindKey() {
        String mainPlain = "meetmeafterthetogaparty".toUpperCase();
        String mainCipher = "phhwphdiwhuwkhwrjdsduwb".toUpperCase();
        String expectedPattern = "d.{3}hijk.{4}p.rs.u.w.{4}b.".toUpperCase(); // Regex pattern

        MonoalphabeticCipher algorithm = new MonoalphabeticCipher();
        String key = algorithm.findKey(mainPlain, mainCipher);

        // Validate key format against expected pattern
        assertTrue(Pattern.matches(expectedPattern, key), "Key does not match expected pattern.");
    }
}
