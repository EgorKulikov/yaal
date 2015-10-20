package net.egork.generated.collections;

import net.egork.generated.collections.iterator.*;

public interface DoubleReversableCollection extends DoubleCollection {
    public DoubleIterator reverseIterator();

    default public double last() {
        return reverseIterator().value();
    }

    default DoubleStream reversed() {
        return () -> reverseIterator();
    }
}
