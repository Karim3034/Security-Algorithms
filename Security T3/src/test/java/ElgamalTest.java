import Security.ElGamal;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ElgamalTest {
    @Test
    public void testElGamalEnc1() {
        ElGamal algorithm = new ElGamal();
        List<Long> cipher = algorithm.encrypt(7187, 4842, 4464, 19, 19);  // privateKey = 191
        assertEquals(2781L, cipher.get(0));
        assertEquals(437L, cipher.get(1));
    }

    @Test
    public void testElGamalEnc2() {
        ElGamal algorithm = new ElGamal();
        List<Long> cipher = algorithm.encrypt(6323, 4736, 2231, 58, 111);  // privateKey = 118
        assertEquals(6066L, cipher.get(0));
        assertEquals(899L, cipher.get(1));
    }

    @Test
    public void testElGamalDec1() {
        ElGamal algorithm = new ElGamal();
        int plain = algorithm.decrypt(2781, 437, 191, 7187);
        assertEquals(plain, 19);
    }

    @Test
    public void testElGamalDec2() {
        ElGamal algorithm = new ElGamal();
        int plain = algorithm.decrypt(6066, 899, 118, 6323);
        assertEquals(plain, 111);
    }
}
