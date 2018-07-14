package net.egork;

import net.egork.chelper.tester.Verdict;
import net.egork.chelper.tester.State;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.io.InputStream;
import java.io.OutputStream;

public class TaskCInteractor {
    public Verdict interact(InputStream input, InputStream solutionOutput, OutputStream solutionInput, State<Boolean> state) {
        InputReader in = new InputReader(input);
        InputReader solOut = new InputReader(solutionOutput);
        OutputWriter solInp = new OutputWriter(solutionInput);
        long n = in.readLong();
        long a = in.readLong();
        long b = in.readLong();
        int tries = 0;
        solInp.printLine(n);
        solInp.flush();
        while (tries < 600) {
            tries++;
            long x = solOut.readLong();
            long y = solOut.readLong();
            if (x == a && y == b) {
                solInp.printLine(0);
                solInp.flush();
                return new Verdict(Verdict.VerdictType.OK, "Guessed in " + tries);
            }
            if (!state.getState()) {
                return new Verdict(Verdict.VerdictType.PE, "Solution ended without guess");
            }
            if (x > a || y > b) {
                solInp.printLine(3);
                solInp.flush();
            } else if (x < a) {
                solInp.printLine(1);
                solInp.flush();
            } else {
                solInp.printLine(2);
                solInp.flush();
            }
        }
        return new Verdict(Verdict.VerdictType.WA, "Too many attempts");
    }
}
