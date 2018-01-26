package on2016_12.on2016_12_30_Good_Bye_2016.D___New_Year_and_Fireworks;



import net.egork.collections.set.EHashSet;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.Set;

import static net.egork.misc.ArrayUtils.count;
import static net.egork.misc.MiscUtils.DX8;
import static net.egork.misc.MiscUtils.DY8;

public class TaskD {
    static class Cell {
        int x;
        int y;
        int dir;

        public Cell(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Cell cell = (Cell) o;

            if (x != cell.x) {
                return false;
            }
            if (y != cell.y) {
                return false;
            }
            return dir == cell.dir;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            result = 31 * result + dir;
            return result;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] t = in.readIntArray(n);
        Set<Cell> current = new EHashSet<>();
        boolean[][] used = new boolean[401][401];
        current.add(new Cell(200, 200, 1));
        for (int i : t) {
            Set<Cell> next = new EHashSet<>();
            for (Cell cell : current) {
                int x = cell.x;
                int y = cell.y;
                for (int j = 0; j < i; j++) {
                    x += DX8[cell.dir];
                    y += DY8[cell.dir];
                    used[x][y] = true;
                }
                next.add(new Cell(x, y, (cell.dir + 1) & 7));
                next.add(new Cell(x, y, (cell.dir + 7) & 7));
            }
            current = next;
        }
        int answer = 0;
        for (boolean[] row : used) {
            answer += count(row, true);
        }
        out.printLine(answer);
    }
}
