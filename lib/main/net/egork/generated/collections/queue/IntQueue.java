package net.egork.generated.collections.queue;

import net.egork.generated.collections.*;

public interface IntQueue extends IntCollection {
    default public int peek() {
        return first();
    }

    public int poll();
}
