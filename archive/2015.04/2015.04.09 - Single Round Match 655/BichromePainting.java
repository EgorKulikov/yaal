package net.egork;

public class BichromePainting {
    public String isThatPossible(String[] board, int k) {
        int size = board.length;
        char[][] map = new char[size][];
        for (int i = 0; i < size; i++) {
            map[i] = board[i].toCharArray();
        }
        int remaining = size * size;
        while (remaining > 0) {
            boolean updated = false;
            for (int i = 0; i <= size - k; i++) {
                for (int j = 0; j <= size - k; j++) {
                    boolean hasWhite = false;
                    boolean hasBlack = false;
                    for (int l = 0; l < k; l++) {
                        for (int m = 0; m < k; m++) {
                            if (map[i + l][j + m] == 'W') {
                                hasWhite = true;
                            }
                            if (map[i + l][j + m] == 'B') {
                                hasBlack = true;
                            }
                        }
                    }
                    if (hasBlack == hasWhite) {
                        continue;
                    }
                    updated = true;
                    for (int l = 0; l < k; l++) {
                        for (int m = 0; m < k; m++) {
                            if (map[i + l][j + m] != 'A') {
                                map[i + l][j + m] = 'A';
                                remaining--;
                            }
                        }
                    }
                }
            }
            if (!updated) {
                return "Impossible";
            }
        }
        return "Possible";
    }
}
