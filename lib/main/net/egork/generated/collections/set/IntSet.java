package net.egork.generated.collections.set;

import net.egork.generated.collections.*;

public interface IntSet extends IntCollection {
    @Override
    default public int count(int value) {
        return contains(value) ? 1 : 0;
    }
}
