package net.egork;

import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.collections.intcollection.IntPair;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        IntList firstList = new IntArrayList();
        IntList secondList = new IntArrayList();
        for (int i = 0; i < count; i++) {
            if (in.readInt() == 1) {
                firstList.add(i);
            } else {
                secondList.add(i);
            }
        }
        int[] first = firstList.toArray();
        int[] second = secondList.toArray();
        List<IntPair> answer = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            int firstSets = 0;
            int secondSets = 0;
            int at = -1;
            int lastWon = -1;
            boolean good = true;
            while (at < count - 1) {
                int firstPosition = Arrays.binarySearch(first, at);
                if (firstPosition < 0) {
                    firstPosition = -firstPosition - 2;
                }
                int secondPosition = Arrays.binarySearch(second, at);
                if (secondPosition < 0) {
                    secondPosition = -secondPosition - 2;
                }
                firstPosition += i;
                secondPosition += i;
                if (firstPosition < first.length) {
                    if (secondPosition < second.length) {
                        if (first[firstPosition] < second[secondPosition]) {
                            at = first[firstPosition];
                            firstSets++;
                            lastWon = 0;
                        } else {
                            at = second[secondPosition];
                            secondSets++;
                            lastWon = 1;
                        }
                    } else {
                        at = first[firstPosition];
                        firstSets++;
                        lastWon = 0;
                    }
                } else {
                    if (secondPosition < second.length) {
                        at = second[secondPosition];
                        secondSets++;
                        lastWon = 1;
                    } else {
                        good = false;
                        break;
                    }
                }
            }
            if (good) {
                if (firstSets > secondSets && lastWon == 0) {
                    answer.add(new IntPair(firstSets, i));
                } else if (secondSets > firstSets && lastWon == 1) {
                    answer.add(new IntPair(secondSets, i));
                }
            }
        }
        Collections.sort(answer);
        out.printLine(answer.size());
        for (IntPair pair : answer) {
            out.printLine(pair.first, pair.second);
        }
    }
}
