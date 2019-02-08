import java.util.Arrays;

class StockSpanner {
    private int[] value = new int[10];
    private int[] span = new int[10];
    private int size;

    public StockSpanner() {

    }

    public int next(int price) {
        int currentSpan = 1;
        while (size > 0 && value[size - 1] <= price) {
            currentSpan += span[size - 1];
            size--;
        }
        if (size == value.length) {
            value = Arrays.copyOf(value, 2 * size);
            span = Arrays.copyOf(span, 2 * size);
        }
        value[size] = price;
        span[size++] = currentSpan;
        return currentSpan;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */