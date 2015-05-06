package on2015_05.on2015_05_04_Yandex_Algorithm_2015_Warm_Up.B___Disassemble_of_The_Cube;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int side = in.readInt();
        char[][][] cube = new char[side][][];
        for (int i = 0; i < side; i++) {
            cube[i] = IOUtils.readTable(in, side, side);
        }
        char[][][][] rotations = new char[3][side][side][side];
        for (int i = 0; i < side; i++) {
            for (int j = 0; j < side; j++) {
                for (int k = 0; k < side; k++) {
                    rotations[0][i][j][k] = cube[i][j][k];
                    rotations[1][i][j][k] = cube[j][k][i];
                    rotations[2][i][j][k] = cube[k][i][j];
                }
            }
        }
        int[] xQueue = new int[side * side * side];
        int[] yQueue = new int[side * side * side];
        int[] zQueue = new int[side * side * side];
        boolean[][][] processed = new boolean[side][side][side];
        for (char[][][] current : rotations) {
            for (int i = 0; i < side; i++) {
                for (int j = 0; j < side; j++) {
                    xQueue[0] = i;
                    yQueue[0] = j;
                    zQueue[0] = 0;
                    ArrayUtils.fill(processed, false);
                    processed[i][j][0] = true;
                    int size = 1;
                    for (int k = 0; k < size; k++) {
                        int x = xQueue[k];
                        int y = yQueue[k];
                        int z = zQueue[k];
                        for (int dx = -1; dx <= 1; dx++) {
                            for (int dy = -1; dy <= 1; dy++) {
                                for (int dz = -1; dz <= 1; dz++) {
                                    if (Math.abs(dx) + Math.abs(dy) + Math.abs(dz) != 1) {
                                        continue;
                                    }
                                    int nx = x + dx;
                                    int ny = y + dy;
                                    int nz = z + dz;
                                    if (nx < 0 || nx >= side || ny < 0 || ny >= side || nz < 0 || nz >= side || processed[nx][ny][nz]) {
                                        continue;
                                    }
                                    if (current[x][y][z] == current[nx][ny][nz] || dz == -1) {
                                        processed[nx][ny][nz] = true;
                                        xQueue[size] = nx;
                                        yQueue[size] = ny;
                                        zQueue[size++] = nz;
                                    }
                                }
                            }
                        }
                    }
                    if (size != side * side * side) {
                        out.printLine("No");
                        return;
                    }
                }
            }
        }
        out.printLine("Yes");
    }
}
