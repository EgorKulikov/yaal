package on2013_02.on2013_02_28_Codeforces_Round__170.A___Learning_Languages;



import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int languageCount = in.readInt();
		IntList[] knowLanguage = new IntList[languageCount];
		for (int i = 0; i < languageCount; i++)
			knowLanguage[i] = new IntArrayList();
		boolean atLeastOne = false;
		for (int i = 0; i < count; i++) {
			int currentCount = in.readInt();
			if (currentCount != 0)
				atLeastOne = true;
			for (int j = 0; j < currentCount; j++)
				knowLanguage[in.readInt() - 1].add(i);
		}
		IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(count);
		for (IntList language : knowLanguage) {
			for (int i : language.toArray()) {
				for (int j : language.toArray())
					setSystem.join(i, j);
			}
		}
		out.printLine(setSystem.getSetCount() - 1 + (!atLeastOne ? 1 : 0));
    }
}
