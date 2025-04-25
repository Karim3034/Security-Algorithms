import Security.DES;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class DESTest {

    @Test
    void testEncryption() {
        DES des = new DES();
        String key = "133457799BBCDFF1"; // 64-bit key in hex
        String plainText = "0123456789ABCDEF"; // 64-bit block in hex
        String expectedCipherText = "85E813540F0AB405"; // Known output from DES example
        String actualCipherText = des.encrypt(plainText, key);
        assertEquals(expectedCipherText.toUpperCase(), actualCipherText.toUpperCase());
    }

    @Test
    void testDecryption() {
        DES des = new DES();
        String key = "133457799BBCDFF1";
        String cipherText = "85E813540F0AB405";
        String expectedPlainText = "0123456789ABCDEF";
        String actualPlainText = des.decrypt(cipherText, key);
        assertEquals(expectedPlainText.toUpperCase(), actualPlainText.toUpperCase());
    }

    @Test
    void testDifferentKey() {
        DES des = new DES();
        String wrongKey = "A1B2C3D4E5F60708";
        String cipherText = "85E813540F0AB405";
        String result = des.decrypt(cipherText, wrongKey);
        assertNotEquals("0123456789ABCDEF", result);
    }

    @Test
    void testSameEncryptionDecryption() {
        DES des = new DES();
        String key = "AABB09182736CCDD";
        String plainText = "1234567890ABCDEF";
        String encrypted = des.encrypt(plainText, key);
        String decrypted = des.decrypt(encrypted, key);
        assertEquals(plainText.toUpperCase(), decrypted.toUpperCase());
    }
}
