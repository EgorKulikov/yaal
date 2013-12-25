package on2013_04.on2013_04_15_Croc_Champ_Online_Round__1.C___Beautiful_IP_Addresses;



import net.egork.collections.set.EHashSet;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Set;

public class TaskC {
	Validator good = new Validator() {
		public boolean valid(int index, int[] values) {
			return true;
		}
	};
	Validator nonZero = new Validator() {
		public boolean valid(int index, int[] values) {
			return values[index] != 0;
		}
	};
	Validator[] special = new Validator[3];

	{
		for (int i = 0; i < 3; i++)
			special[i] = new SpecialValidator(-i);
	}

	Set<String> answer = new EHashSet<String>();

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int digitCount = in.readInt();
		int[] digits = IOUtils.readIntArray(in, digitCount);

		for (int i = 1; i <= 3; i++) {
			for (int j = 1; j <= 3; j++) {
				for (int k = 1; k <= 3; k++) {
					for (int l = 1; l <= 3; l++) {
						int total = i + j + k + l;
						boolean[] dotAfter = new boolean[total];
						dotAfter[i - 1] = true;
						dotAfter[i + j - 1] = true;
						dotAfter[i + j + k - 1] = true;
						Validator[] validators = new Validator[total];
						int current = addValidators(validators, 0, i);
						current = addValidators(validators, current, j);
						current = addValidators(validators, current, k);
						current = addValidators(validators, current, l);
						if (current != total)
							throw new RuntimeException();
						int[] result = new int[total];
						addAll(digits, dotAfter, validators, 0, total - 1, result, 0);
					}
				}
			}
		}
		out.printLine(answer.size());
		for (String s : answer)
			out.printLine(s);
    }

	private void addAll(int[] digits, boolean[] dotAfter, Validator[] validators, int from, int to, int[] result, int mask) {
		if (from > to) {
			if (mask == (1 << digits.length) - 1) {
				StringBuilder builder = new StringBuilder();
				for (int i = 0; i < result.length; i++) {
					builder.append(result[i]);
					if (dotAfter[i])
						builder.append('.');
				}
				answer.add(builder.toString());
			}
			return;
		}
		for (int i1 = 0; i1 < digits.length; i1++) {
			int i = digits[i1];
			result[from] = i;
			result[to] = i;
			if (validators[from].valid(from, result) && validators[to].valid(to, result))
				addAll(digits, dotAfter, validators, from + 1, to - 1, result, mask | (1 << i1));
			result[from] = 0;
			result[to] = 0;
		}
	}

	private int addValidators(Validator[] validators, int position, int toAdd) {
		if (toAdd == 1) {
			validators[position] = good;
			return position + 1;
		}
		if (toAdd == 2) {
			validators[position] = nonZero;
			validators[position + 1] = good;
			return position + 2;
		}
		for (int i = 0; i < 3; i++)
			validators[position + i] = special[i];
		return position + 3;
	}

	interface Validator {
		boolean valid(int index, int[] values);
	}

	class SpecialValidator implements Validator {
		final int delta;

		SpecialValidator(int delta) {
			this.delta = delta;
		}

		public boolean valid(int index, int[] values) {
			return (delta != 0 || values[index + delta] != 0) && values[index + delta] * 100 + values[index + delta + 1] * 10 + values[index + delta + 2] <= 255;
		}
	}
}
