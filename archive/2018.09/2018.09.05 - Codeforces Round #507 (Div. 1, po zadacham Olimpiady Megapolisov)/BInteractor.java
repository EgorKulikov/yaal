package net.egork;

import net.egork.chelper.tester.Verdict;
import net.egork.chelper.tester.State;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.io.InputStream;
import java.io.OutputStream;

public class BInteractor {
    public Verdict interact(InputStream input, InputStream solutionOutput, OutputStream solutionInput, State<Boolean> state) {
        InputReader in = new InputReader(input);
        InputReader solOut = new InputReader(solutionOutput);
        OutputWriter solInp = new OutputWriter(solutionInput);
        long n = in.readLong();
        int k = in.readInt();
        solInp.printLine(n);
        solInp.printLine(k);
        solInp.flush();
        for (int i = 0; i <= 4500; i++) {
            long l = solOut.readLong();
            long r = solOut.readLong();
            long p = in.readLong();
            if (l > r || l <= 0 || r > n) {
                solInp.printLine("Bad");
                solInp.flush();
                return new Verdict(Verdict.VerdictType.WA, "Incorrect move");
            }
            if (l <= p && p <= r) {
                solInp.printLine("Yes");
            } else {
                solInp.printLine(i == 4500 ? "Bad" : "No");
            }
            solInp.flush();
            if (l == p && r == p) {
                return Verdict.OK;
            }
        }
        return Verdict.WA;
    }
}
