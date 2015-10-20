package net.egork.generated.collections.queue;

import net.egork.generated.collections.*;

public interface CharQueue extends CharCollection {
    default public char peek() {
        return first();
    }

    public char poll();
}
