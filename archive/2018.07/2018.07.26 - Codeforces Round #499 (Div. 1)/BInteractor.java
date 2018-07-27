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
        int m = in.readInt();
        int n = in.readInt();
        int x = in.readInt();
        int[] p = in.readIntArray(n);
        solInp.printLine(m, n);
        solInp.flush();
        for (int i = 0; i < 60; i++) {
            int d = solOut.readInt();
            if (d == x) {
                solInp.printLine(0);
                return Verdict.OK;
            }
            solInp.printLine(Integer.compare(x, d) * (2 * p[i % n] - 1));
            solInp.flush();
        }
        return Verdict.WA;
    }
}
