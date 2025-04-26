package Security;
import java.util.*;

public class ColumnarCipher {

    public static char[][] encToAnalyse(String plainText, List<Integer> key) {
        int cols = key.size();
        int rows = plainText.length() / cols;

        if (plainText.length() % cols != 0) {
            rows++;
        }

        char[][] matrix = new char[rows][cols];
        int count = 0;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (count < plainText.length()) {
                    matrix[row][col] = plainText.charAt(count);
                    count++;
                }
                else {
                    matrix[row][col] = 'x'; // Fill with 'X' if the plainText is shorter than the matrix size
                }
            }
        }
        return matrix;
    }
    public List<Integer> analyse(String plainText, String cipherText) {
        // TODO: Analyze the plainText and cipherText to determine the key(s)
        plainText = plainText.toLowerCase();
        cipherText = cipherText.toLowerCase();

        boolean flag = false;
        int rows = 0, cols = 0, iterator;
        ArrayList<Integer> key = new ArrayList<>();
        char[][] matrix = new char[plainText.length()][plainText.length()];
        for (int i = 1; i < plainText.length(); i++) {
            key.add(i);
            matrix = encToAnalyse(plainText, key);
           
           
            rows = matrix.length;
            cols = matrix[0].length;

            // Check if this matrix is the right matrix or not
            for (int col = 0; col < cols; col++) {
                iterator = 0;
                for (int row = 0; row < rows; row++) {
                    if (iterator < cipherText.length() &&
                        matrix[row][col] == cipherText.charAt(iterator)) {
                        iterator++;
                    }
                }
                if (iterator == rows) {
                    flag = true;
                    break;
                }
            }
            if (flag)
                break;
        }


        for (int col = 0; col < cols; col++) {
            iterator = 0;
            int counter = 0;

            while (iterator < cipherText.length()) {
                for (int row = 0; row < rows; row++) {
                    if (iterator < cipherText.length() &&
                    matrix[row][col] == cipherText.charAt(iterator)) {
                        counter++;
                    } else {
                        counter = 0;
                    }

                    iterator++;

                    if (counter == rows) {
                        break;
                    }
                }
                if (counter == rows) {
                    break;
                }
            }
            key.set(col, (iterator/ rows));
        }

        return key;
    }

    public String decrypt(String cipherText, List<Integer> key) {
        int cipherSize = cipherText.length();
        int rows = (int) Math.ceil((double) cipherSize / key.size());
        char[][] grid = new char[rows][key.size()];
        int count = 0;

        Map<Integer, Integer> keyMap = new HashMap<>();
        for (int i = 0; i < key.size(); i++) {
            keyMap.put(key.get(i) - 1, i);
        }

        int remainingCols = cipherSize % key.size();
        for (int i = 0; i < key.size(); i++) {
            for (int j = 0; j < rows; j++) {
                if (remainingCols != 0 && j == rows - 1 && keyMap.get(i) >= remainingCols) continue;
                grid[j][keyMap.get(i)] = cipherText.charAt(count++);
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < key.size(); j++) {
                result.append(grid[i][j]);
            }
        }
        return result.toString().toUpperCase().trim();
    }

    public String encrypt(String plainText, List<Integer> key) {
        int ptSize = plainText.length();
        int rows = (int) Math.ceil((double) ptSize / key.size());
        char[][] grid = new char[rows][key.size()];
        int count = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < key.size(); j++) {
                if (count >= ptSize) {
                    grid[i][j] = 'x';
                } else {
                    grid[i][j] = plainText.charAt(count++);
                }
            }
        }

        Map<Integer, Integer> keyMap = new HashMap<>();
        for (int i = 0; i < key.size(); i++) {
            keyMap.put(key.get(i) - 1, i);
        }

        StringBuilder cipherText = new StringBuilder();
        for (int i = 0; i < key.size(); i++) {
            for (int j = 0; j < rows; j++) {
                cipherText.append(Character.toUpperCase(grid[j][keyMap.get(i)]));
            }
        }
        return cipherText.toString();
    }
}
