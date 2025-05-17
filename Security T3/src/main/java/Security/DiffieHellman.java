package Security;

import java.util.ArrayList;
import java.util.List;

public class DiffieHellman {

     public int power(int base, int exponent, int mod) {
        int result = 1;
        while (exponent > 0) {
            result = (base * result) % mod;
            exponent--;
        }
        return result;
    }
    public List<Integer> getKeys(int q, int alpha, int xa, int xb) {
        int ya = power(alpha, xa, q);  // A's public key
        int yb = power(alpha, xb, q);  // B's public key

        // Shared keys from both sides (should be the same)
        List<Integer> keys = new ArrayList<>(2);
        keys.add(power(yb, xa, q));  // A's computation of shared key
        keys.add(power(ya, xb, q));  // B's computation of shared key

        return keys;
        //throw new UnsupportedOperationException("Not implemented yet.");
    }
}
