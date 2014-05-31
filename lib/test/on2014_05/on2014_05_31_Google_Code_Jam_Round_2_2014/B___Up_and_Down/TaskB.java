package on2014_05.on2014_05_31_Google_Code_Jam_Round_2_2014.B___Up_and_Down;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] array = IOUtils.readIntArray(in, count);
		int answer = 0;
		int left = 0;
		int right = count - 1;
		for (int i = 0; i < count; i++) {
			int current = ArrayUtils.minPosition(array, left, right + 1);
			if (current - left > right - current) {
				answer += right - current;
				for (int j = current; j < right; j++) {
					int temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
				right--;
			} else {
				answer += current - left;
				for (int j = current; j > left; j--) {
					int temp = array[j];
					array[j] = array[j - 1];
					array[j - 1] = temp;
				}
				left++;
			}
		}
		out.printLine("Case #" + testNumber + ":", answer);
    }
}
