package net.egork.timus;

import net.egork.utils.solver.Solver;
import net.egork.utils.io.inputreader.InputReader;

import java.io.PrintWriter;

public class Task1306 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		int[] heap = new int[n / 2 + 2];
		int heapSize = 0;
		for (int i = 0; i < n; i++) {
			int number = in.readInt();
			push(heap, heapSize++, number);
			if (heapSize == heap.length)
				pop(heap, --heapSize);
		}
		long first = actualValue(pop(heap, --heapSize));
		long second;
		if (n % 2 == 0)
			second = actualValue(pop(heap, --heapSize));
		else
			second = first;
		long answer = first + second;
		out.println((answer / 2) + "." + (answer % 2 * 5));
	}

	private int pop(int[] heap, int size) {
		int result = heap[0];
		int index = 0;
		heap[0] = heap[size];
		while (true) {
			int child = 2 * index + 1;
			if (child >= size)
				return result;
			if (child + 1 < size && actualValue(heap[child + 1]) > actualValue(heap[child]))
				child++;
			if (actualValue(heap[index]) >= actualValue(heap[child]))
				return result;
			heap[index] = heap[child];
			heap[child] = heap[size];
			index = child;
		}
	}

	private long actualValue(int value) {
		long result = value;
		if (result < 0)
			result += 1L << 32;
		return result;
	}


	private void push(int[] heap, int index, int number) {
		heap[index] = number;
		while (index != 0) {
			int nextIndex = (index - 1) >> 1;
			if (actualValue(heap[nextIndex]) >= actualValue(heap[index]))
				return;
			heap[index] = heap[nextIndex];
			heap[nextIndex] = number;
			index = nextIndex;
		}
	}
}

