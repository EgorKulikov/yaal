package on2015_05.on2015_05_24_Yandex_Algorithm_2015_Round_1.B_______________________;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int[] x = new int[count];
        int[] y = new int[count];
        int[] population = new int[count];
        IOUtils.readIntArrays(in, x, y, population);
        double[] angle = new double[count * (count - 1) / 2];
        int[] first = new int[angle.length];
        int[] second = new int[angle.length];
        int at = 0;
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < i; j++) {
                angle[at] = Math.atan2(y[j] - y[i], x[j] - x[i]) - Math.PI / 2;
                while (angle[at] < 1e-9) {
                    angle[at] += Math.PI;
                }
                first[at] = i;
                second[at++] = j;
            }
        }
        int[] aOrder = ArrayUtils.order(angle);
        int[] bOrder = ArrayUtils.createOrder(count);
        ArrayUtils.sort(bOrder, (a, b) -> x[a] != x[b] ? x[a] - x[b] : y[a] - y[b]);
        long left = 0;
        long right = ArrayUtils.sumArray(population);
        int startRight = 0;
        while (startRight < count && right - left > population[bOrder[startRight]]) {
            left += population[bOrder[startRight]];
            right -= population[bOrder[startRight]];
            startRight++;
        }
        int[] pos = ArrayUtils.reversePermutation(bOrder);
        long answer = Math.abs(left - right);
        for (int i : aOrder) {
            if (Math.abs(pos[first[i]] - pos[second[i]]) != 1) {
                throw new RuntimeException();
            }
            if (pos[first[i]] + pos[second[i]] == 2 * startRight - 1) {
                left -= population[bOrder[startRight - 1]];
                left += population[bOrder[startRight]];
                right += population[bOrder[startRight - 1]];
                right -= population[bOrder[startRight]];
            }
            int temp = pos[first[i]];
            pos[first[i]] = pos[second[i]];
            pos[second[i]] = temp;
            bOrder[pos[first[i]]] = first[i];
            bOrder[pos[second[i]]] = second[i];
            while (startRight < count && right - left > population[bOrder[startRight]]) {
                left += population[bOrder[startRight]];
                right -= population[bOrder[startRight]];
                startRight++;
            }
            while (startRight > 0 && left - right > population[bOrder[startRight - 1]]) {
                startRight--;
                left -= population[bOrder[startRight]];
                right += population[bOrder[startRight]];
            }
            answer = Math.min(answer, Math.abs(left - right));
        }
        out.printLine(answer);
    }
}
