import Security.ColumnarCipher;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class ColumnarCipherTest {
    @Test
    void testEncryptionWithValidKey() {
        ColumnarCipher cipher = new ColumnarCipher();
        List<Integer> key = List.of(1, 3, 4, 2, 5);
        String encryptedText = cipher.encrypt("COMPUTERSCIENCE", key);
        assertEquals("CTIPSCOEEMRNUCE", encryptedText, "Encryption failed with key 13425.");
    }

    @Test
    void testDecryptionWithValidKey() {
        ColumnarCipher cipher = new ColumnarCipher();
        List<Integer> key = List.of(1, 3, 4, 2, 5);
        String decryptedText = cipher.decrypt("CTIPSCOEEMRNUCE", key);
        assertEquals("COMPUTERSCIENCE", decryptedText, "Decryption failed with key 13425.");
    }

    @Test
    void testEncryptionWithDifferentKey() {
        ColumnarCipher cipher = new ColumnarCipher();
        List<Integer> key = List.of(5, 4, 3, 2, 1);
        String encryptedText = cipher.encrypt("COMPUTERSCIENCE", key);
        assertNotEquals("CTIPSCOEEMRNUCE", encryptedText, "Encryption with key 54321 should produce different output.");
    }

    @Test
    void testDecryptionWithDifferentKey() {
        ColumnarCipher cipher = new ColumnarCipher();
        List<Integer> key = List.of(5, 4, 3, 2, 1);
        String decryptedText = cipher.decrypt("CTIMRNPSEOEUCE", key);
        assertNotEquals("COMPUTERSCIENCE", decryptedText, "Decryption should fail with incorrect key.");
    }

    @Test
    void testKeyAnalysis() {
        ColumnarCipher cipher = new ColumnarCipher();
        List<Integer> key = cipher.analyse("COMPUTERSCIENCE", "CTIPSCOEEMRNUCE");
        assertEquals(List.of(1, 3, 4, 2, 5), key, "Key analysis failed.");
    }

    @Test
    void testEncryptionWithPadding() {
        ColumnarCipher cipher = new ColumnarCipher();
        List<Integer> key = List.of(1, 2, 3, 4);
        String encryptedText = cipher.encrypt("DATASECURITY", key);
        assertEquals("DSRAEITCTAUY", encryptedText, "Encryption with padding failed.");
    }

    @Test
    void testDecryptionWithPadding() {
        ColumnarCipher cipher = new ColumnarCipher();
        List<Integer> key = List.of(1, 2, 3, 4);
        String decryptedText = cipher.decrypt("DSRXAEITCTAUY", key);
        assertEquals("DATASECURITYX", decryptedText, "Decryption with padding failed.");
    }

    @Test
    void testDecryption() {
        ColumnarCipher cipher = new ColumnarCipher();
        List<Integer> key = List.of(1, 3, 2);
        String decryptedText = cipher.decrypt("CPEMTOUR", key);
        assertEquals("COMPUTER", decryptedText, "Decryption failed with key 132.");
    }

    @Test
    void testEmptyStringEncryption() {
        ColumnarCipher cipher = new ColumnarCipher();
        List<Integer> key = List.of(1, 2, 3);
        assertEquals("", cipher.encrypt("", key), "Encryption of an empty string should return an empty string.");
    }

    @Test
    void testEmptyStringDecryption() {
        ColumnarCipher cipher = new ColumnarCipher();
        List<Integer> key = List.of(1, 2, 3);
        assertEquals("", cipher.decrypt("", key), "Decryption of an empty string should return an empty string.");
    }
}
