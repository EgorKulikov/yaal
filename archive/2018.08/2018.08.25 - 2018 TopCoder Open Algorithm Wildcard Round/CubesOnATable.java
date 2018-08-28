package net.egork;

import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;

import static java.lang.Math.abs;

public class CubesOnATable {
    public int[] placeCubes(int surface) {
        if (surface < 5 || surface == 6 || surface == 7) {
            return new int[0];
        }
        IntList answer = new IntArrayList();
        int height = 0;
        int originalSurface = surface;
        while (surface >= 13) {
            answer.add(9);
            answer.add(9);
            surface -= height == 0 ? 5 : 4;
            answer.add(height++);
        }
        if (surface == 11) {
            answer.add(0);
            answer.add(0);
            answer.add(0);
            answer.add(1);
            answer.add(0);
            answer.add(0);
            answer.add(2);
            answer.add(0);
            answer.add(0);
        } else if (surface == 10) {
            answer.add(0);
            answer.add(0);
            answer.add(0);
            answer.add(2);
            answer.add(0);
            answer.add(0);
        } else if (surface == 9) {
            answer.add(0);
            answer.add(0);
            answer.add(0);
            answer.add(0);
            answer.add(0);
            answer.add(1);
        } else if (surface == 8) {
            answer.add(0);
            answer.add(0);
            answer.add(0);
            answer.add(1);
            answer.add(0);
            answer.add(0);
        } else if (surface == 12) {
            answer.add(0);
            answer.add(0);
            answer.add(0);
            answer.add(1);
            answer.add(0);
            answer.add(0);
            answer.add(0);
            answer.add(1);
            answer.add(0);
            answer.add(1);
            answer.add(1);
            answer.add(0);
        } else if (surface == 5) {
            answer.add(0);
            answer.add(0);
            answer.add(0);
        }
        originalSurface -= 5 * (answer.size() / 3);
        for (int i = 0; i < answer.size(); i += 3) {
            int x = answer.get(i);
            int y = answer.get(i + 1);
            int z = answer.get(i + 2);
            for (int j = 0; j < i; j += 3) {
                int xx = answer.get(j);
                int yy = answer.get(j + 1);
                int zz = answer.get(j + 2);
                if (x == xx && y == yy && abs(z - zz) == 1) {
                    originalSurface++;
                }
                if (z == zz && abs(x - xx) + abs(y - yy) == 1) {
                    originalSurface += 2;
                }
            }
        }
        if (originalSurface != 0) {
            throw new RuntimeException();
        }
        return answer.toArray();
    }
}
