package net.egork.concurrency;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author egor@egork.net
 */
public class Scheduler {
	private final AtomicInteger testsRemaining;

	public Scheduler(InputReader in, OutputWriter out, TaskFactory factory, int numParallel) {
		try {
			testsRemaining = new AtomicInteger(in.readInt());
			Task[] tasks = new Task[testsRemaining.get()];
			for (int i = 0; i < tasks.length; i++) {
				tasks[i] = factory.newTask();
			}
			for (Task task : tasks) {
				task.read(in);
				new Thread(() -> {
					try {
						Scheduler.this.wait();
						task.solve();
						System.err.println(testsRemaining.decrementAndGet());
						Scheduler.this.notify();
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
				}).start();
			}
			for (int i = 0; i < numParallel; i++) {
				notify();
			}
			while (testsRemaining.get() > 0) {
				testsRemaining.wait(10);
			}
			for (int i = 0; i < tasks.length; i++) {
				tasks[i].write(out, i + 1);
			}
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
