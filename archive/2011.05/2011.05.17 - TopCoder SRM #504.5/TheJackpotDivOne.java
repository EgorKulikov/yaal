import java.math.BigInteger;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class TheJackpotDivOne {
	public long[] find(long[] money, long jackpot) {
		BigInteger sum = BigInteger.ZERO;
		Queue<Long> queue = new PriorityQueue<Long>();
		long maximum = 0;
		for (long person : money) {
			sum = sum.add(BigInteger.valueOf(person));
			queue.add(person);
			maximum = Math.max(maximum, person);
		}
		BigInteger length = BigInteger.valueOf(money.length);
		while (jackpot != 0 && queue.peek() < maximum) {
			long current = queue.poll();
			long upTo = sum.divide(length).longValue() + 1;
			long add = Math.min(upTo - current, jackpot);
			jackpot -= add;
			current += add;
			sum = sum.add(BigInteger.valueOf(add));
			queue.add(current);
		}
		long[] result = new long[money.length];
		for (int i = 0; i < result.length; i++)
			result[i] = queue.poll();
		Arrays.sort(result);
		for (int i = 0; i < result.length; i++) {
			long add = (jackpot + result.length - i - 1) / (result.length - i);
			result[i] += add;
			jackpot -= add;
		}
		Arrays.sort(result);
		return result;
	}

// BEGIN CUT HERE
   public static void main(String[] args) {
		if ( args.length == 0 ) {
			TheJackpotDivOneHarness.run_test(-1);
		} else {
			for ( int i=0; i<args.length; ++i )
				TheJackpotDivOneHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class TheJackpotDivOneHarness {
	public static void run_test( int casenum ) {
		if ( casenum != -1 ) {
			if ( runTestCase( casenum ) == -1 )
				System.err.println("Illegal input! Test case " + casenum + " does not exist.");
			return;
		}
		
		int correct = 0, total = 0;
		for ( int i=0;; ++i ) {
			int x = runTestCase(i);
			if ( x == -1 ) {
				if ( i >= 100 ) break;
				continue;
			}
			correct += x;
			++total;
		}
		
		if ( total == 0 ) {
			System.err.println("No test cases run.");
		} else if ( correct < total ) {
			System.err.println("Some cases FAILED (passed " + correct + " of " + total + ").");
		} else {
			System.err.println("All " + total + " tests passed!");
		}
	}
	
	static boolean compareOutput(long[] expected, long[] result) { if ( expected.length != result.length ) return false; for ( int i=0; i<expected.length; ++i ) if ( expected[i] != result[i] ) return false; return true; }

	static String formatResult(long[] res) {
		String ret = "";
		ret += "{";
		for (int i=0; i<res.length; ++i) {
			if (i > 0) ret += ",";
			ret += String.format(" %d", res[i]);
		}
		ret += " }";
		return ret;
	}
	
	static int verifyCase( int casenum, long[] expected, long[] received ) { 
		System.err.print("Example " + casenum + "... ");
		if ( compareOutput( expected, received ) ) {
			System.err.println("PASSED");
			return 1;
		} else {
			System.err.println("FAILED");
			System.err.println("    Expected: " + formatResult(expected)); 
			System.err.println("    Received: " + formatResult(received)); 
			return 0;
		}
	}

	static int runTestCase( int casenum ) {
		switch( casenum ) {
		case 0: {
			long[] money              = {1, 2, 3, 4};
			long jackpot              = 2L;
			long[] expected__         = {2, 3, 3, 4 };

			return verifyCase( casenum, expected__, new TheJackpotDivOne().find( money, jackpot ) );
		}
		case 1: {
			long[] money              = {4};
			long jackpot              = 7L;
			long[] expected__         = {11 };

			return verifyCase( casenum, expected__, new TheJackpotDivOne().find( money, jackpot ) );
		}
		case 2: {
			long[] money              = {4, 44, 7, 77};
			long jackpot              = 47L;
			long[] expected__         = {24, 34, 44, 77 };

			return verifyCase( casenum, expected__, new TheJackpotDivOne().find( money, jackpot ) );
		}
		case 3: {
			long[] money              = {4, 10, 8, 3, 6, 5, 8, 3, 7, 5};
			long jackpot              = 1000L;
			long[] expected__         = {105, 106, 106, 106, 106, 106, 106, 106, 106, 106 };

			return verifyCase( casenum, expected__, new TheJackpotDivOne().find( money, jackpot ) );
		}

		// custom cases

/*      case 4: {
			long[] money              = ;
			long jackpot              = L;
			long[] expected__         = ;

			return verifyCase( casenum, expected__, new TheJackpotDivOne().find( money, jackpot ) );
		}*/
/*      case 5: {
			long[] money              = ;
			long jackpot              = L;
			long[] expected__         = ;

			return verifyCase( casenum, expected__, new TheJackpotDivOne().find( money, jackpot ) );
		}*/
/*      case 6: {
			long[] money              = ;
			long jackpot              = L;
			long[] expected__         = ;

			return verifyCase( casenum, expected__, new TheJackpotDivOne().find( money, jackpot ) );
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE


