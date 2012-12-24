package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Stack;

public class BookExercises {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        Stack<Book> stack = new Stack<Book>();
        for (int i = 0; i < count; i++) {
            int remaining = in.readInt();
            if (remaining == 0) {
                in.readString();
                continue;
            }
            if (remaining != -1) {
                String name = in.readString();
                stack.add(new Book(name, remaining, stack.isEmpty() ? remaining : Math.min(remaining, stack.peek().leastRemaining)));
                continue;
            }
            int thrown = 0;
            while (stack.peek().remaining != stack.peek().leastRemaining) {
                stack.pop();
                thrown++;
            }
            out.printLine(thrown, stack.pop().name);
        }
    }

    class Book {
        final String name;
        final int remaining;
        final int leastRemaining;

        Book(String name, int remaining, int leastRemaining) {
            this.name = name;
            this.remaining = remaining;
            this.leastRemaining = leastRemaining;
        }
    }
}
