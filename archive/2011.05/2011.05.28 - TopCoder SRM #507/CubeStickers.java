import net.egork.collections.map.CPPMap;
import net.egork.misc.Factory;

import java.util.Map;

public class CubeStickers {
	public String isPossible(String[] sticker) {
		Map<String, Integer> map = new CPPMap<String, Integer>(new Factory<Integer>() {
			public Integer create() {
				return 0;
			}
		});
		for (String color : sticker)
			map.put(color, Math.min(2, map.get(color) + 1));
		int total = 0;
		for (int value : map.values())
			total += value;
		return total >= 6 ? "YES" : "NO";
	}


// BEGIN CUT HERE
	public static void main(String[] args) {
		if (args.length == 0) {
			CubeStickersHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				CubeStickersHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class CubeStickersHarness {
	public static void run_test(int casenum) {
		if (casenum != -1) {
			if (runTestCase(casenum) == -1)
				System.err.println("Illegal input! Test case " + casenum + " does not exist.");
			return;
		}
		
		int correct = 0, total = 0;
		for (int i=0;; ++i) {
			int x = runTestCase(i);
			if (x == -1) {
				if (i >= 100) break;
				continue;
			}
			correct += x;
			++total;
		}
		
		if (total == 0) {
			System.err.println("No test cases run.");
		} else if (correct < total) {
			System.err.println("Some cases FAILED (passed " + correct + " of " + total + ").");
		} else {
			System.err.println("All " + total + " tests passed!");
		}
	}
	
	static boolean compareOutput(String expected, String result) { return expected.equals(result); }
	static String formatResult(String res) {
		return String.format("\"%s\"", res);
	}
	
	static int verifyCase(int casenum, String expected, String received) { 
		System.err.print("Example " + casenum + "... ");
		if (compareOutput(expected, received)) {
			System.err.println("PASSED");
			return 1;
		} else {
			System.err.println("FAILED");
			System.err.println("    Expected: " + formatResult(expected)); 
			System.err.println("    Received: " + formatResult(received)); 
			return 0;
		}
	}

	static int runTestCase(int casenum) {
		switch(casenum) {
		case 0: {
			String[] sticker          = {"cyan","magenta","yellow","purple","black","white","purple"};
			String expected__         = "YES";

			return verifyCase(casenum, expected__, new CubeStickers().isPossible(sticker));
		}
		case 1: {
			String[] sticker          = {"blue","blue","blue","blue","blue","blue","blue","blue","blue","blue"};
			String expected__         = "NO";

			return verifyCase(casenum, expected__, new CubeStickers().isPossible(sticker));
		}
		case 2: {
			String[] sticker          = {"red","yellow","blue","red","yellow","blue","red","yellow","blue"};
			String expected__         = "YES";

			return verifyCase(casenum, expected__, new CubeStickers().isPossible(sticker));
		}
		case 3: {
			String[] sticker          = {"purple","orange","orange","purple","black","orange","purple"};
			String expected__         = "NO";

			return verifyCase(casenum, expected__, new CubeStickers().isPossible(sticker));
		}
		case 4: {
			String[] sticker          = {"white","gray","green","blue","yellow","red","target","admin"};
			String expected__         = "YES";

			return verifyCase(casenum, expected__, new CubeStickers().isPossible(sticker));
		}

		// custom cases

/*      case 5: {
			String[] sticker          = ;
			String expected__         = ;

			return verifyCase(casenum, expected__, new CubeStickers().isPossible(sticker));
		}*/
/*      case 6: {
			String[] sticker          = ;
			String expected__         = ;

			return verifyCase(casenum, expected__, new CubeStickers().isPossible(sticker));
		}*/
/*      case 7: {
			String[] sticker          = ;
			String expected__         = ;

			return verifyCase(casenum, expected__, new CubeStickers().isPossible(sticker));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE


