package net.egork.generated.collections.queue;

import net.egork.generated.collections.*;

public interface DoubleQueue extends DoubleCollection {
    default public double peek() {
        return first();
    }

    public double poll();
}
