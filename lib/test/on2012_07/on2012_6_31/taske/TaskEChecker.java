package on2012_07.on2012_6_31.taske;



import net.egork.chelper.task.Test;
import net.egork.chelper.tester.Verdict;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.io.StringWriter;
import java.util.*;

public class TaskEChecker {
	public Verdict check(InputReader input, InputReader expected, InputReader actual) {
		return Verdict.UNDECIDED;
	}

	public double getCertainty() {
		return 0;
	}

	public Collection<? extends Test> generateTests() {
        StringWriter sw = new StringWriter();
        OutputWriter out = new OutputWriter(sw);
        Random random = new Random(239);
        out.printLine(100000, 200000);
        List<Integer> sample = new ArrayList<Integer>(100000);
        for (int i = 1; i <= 100000; i++)
            sample.add(i);
        Collections.shuffle(sample, random);
        out.printLine(sample.toArray());
        List<Integer> permutation = new ArrayList<Integer>(200000);
        for (int i = 1; i <= 200000; i++)
            permutation.add(i);
        Collections.shuffle(permutation, random);
        out.printLine(permutation.toArray());
        return Collections.singleton(new Test(sw.toString(), "0", 0));
	}
}
