package net.egork.generated.collections;

import net.egork.generated.collections.iterator.LongIterator;

public interface LongReversableCollection extends LongCollection {
    //abstract
    public LongIterator reverseIterator();

    //base
    default public long last() {
        return reverseIterator().value();
    }

    default LongStream reversed() {
        return () -> reverseIterator();
    }
}
