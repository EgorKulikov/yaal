package net.egork.generated.collections;

import net.egork.generated.collections.iterator.*;

public interface IntReversableCollection extends IntCollection {
    public IntIterator reverseIterator();

    default public int last() {
        return reverseIterator().value();
    }

    default IntStream reversed() {
        return () -> reverseIterator();
    }
}
