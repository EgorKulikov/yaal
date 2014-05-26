package on2014_05.on2014_05_23_Yandex_Algorithm_2014__Qualification__Test_run.C__________________;



import net.egork.collections.intervaltree.IntervalTree;
import net.egork.collections.intervaltree.LongIntervalTree;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] width = new int[count];
		int[] height = new int[count];
		IOUtils.readIntArrays(in, width, height);
		int[] cWidth = width.clone();
		int[] cHeight = height.clone();
		Arrays.sort(cWidth);
		Arrays.sort(cHeight);
		cWidth = ArrayUtils.unique(cWidth);
		cHeight = ArrayUtils.unique(cHeight);
		for (int i = 0; i < count; i++) {
			width[i] = Arrays.binarySearch(cWidth, width[i]);
			height[i] = Arrays.binarySearch(cHeight, height[i]) + 1;
		}
		IntervalTree tree = new LongIntervalTree(cWidth.length) {
			@Override
			protected long joinValue(long left, long right) {
				return Math.max(left, right);
			}

			@Override
			protected long joinDelta(long was, long delta) {
				return Math.max(was, delta);
			}

			@Override
			protected long accumulate(long value, long delta, int length) {
				return Math.max(value, delta);
			}

			@Override
			protected long neutralValue() {
				return 0;
			}

			@Override
			protected long neutralDelta() {
				return 0;
			}
		};
		for (int i = count - 1; i >= 0; i--) {
			if (tree.query(width[i], width[i]) >= height[i]) {
				out.printLine("Invisible paper detected");
				return;
			}
			tree.update(0, width[i], height[i]);
		}
		out.printLine("Well done");
    }
}
