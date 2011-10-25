import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskE implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int count = in.readInt();
		String[] poem = new String[count];
		for (int i = 0; i < count; i++)
			poem[i] = in.readLine().replace('-', ' ');
		String size = determineSize(poem[0]);
		for (int i = 1; i < poem.length; i++) {
			if (!size.equals(determineSize(poem[i])))
				size = "not a poem";
		}
		out.println(size);
	}

	private String determineSize(String s) {
		String[] tokens = s.split(" ");
		boolean isTrochee = true;
		boolean isIamb = true;
		boolean isDactyl = true;
		boolean isAmphibrach = true;
		boolean isAnapest = true;
		for (int i = 0; i < tokens.length; i++) {
			if (tokens[i].charAt(0) == '[') {
				if (i % 2 == 0)
					isIamb = false;
				else
					isTrochee = false;
				if (i % 3 == 0) {
					isAmphibrach = false;
					isAnapest = false;
				} else if (i % 3 == 1) {
					isDactyl = false;
					isAnapest = false;
				} else {
					isDactyl = false;
					isAmphibrach = false;
				}
			} else {
				if (i % 2 == 0)
					isTrochee = false;
				else
					isIamb = false;
				if (i % 3 == 0)
					isDactyl = false;
				else if (i % 3 == 1)
					isAmphibrach = false;
				else
					isAnapest = false;
			}
		}
		if (isTrochee)
			return "trochee";
		if (isIamb)
			return "iamb";
		if (isDactyl)
			return "dactyl";
		if (isAmphibrach)
			return "amphibrach";
		if (isAnapest)
			return "anapaest";
		return "not a poem";
	}
}

