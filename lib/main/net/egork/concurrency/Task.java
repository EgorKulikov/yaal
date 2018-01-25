package net.egork.concurrency;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

/**
 * @author egor@egork.net
 */
public interface Task {
    public void read(InputReader in);

    public void solve();

    public void write(OutputWriter out, int testNumber);
}
