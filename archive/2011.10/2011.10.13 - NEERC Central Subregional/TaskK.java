import net.egork.io.IOUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class TaskK implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int commandSize = in.readInt();
		int count = in.readInt();
		int[] commandType = new int[128];
		long[] argument = new long[128];
		for (int i = 0; i < commandSize; i++) {
			int address = in.readInt();
			String command = in.readString();
			if ("ADD".equals(command)) {
				commandType[address] = 1;
				argument[address] = in.readLong();
			} else if ("SUB".equals(command)) {
				commandType[address] = 2;
				argument[address] = in.readLong();
			} else if ("NEG".equals(command))
				commandType[address] = 3;
			else if ("NOT".equals(command))
				commandType[address] = 4;
			else if ("CALL".equals(command)) {
				commandType[address] = 5;
				argument[address] = in.readLong();
			} else if ("RET".equals(command))
				commandType[address] = 6;

		}
		Map<Integer, CallResult> map = new HashMap<Integer, CallResult>();
		CallResult result = run(0, map, commandType, argument);
		long[] arguments = IOUtils.readLongArray(in, count);
		out.println(count);
		for (long curArgument : arguments)
			printValue(curArgument * result.sign + result.delta, out);
	}

	private void printValue(long value, PrintWriter out) {
		value = value & ((1L << 60) - 1);
		if (value >= (1L << 59))
			value = value - (1L << 60);
		out.println(value);
	}

	private CallResult run(int address, Map<Integer, CallResult> map, int[] commandType, long[] argument) {
		if (map.containsKey(address))
			return map.get(address);
		if (address == 128 || commandType[address] == 0)
			throw new IllegalStateException();
		CallResult result;
		if (commandType[address] == 6)
			result = new CallResult(1, 0);
		else
			result = combine(processCommand(commandType[address], argument[address], map, commandType, argument), run(address + 1, map, commandType, argument));
		map.put(address, result);
		return result;
	}

	private CallResult combine(CallResult first, CallResult second) {
		return new CallResult(first.sign * second.sign, first.delta * second.sign + second.delta);
	}

	private CallResult processCommand(int command, long argument, Map<Integer, CallResult> map, int[] commandType,
		long[] longs)
	{
		if (command == 1)
			return new CallResult(1, argument);
		if (command == 2)
			return new CallResult(-1, argument);
		if (command == 3)
			return new CallResult(-1, 0);
		if (command == 4)
			return new CallResult(-1, -1);
		if (command == 5)
			return run((int) argument, map, commandType, longs);
		throw new IllegalArgumentException();
	}
}

class CallResult {
	public final int sign;
	public final long delta;

	CallResult(int sign, long delta) {
		this.sign = sign;
		this.delta = delta;
	}
}
