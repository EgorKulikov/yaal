package net.egork;

import static java.lang.Math.max;

public class SquareCutout {
    public int verify(String[] cutout) {
        int minRow = cutout.length;
        int minColumn = cutout[0].length();
        int maxRow = -1;
        int maxColumn = -1;
        for (int i = 0; i < cutout.length; i++) {
            for (int j = 0; j < cutout[i].length(); j++) {
                if (cutout[i].charAt(j) == '#') {
                    maxRow = max(maxRow, i);
                    maxColumn = max(maxColumn, j);
                    minRow = Math.min(minRow, i);
                    minColumn = Math.min(minColumn, j);
                }
            }
        }
        if (maxRow == -1) {
            return 1;
        }
        for (int i = 0; i < cutout.length; i++) {
            for (int j = 0; j < cutout[i].length(); j++) {
                char expected = (i >= minRow && i <= maxRow && j >= minColumn && j <= maxColumn) ? '#' : '.';
                if (expected != cutout[i].charAt(j)) {
                    return 0;
                }
            }
        }
        boolean vertical = minColumn == 0 || maxColumn == cutout[0].length() - 1;
        boolean horizontal = minRow == 0 || maxRow == cutout.length - 1;
        int height = maxRow - minRow + 1;
        int width = maxColumn - minColumn + 1;
        if (height == width) {
            return height;
        }
        if (!vertical && !horizontal) {
            return 0;
        }
        if (vertical && !horizontal && width > height) {
            return 0;
        }
        if (horizontal && !vertical && height > width) {
            return 0;
        }
        return max(height, width);
    }
}
