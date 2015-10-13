package on2015_10.on2015_10_04_Grand_Prix_of_Eurasia_2015.A___________;



import net.egork.collections.map.Indexer;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        n = 1 << n;
        String[] names = IOUtils.readStringArray(in, n);
        String results = in.readString();
        Indexer<String> indexer = new Indexer<>();
        int[] team = new int[2 * n - 1];
        int[] winner = new int[2 * n - 1];
        for (int i = 0; i < n; i++) {
            team[i] = i;
            indexer.get(names[i]);
        }
        int at = n;
        for (int j = 0; j < 2 * n - 2; j += 2) {
            winner[j] = winner[j + 1] = at;
            team[at++] = results.charAt(j >> 1) == 'W' ? team[j] : team[j + 1];
        }
        int q = in.readInt();
        for (int i = 0; i < q; i++) {
            String f = in.readString();
            String s = in.readString();
            int a = indexer.get(f);
            int b = indexer.get(s);
            int p1 = a;
            int p2 = b;
            while (p1 < 2 * n - 2 && (team[p1] == a || team[p2] == b) && p1 != p2) {
                p1 = winner[p1];
                p2 = winner[p2];
            }
            if (team[p1] == a) {
                out.printLine("Win");
            } else if (team[p2] == b) {
                out.printLine("Lose");
            } else {
                out.printLine("Unknown");
            }
        }
    }
}
