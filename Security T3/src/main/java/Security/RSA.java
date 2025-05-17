package Security;

public class RSA {

    private int power(int base, int exponent, int mod) {
        int result = 1;
        while (exponent > 0) {
            result = (base * result) % mod;
            exponent--;
        }
        return result;
    }

    // Extended Euclidean algorithm to find multiplicative inverse
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

    public int encrypt(int p, int q, int M, int e) {
        int n = p * q;
        return power(M, e, n);
        //throw new UnsupportedOperationException("Not implemented yet.");
    }

    public int decrypt(int p, int q, int C, int e) {
        int n = p * q;
        int phi = (p - 1) * (q - 1);
        int d = getMultiplicativeInverse(e, phi);
        return power(C, d, n);
        //throw new UnsupportedOperationException("Not implemented yet.");
    }
}
