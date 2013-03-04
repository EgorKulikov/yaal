package on2012_04.on2012_3_14.taska;



import net.egork.collections.sequence.Array;
import net.egork.collections.sequence.StringWrapper;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	char[] original = "ejpmysljylckdkxveddknmcrejsicpdrysirbcpcypcrtcsradkhwyfrepkymveddknkmkrkcddekrkdeoyakwaejtysrreujdrlkgcjvyeq ".toCharArray();
	char[] result = "ourlanguageisimpossibletounderstandtherearetwentysixfactorialpossibilitiessoitisokayifyouwanttojustgiveupaoz ".toCharArray();
	char[] map = new char[256];

	{
		for (int i = 0; i < original.length; i++)
			map[original[i]] = result[i];
		for (int i = 0; i < 26; i++) {
			if (map[i + 'a'] == 0) {
				for (int j = 0; j < 26; j++) {
					if (Array.wrap(result).indexOf((char)(j + 'a')) == -1) {
						map[i + 'a'] = (char) (j + 'a');
						break;
					}
				}
			}
		}
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		String s = in.readLine();
		out.print("Case #" + testNumber + ": ");
		for (char c : StringWrapper.wrap(s))
			out.print(map[c]);
		out.printLine();
	}
}
