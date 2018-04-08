package on2018_04.on2018_04_07_Qualification_Round_2018.Cubic_UFO;



import net.egork.geometry.Point;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Math.*;
import static java.util.Arrays.fill;
import static net.egork.geometry.Polygon.convexHull;

public class CubicUFO {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        double a = in.readDouble();
        double left = 0;
        double right = atan(sqrt(2));
        double[] x = new double[8];
        double[] y = new double[8];
        double[] z = new double[8];
        x[0] = x[4] = sqrt(2) / 2;
        x[2] = x[6] = -sqrt(2) / 2;
        y[1] = y[5] = sqrt(2) / 2;
        y[3] = y[7] = -sqrt(2) / 2;
        fill(z, 0, 4, 0.5);
        fill(z, 4, 8, -0.5);
        double[] xx = new double[8];
        Point[] p = new Point[8];
        for (int i = 0; i < 200; i++) {
            double angle = (left + right) / 2;
            for (int j = 0; j < 8; j++) {
                xx[j] = cos(angle) * x[j] + sin(angle) * z[j];
                p[j] = new Point(xx[j], y[j]);
            }
            double current = convexHull(p).area();
            if (current > a) {
                right = angle;
            } else {
                left = angle;
            }
        }
        double angle = (left + right) / 2;
        for (int i = 0; i < 8; i++) {
            double x1 = cos(angle) * x[i] + sin(angle) * z[i];
            double z1 = cos(angle) * z[i] - sin(angle) * x[i];
            x[i] = x1;
            z[i] = z1;
        }
        double[] temp = y;
        y = z;
        z = temp;
        out.printLine("Case #" + testNumber + ":");
        out.printFormat("%.10f %.10f %.10f\n", (x[0] + x[2]) / 2, (y[0] + y[2]) / 2, (z[0] + z[2]) / 2);
        out.printFormat("%.10f %.10f %.10f\n", (x[0] + x[5]) / 2, (y[0] + y[5]) / 2, (z[0] + z[5]) / 2);
        out.printFormat("%.10f %.10f %.10f\n", (x[0] + x[7]) / 2, (y[0] + y[7]) / 2, (z[0] + z[7]) / 2);
    }
}
