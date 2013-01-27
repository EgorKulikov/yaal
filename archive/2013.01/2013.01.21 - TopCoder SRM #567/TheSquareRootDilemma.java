package net.egork;

public class TheSquareRootDilemma {
	public int countPairs(int N, int M) {
        long limit = (long)N * M;
        int result = 0;
        for (int i = 1; i <= N; i++) {
            int iCopy = i;
            for (int j = 2; j * j <= iCopy; j++) {
                while (iCopy % (j * j) == 0)
                    iCopy /= j * j;
            }
            for (int j = 1; ; j++) {
                if (iCopy * j * j > M) {
                    result += j - 1;
                    break;
                }
            }
        }
        return result;
	}
}
