package on2018_04.on2018_04_07_Qualification_Round_2018.Cubic_UFO;



import net.egork.chelper.checkers.Checker;
import net.egork.chelper.tester.Verdict;
import net.egork.geometry.Point;
import net.egork.io.InputReader;

import java.io.StringBufferInputStream;
import java.util.InputMismatchException;

import static java.lang.Math.abs;
import static java.lang.Math.acos;
import static java.lang.Math.hypot;
import static net.egork.geometry.Polygon.convexHull;


public class CubicUFOChecker implements Checker {
    public CubicUFOChecker(String parameters) {
    }

    public Verdict check(String input, String expectedOutput, String actualOutput) {
        InputReader in = new InputReader(new StringBufferInputStream(input));
        InputReader expected;
        if (expectedOutput == null)
            expected = null;
        else
            expected = new InputReader(new StringBufferInputStream(expectedOutput));
        InputReader actual = new InputReader(new StringBufferInputStream(actualOutput));
		try {
			return check(in, expected, actual);
		} catch (InputMismatchException e) {
			return new Verdict(Verdict.VerdictType.PE, e.getMessage());
		}
    }

    public Verdict check(InputReader in, InputReader expected, InputReader actual) {
        int t = in.readInt();
        for (int i = 0; i < t; i++) {
            double a = in.readDouble();
            actual.readLine();
            double[] x = new double[3];
            double[] y = new double[3];
            double[] z = new double[3];
            actual.readDoubleArrays(x, z, y);
            for (int j = 0; j < 3; j++) {
                if (abs(hypot(hypot(y[j], z[j]), x[j]) - 0.5) > 1e-6) {
                    return new Verdict(Verdict.VerdictType.WA, "Length incorrect");
                }
                for (int k = 0; k < j; k++) {
                    double angle = x[j] * x[k] + y[j] * y[k] + z[j] * z[k];
                    angle /= 0.5;
                    angle /= 0.5;
                    angle = acos(angle);
                    if (abs(angle - Math.PI / 2) > 1e-6) {
                        return new Verdict(Verdict.VerdictType.WA, "Angle incorrect");
                    }
                }
            }
            Point[] p = new Point[8];
            int[] c = new int[3];
            for (int j = 0; j < 8; j++) {
                double xx = 0;
                double yy = 0;
                for (int k = 0; k < 3; k++) {
                    c[k] = (j >> k & 1) == 0 ? 1 : -1;
                }
                for (int k = 0; k < 3; k++) {
                    xx += x[k] * c[k];
                    yy += y[k] * c[k];
                }
                p[j] = new Point(xx, yy);
            }
            double result = convexHull(p).area();
            if (abs(result - a) > 1e-6) {
                return new Verdict(Verdict.VerdictType.WA, "Got " + result + ", must be " + a);
            }
        }
        return Verdict.OK;
    }
}
