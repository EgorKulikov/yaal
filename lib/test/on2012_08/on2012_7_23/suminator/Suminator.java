package on2012_08.on2012_7_23.suminator;



import net.egork.collections.sequence.Array;

import java.util.Stack;

public class Suminator {
	public int findMissing(int[] program, int wantedResult) {
		int position = Array.wrap(program).indexOf(-1);
		program[position] = 0;
		long result = evaluate(program);
		if (result == wantedResult)
			return 0;
		program[position] = 1;
		result = evaluate(program);
		if (result == wantedResult)
			return 1;
		if (result > wantedResult)
			return -1;
		program[position] = 2;
		long otherResult = evaluate(program);
		if (otherResult == result + 1)
			return (int) (wantedResult - result + 1);
		return -1;
	}

	private long evaluate(int[] program) {
		Stack<Long> stack = new Stack<Long>();
		for (int i : program) {
			if (i != 0)
				stack.add((long)i);
			else {
				long first = stack.isEmpty() ? 0 : stack.pop();
				long second = stack.isEmpty() ? 0 : stack.pop();
				stack.add(first + second);
			}
		}
		return stack.isEmpty() ? 0 : stack.peek();
	}


}

