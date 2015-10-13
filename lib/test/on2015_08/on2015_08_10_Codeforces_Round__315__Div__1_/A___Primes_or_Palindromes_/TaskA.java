package on2015_08.on2015_08_10_Codeforces_Round__315__Div__1_.A___Primes_or_Palindromes_;



import net.egork.numbers.IntegerUtils;
import net.egork.numbers.Rational;
import net.egork.string.StringUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int p = in.readInt();
        int q = in.readInt();
        Rational threshold = new Rational(p, q);
        int prime = 0;
        int palin = 0;
        int limit = 1300000;
        boolean[] isPrime = IntegerUtils.generatePrimalityTable(limit);
        int answer = 0;
        for (int i = 1; i < limit; i++) {
            if (isPrime[i]) {
                prime++;
            }
            if (StringUtils.reverse(Integer.toString(i)).equals(Integer.toString(i))) {
                palin++;
            }
            if (threshold.compareTo(new Rational(prime, palin)) >= 0) {
                answer = i;
            }
        }
        out.printLine(answer);
    }
}
