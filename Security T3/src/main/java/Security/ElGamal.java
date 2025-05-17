package Security;
import java.util.ArrayList;
import java.util.List;

public class ElGamal {

    private static int power(int base, int exponent, int mod) {
        int result = 1;
        while (exponent > 0) {
            result = (base * result) % mod;
            exponent--;
        }
        return result;
    }
    public List<Long> encrypt(int q, int alpha, int y, int k, int m) {
        int K = power(y, k, q);

        List<Long> c = new ArrayList<>(2);
        c.add((long) power(alpha, k, q));     // C1
        c.add((long) (K * m % q));            // C2

        return c;
        //throw new UnsupportedOperationException("Not implemented yet.");
    }

    public int decrypt(int c1, int c2, int x, int q) {
        int K_1 = power(c1, q - 1 - x, q);
        int m = (c2 * K_1) % q;
        return m;
        //throw new UnsupportedOperationException("Not implemented yet.");
    }
}
