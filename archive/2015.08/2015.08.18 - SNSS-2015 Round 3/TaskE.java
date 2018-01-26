package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskE {
    int[][] pos = new int[20][20];
    int[] near = new int[400];
    int[] far = new int[400];
    int qty = 0;

    {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j <= i; j++) {
                near[qty] = j;
                far[qty] = i;
                pos[i][j] = pos[j][i] = qty++;
            }
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        double p = in.readDouble();
        double[][] matrix = new double[qty][qty + 1];
        for (int i = 0; i < qty; i++) {
            matrix[i][i] = 1;
            int first = near[i] + 1;
            int second = far[i];
            if (first < 20) {
                matrix[i][pos[first][second]] += -p;
            }
            first = Math.max(near[i] - 2, 0);
            matrix[i][pos[first][second]] += p - 1;
            matrix[i][qty] = 1;
        }
        for (int i = 0; i < qty; i++) {
            double max = 0;
            int at = i;
            for (int j = i; j < qty; j++) {
                if (Math.abs(matrix[j][i]) > max) {
                    max = Math.abs(matrix[j][i]);
                    at = j;
                }
            }
            for (int j = i; j <= qty; j++) {
                double temp = matrix[at][j];
                matrix[at][j] = matrix[i][j];
                matrix[i][j] = temp;
            }
            for (int j = qty; j >= i; j--) {
                matrix[i][j] /= matrix[i][i];
            }
            for (int j = 0; j < qty; j++) {
                if (i == j) {
                    continue;
                }
                for (int k = qty; k >= i; k--) {
                    matrix[j][k] -= matrix[j][i] * matrix[i][k];
                }
            }
        }
        out.printLine(matrix[0][qty]);
    }
}
