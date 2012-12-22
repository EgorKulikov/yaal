public class CubePacking {
	public int getMinimumVolume(int Ns, int Nb, int L) {
		long volume = Nb * L * L * L + Ns;
		long answer = Nb * L * L * L + ((Ns + L * L - 1) / (L * L)) * (L * L);
		for (long a = L; a * a * a < answer; a++) {
			for (long b = a; a * b * b < answer; b++) {
				long baseL = (a / L) * (b / L);
				long base = a * b;
				long c = Math.max(((Nb + baseL - 1) / baseL) * L, (volume + base - 1) / base);
				answer = Math.min(answer, a * b * c);
			}
		}
		return (int) answer;
	}


// BEGIN CUT HERE
	public static void main(String[] args) {
		if (args.length == 0) {
			CubePackingHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				CubePackingHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class CubePackingHarness {
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
	
	static boolean compareOutput(int expected, int result) { return expected == result; }
	static String formatResult(int res) {
		return String.format("%d", res);
	}
	
	static int verifyCase(int casenum, int expected, int received) { 
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
			int Ns                    = 2;
			int Nb                    = 2;
			int L                     = 2;
			int expected__            = 20;

			return verifyCase(casenum, expected__, new CubePacking().getMinimumVolume(Ns, Nb, L));
		}
		case 1: {
			int Ns                    = 19;
			int Nb                    = 1;
			int L                     = 2;
			int expected__            = 27;

			return verifyCase(casenum, expected__, new CubePacking().getMinimumVolume(Ns, Nb, L));
		}
		case 2: {
			int Ns                    = 51;
			int Nb                    = 7;
			int L                     = 5;
			int expected__            = 950;

			return verifyCase(casenum, expected__, new CubePacking().getMinimumVolume(Ns, Nb, L));
		}
		case 3: {
			int Ns                    = 12345;
			int Nb                    = 987;
			int L                     = 10;
			int expected__            = 999400;

			return verifyCase(casenum, expected__, new CubePacking().getMinimumVolume(Ns, Nb, L));
		}

		// custom cases

/*      case 4: {
			int Ns                    = ;
			int Nb                    = ;
			int L                     = ;
			int expected__            = ;

			return verifyCase(casenum, expected__, new CubePacking().getMinimumVolume(Ns, Nb, L));
		}*/
/*      case 5: {
			int Ns                    = ;
			int Nb                    = ;
			int L                     = ;
			int expected__            = ;

			return verifyCase(casenum, expected__, new CubePacking().getMinimumVolume(Ns, Nb, L));
		}*/
/*      case 6: {
			int Ns                    = ;
			int Nb                    = ;
			int L                     = ;
			int expected__            = ;

			return verifyCase(casenum, expected__, new CubePacking().getMinimumVolume(Ns, Nb, L));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE


