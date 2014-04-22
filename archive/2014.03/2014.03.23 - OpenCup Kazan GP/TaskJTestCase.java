package net.egork;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;
import net.egork.utils.io.OutputWriter;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class TaskJTestCase {
    @TestCase
    public Collection<Test> loadTests() {
        List<Test> tests = new ArrayList<Test>();
        Random random = new Random(239);
        int testCount = 0;
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
            tests.add(new Test(sw.toString()));
        }
        return tests;
    }

    @TestCase
    public Collection<Test> accuracyTests() {
        List<Test> tests = new ArrayList<Test>();
        Random random = new Random(239);
        int testCount = 000;
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
            StringWriter swAnswer = new StringWriter();
            OutputWriter outAnswer = new OutputWriter(swAnswer);
			int qty = 100;
			out.printLine(qty);
			Node root = new Node(-1);
			for (int i = 0; i < qty; i++) {
				int type = random.nextInt(3);
				int id = random.nextInt(10);
				if (type == 0) {
					Node at = root.find(id);
					if (at.id == id) {
						outAnswer.printLine("FALSE", Math.max(at.level(), 1));
					} else {
						Node node = new Node(id);
						node.parent = at;
						if (at.id < id)
							at.right = node;
						else
							at.left = node;
						outAnswer.printLine("TRUE", Math.max(node.level(), 1));
					}
					out.printLine("ADD", id);
				} else if (type == 1) {
					Node at = root.find(id);
					if (at.id == id)
						outAnswer.printLine("TRUE", Math.max(at.level(), 1));
					else
						outAnswer.printLine("FALSE", Math.max(at.level(), 1));
					out.printLine("FIND", id);
				} else {
					Node at = root.find(id);
					out.printLine("REMOVE", id);
					if (at.id != id) {
						outAnswer.printLine("FALSE", Math.max(at.level(), 1));
						continue;
					}
					if (at.left == null) {
						outAnswer.printLine("TRUE", Math.max(at.level(), 1));
						if (at.right != null)
							at.right.parent = at.parent;
						if (at.parent.left == at)
							at.parent.left = at.right;
						else
							at.parent.right = at.right;
					} else if (at.right == null) {
						outAnswer.printLine("TRUE", Math.max(at.level(), 1));
						at.left.parent = at.parent;
						if (at.parent.left == at) {
							at.parent.left = at.left;
						} else
							at.parent.right = at.left;
					} else {
						Node replacement = at.right;
						while (replacement.left != null)
							replacement = replacement.left;
						int result = replacement.level();
						if (replacement == at.right) {
							replacement.left = at.left;
							at.left.parent = replacement;
							replacement.parent = at.parent;
							if (at.parent.left == at)
								at.parent.left = replacement;
							else
								at.parent.right = replacement;
						} else {
							if (at.parent.left == at)
								at.parent.left = replacement;
							else
								at.parent.right = replacement;
							replacement.parent.left = replacement.right;
							if (replacement.right != null)
								replacement.right.parent = replacement.parent;
							replacement.left = at.left;
							replacement.right = at.right;
							at.left.parent = at.right.parent = replacement;
							replacement.parent = at.parent;
						}
						outAnswer.printLine("TRUE", Math.max(result, 1));
					}
				}
			}
            tests.add(new Test(sw.toString(), swAnswer.toString()));
        }
        return tests;
    }

	static class Node {
		int id;
		Node left;
		Node right;
		Node parent;

		Node(int id) {
			this.id = id;
		}

		int level() {
			if (parent == null)
				return 0;
			return parent.level() + 1;
		}

		public Node find(int id) {
			if (this.id == id)
				return this;
			if (id > this.id) {
				if (right != null)
					return right.find(id);
				return this;
			}
			if (left != null)
				return left.find(id);
			return this;
		}
	}
}
