package net.egork;

public class SwappingNodes {
    public int[] swapNodes(int[] leaves, int numberOfLeaves) {
        for (int i = 1; 2 * i <= numberOfLeaves; i *= 2) {
            for (int j = 0; j < numberOfLeaves; j += 2 * i) {
                boolean swap = false;
                for (int k = 0; k < i; k++) {
                    if (leaves[j + k] > leaves[j + i + k]) {
                        swap = true;
                        break;
                    } else if (leaves[j + k] < leaves[j + i + k]) {
                        break;
                    }
                }
                if (swap) {
                    for (int k = 0; k < i; k++) {
                        int temp = leaves[j + k];
                        leaves[j + k] = leaves[j + i + k];
                        leaves[j + i + k] = temp;
                    }
                }
            }
        }
        return leaves;
    }
}
