import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;

import static java.lang.Math.abs;
import static java.lang.Math.max;
import static net.egork.misc.ArrayUtils.orderBy;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author Egor Kulikov (egor@egork.net)
 */
public class Main {
    private static String[] files = {
//            "a_example.in", "a1.out",
            "b_short_walk.in", "b1.out",
//            "c_going_green.in", "c1.out",
//            "d_wide_selection.in", "d1.out",
//            "e_precise_fit.in", "e1.out",
//            "f_different_footprints.in", "f1.out",
    };

    public static void main(String[] args) {
        for (int i = 0; i < files.length; i += 2) {
            System.err.println(files[0]);
            InputStream inputStream;
            try {
                inputStream = new FileInputStream(files[i]);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            OutputStream outputStream;
            try {
                outputStream = new FileOutputStream(files[i + 1]);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            InputReader in = new InputReader(inputStream);
            OutputWriter out = new OutputWriter(outputStream);
            Checker solver = new Checker();
            solver.solve(1, in, out);
            out.close();
        }
    }

    static class Checker {
        public static int h;
        public static int w;
        public static int d;
        public static Building[] buildings;

        static char id = 'b';

        public void solve(int testNumber, InputReader in, OutputWriter out) {
            h = in.readInt();
            w = in.readInt();
            d = in.readInt();
            int b = in.readInt();
            buildings = new Building[b];
            for (int i = 0; i < b; i++) {
                buildings[i] = Building.read(in, i);
            }

            Solution solution = new Solution();
/*            Solution solution = null;
            try {
                solution = Solution.load(new InputReader(new FileInputStream(id + ".out")));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            for (int i = 0; i < h; i++) {
                for (int j = w / 10; j < w; j++) {
                    if (solution.map[i][j] != null) {
                        solution.removeBuilding(solution.map[i][j]);
                    }
                }
            }*/
            System.err.println("loading complete");

            int[] row = new int[h * w];
            int[] col = new int[h * w];
            int[] dst = new int[h * w];

            int ci = h / 2;
            int cj = w / 2;
            //Solve here
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    row[i * h + j] = i;
                    col[i * h + j] = j;
                    dst[i * h + j] = abs(i - ci) + abs(j - cj);
                }
            }
            orderBy(dst, row, col);
            for (int d = 0; d < row.length; d++) {
                int i = row[d];
                int j = col[d];
//                for (int i = 0; i < h; i++) {
//                    for (int j = 0; j < w; j++) {
//                        if (max(abs(i - ci), abs(j - cj)) != d) {
//                            continue;
//                        }
                        double bestDelta = -1;
                        int at = -1;
                        for (int k = 0; k < b; k++) {
                            int dx = 0;
                            int dy = 0;
                            if (i < ci) {
                                dx = -buildings[k].h + 1;
                            }
                            if (j < cj) {
                                dy = -buildings[k].w + 1;
                            }
                            if (i + dx < 0 || j + dy < 0 || !Placement.isCorrect(buildings[k], i + dx, j + dy)) {
                                continue;
                            }
                            Placement building = new Placement(buildings[k], i + dx, j + dy);
                            if (solution.canAddBuilding(building)) {
                                long wasScore = solution.score;
                                solution.addBuilding(building);
                                long newScore = solution.score;
                                solution.removeBuilding(building);
                                if (solution.score != wasScore) {
                                    throw new RuntimeException();
                                }
                                double delta = (double) (newScore - wasScore) / building.building.area;
//                                if (!building.building.isUtility) {
//                                    delta *= 1.1;
//                                }
                                if (delta > bestDelta) {
                                    bestDelta = delta;
                                    at = k;
                                }
                            }
                        }
                        if (at != -1) {
                            int dx = 0;
                            int dy = 0;
                            if (i < ci) {
                                dx = -buildings[at].h + 1;
                            }
                            if (j < cj) {
                                dy = -buildings[at].w + 1;
                            }
                            solution.addBuilding(new Placement(buildings[at], i + dx, j + dy));
                        }
//                    }
//                }
                if (d % 1000 == 0) {
                    System.err.println(d + " " + solution.score);
                    if (d % 100000 == 0) {
                        try {
                            solution.printSolution(new OutputWriter(new FileOutputStream("temp" + id + ".out")));
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
            /*for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    double bestDelta = -1;
                    int at = -1;
                    for (int kk = 0; kk < b; kk++) {
                        int k = kk;//(kk + shift) % b;
                        if (!Placement.isCorrect(buildings[k], i, j)) {
                            continue;
                        }
                        Placement building = new Placement(buildings[k], i, j);
                        if (solution.canAddBuilding(building)) {
                            long wasScore = solution.score;
                            solution.addBuilding(building);
                            long newScore = solution.score;
                            solution.removeBuilding(building);
                            if (solution.score != wasScore) {
                                throw new RuntimeException();
                            }
                            double delta = (double)(newScore - wasScore) / (building.building.area + 20);
//                            if (buildings[k].isUtility) {
//                                delta *= 11;
//                                delta /= 10;
//                            }
                            if (delta > bestDelta) {
                                bestDelta = delta;
                                at = k;
                            }
                        }
                    }
                    if (at != -1) {
                        solution.addBuilding(new Placement(buildings[at], i, j));
                    }
                }
                System.err.println(i + " " + solution.score);
            }*/

            solution.printSolution(out);
            System.err.println(solution.score);
            id++;
        }

        public static class Building {
            public final int id;
            public final int h;
            public final int w;
            public final char[][] plan;
            public final boolean isUtility;
            public final int number;
            public int[] dx;
            public int[] dy;
            public final int area;

            public Building(char[][] plan, boolean isUtility, int number, int id) {
                this.h = plan.length;
                this.w = plan[0].length;
                this.plan = plan;
                this.isUtility = isUtility;
                this.number = number;
                this.id = id;
                int area = 0;
                for (int i = 0; i < h; i++) {
                    for (int j = 0; j < w; j++) {
                        if (plan[i][j] == '#') {
                            area++;
                        }
                    }
                }
                this.area = area;
                expand();
            }

            public static Building read(InputReader in, int id) {
                char type = in.readCharacter();
                int h = in.readInt();
                int w = in.readInt();
                int number = in.readInt();
                char[][] plan = in.readTable(h, w);
                return new Building(plan, type == 'U', number, id);
            }

            public void expand() {
                ArrayList<Integer> dxx = new ArrayList<>();
                ArrayList<Integer> dyy = new ArrayList<>();
                Set<Integer> was = new HashSet<>();
                for (int i = 0; i < h; i++) {
                    for (int j = 0; j < w; j++) {
                        if (plan[i][j] == '#') {
                            for (int k = -d; k <= d; k++) {
                                for (int l = -d + abs(k); l <= d - abs(k); l++) {
                                    int row = k + i;
                                    int col = l + j;
                                    int hash = ((row + 100) << 20) + col;
                                    if (!was.contains(hash)) {
                                        was.add(hash);
                                        dxx.add(row);
                                        dyy.add(col);
                                    }
                                }
                            }
                        }
                    }
                }
                dx = new int[dxx.size()];
                dy = new int[dyy.size()];
                for (int i = 0; i < dxx.size(); i++) {
                    dx[i] = dxx.get(i);
                    dy[i] = dyy.get(i);
                }
            }
        }

        public static class Placement {
            public final Building building;
            public final int row;
            public final int column;

            public Placement(Building building, int row, int column) {
                this.building = building;
                this.row = row;
                this.column = column;
                if (!isCorrect(building, row, column)) {
                    throw new RuntimeException("Bad placement");
                }
            }

            public static boolean isCorrect(Building building, int row, int column) {
                return !(row + building.h > h || column + building.w > w);
            }


            public String toString() {
                return building.id + " " + row + " " + column;
            }

        }

        public static class Solution {
            public final List<Placement> buildings = new ArrayList<>();
            public long score;
            public final Placement[][] map = new Placement[h][w];

            public boolean canAddBuilding(Placement building) {
                for (int i = 0; i < building.building.h; i++) {
                    for (int j = 0; j < building.building.w; j++) {
                        if (map[i + building.row][j + building.column] != null &&
                                building.building.plan[i][j] == '#') {
                            return false;
                        }
                    }
                }
                return true;
            }

            public void addBuilding(Placement building) {
                recalculateScore(building, true);
                for (int i = 0; i < building.building.h; i++) {
                    for (int j = 0; j < building.building.w; j++) {
                        if (building.building.plan[i][j] == '#') {
                            if (map[i + building.row][j + building.column] != null) {
                                throw new RuntimeException("Can't be here");
                            }
                            map[i + building.row][j + building.column] = building;
                        }
                    }
                }
                buildings.add(building);
            }

            public static Solution load(InputReader in) {
                Solution solution = new Solution();
                int qty = in.readInt();
                for (int i = 0; i < qty; i++) {
                    solution.addBuilding(new Placement(Checker.buildings[in.readInt()], in.readInt(), in.readInt()));
                }
                return solution;
            }

            public List<Placement> forceAddBuilding(Placement building) {
                List<Placement> result = new ArrayList<>();
                for (int i = 0; i < building.building.h; i++) {
                    for (int j = 0; j < building.building.w; j++) {
                        if (building.building.plan[i][j] == '#') {
                            Placement current = map[i + building.row][j + building.column];
                            if (current != null) {
                                result.add(current);
                                removeBuilding(current);
                            }
                        }
                    }
                }
                addBuilding(building);
                return result;
            }

            static long nmb = 0;

            private void recalculateScore(Placement building, boolean isAdded) {
                long delta = 0;
                if (building.building.isUtility) {
                    Set<Placement> accounted = new HashSet<>();
                    for (int i = 0; i < building.building.dx.length; i++) {
                        int row = building.building.dx[i] + building.row;
                        int col = building.building.dy[i] + building.column;
                        if (MiscUtils.isValidCell(row, col, h, w) && map[row][col] != null &&
                                !map[row][col].building.isUtility &&
                                !accounted.contains(map[row][col])) {
                            accounted.add(map[row][col]);
                            if (!hasType(map[row][col], building.building.number)) {
                                delta += map[row][col].building.number;
                            }
                        }
                    }
                } else {
                    IntSet presentUtilities = new IntHashSet();
                    for (int i = 0; i < building.building.dx.length; i++) {
                        int row = building.building.dx[i] + building.row;
                        int col = building.building.dy[i] + building.column;
                        if (MiscUtils.isValidCell(row, col, h, w) && map[row][col] != null &&
                                map[row][col].building.isUtility && !presentUtilities.contains
                                (map[row][col].building.number)) {
                            presentUtilities.add(map[row][col].building.number);
                        }
                    }
                    delta = (long) presentUtilities.size() * building.building.number;
                }
                if (isAdded) {
                    score += delta;
                } else {
                    score -= delta;
                }
//                if ((++nmb) % 1000 == 0) {
//                    System.err.println(score);
//                }
            }

            private boolean hasType(Placement building, int utilityType) {
                for (int i = 0; i < building.building.dx.length; i++) {
                    int row = building.building.dx[i] + building.row;
                    int col = building.building.dy[i] + building.column;
                    if (MiscUtils.isValidCell(row, col, h, w) && map[row][col] != null &&
                            map[row][col].building.isUtility &&
                            map[row][col].building.number == utilityType) {
                        return true;
                    }
                }
                return false;
            }

            public void removeBuilding(Placement building) {
                for (int i = 0; i < building.building.h; i++) {
                    for (int j = 0; j < building.building.w; j++) {
                        if (building.building.plan[i][j] == '#') {
                            if (map[i + building.row][j + building.column] != building) {
                                throw new RuntimeException("Removing building that is not there");
                            }
                            map[i + building.row][j + building.column] = null;
                        }
                    }
                }
                recalculateScore(building, false);
                buildings.remove(building);
            }

            public void printSolution(OutputWriter out) {
                out.printLine(buildings.size());
                for (Placement building : buildings) {
                    out.printLine(building);
                }
                BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
                int countUtils = 0;
                Map<Integer, Integer> id = new HashMap<>();
                for (int i = 0; i < h; i++) {
                    for (int j = 0; j < w; j++) {
                        if (map[i][j] != null && map[i][j].building.isUtility) {
                            int cId = map[i][j].building.number;
                            if (!id.containsKey(cId)) {
                                id.put(cId, countUtils++);
                            }
                        }
                    }
                }
                for (int i = 0; i < h; i++) {
                    for (int j = 0; j < w; j++) {
                        Color color;
                        if (map[i][j] == null) {
                            color = Color.WHITE;
                        } else if (!map[i][j].building.isUtility) {
                            color = Color.BLACK;
                        } else {
                            int type = id.get(map[i][j].building.number);
                            int r = type * 255 / (countUtils);
                            int b = (countUtils - 1 - type) * 255 / (countUtils);
                            color = new Color(r, 0, b);
                        }
                        image.setRGB(j, i, color.getRGB());
                    }
                }
                try {
                    ImageIO.write(image, "png", new File(Checker.id + ".png"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        }

    }

    static class OutputWriter {
        private final PrintWriter writer;

        public OutputWriter(OutputStream outputStream) {
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
        }

        public OutputWriter(Writer writer) {
            this.writer = new PrintWriter(writer);
        }

        public void print(Object... objects) {
            for (int i = 0; i < objects.length; i++) {
                if (i != 0) {
                    writer.print(' ');
                }
                writer.print(objects[i]);
            }
        }

        public void printLine(Object... objects) {
            print(objects);
            writer.println();
        }

        public void close() {
            writer.close();
        }

        public void printLine(int i) {
            writer.println(i);
        }

    }

    static class IntHash {
        private IntHash() {
        }

        public static int hash(int c) {
            return c;
        }

    }

    static interface IntCollection extends IntStream {
        public int size();

        default public void add(int value) {
            throw new UnsupportedOperationException();
        }

        default public IntCollection addAll(IntStream values) {
            for (IntIterator it = values.intIterator(); it.isValid(); it.advance()) {
                add(it.value());
            }
            return this;
        }

    }

    static class MiscUtils {
        public static boolean isValidCell(int row, int column, int rowCount, int columnCount) {
            return row >= 0 && row < rowCount && column >= 0 && column < columnCount;
        }

    }

    static interface IntStream extends Iterable<Integer>, Comparable<IntStream> {
        public IntIterator intIterator();

        default public Iterator<Integer> iterator() {
            return new Iterator<Integer>() {
                private IntIterator it = intIterator();

                public boolean hasNext() {
                    return it.isValid();
                }

                public Integer next() {
                    int result = it.value();
                    it.advance();
                    return result;
                }
            };
        }

        default public int compareTo(IntStream c) {
            IntIterator it = intIterator();
            IntIterator jt = c.intIterator();
            while (it.isValid() && jt.isValid()) {
                int i = it.value();
                int j = jt.value();
                if (i < j) {
                    return -1;
                } else if (i > j) {
                    return 1;
                }
                it.advance();
                jt.advance();
            }
            if (it.isValid()) {
                return 1;
            }
            if (jt.isValid()) {
                return -1;
            }
            return 0;
        }

        default public boolean contains(int value) {
            for (IntIterator it = intIterator(); it.isValid(); it.advance()) {
                if (it.value() == value) {
                    return true;
                }
            }
            return false;
        }

    }

    static interface IntReversableCollection extends IntCollection {
    }

    static class InputReader {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
        private SpaceCharFilter filter;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        public char[] readCharArray(int size) {
            char[] array = new char[size];
            for (int i = 0; i < size; i++) {
                array[i] = readCharacter();
            }
            return array;
        }

        public char[][] readTable(int rowCount, int columnCount) {
            char[][] table = new char[rowCount][];
            for (int i = 0; i < rowCount; i++) {
                table[i] = this.readCharArray(columnCount);
            }
            return table;
        }

        public int read() {
            if (numChars == -1) {
                throw new InputMismatchException();
            }
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0) {
                    return -1;
                }
            }
            return buf[curChar++];
        }

        public int readInt() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public boolean isSpaceChar(int c) {
            if (filter != null) {
                return filter.isSpaceChar(c);
            }
            return isWhitespace(c);
        }

        public static boolean isWhitespace(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        public char readCharacter() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            return (char) c;
        }

        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);

        }

    }

    static class IntArray extends IntAbstractStream implements IntList {
        private int[] data;

        public IntArray(int[] arr) {
            data = arr;
        }

        public int size() {
            return data.length;
        }

        public int get(int at) {
            return data[at];
        }

        public void addAt(int index, int value) {
            throw new UnsupportedOperationException();
        }

        public void removeAt(int index) {
            throw new UnsupportedOperationException();
        }

    }

    static abstract class IntAbstractStream implements IntStream {

        public String toString() {
            StringBuilder builder = new StringBuilder();
            boolean first = true;
            for (IntIterator it = intIterator(); it.isValid(); it.advance()) {
                if (first) {
                    first = false;
                } else {
                    builder.append(' ');
                }
                builder.append(it.value());
            }
            return builder.toString();
        }


        public boolean equals(Object o) {
            if (!(o instanceof IntStream)) {
                return false;
            }
            IntStream c = (IntStream) o;
            IntIterator it = intIterator();
            IntIterator jt = c.intIterator();
            while (it.isValid() && jt.isValid()) {
                if (it.value() != jt.value()) {
                    return false;
                }
                it.advance();
                jt.advance();
            }
            return !it.isValid() && !jt.isValid();
        }


        public int hashCode() {
            int result = 0;
            for (IntIterator it = intIterator(); it.isValid(); it.advance()) {
                result *= 31;
                result += it.value();
            }
            return result;
        }

    }

    static interface IntList extends IntReversableCollection {
        public abstract int get(int index);

        public abstract void addAt(int index, int value);

        public abstract void removeAt(int index);

        default public IntIterator intIterator() {
            return new IntIterator() {
                private int at;
                private boolean removed;

                public int value() {
                    if (removed) {
                        throw new IllegalStateException();
                    }
                    return get(at);
                }

                public boolean advance() {
                    at++;
                    removed = false;
                    return isValid();
                }

                public boolean isValid() {
                    return !removed && at < size();
                }

                public void remove() {
                    removeAt(at);
                    at--;
                    removed = true;
                }
            };
        }


        default public void add(int value) {
            addAt(size(), value);
        }

    }

    static interface IntSet extends IntCollection {
    }

    static class IntegerUtils {
        public static int gcd(int a, int b) {
            a = abs(a);
            b = abs(b);
            while (b != 0) {
                int temp = a % b;
                a = b;
                b = temp;
            }
            return a;
        }

    }

    static class IntHashSet extends IntAbstractStream implements IntSet {
        private static final Random RND = new Random();
        private static final int[] SHIFTS = new int[4];
        private static final byte PRESENT_MASK = 1;
        private static final byte REMOVED_MASK = 2;
        private int size;
        private int realSize;
        private int[] values;
        private byte[] present;
        private int step;
        private int ratio;

        static {
            for (int i = 0; i < 4; i++) {
                SHIFTS[i] = RND.nextInt(31) + 1;
            }
        }

        public IntHashSet() {
            this(3);
        }

        public IntHashSet(int capacity) {
            capacity = max(capacity, 3);
            values = new int[capacity];
            present = new byte[capacity];
            ratio = 2;
            initStep(capacity);
        }

        public IntHashSet(IntCollection c) {
            this(c.size());
            addAll(c);
        }

        public IntHashSet(int[] arr) {
            this(new IntArray(arr));
        }

        private void initStep(int capacity) {
            step = RND.nextInt(capacity - 2) + 1;
            while (IntegerUtils.gcd(step, capacity) != 1) {
                step++;
            }
        }


        public IntIterator intIterator() {
            return new IntIterator() {
                private int position = size == 0 ? values.length : -1;

                public int value() throws NoSuchElementException {
                    if (position == -1) {
                        advance();
                    }
                    if (position >= values.length) {
                        throw new NoSuchElementException();
                    }
                    if ((present[position] & PRESENT_MASK) == 0) {
                        throw new IllegalStateException();
                    }
                    return values[position];
                }

                public boolean advance() throws NoSuchElementException {
                    if (position >= values.length) {
                        throw new NoSuchElementException();
                    }
                    position++;
                    while (position < values.length && (present[position] & PRESENT_MASK) == 0) {
                        position++;
                    }
                    return isValid();
                }

                public boolean isValid() {
                    return position < values.length;
                }

                public void remove() {
                    if ((present[position] & PRESENT_MASK) == 0) {
                        throw new IllegalStateException();
                    }
                    present[position] = REMOVED_MASK;
                }
            };
        }


        public int size() {
            return size;
        }


        public void add(int value) {
            ensureCapacity((realSize + 1) * ratio + 2);
            int current = getHash(value);
            while (present[current] != 0) {
                if ((present[current] & PRESENT_MASK) != 0 && values[current] == value) {
                    return;
                }
                current += step;
                if (current >= values.length) {
                    current -= values.length;
                }
            }
            while ((present[current] & PRESENT_MASK) != 0) {
                current += step;
                if (current >= values.length) {
                    current -= values.length;
                }
            }
            if (present[current] == 0) {
                realSize++;
            }
            present[current] = PRESENT_MASK;
            values[current] = value;
            size++;
        }

        private int getHash(int value) {
            int hash = IntHash.hash(value);
            int result = hash;
            for (int i : SHIFTS) {
                result ^= hash >> i;
            }
            result %= values.length;
            if (result < 0) {
                result += values.length;
            }
            return result;
        }

        private void ensureCapacity(int capacity) {
            if (values.length < capacity) {
                capacity = max(capacity * 2, values.length);
                rebuild(capacity);
            }
        }

        private void rebuild(int capacity) {
            initStep(capacity);
            int[] oldValues = values;
            byte[] oldPresent = present;
            values = new int[capacity];
            present = new byte[capacity];
            size = 0;
            realSize = 0;
            for (int i = 0; i < oldValues.length; i++) {
                if ((oldPresent[i] & PRESENT_MASK) == PRESENT_MASK) {
                    add(oldValues[i]);
                }
            }
        }


        public boolean contains(int value) {
            int current = getHash(value);
            while (present[current] != 0) {
                if (values[current] == value && (present[current] & PRESENT_MASK) != 0) {
                    return true;
                }
                current += step;
                if (current >= values.length) {
                    current -= values.length;
                }
            }
            return false;
        }

    }

    static interface IntIterator {
        public int value() throws NoSuchElementException;

        public boolean advance();

        public boolean isValid();

    }
}

