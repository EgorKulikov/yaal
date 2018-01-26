package on2016_09.on2016_09_03_2016_TopCoder_Open_Algorithm.CloneGauntlet2;



import static net.egork.misc.ArrayUtils.concatenate;

public class CloneGauntlet2 {
    public int minClones(int[] parent, int[] connection) {
        int n = connection.length;
        parent = concatenate(new int[1], parent);
        boolean[] reachable = new boolean[n];
        boolean[] onPath = new boolean[n];
        int x = n - 1;
        while (x != 0) {
            onPath[x] = true;
            x = parent[x];
        }
        onPath[0] = true;
        reachable[0] = true;
        int answer = 0;
        int current = 0;
        int myPos = 0;
        while (true) {
            int to = connection[current];
            if (reachable[to] || !reachable[parent[to]]) {
                return -1;
            }
            if (onPath[to]) {
                answer++;
                myPos = to;
            } else if (current != myPos) {
                answer++;
            }
            if (to == n - 1) {
                return answer;
            }
            reachable[to] = true;
            current = to;
        }
    }
}
