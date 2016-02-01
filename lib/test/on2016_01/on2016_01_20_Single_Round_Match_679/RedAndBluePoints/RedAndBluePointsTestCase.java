package on2016_01.on2016_01_20_Single_Round_Match_679.RedAndBluePoints;



import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;
import net.egork.geometry.Point;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class RedAndBluePointsTestCase {
	Random r = new Random(239);

    @TestCase
    public Collection<NewTopCoderTest> createTests() {
		List<NewTopCoderTest> tests = new ArrayList<NewTopCoderTest>();
	    int[][] a = new int[4][50];
	    for (int i = 0; i < 4; i++) {
		    for (int j = 0; j < 50; j++) {
			    a[i][j] = r.nextInt(1001);
		    }
	    }
	    while (!check(a));
	    tests.add(createTest(null, a[0], a[1], a[2], a[3]));
        return tests;
    }

	private boolean check(int[][] a) {
		Point[] p = new Point[100];
		for (int i = 0; i < 50; i++) {
			p[i] = new Point(a[0][i], a[1][i]);
			p[i + 50] = new Point(a[2][i], a[3][i]);
		}
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < i; j++) {
				if (p[i].equals(p[j])) {
					change(a, i);
					return false;
				}
				for (int k = 0; k < j; k++) {
					if (p[i].line(p[j]).contains(p[k])) {
						change(a, i);
						return false;
					}
				}
			}
		}
		return true;
	}

	private void change(int[][] a, int i) {
		int x = 0;
		if (i >= 50) {
			x = 2;
			i -= 50;
		}
		a[x][i] = r.nextInt(1001);
		a[x + 1][i] = r.nextInt(1001);
	}

	private NewTopCoderTest createTest(Object answer, Object...arguments) {
		return new NewTopCoderTest(arguments, answer);
	}
}
