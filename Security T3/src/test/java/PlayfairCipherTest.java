import Security.PlayfairCipher;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PlayfairCipherTest {

    @Test
    void testEncryption() {
        PlayfairCipher cipher = new PlayfairCipher("KEYWORD");
        assertEquals("GYIZSCOKCFBU", cipher.encrypt("HELLO WORLD"));
    }

    @Test
    void testDecryption() {
        PlayfairCipher cipher = new PlayfairCipher("KEYWORD");
        assertEquals("HELLOWORLD", cipher.decrypt("GYIZSCOKCFBU"));
    }

    @Test
    void testDifferentKey() {
        PlayfairCipher cipher = new PlayfairCipher("SECRET");
        assertNotEquals("HELLOWORLD", cipher.decrypt("GATLMZCLRQX"));
    }
}
