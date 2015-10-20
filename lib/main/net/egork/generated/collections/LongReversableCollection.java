package net.egork.generated.collections;

import net.egork.generated.collections.iterator.*;

public interface LongReversableCollection extends LongCollection {
    public LongIterator reverseIterator();

    default public long last() {
        return reverseIterator().value();
    }

    default LongStream reversed() {
        return () -> reverseIterator();
    }
}
