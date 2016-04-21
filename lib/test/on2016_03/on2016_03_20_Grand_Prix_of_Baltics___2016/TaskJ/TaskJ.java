package on2016_03.on2016_03_20_Grand_Prix_of_Baltics___2016.TaskJ;



import net.egork.generated.collections.list.IntArrayList;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskJ {

    private int cur;
    private IntArrayList a;
    private CharVector[] res;

    static class CharVector {
        byte[] bytes = new byte[10];
        int size = 0;

        public void append(char c) {
            if (bytes.length == size) {
                byte[] nbytes = new byte[size * 3 / 2 + 1];
                System.arraycopy(bytes, 0, nbytes, 0, size);
                bytes = nbytes;
            }
            bytes[size++] = (byte) c;
        }

        public void append(int val) {
            String s = "" + val;
            for (int i = 0; i < s.length(); i++) {
                append(s.charAt(i));
            }
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        a = new IntArrayList();
        while (!in.isExhausted()) {
            a.add(in.readInt());
        }
        cur = 0;
        res = new CharVector[a.size()];
//        Node root = parse(0, (int)1e9);
//        root.calcWidth();
//        for (int i = 0; i < res.length; i++) {
//            res[i] = new StringBuilder();
//        }
//        print(root, 0, 0);
        parseAndPrint(0, (int)1e9, 0, 0);
        for (CharVector re : res) {
            if (re == null) {
                break;
            }
            for (int i = 0; i < re.size; i++) {
                out.print((char)re.bytes[i]);
            }
            out.printLine();
        }
    }

    private int parseAndPrint(int l, int r, int x, int y) {
        if (cur < a.size() && a.get(cur) >= l && a.get(cur) <= r) {
            if (res[y] == null) {
                res[y] = new CharVector();
            }
            while (res[y].size < x) {
                res[y].append(' ');
            }
            int val = a.get(cur);
            cur++;
            int lw = parseAndPrint(l, val - 1, x, y + 1);
            for (int i = 0; i < lw; i++) {
                res[y].append(' ');
            }
            res[y].append(val);
            int rw = parseAndPrint(val + 1, r, res[y].size, y + 1);
            return lw + rw + ("" + val).length();
        } else {
            return 0;
        }
    }
}
