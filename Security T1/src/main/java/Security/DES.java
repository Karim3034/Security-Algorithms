package Security;

import java.math.BigInteger;
import java.util.*;

public class DES {

    // Initial Permutation Table
    private static final int[] IP = {58, 50, 42, 34, 26, 18, 10, 2, 60, 52, 44, 36, 28, 20, 12, 4, 62, 54, 46, 38, 30, 22, 14, 6, 64, 56, 48, 40, 32, 24, 16, 8, 57, 49, 41, 33, 25, 17, 9, 1, 59, 51, 43, 35, 27, 19, 11, 3, 61, 53, 45, 37, 29, 21, 13, 5, 63, 55, 47, 39, 31, 23, 15, 7};

    // Final Permutation Table
    private static final int[] FP = {40, 8, 48, 16, 56, 24, 64, 32, 39, 7, 47, 15, 55, 23, 63, 31, 38, 6, 46, 14, 54, 22, 62, 30, 37, 5, 45, 13, 53, 21, 61, 29, 36, 4, 44, 12, 52, 20, 60, 28, 35, 3, 43, 11, 51, 19, 59, 27, 34, 2, 42, 10, 50, 18, 58, 26, 33, 1, 41, 9, 49, 17, 57, 25};

    // Expansion (E) Table
    private static final int[] E = {32, 1, 2, 3, 4, 5, 4, 5, 6, 7, 8, 9, 8, 9, 10, 11, 12, 13, 12, 13, 14, 15, 16, 17, 16, 17, 18, 19, 20, 21, 20, 21, 22, 23, 24, 25, 24, 25, 26, 27, 28, 29, 28, 29, 30, 31, 32, 1};

    // Permutation (P) Table
    private static final int[] P = {16, 7, 20, 21, 29, 12, 28, 17, 1, 15, 23, 26, 5, 18, 31, 10, 2, 8, 24, 14, 32, 27, 3, 9, 19, 13, 30, 6, 22, 11, 4, 25};

    // S-boxes (8 of them)
    private static final int[][][] SBOXES = {
            {{14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7}, {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8}, {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0}, {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}}
            , {{15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10}, {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5}, {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15}, {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9}}
            , {{10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8}, {13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1}, {13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7}, {1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}}
            , {{7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15}, {13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9}, {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4}, {3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14}},
            {{2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9}, {14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6}, {4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14}, {11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3}},
            {{12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11}, {10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8}, {9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6}, {4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13}},
            {{4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1}, {13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6}, {1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2}, {6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}},
            {{13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7}, {1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2}, {7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8}, {2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}}};

    // Shift schedule for key halves
    private static final int[] SHIFTS = {1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};

    private static final int[] PC1 = {57, 49, 41, 33, 25, 17, 9, 1, 58, 50, 42, 34, 26, 18, 10, 2, 59, 51, 43, 35, 27, 19, 11, 3, 60, 52, 44, 36, 63, 55, 47, 39, 31, 23, 15, 7, 62, 54, 46, 38, 30, 22, 14, 6, 61, 53, 45, 37, 29, 21, 13, 5, 28, 20, 12, 4};

    private static final int[] PC2 = {14, 17, 11, 24, 1, 5, 3, 28, 15, 6, 21, 10, 23, 19, 12, 4, 26, 8, 16, 7, 27, 20, 13, 2, 41, 52, 31, 37, 47, 55, 30, 40, 51, 45, 33, 48, 44, 49, 39, 56, 34, 53, 46, 42, 50, 36, 29, 32};

    private static String hexToBinary(String hex) {
        // Convert the hex string to a BigInteger
        BigInteger bigInt = new BigInteger(hex, 16);

        // Convert BigInteger to binary string and ensure it is 64 bits long
        String binaryString = bigInt.toString(2);

        // Pad the binary string with leading zeros to ensure it is 64 bits
        while (binaryString.length() < 64) {
            binaryString = "0" + binaryString;
        }

        return binaryString;
    }


    // Converts a binary string to a hex string
    private static String binaryToHex(String binary) {
        // TODO: Implement this method to convert a binary string to a hexadecimal string.
        return String.format("%016X",new BigInteger(binary,2)); // Placeholder return statement
    }

