package net.egork.generated.collections.queue;

import net.egork.generated.collections.*;

public interface DoubleQueue extends DoubleCollection {
    default public double first() {
        return peek();
    }

    public double peek();

    public double poll();
}
