import Security.DiffieHellman;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class DiffieHellmanTest {
    @Test
    public void testDiffieHellman1() {
        DiffieHellman algorithm = new DiffieHellman();
        List<Integer> key = algorithm.getKeys(19, 2, 6, 13);
        assertEquals(7, key.get(0));
        assertEquals(7, key.get(1));
    }

    @Test
    public void testDiffieHellman2() {
        DiffieHellman algorithm = new DiffieHellman();
        List<Integer> key = algorithm.getKeys(353, 2, 97, 233);
        assertEquals(81, key.get(0));
        assertEquals(81, key.get(1));
    }

    @Test
    public void testDiffieHellman3() {
        DiffieHellman algorithm = new DiffieHellman();
        List<Integer> key = algorithm.getKeys(353, 3, 97, 233);
        assertEquals(160, key.get(0));
        assertEquals(160, key.get(1));
    }

    @Test
    public void testDiffieHellmanNew() {
        DiffieHellman algorithm = new DiffieHellman();
        List<Integer> key = algorithm.getKeys(541, 10, 50, 100);
        assertEquals(449, key.get(0));
        assertEquals(449, key.get(1));
    }
}
