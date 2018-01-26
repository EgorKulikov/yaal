package on2017_09.on2017_09_09_2017_TopCoder_Open_Algorithm.ChromosomalCrossover;



import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;

public class ChromosomalCrossover {
    public int maximalLength(String A, String B) {
        StringBuilder a = new StringBuilder();
        StringBuilder b = new StringBuilder();
        int n = A.length();
        for (int i = 0; i < n; i++) {
            a.append((char)(A.charAt(i) - 'A'));
            b.append((char)(B.charAt(i) - 'A'));
        }
        A = a.toString();
        B = b.toString();
        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (i == j) {
                    for (int k = i; k < n; k++) {
                        if (A.charAt(k) != B.charAt(k)) {
                            break;
                        }
                        answer = Math.max(answer, k - i + 1);
                    }
                } else {
                    IntList remaining = new IntArrayList();
                    boolean bad = false;
                    for (int k = 0; k < i - j && k < n - i; k++) {
                        int left = (1 << A.charAt(i + k)) | (1 << B.charAt(i + k));
                        int right = (1 << A.charAt(j + k)) | (1 << B.charAt(j + k));
                        if ((left & right) == 0) {
                            bad = true;
                            break;
                        }
                        if ((left & right) == left) {
                            remaining.add(left);
                        } else {
                            remaining.add(left - (left & right));
                        }
                        answer = Math.max(answer, k + 1);
                    }
                    if (bad) {
                        continue;
                    }
                    for (int k = i - j; k < n - i; k++) {
                        int right = remaining.get(k - i + j);
                        int left = (1 << A.charAt(i + k)) | (1 << B.charAt(i + k));
                        if ((left & right) == 0) {
                            bad = true;
                            break;
                        }
                        if ((left & right) == left) {
                            remaining.add(left);
                        } else {
                            remaining.add(left - (left & right));
                        }
                        answer = Math.max(answer, k + 1);
                    }
                }
            }
        }
        return answer;
    }
}
