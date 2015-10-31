package on2015_08.on2015_08_22_Codeforces_Round__317__AimFund_Thanks_Round___Div__1_.D___Campus;


import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        IntList[] dormitories = new IntList[n];
        IntList[] armies = new IntList[n];
        for (int i = 0; i < n; i++) {
            dormitories[i] = new IntArrayList(1);
            dormitories[i].add(i);
            armies[i] = new IntArrayList(1);
            armies[i].add(i);
        }
        int[] ignoreArmyBefore = new int[n];
        int[] lastExplicitArmy = new int[n];
        Arrays.fill(ignoreArmyBefore, -1);
        Arrays.fill(lastExplicitArmy, -1);
        long[] baseQty = new long[n];
        int[] baseQtyTime = new int[n];
        Arrays.fill(baseQtyTime, -1);
        IntList[] updateTimes = new IntList[n];
        IntList[] updateCumulQtys = new IntList[n];
        long[] allCumulUpds = new long[m + 1];
        int cumulUpdsAt = 1;
        AtomicInteger[] lastArmyVisits = new AtomicInteger[n];
        for (int i = 0; i < n; i++) {
            updateTimes[i] = new IntArrayList(1);
            updateCumulQtys[i] = new IntArrayList(1);
            updateCumulQtys[i].add(0);
            lastArmyVisits[i] = new AtomicInteger(-1);
        }
        for (int i = 0; i < m; i++) {
            char type = in.readCharacter();
            if (type == 'U') {
                int first = in.readInt() - 1;
                int second = in.readInt() - 1;
                if (dormitories[first].size() > dormitories[second].size()) {
                    int temp = first;
                    first = second;
                    second = temp;
                }
                IntList toAdd = dormitories[first];
                dormitories[second].addAll(toAdd);
                for (int current : toAdd.toArray()) {
                    int lastArmyVisit = lastArmyVisits[current].get();
                    if (lastArmyVisit < ignoreArmyBefore[current]) {
                        lastArmyVisit = lastExplicitArmy[current];
                    }
                    if (baseQtyTime[current] > lastArmyVisit) {
                        lastArmyVisit = baseQtyTime[current];
                    } else {
                        baseQty[current] = 0;
                    }
                    int lastNotNeededUpdate = get(updateTimes[current], lastArmyVisit);
                    baseQty[current] += allCumulUpds[updateCumulQtys[current].get(updateTimes[current].size())] - allCumulUpds[updateCumulQtys[current].get(lastNotNeededUpdate)];
                    baseQtyTime[current] = i;
                    dormitories[current] = dormitories[second];
                    updateTimes[current] = updateTimes[second];
                    updateCumulQtys[current] = updateCumulQtys[second];
                }
            } else if (type == 'M') {
                int first = in.readInt() - 1;
                int second = in.readInt() - 1;
                if (armies[first].size() > armies[second].size()) {
                    int temp = first;
                    first = second;
                    second = temp;
                }
                IntList toAdd = armies[first];
                armies[second].addAll(toAdd);
                for (int current : toAdd.toArray()) {
                    int lastArmyVisit = lastArmyVisits[current].get();
                    if (lastArmyVisit > ignoreArmyBefore[current]) {
                        lastExplicitArmy[current] = lastArmyVisit;
                    }
                    ignoreArmyBefore[current] = i;
                    lastArmyVisits[current] = lastArmyVisits[second];
                    armies[current] = armies[second];
                }
            } else if (type == 'A') {
                int id = in.readInt() - 1;
                updateTimes[id].add(i);
                allCumulUpds[cumulUpdsAt] = dormitories[id].size() + allCumulUpds[updateCumulQtys[id].last()];
                updateCumulQtys[id].add(cumulUpdsAt++);
            } else if (type == 'Z') {
                int id = in.readInt() - 1;
                lastArmyVisits[id].set(i);
            } else {
                int current = in.readInt() - 1;
                int lastArmyVisit = lastArmyVisits[current].get();
                if (lastArmyVisit < ignoreArmyBefore[current]) {
                    lastArmyVisit = lastExplicitArmy[current];
                }
                if (baseQtyTime[current] > lastArmyVisit) {
                    lastArmyVisit = baseQtyTime[current];
                } else {
                    baseQty[current] = 0;
                }
                int lastNotNeededUpdate = get(updateTimes[current], lastArmyVisit);
                baseQty[current] += allCumulUpds[updateCumulQtys[current].get(updateTimes[current].size())] - allCumulUpds[updateCumulQtys[current].get(lastNotNeededUpdate)];
                baseQtyTime[current] = i;
                out.printLine(baseQty[current]);
            }
        }
    }

    private int get(IntList time, int visit) {
        int left = 0;
        int right = time.size();
        while (left < right) {
            int middle = (left + right) >> 1;
            if (time.get(middle) > visit) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        return left;
    }
}
