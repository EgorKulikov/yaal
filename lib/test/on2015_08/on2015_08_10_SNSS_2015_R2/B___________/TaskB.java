package on2015_08.on2015_08_10_SNSS_2015_R2.B___________;



import net.egork.io.IOUtils;
import net.egork.string.SimpleStringHash;
import net.egork.string.StringHash;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int a = in.readInt();
//        int a = 2000;
        int b = in.readInt();
//        int b = 2000;
        int[][] field = IOUtils.readIntTable(in, a, b);
//        int[][] field = new int[2000][2000];
//        Random random = new Random(239);
//        for (int i = 0; i < 2000; i++) {
//            for (int j = 0; j < 2000; j++) {
//                field[i][j] = random.nextInt(2);
//            }
//        }
        StringHash[] horizontalDirect = new StringHash[a];
        for (int i = 0; i < a; i++) {
            StringBuilder current = new StringBuilder(b);
            for (int j = 0; j < b; j++) {
                current.append(field[i][j]);
            }
            horizontalDirect[i] = new SimpleStringHash(current.toString());
        }
        StringHash[] horizontalReverse = new StringHash[a];
        for (int i = 0; i < a; i++) {
            StringBuilder current = new StringBuilder(b);
            for (int j = b - 1; j >= 0; j--) {
                current.append(field[i][j]);
            }
            horizontalReverse[i] = new SimpleStringHash(current.toString());
        }
        StringHash[] verticalDirect = new StringHash[b];
        for (int i = 0; i < b; i++) {
            StringBuilder current = new StringBuilder(a);
            for (int j = 0; j < a; j++) {
                current.append(field[j][i]);
            }
            verticalDirect[i] = new SimpleStringHash(current.toString());
        }
        StringHash[] verticalReverse = new StringHash[b];
        for (int i = 0; i < b; i++) {
            StringBuilder current = new StringBuilder(a);
            for (int j = a - 1; j >= 0; j--) {
                current.append(field[j][i]);
            }
            verticalReverse[i] = new SimpleStringHash(current.toString());
        }
        int answer = 0;
        int row = 1;
        int column = 1;
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                int left = answer;
                int right = Math.min(Math.min(i, a - i - 1), Math.min(j, b - j - 1));
                while (left < right) {
                    int middle = (left + right + 1) >> 1;
                    long h1 = horizontalDirect[i].hash(j + 1, j + middle + 1);
                    long h2 = horizontalReverse[i].hash(b - j, b - j + middle);
                    long h3 = verticalDirect[j].hash(i + 1, i + middle + 1);
                    long h4 = verticalReverse[j].hash(a - i, a - i + middle);
                    if (h1 == h2 && h1 == h3 && h1 == h4) {
                        left = middle;
                    } else {
                        right = middle - 1;
                    }
                }
                if (left > answer) {
                    answer = left;
                    row = i + 1;
                    column = j + 1;
                }
            }
        }
        out.printLine(answer, row, column);
    }
}
