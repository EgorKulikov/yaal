package on2015_05.on2015_05_08_May_Challenge_2015.Chef_and_new_recipe;



import net.egork.misc.ArrayUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class ChefAndNewRecipe {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int[] items = in.readIntArray(count);
        int minElement = ArrayUtils.minElement(items);
        if (minElement < 2) {
            out.printLine(-1);
        } else {
            out.printLine(ArrayUtils.sumArray(items) - minElement + 2);
        }
    }
}
