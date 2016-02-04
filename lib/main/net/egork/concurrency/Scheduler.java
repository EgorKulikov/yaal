package net.egork.concurrency;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author egor@egork.net
 */
public class Scheduler {
    private final AtomicInteger testsRemaining;
    private final AtomicInteger threadsRemaining;

    public Scheduler(InputReader in, OutputWriter out, TaskFactory factory, int numParallel) {
        try {
            testsRemaining = new AtomicInteger(in.readInt());
            threadsRemaining = new AtomicInteger(numParallel);
            Task[] tasks = new Task[testsRemaining.get()];
            for (int i = 0; i < tasks.length; i++) {
                tasks[i] = factory.newTask();
            }
            for (Task task : tasks) {
                task.read(in);
                new Thread(() -> {
                    boolean freeThread = false;
                    synchronized (this) {
                        do {
                            try {
                                wait(10);
                            } catch (InterruptedException ignored) {
                            }
                            if (threadsRemaining.get() != 0) {
                                synchronized (threadsRemaining) {
                                    if (threadsRemaining.get() != 0) {
                                        threadsRemaining.decrementAndGet();
                                        freeThread = true;
                                    }
                                }
                            }
                        } while (!freeThread);
                    }
                    task.solve();
                    System.err.println(testsRemaining.decrementAndGet());
                    threadsRemaining.incrementAndGet();
                }).start();
            }
            synchronized (this) {
                while (testsRemaining.get() > 0) {
                    wait(10);
                }
            }
            for (int i = 0; i < tasks.length; i++) {
                tasks[i].write(out, i + 1);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
