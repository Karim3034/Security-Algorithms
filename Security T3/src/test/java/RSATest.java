import Security.RSA;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RSATest {
    @Test
    public void testEnc1() {
        RSA algorithm = new RSA();
        int cipher = algorithm.encrypt(11, 17, 88, 7);
        assertEquals(11, cipher);
    }

    @Test
    public void testDec1() {
        RSA algorithm = new RSA();
        int plain = algorithm.decrypt(11, 17, 11, 7);
        assertEquals(88, plain);
    }

    @Test
    public void testEnc2() {
        RSA algorithm = new RSA();
        int cipher = algorithm.encrypt(13, 19, 65, 5);
        assertEquals(221, cipher);
    }

    @Test
    public void testDec2() {
        RSA algorithm = new RSA();
        int plain = algorithm.decrypt(13, 19, 221, 5);
        assertEquals(65, plain);
    }

    @Test
    public void testEnc3() {
        RSA algorithm = new RSA();
        int cipher = algorithm.encrypt(61, 53, 70, 7);
        assertEquals(2338, cipher);
    }

    @Test
    public void testDec3() {
        RSA algorithm = new RSA();
        int plain = algorithm.decrypt(61, 53, 2338, 7);
        assertEquals(70, plain);
    }

    @Test
    public void testNewEnc() {
        RSA algorithm = new RSA();
        int cipher = algorithm.encrypt(257, 337, 18537, 17);
        assertEquals(12448, cipher);
    }

    @Test
    public void testNewDec4() {
        RSA algorithm = new RSA();
        int plain = algorithm.decrypt(257, 337, 12448, 17);
        assertEquals(18537, plain);
    }
}
