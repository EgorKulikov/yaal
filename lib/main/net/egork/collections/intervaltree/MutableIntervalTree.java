package net.egork.collections.intervaltree;

/**
 * @author Egor Kulikov (egorku@yandex-team.ru)
 */
public abstract class MutableIntervalTree<V, D> implements IntervalTree<V, D> {
    protected int size;
    protected V[] value;
    protected D[] delta;
    protected V[] valueResult;
    protected final V neutralValue;
    protected final D neutralDelta;

    protected MutableIntervalTree(int size) {
        this.size = size;
        int nodeCount = Math.max(1, Integer.highestOneBit(size) << 2);
        //noinspection unchecked
        value = (V[])new Object[nodeCount];
        //noinspection unchecked
        valueResult = (V[])new Object[nodeCount];
        //noinspection unchecked
        delta = (D[])new Object[nodeCount];
        neutralValue = createNeutral();
        neutralDelta = neutralDelta();
    }

    protected abstract void joinValue(V root, V left, V right);
    protected abstract D joinDelta(D was, D delta);
    protected abstract void accumulate(V value, D delta, int length);
    protected abstract V createNeutral();
    protected abstract D neutralDelta();

    protected V initValue(int index) {
        return createNeutral();
    }

    public void init() {
        init(0, 0, size - 1);
    }

    private void init(int root, int left, int right) {
        if (left == right) {
            value[root] = initValue(left);
            delta[root] = neutralDelta;
        } else {
            int middle = (left + right) >> 1;
            init(2 * root + 1, left, middle);
            init(2 * root + 2, middle + 1, right);
            joinValue(value[root] = createNeutral(), value[2 * root + 1], value[2 * root + 2]);
            delta[root] = neutralDelta;
        }
    }

    public void update(int from, int to, D delta) {
        update(0, 0, size - 1, from, to, delta);
    }

    private void update(int root, int left, int right, int from, int to, D delta) {
        if (left > to || right < from)
            return;
        if (left >= from && right <= to) {
            this.delta[root] = joinDelta(this.delta[root], delta);
            accumulate(value[root], delta, right - left + 1);
            return;
        }
        this.delta[2 * root + 1] = joinDelta(this.delta[2 * root + 1], this.delta[root]);
        this.delta[2 * root + 2] = joinDelta(this.delta[2 * root + 2], this.delta[root]);
        int middle = (left + right) >> 1;
        accumulate(value[2 * root + 1], this.delta[root], middle - left + 1);
        accumulate(value[2 * root + 2], this.delta[root], right - middle);
        this.delta[root] = neutralDelta;
        update(2 * root + 1, left, middle, from, to, delta);
        update(2 * root + 2, middle + 1, right, from, to, delta);
        joinValue(value[root], value[2 * root + 1], value[2 * root + 2]);
    }

    public V query(int from, int to) {
        return query(0, 0, size - 1, from, to);
    }

    private V query(int root, int left, int right, int from, int to) {
        if (left > to || right < from)
            return neutralValue;
        if (left >= from && right <= to)
            return value[root];
        delta[2 * root + 1] = joinDelta(delta[2 * root + 1], delta[root]);
        delta[2 * root + 2] = joinDelta(delta[2 * root + 2], delta[root]);
        int middle = (left + right) >> 1;
        accumulate(value[2 * root + 1], delta[root], middle - left + 1);
        accumulate(value[2 * root + 2], delta[root], right - middle);
        this.delta[root] = neutralDelta;
        if (valueResult[root] == null)
            valueResult[root] = createNeutral();
        joinValue(valueResult[root], query(2 * root + 1, left, middle, from, to), query(2 * root + 2, middle + 1, right, from, to));
        return valueResult[root];
    }
}
