package on2015_04.on2015_04_17_Single_Round_Match_656.ForkliftTruckOperator;


import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.misc.ArrayUtils;

import java.util.Arrays;

public class ForkliftTruckOperator {
    int[] sng;
    int[][] dbl;

    public int getNumber(String pallets) {
        sng = new int[pallets.length() + 1];
        Arrays.fill(sng, -1);
        sng[1] = 0;
        dbl = new int[pallets.length() + 1][pallets.length() + 1];
        ArrayUtils.fill(dbl, -1);
        IntList lenList = new IntArrayList();
        int current = 1;
        char symbol = pallets.charAt(0);
        for (int i = 1; i < pallets.length(); i++) {
            if (pallets.charAt(i) == symbol) {
                current++;
            } else {
                lenList.add(current);
                current = 1;
                symbol = pallets.charAt(i);
            }
        }
        lenList.add(current);
        int answer = Integer.MAX_VALUE;
        int total = pallets.length();
        for (int add = 0; ; add++) {
            while (lenList.get(0) == 0) {
                lenList = lenList.subList(1, lenList.size());
            }
            while (lenList.get(lenList.size() - 1) == 0) {
                lenList = lenList.subList(0, lenList.size() - 1);
            }
            if (lenList.size() == 1) {
                answer = Math.min(answer, solve(lenList.get(0)) + add);
            } else if (lenList.size() == 2) {
                answer = Math.min(answer, solve(lenList.get(0), lenList.get(1)) + add);
            } else if (lenList.size() == 3) {
                answer = Math.min(answer, solve(lenList.get(0), lenList.get(1), lenList.get(2)) + add);
            } else if (lenList.size() == 4) {
                if (lenList.get(0) == lenList.get(2)) {
                    answer = Math.min(answer, solve(lenList.get(0), lenList.get(1) + lenList.get(3)) + 1 + add);
                }
                if (lenList.get(1) == lenList.get(3)) {
                    answer = Math.min(answer, solve(lenList.get(1), lenList.get(0) + lenList.get(2)) + 1 + add);
                }
            } else if (lenList.size() == 5) {
                if (lenList.get(1) == lenList.get(3)) {
                    answer = Math.min(answer, Math.min(
                        solve(lenList.get(0), lenList.get(1), lenList.get(2) + lenList.get(4)),
                        solve(lenList.get(0) + lenList.get(2), lenList.get(1), lenList.get(4))
                    ) + 1 + add);
                }
            }
            if (total % 2 == 0) {
                if (lenList.get(0) >= total / 2) {
                    lenList.set(0, lenList.get(0) - total / 2);
                } else if (lenList.get(lenList.size() - 1) >= total / 2) {
                    lenList.set(lenList.size() - 1, lenList.get(lenList.size() - 1) - total / 2);
                } else {
                    break;
                }
                total /= 2;
            } else {
                break;
            }
        }
        return answer >= Integer.MAX_VALUE / 2 ? -1 : answer;
    }

    private int solve(int first, int second, int third) {
        int answer = Integer.MAX_VALUE / 2;
        if (first == third) {
            answer = Math.min(answer, solve(first, second) + 1);
        }
        if (second <= first || second <= third) {
            answer = Math.min(answer, solve(second, first + third - second) + 1);
        }
        if (second % 2 == 0) {
            answer = Math.min(answer, solve(first, second / 2, third) + 1);
        }
        if (first >= second + third && (first + second + third) % 2 == 0) {
            if (first == second + third) {
                answer = Math.min(answer, solve(second, third) + 1);
            } else {
                answer = Math.min(answer, solve(first - (first + second + third) / 2, second, third) + 1);
            }
        }
        if (third >= first + second && (first + second + third) % 2 == 0) {
            if (third == first + second) {
                answer = Math.min(answer, solve(first, second) + 1);
            } else {
                answer = Math.min(answer, solve(third - (first + second + third) / 2, second, first) + 1);
            }
        }
        return answer;
    }

    private int solve(int first, int second) {
        if (dbl[first][second] != -1) {
            return dbl[first][second];
        }
        if (first == 0 || second == 0) {
            return dbl[first][second] = solve(first + second);
        }
        int answer = Integer.MAX_VALUE;
        if ((first + second) % 2 == 0) {
            answer = solve(Math.min(first, second), (first + second) / 2 - Math.min(first, second)) + 1;
        }
        answer = Math.min(answer, solve(Math.min(first, second), Math.max(first, second) - Math.min(first, second)) + 1);
        if (first % 2 == 0) {
            answer = Math.min(answer, solve(first / 2, second) + 1);
        }
        if (second % 2 == 0) {
            answer = Math.min(answer, solve(first, second / 2) + 1);
        }
        return dbl[first][second] = answer;
    }

    private int solve(int length) {
        if (sng[length] != -1) {
            return sng[length];
        }
        int answer = Integer.MAX_VALUE;
        for (int i = 1; i * 2 <= length; i++) {
            answer = Math.min(answer, solve(i, length - 2 * i) + 1);
        }
        return sng[length] = answer;
    }
}
