package on2014_12.on2014_12_11_Single_Round_Match_641.ShufflingCardsDiv1;



import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;

import java.util.Arrays;

public class ShufflingCardsDiv1 {
    public int shuffle(int[] permutation) {
        MiscUtils.decreaseByOne(permutation);
        int size = permutation.length;
        if (Arrays.equals(permutation, ArrayUtils.createOrder(size))) {
            return 0;
        }
        int bad = 0;
        for (int i = 0; i < size; i += 2) {
            if (permutation[i] >= (size >> 1)) {
                bad++;
            }
        }
        int per = size >> 2;
        if (bad == per) {
            return 2;
        }
		return 1 + Math.max(2, (bad + per - 1) / per);
    }
}
