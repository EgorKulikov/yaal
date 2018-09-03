package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.NavigableSet;
import java.util.TreeSet;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.readString();
        NavigableSet<String> answer = new TreeSet<>();
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (i != j) {
                    char[] arr = s.toCharArray();
                    if (arr[i] == '9' || arr[j] == '0') {
                        continue;
                    }
                    for (int k = 0; k < 2; k++) {
                        for (int l = 0; l < 2; l++) {
                            arr = s.toCharArray();
                            arr[i] += k;
                            arr[j] -= l;
                            char temp = arr[i];
                            arr[i] = arr[j];
                            arr[j] = temp;
                            String ss = apply(arr.clone());
                            if (ss.equals(s)) {
                                answer.add(new String(arr));
                            }
                        }
                    }
                }
            }
        }
        if (answer.isEmpty()) {
            out.printLine(-1);
        } else {
            for (String t : answer) {
                out.printLine(t);
            }
        }
    }

    private String apply(char[] arr) {
        int a = -1;
        int b = -1;
        for (int i = 0; i < 16; i++) {
            if (a == -1 || arr[i] < arr[a]) {
                a = i;
            }
            if (b == -1 || arr[i] >= arr[b]) {
                b = i;
            }
        }
        if (arr[a] != '9') {
            arr[a]++;
        }
        if (arr[b] != '0') {
            arr[b]--;
        }
        char temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
        return new String(arr);
    }
}
