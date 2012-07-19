package on2012_6_5.equivalentsuffixtries;



import net.egork.chelper.task.Test;
import net.egork.chelper.tester.Verdict;
import net.egork.collections.map.TreeCounter;
import net.egork.utils.io.InputReader;

import java.util.*;

public class EquivalentSuffixTriesChecker {
	public Verdict check(InputReader input, InputReader expected, InputReader actual) {
		return Verdict.UNDECIDED;
	}

	public double getCertainty() {
		return 0;
	}

	public Collection<? extends Test> generateTests() {
        TreeCounter<Node> counter = new TreeCounter<Node>();
        Map<Node, String> example = new TreeMap<Node, String>();
        generateStrings("", 'a' - 1, 9, counter, example);
        List<Test> tests = new ArrayList<Test>();
        for (Node node : counter.keySet()) {
            long result = counter.get(node);
            result %= EquivalentSuffixTries.MOD;
            int mask = 0;
            String s = example.get(node);
            for (int i = 0; i < s.length(); i++)
                mask |= 1 << (s.charAt(i) - 'a');
            for (int i = 0; i < Integer.bitCount(mask); i++) {
                result *= 26 - i;
                result %= EquivalentSuffixTries.MOD;
            }
            tests.add(new Test("1\n" + s, Long.toString(result), 0));
        }
		return tests;
//        return Collections.emptyList();
	}

    private void generateStrings(String s, int maxChar, int remainingSteps, TreeCounter<Node> counter, Map<Node, String> example) {
        if (s.length() != 0) {
            Node base = buildTree(s);
            counter.add(base);
            example.put(base, s);
        }
        if (remainingSteps == 0)
            return;
        for (int i = 'a'; i <= maxChar + 1; i++)
            generateStrings(s + ((char)i), Math.max(maxChar, i), remainingSteps - 1, counter, example);
    }

    private Node buildTree(String s) {
        Node base = new Node();
        for (int i = 0; i < s.length(); i++) {
            Node current = base;
            for (int j = i; j < s.length(); j++)
                current = current.go(s.charAt(j));
        }
        base.canonize();
        return base;
    }

    static class Node implements Comparable<Node> {
        Map<Character, Node> children = new HashMap<Character, Node>();
        Node[] sortedChildren;

        Node go(char c) {
            if (children.containsKey(c))
                return children.get(c);
            Node newNode = new Node();
            children.put(c, newNode);
            return newNode;
        }

        void canonize() {
            sortedChildren = children.values().toArray(new Node[children.size()]);
            for (Node node : sortedChildren)
                node.canonize();
            Arrays.sort(sortedChildren);
        }

        public int compareTo(Node o) {
            if (children.size() != o.children.size())
                return children.size() - o.children.size();
            for (int i = 0; i < sortedChildren.length; i++) {
                int value = sortedChildren[i].compareTo(o.sortedChildren[i]);
                if (value != 0)
                    return value;
            }
            return 0;
        }
    }
}
