package net.egork;

import javafx.geometry.Pos;
import net.egork.collections.intcollection.IntTreapArray;
import net.egork.collections.set.TreapSet;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.NavigableSet;
import java.util.TreeSet;

import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int r = in.readInt();
        int c = in.readInt();
        int n = in.readInt();
        int k = in.readInt();
        int[] x = new int[n];
        int[] y = new int[n];
        readIntArrays(in, x, y);
        decreaseByOne(x, y);
        orderBy(y, x);
        Position[] headPosition = new Position[k];
        for (int i = 0; i < k; i++) {
            headPosition[i] = new Position(-1, -1);
            if (i != 0) {
                headPosition[i].last = headPosition[i - 1];
                headPosition[i - 1].next = headPosition[i];
            }
        }
        Position[] tailPosition = new Position[k];
        for (int i = 0; i < k; i++) {
            tailPosition[i] = new Position(r, -2);
            if (i != 0) {
                tailPosition[i].last = tailPosition[i - 1];
                tailPosition[i - 1].next = tailPosition[i];
            }
        }
        Position[] positions = new Position[n];
        for (int i = 0; i < n; i++) {
            positions[i] = new Position(x[i], i);
        }
        long answer = 0;
        for (int i = 0; i < c; i++) {
            int at = 0;
            while (at < n && y[at] < i) {
                at++;
            }
            for (int j = 0; j < k - 1; j++) {
                headPosition[j + 1].link = tailPosition[j];
            }
            NavigableSet<Position> set = new TreeSet<>();
            set.add(headPosition[k - 1]);
            set.add(tailPosition[0]);
            headPosition[k - 1].next = tailPosition[0];
            tailPosition[0].last = headPosition[k - 1];
            long delta = 0;
            for (int j = i; j < c; j++) {
                while (at < n && y[at] == j) {
                    Position where = set.lower(positions[at]);
                    Position current = where;
                    for (int l = 1; l < k; l++) {
                        delta -= current.value(r);
                        current = current.last;
                    }
                    Position next = where.next;
                    delta -= next.value(r);
                    current = positions[at];
                    where.next = current;
                    current.last = where;
                    next.last = current;
                    current.next = next;
                    current.link = k == 1 ? current : where.link;
                    set.add(current);
                    delta += next.value(r);
                    delta += current.value(r);
                    current = where;
                    for (int l = 1; l < k; l++) {
                        current.link = current.link.last;
                        delta += current.value(r);
                        current = current.last;
                    }
                    at++;
                }
                answer += delta;
            }
        }
        out.printLine(answer);
    }

    static class Position implements Comparable<Position> {
        int row;
        int id;
        Position last;
        Position next;
        Position link;

        public Position(int row, int id) {
            this.row = row;
            this.id = id;
        }

        @Override
        public int compareTo(Position o) {
            if (row != o.row) {
                return row - o.row;
            }
            return id - o.id;
        }

        public long value(int r) {
            if (link == null) {
                return 0;
            }
            return (row - last.row) * (r - link.row);
        }
    }
}
