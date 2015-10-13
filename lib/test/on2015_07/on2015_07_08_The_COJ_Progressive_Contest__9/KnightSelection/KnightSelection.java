package on2015_07.on2015_07_08_The_COJ_Progressive_Contest__9.KnightSelection;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class KnightSelection {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int[] primes = IntegerUtils.generatePrimes(count + 1);
        int answer = 0;
        for (int i = 0; i < primes.length; i++) {
            int pow = primes[i];
            while (pow <= count) {
                for (int j = i + 1; j < primes.length; j++) {
                    int current = pow * primes[j];
                    if (current > count) {
                        break;
                    }
                    while (current <= count) {
                        answer++;
                        current *= primes[j];
                    }
                }
                pow *= primes[i];
            }
        }
        out.printLine(answer);
    }
}
