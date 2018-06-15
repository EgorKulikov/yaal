package on2018_06.on2018_06_14_2018_TopCoder_Open_Algorithm.SubarrayAverages;



public class SubarrayAverages {
    public double[] findBest(int[] arr) {
        double[] result = new double[arr.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = arr[i];
        }
        for (int i = 0; i < result.length; i++) {
            double best = result[i];
            int to = i;
            double sum = result[i];
            for (int j = i + 1; j < result.length; j++) {
                sum += result[j];
                if (sum / (j - i + 1) < best) {
                    best = sum / (j - i + 1);
                    to = j;
                }
            }
            for (int j = i; j <= to; j++) {
                result[j] = best;
            }
        }
        return result;
    }
}