    // Generic permutation function
    private static String permute(String input, int[] table) {
        StringBuilder output = new StringBuilder();
        for (int index : table) {
            output.append(input.charAt(index - 1));
        }
        return output.toString();
    }

    // Single left circular shift
    private static String leftShift(String half, int shifts) {
        shifts = shifts % half.length();  // Safe shift
        return half.substring(shifts) + half.substring(0, shifts);
    }


    public String encrypt(String plainTextHex, String keyHex) {
        String plainBin = hexToBinary(plainTextHex);
        String keyBin = hexToBinary(keyHex);

        // Step 1: Initial Permutation
        String permuted = permute(plainBin, IP);

        // Step 2: Split into L and R
        String L = permuted.substring(0, 32);
        String R = permuted.substring(32);

        // Step 3: Generate subkeys
        String[] subkeys = generateSubKeys(keyBin);
        String feis = "";
        // Step 4: 16 Rounds
        for (int i = 0; i < 16; i++) {
            String temp = R;
            feis = feistel(R,subkeys[i]);
            R = xor(L, feis);
            L = temp;
        }

        // Step 5: Final Permutation
        String combined = R + L;
        String cipherBin = permute(combined, FP);
        return binaryToHex(cipherBin);
    }

    public String decrypt(String cipherTextHex, String keyHex) {
        String cipherBin = hexToBinary(cipherTextHex);
        String keyBin = hexToBinary(keyHex);
        String permuted = permute(cipherBin, IP);
        String L = permuted.substring(0, 32);
        String R = permuted.substring(32);

        String[] subkeys = generateSubKeys(keyBin);
        String feis;
        for (int i = 15; i >= 0; i--) {
            String temp = R;
            feis = feistel(R, subkeys[i]);
            R = xor(L, feis);
            L = temp;
        }

        String combined = R + L;
        String plainBin = permute(combined, FP);
        return binaryToHex(plainBin);
    }

    // Performs bitwise XOR on two binary strings of equal length
    private String xor(String a, String b) {
        // TODO: Implement bitwise XOR between strings 'a' and 'b'.
        if (a.length() != b.length()) {
            throw new IllegalArgumentException("Strings must be of equal length for XOR operation.");
        }

        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<a.length();i++){
            stringBuilder.append((a.charAt(i)-'0')^(b.charAt(i)-'0'));
        }
        return stringBuilder.toString(); // Placeholder return
    }

    // Generate the subkeys (PC-1, shifts, PC-2)
    private static String[] generateSubKeys(String keyBin) {
        String[] subkeys = new String[16];
        // TODO: Apply PC-1 to the 64-bit key to get a 56-bit key
        String res = permute(keyBin,PC1);
        // TODO: Split the 56-bit key into two halves
        String L = res.substring(0, 28);
        String R = res.substring(28);
        // TODO: Generate 16 subkeys by shifting and applying PC-2
        for (int i = 0; i < 16; i++) {
            L =leftShift(L, SHIFTS[i]);
            R = leftShift(R, SHIFTS[i]);
            res = L + R;
            subkeys[i] = permute(res, PC2);
        }
        return subkeys;
    }


    // Feistel function
    private String feistel(String R, String subKey) {
        // TODO: Expand R to 48 bits using E-table
        String res = permute(R,E);
        // TODO: XOR the expanded R with the subkey
        res = xor(res,subKey);
        // TODO: Divide the xored result into 8 blocks and apply S-box substitution
        StringBuilder substituted = new StringBuilder();
        int row=0,col=0;
        StringBuilder conc = new StringBuilder();
        int idx = 0;
        StringBuilder val = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            idx = i*6;
            conc.append(res.charAt(idx));
            conc.append(res.charAt(5+idx));
            row = Integer.parseInt(conc.toString(),2);
            col = Integer.parseInt(res.substring(1+idx,5+idx),2);
            val = new StringBuilder(Integer.toBinaryString(Integer.parseInt(String.valueOf(SBOXES[i][row][col]))));
            while(val.length()<4){
                val.insert(0,'0');
            }
            substituted.append(val);
            conc = new StringBuilder();
        }
        // TODO: Apply permutation P to the substituted output
        return permute(String.valueOf(substituted),P);
    }

}
