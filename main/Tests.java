import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

/**
 * @author egor@egork.net
 */
public class Tests {
//	private static File dir = new File("tests");
	private int curTest = 1;
	private static boolean verbose = true;

	private String testName;
	private PrintWriter out;
	private Random r = new Random(5516681322906813814L);


	public void run(int testSet) {
		if (testSet == 1) {
			generateTests1(100);
		}
		if (testSet == 2) {
			generateTests2(100000);
		}
		if (testSet == 3) {
			generateTests34(100000, 100000);
		}
		if (testSet == 4) {
			generateTests34(1000000, 1000000);
		}
	}

	private void generateTests1(int maxN) {
		a = new int[maxN];
		b = new int[maxN];
		queries = new int[maxN][];
		for (int i = 0; i < 3; i++) {
			generateSingleLengthQueries(maxN, maxN);
		}
		for (int i = 0; i < 3; i++) {
			n = maxN;
			bigSuite(n * n / 10, n);
		}
		n = r.nextInt(maxN) + 1;
		bigSuite(Math.max(n, n * n / 10), n);
	}

	private void generateTests2(int maxN) {
		a = new int[maxN];
		b = new int[maxN];
		queries = new int[1][];
		for (int i = 0; i < 3; i++) {
			n = maxN;
			bigSuite(n, 1);
		}
		n = r.nextInt(maxN) + 1;
		bigSuite(n, 1);
	}

	int n;
	int[] a;
	int[] b;
	int q;
	int[][] queries;

	private void generateTests34(int maxN, int maxQ) {
		a = new int[maxN];
		b = new int[maxN];
		queries = new int[maxQ][];
		for (int i = 0; i < 3; i++) {
			generateSingleLengthQueries(maxN, maxQ);
		}
		for (int i = 10; i < maxQ; i *= 10) {
			n = maxN;
			bigSuite(maxQ, i);
			if (i == 10) {
				n = r.nextInt(maxN) + 1;
				bigSuite(maxQ, i);
			}
		}
		n = maxN;
		bigSuite(maxQ, maxQ / 2);
		n = maxN;
		bigSuite(maxQ, 2);
	}

	private void bigSuite(int maxQ, int q) {
		Arrays.fill(a, 1);
		Arrays.fill(b, n);
		testSuite(n, maxQ, q);
		for (int k = 0; k < n; k++) {
			randomPair(k, n);
		}
		testSuite(n, maxQ, q);
	}

	private void testSuite(int n, int maxQ, int q) {
		generateFittingNonRandomLength(n, q, Math.min(maxQ / q, n));
		generateAnyNonRandomLength(n, q, Math.min(maxQ / q, n));
		generateFittingRandomLength(n, q, maxQ / q);
		generateAnyRandomLength(n, q, maxQ / q);
	}

	private void generateAnyNonRandomLength(int n, int q, int k) {
		this.q = q;
		for (int i = 0; i < q; i++) {
			queries[i] = new int[k];
			int max = Math.min(n / k * 2, n);
			for (int j = 0; j < queries[i].length; j++) {
				queries[i][j] = r.nextInt(max) + 1;
			}
		}
		test();
	}

	private void generateFittingNonRandomLength(int n, int q, int k) {
		this.q = q;
		for (int i = 0; i < q; i++) {
			queries[i] = new int[k];
			int max = Math.min(n / k * 2 - 1, n);
			if (k == n) {
				Arrays.fill(queries[i], 1);
			} else {
				while (true) {
					for (int j = 0; j < queries[i].length; j++) {
						queries[i][j] = r.nextInt(max) + 1;
					}
					long sum = 0;
					for (int j : queries[i]) {
						sum += j;
					}
					if (sum <= n) {
						break;
					}
				}
			}
		}
		test();
	}

	private void generateFittingRandomLength(int n, int q, int k) {
		this.q = q;
		for (int i = 0; i < q; i++) {
			queries[i] = new int[Math.min(n, r.nextInt(2 * k - 1) + 1)];
			int max = Math.min(n / queries[i].length * 2 - 1, n);
			if (queries[i].length * 2 >= n) {
				Arrays.fill(queries[i], 1);
			} else {
				while (true) {
					for (int j = 0; j < queries[i].length; j++) {
						queries[i][j] = r.nextInt(max) + 1;
					}
					long sum = 0;
					for (int j : queries[i]) {
						sum += j;
					}
					if (sum <= n) {
						break;
					}
				}
			}
		}
		test();
	}

	private void generateAnyRandomLength(int n, int q, int k) {
		this.q = q;
		for (int i = 0; i < q; i++) {
			queries[i] = new int[Math.min(n, r.nextInt(2 * k - 1) + 1)];
			int max = Math.min(n / queries[i].length * 2, n);
			for (int j = 0; j < queries[i].length; j++) {
				queries[i][j] = r.nextInt(max) + 1;
			}
		}
		test();
	}

	private void generateSingleLengthQueries(int maxN, int maxQ) {
		n = maxN;
		q = maxQ;
		for (int i = 0; i < maxN; i++) {
			randomPair(i, maxN);
		}
		for (int i = 0; i < maxQ; i++) {
			queries[i] = new int[1];
			queries[i][0] = r.nextInt(maxN) + 1;
		}
	}

	private void randomPair(int at, int maxN) {
		int x = r.nextInt(maxN) + 1;
		int y = r.nextInt(maxN) + 1;
		a[at] = Math.min(x, y);
		b[at] = Math.max(x, y);
	}

	private void test() {
		open();
		out.println(n);
		for (int i = 0; i < n; i++) {
			out.println(a[i] + " " + b[i]);
		}
		out.println(q);
		for (int i = 0; i < q; i++) {
			out.print(queries[i].length);
			for (int j : queries[i]) {
				out.print(" " + j);
			}
			out.println();
		}
		close();
	}

	private void open() {
		try {
			testName = "" + curTest;
			out = new PrintWriter(new File(testName));
			if (verbose) {
				System.out.print("Generating " + testName + "...");
			}
		} catch (IOException e) {
			throw new RuntimeException("Unable to open " + testName + " for writing", e);
		}
	}

	private void close() {
		out.close();
		if (verbose) {
			System.out.println(" done.");
		}
		curTest++;
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			throw new RuntimeException("Only parameter should be index of testset");
		}
		int testSet = Integer.parseInt(args[0]);
		Locale.setDefault(Locale.US);
//		dir.mkdirs();
		new Tests().run(testSet);
	}
}
