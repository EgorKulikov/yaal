package net.egork.collections.intervaltree;

/**
 * @author Egor Kulikov (egorku@yandex-team.ru)
 */
public abstract class ArrayBasedIntervalTree<V, D> extends SimpleIntervalTree<V, D> {
    private V[] array;

    protected ArrayBasedIntervalTree(V[] array) {
        super(array.length);
        this.array = array;
    }

    @Override
    protected V initValue(int index) {
        return array[index];
    }
}
