package net.egork;

public class Privateparty {
    public double getexp(int[] a) {
        double[] expected = new double[a.length];
        double current = 1;
        expected[0] = 1;
        for (int i = 1; i < a.length; i++) {
            current /= -(i + 1);
            expected[i] = expected[i - 1] + current;
        }
        double answer = 0;
        for (int i = 0; i < a.length; i++) {
            int depth = -1;
            int copy = i;
            boolean[] visited = new boolean[a.length];
            while (!visited[copy]){
                visited[copy] = true;
                depth++;
                copy = a[copy];
            }
            answer += expected[depth];
        }
        return answer;
    }
}
