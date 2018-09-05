package on2018_09.on2018_09_01_IIT_BHU.PrimeMaker;



import static net.egork.numbers.IntegerUtils.generatePrimalityTable;

public class PrimeMaker {
    public int minimalOperation(int n) {
        if (n == 1000000) {
            return 1;
        }
        boolean[] isPrime = generatePrimalityTable(1000000);
        if (isPrime[n]) {
            return 0;
        }
        int by = 1;
        while (n >= by) {
            int up = n / (10 * by);
            int down = n % by;
            if (isPrime[up * by + down]) {
                return 1;
            }
            for (int i = 0; i < 10; i++) {
                if (isPrime[10 * up * by + i * by + down]) {
                    return 1;
                }
            }
            by *= 10;
        }
        return 2;
    }
}
