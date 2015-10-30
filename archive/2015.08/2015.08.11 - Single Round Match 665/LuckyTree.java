package net.egork;

public class LuckyTree {
    public static void main(String[] args) {
        for (int i = 861; i <= 861; i++) {
            if (new LuckyTree().construct(i).length != 0) {
                System.err.println(i);
            }
        }
    }

    public int[] construct(int favorite) {
        if (favorite > 625) {
            for (int n = 3; (51 - n - 1) * (51 - n) / 2 + ((n + 1) / 2) * (n / 2) > favorite; n++) {
                int max = (51 - n - 1) * (51 - n) / 2 + ((n + 1) / 2) * (n / 2);
                int alphas = 51 - n - 1;
                for (int i = 0; i < n; i++) {
                    int cAlpha = alphas / n + (alphas % n > i ? 1 : 0);
                    max -= cAlpha * (cAlpha - 1) / 2;
                }
                if (max < favorite) {
                    continue;
                }
                int[] alpha = new int[n];
                int[] result = go(0, 1, alpha, 51 - n - 1, n, favorite);
                if (result != null) {
                    return result;
                }
            }
            return new int[0];
        }
//        boolean[] can = new boolean[1276];
        for (int a = 0; a <= 48; a++) {
            for (int b = 0; a + b <= 48; b++) {
                for (int c = 0; a + b + c <= 48; c++) {
                    for (int d = 0; a + b + c + d <= 48; d++) {
                        int value = a * (b + 1) + d * (c + 1) + a * d;
                        if (value == favorite) {
                            int count = a + b + c + d + 3;
                            int[] result = new int[3 * count - 2];
                            result[0] = count;
                            result[1] = 0;
                            result[2] = 1;
                            result[3] = 4;
                            result[4] = 0;
                            result[5] = 2;
                            result[6] = 4;
                            int at = 7;
                            int ver = 3;
                            for (int i = 0; i < a; i++) {
                                result[at++] = 1;
                                result[at++] = ver++;
                                result[at++] = 7;
                            }
                            for (int i = 0; i < b; i++) {
                                result[at++] = 1;
                                result[at++] = ver++;
                                result[at++] = 4;
                            }
                            for (int i = 0; i < c; i++) {
                                result[at++] = 2;
                                result[at++] = ver++;
                                result[at++] = 4;
                            }
                            for (int i = 0; i < d; i++) {
                                result[at++] = 2;
                                result[at++] = ver++;
                                result[at++] = 7;
                            }
                            return result;
                        }
//                        can[(value)] = true;
                    }
                }
            }
        }
        for (int a = 0; a <= 48; a++) {
            for (int b = 0; a + b <= 48; b++) {
                for (int c = 0; a + b + c <= 48; c++) {
                    for (int d = 0; a + b + c + d <= 48; d++) {
                        int value = a * (b + 1) + 1 + c * (d + 1) + a * c + b * d;
                        if (value == favorite) {
                            int count = a + b + c + d + 3;
                            int[] result = new int[3 * count - 2];
                            result[0] = count;
                            result[1] = 0;
                            result[2] = 1;
                            result[3] = 4;
                            result[4] = 0;
                            result[5] = 2;
                            result[6] = 7;
                            int at = 7;
                            int ver = 3;
                            for (int i = 0; i < a; i++) {
                                result[at++] = 1;
                                result[at++] = ver++;
                                result[at++] = 7;
                            }
                            for (int i = 0; i < b; i++) {
                                result[at++] = 1;
                                result[at++] = ver++;
                                result[at++] = 4;
                            }
                            for (int i = 0; i < c; i++) {
                                result[at++] = 2;
                                result[at++] = ver++;
                                result[at++] = 4;
                            }
                            for (int i = 0; i < d; i++) {
                                result[at++] = 2;
                                result[at++] = ver++;
                                result[at++] = 7;
                            }
                            return result;
                        }
                    }
                }
            }
        }
//        for (int a0 = 0; a0 <= 44; a0++) {
//
//        }
//        for (int i = 626; i <= 1275; i++) {
//            if (can[i]) {
//                System.err.println(i);
//            }
//        }
		return new int[0];
    }

    long calls = 0;

    private int[] go(int current, int min, int[] alpha, int remaining, int n, int target) {
        calls++;
        if (calls > 1000000) {
            return null;
        }
        if (current == n) {
            int vertices = 51 - remaining;
            int result = (vertices - n - 1) * (vertices - n) / 2 + ((n + 1) / 2) * (n / 2);
            for (int i : alpha) {
                result -= i * (i - 1) / 2;
            }
            if (result != target) {
                return null;
            }
            int[] answer = new int[3 * vertices - 2];
            answer[0] = vertices;
            int at = 1;
            int ver = 1;
            for (int i = 0; i < n; i++) {
                answer[at++] = 0;
                answer[at++] = ver++;
                answer[at++] = (i < n / 2) ? 4 : 7;
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < alpha[i]; j++) {
                    answer[at++] = i + 1;
                    answer[at++] = ver++;
                    answer[at++] = (i < n / 2) ? 7 : 4;
                }
            }
            return answer;
        }
        for (int i = min; i * (n - current) <= remaining; i++) {
            alpha[current] = i;
            int[] result = go(current + 1, i, alpha, remaining - i, n, target);
            if (result != null) {
                return result;
            }
        }
        return null;
    }
}
