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
    private int getMultiplicativeInverse(int number, int baseN) {
        int A1 = 1, A2 = 0, A3 = baseN;
        int B1 = 0, B2 = 1, B3 = number;

        while (B3 != 1 && B3 != 0) {
            int Q = A3 / B3;
            int temp;

            temp = A1;
            A1 = B1;
            B1 = temp - Q * B1;

            temp = A2;
            A2 = B2;
            B2 = temp - Q * B2;

            temp = A3;
            A3 = B3;
            B3 = temp - Q * B3;
        }

        int inverse = (B2 % baseN + baseN) % baseN;
        if (B3 == 1) {
            return inverse;
        } else {
            return -1;  // Inverse doesn't exist
        }
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
        int K = power(c1, x, q);
        int K_1 = getMultiplicativeInverse(K, q);
        int m = (c2 * K_1) % q;
        return m;
        //throw new UnsupportedOperationException("Not implemented yet.");
    }
}
