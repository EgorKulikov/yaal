package net.egork;

import net.egork.chelper.tester.Verdict;
import net.egork.chelper.tester.State;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.io.InputStream;
import java.io.OutputStream;

import static java.util.Arrays.binarySearch;

public class GInteractor {
    public Verdict interact(InputStream input, InputStream solutionOutput, OutputStream solutionInput, State<Boolean> state) {
        InputReader in = new InputReader(input);
        InputReader solOut = new InputReader(solutionOutput);
        OutputWriter solInp = new OutputWriter(solutionInput);
        long x = in.readLong();
        for (int i = 0; i < 5; i++) {
            int k = solOut.readInt();
            if (k > x || k > 10000) {
                return new Verdict(Verdict.VerdictType.WA, "Request too long");
            }
            long[] t = solOut.readLongArray(k);
            for (int j = 1; j < k; j++) {
                if (t[j] <= t[j - 1]) {
                    return new Verdict(Verdict.VerdictType.WA, "Non monotonous array");
                }
            }
            if (t[0] < 1) {
                return new Verdict(Verdict.VerdictType.WA, "Non positive number");
            }
            if (t[k - 1] > 10004205361450474L) {
                return new Verdict(Verdict.VerdictType.WA, "Number too big");
            }
            int bs = binarySearch(t, x);
            if (bs >= 0) {
                solInp.printLine(-1);
                solInp.flush();
                return Verdict.OK;
            }
            solInp.printLine(-bs - 1);
            solInp.flush();
        }
        return Verdict.WA;
    }
}
