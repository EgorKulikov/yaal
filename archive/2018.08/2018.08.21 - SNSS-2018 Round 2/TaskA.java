package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;
import static java.util.Collections.sort;

public class TaskA {
    enum Direction {
        LEFT(false), RIGHT(false, LEFT), UP(true), DOWN(true, UP);

        public boolean isHorizontal;
        public Direction opposite;

        Direction(boolean isHorizontal) {
            this.isHorizontal = isHorizontal;
        }

        Direction(boolean isHorizontal, Direction opposite) {
            this.isHorizontal = isHorizontal;
            this.opposite = opposite;
            opposite.opposite = this;
        }
    }

    static class Segment implements Comparable<Segment> {
        int at;
        int start;
        int end;
        Direction direction;

        public Segment(int at, int start, int end, Direction direction) {
            this.at = at;
            this.start = start;
            this.end = end;
            this.direction = direction;
        }

        @Override
        public int compareTo(Segment o) {
            if (direction.isHorizontal != o.direction.isHorizontal) {
                return Boolean.compare(direction.isHorizontal, o.direction.isHorizontal);
            }
            if (at != o.at) {
                return at - o.at;
            }
            return start - o.start;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        int[] x = new int[n];
        int[] y = new int[n];
        in.readIntArrays(x, y);
//        boolean[][] map = new boolean[200][200];
        List<Segment> segments = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Segment closest = null;
            for (Segment segment : segments) {
                if (segment.direction.isHorizontal && segment.start <= x[i] && segment.end >= x[i] && segment.at < y[i] &&
                        (closest == null || segment.at > closest.at)) {
                    closest = segment;
                }
            }
/*            int cx = x[i] + 100;
            int cy = y[i] + 100;
            boolean color = !map[cx][cy];
            for (int j = 1; j <= k / 2 + 1; j++) {
                boolean ok = j != k / 2 + 1;
                for (int a = cx - j; a < cx + j && ok; a++) {
                    for (int b = cy - j; b < cy + j && ok; b++) {
                        if (map[a][b] == color) {
                            ok = false;
                        }
                    }
                }
                if (!ok) {
                    j--;
                    for (int a = cx - j; a < cx + j; a++) {
                        for (int b = cy - j; b < cy + j; b++) {
                            map[a][b] = color;
                        }
                    }
                    break;
                }
            }*/
            boolean rebel = closest == null || closest.direction == Direction.UP;
            int size = k / 2;
            for (Segment segment : segments) {
                int main = segment.direction.isHorizontal ? y[i] : x[i];
                int supplemental = x[i] + y[i] - main;
                int distance = abs(main - segment.at);
                if (abs(supplemental - segment.start) <= distance || abs(supplemental - segment.end) <= distance || segment.start < supplemental && supplemental < segment.end) {
                    size = min(size, distance);
                }
            }
            if (size == 0) {
                continue;
            }
            List<Segment> next = new ArrayList<>();
            Segment current = new Segment(x[i] - size, y[i] - size, y[i] + size, rebel ? Direction.RIGHT : Direction.LEFT);
            Direction state = Direction.LEFT;
            for (Segment segment : segments) {
                if (state == Direction.LEFT && (segment.direction.isHorizontal || segment.at > x[i])) {
                    if (current != null) {
                        next.add(current);
                    }
                    current = new Segment(x[i] + size, y[i] - size, y[i] + size, rebel ? Direction.LEFT : Direction.RIGHT);
                    state = Direction.RIGHT;
                }
                if (state == Direction.RIGHT && segment.direction.isHorizontal) {
                    if (current != null) {
                        next.add(current);
                    }
                    current = new Segment(y[i] - size, x[i] - size, x[i] + size, rebel ? Direction.DOWN : Direction.UP);
                    state = Direction.UP;
                }
                if (state == Direction.UP && segment.at > y[i]) {
                    if (current != null) {
                        next.add(current);
                    }
                    current = new Segment(y[i] + size, x[i] - size, x[i] + size, rebel ? Direction.UP : Direction.DOWN);
                    state = Direction.DOWN;
                }
                if (current != null && current.at == segment.at && max(current.start, segment.start) < min(current.end, segment.end)) {
                    if (current.start < segment.start) {
                        next.add(new Segment(current.at, current.start, segment.start, current.direction));
                    } else if (segment.start < current.start) {
                        next.add(new Segment(segment.at, segment.start, current.start, segment.direction));
                    }
                    if (current.end < segment.end) {
                        next.add(new Segment(segment.at, current.end, segment.end, segment.direction));
                        current = null;
                    } else if (current.end > segment.end) {
                        current.start = segment.end;
                    } else {
                        current = null;
                    }
                } else {
                    next.add(segment);
                }
            }
            if (state == Direction.LEFT) {
                if (current != null) {
                    next.add(current);
                }
                current = new Segment(x[i] + size, y[i] - size, y[i] + size, rebel ? Direction.LEFT : Direction.RIGHT);
                state = Direction.RIGHT;
            }
            if (state == Direction.RIGHT) {
                if (current != null) {
                    next.add(current);
                }
                current = new Segment(y[i] - size, x[i] - size, x[i] + size, rebel ? Direction.DOWN : Direction.UP);
                state = Direction.UP;
            }
            if (state == Direction.UP) {
                if (current != null) {
                    next.add(current);
                }
                current = new Segment(y[i] + size, x[i] - size, x[i] + size, rebel ? Direction.UP : Direction.DOWN);
            }
            if (current != null) {
                next.add(current);
            }
            sort(next);
            segments = next;
/*            for (Segment segment : segments) {
                for (int j = segment.start; j < segment.end; j++) {
                    if (segment.direction.isHorizontal) {
                        if (map[j + 100][segment.at + 100] == map[j + 100][segment.at + 99]) {
                            throw new RuntimeException();
                        }
//                        if (map[j + 100][segment.at + 99] && segment.direction != Direction.UP) {
//                            throw new RuntimeException();
//                        }
                    } else {
                        if (map[segment.at + 100][j + 100] == map[segment.at + 99][j + 100]) {
                            throw new RuntimeException();
                        }
                    }
                }
            }
            for (int j = 0; j < 199; j++) {
                for (int l = 0; l < 199; l++) {
                    if (map[j][l] != map[j + 1][l]) {
                        boolean found = false;
                        for (Segment segment : segments) {
                            if (!segment.direction.isHorizontal && segment.at == j - 99 && segment.start <= l - 100 && segment.end > l - 100) {
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            throw new RuntimeException();
                        }
                    }
                    if (map[j][l] != map[j][l + 1]) {
                        boolean found = false;
                        for (Segment segment : segments) {
                            if (segment.direction.isHorizontal && segment.at == l - 99 && segment.start <= j - 100 && segment.end > j - 100) {
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            throw new RuntimeException();
                        }
                    }
                }
            }*/
        }
        long answer = 0;
        for (Segment segment : segments) {
            if (segment.direction.isHorizontal) {
                long delta = (long)segment.at * (segment.end - segment.start);
                if (segment.direction == Direction.UP) {
                    answer += delta;
                } else {
                    answer -= delta;
                }
            }
        }
        out.printLine(answer);
        /*long result = 0;
        for (int i = 0; i < map.length; i++) {
            result += count(map[i], true);
        }
        System.err.println(result);*/
    }
}
