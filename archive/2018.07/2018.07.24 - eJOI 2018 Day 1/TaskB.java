package net.egork;

import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.generated.collections.pair.IntIntPair;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.max;

public class TaskB {
    int answerSize = MAX_VALUE;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.readString();
        String t = in.readString();
        IntList sPos = new IntArrayList();
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != s.charAt(i - 1)) {
                sPos.add(i);
            }
        }
        IntList tPos = new IntArrayList();
        for (int i = 1; i < t.length(); i++) {
            if (t.charAt(i) != t.charAt(i - 1)) {
                tPos.add(i);
            }
        }
        if (s.charAt(s.length() - 1) == t.charAt(t.length() - 1)) {
            if (sPos.size() < tPos.size()) {
                sPos.add(s.length());
            } else {
                tPos.add(t.length());
            }
        }
        List<IntIntPair> answer = null;
        List<IntIntPair> candidate;
        int a;
        int b;
        a = sPos.isEmpty() ? 0 : sPos.get(sPos.size() / 2);
        b = tPos.isEmpty() ? 0 : tPos.get(tPos.size() / 2);
        candidate = solve(s, t, a, b);
        if (candidate != null && (answer == null || candidate.size() < answer.size())) {
            answer = candidate;
        }
        a = sPos.isEmpty() ? 0 : sPos.get((sPos.size() - 1) / 2);
        b = tPos.isEmpty() ? 0 : tPos.get((tPos.size() - 1) / 2);
        candidate = solve(s, t, a, b);
        if (candidate != null && (answer == null || candidate.size() < answer.size())) {
            answer = candidate;
        }
        a = sPos.isEmpty() ? 0 : sPos.get((sPos.size()) / 2);
        b = tPos.isEmpty() ? 0 : tPos.get((tPos.size() - 1) / 2);
        candidate = solve(s, t, a, b);
        if (candidate != null && (answer == null || candidate.size() < answer.size())) {
            answer = candidate;
        }
        a = sPos.isEmpty() ? 0 : sPos.get((sPos.size() - 1) / 2);
        b = tPos.isEmpty() ? 0 : tPos.get((tPos.size()) / 2);
        candidate = solve(s, t, a, b);
        if (candidate != null && (answer == null || candidate.size() < answer.size())) {
            answer = candidate;
        }
        IntList ss = sPos;
        IntList tt = tPos;
        if (s.charAt(s.length() - 1) == t.charAt(t.length() - 1)) {
            if (sPos.size() < tPos.size()) {
                sPos.add(s.length());
            } else {
                tPos.add(t.length());
            }
        }
        a = sPos.isEmpty() ? 0 : sPos.get(sPos.size() / 2);
        b = tPos.isEmpty() ? 0 : tPos.get(tPos.size() / 2);
        candidate = solve(s, t, a, b);
        if (candidate != null && (answer == null || candidate.size() < answer.size())) {
            answer = candidate;
        }
        a = sPos.isEmpty() ? 0 : sPos.get((sPos.size() - 1) / 2);
        b = tPos.isEmpty() ? 0 : tPos.get((tPos.size() - 1) / 2);
        candidate = solve(s, t, a, b);
        if (candidate != null && (answer == null || candidate.size() < answer.size())) {
            answer = candidate;
        }
        a = sPos.isEmpty() ? 0 : sPos.get((sPos.size()) / 2);
        b = tPos.isEmpty() ? 0 : tPos.get((tPos.size() - 1) / 2);
        candidate = solve(s, t, a, b);
        if (candidate != null && (answer == null || candidate.size() < answer.size())) {
            answer = candidate;
        }
        a = sPos.isEmpty() ? 0 : sPos.get((sPos.size() - 1) / 2);
        b = tPos.isEmpty() ? 0 : tPos.get((tPos.size()) / 2);
        candidate = solve(s, t, a, b);
        if (candidate != null && (answer == null || candidate.size() < answer.size())) {
            answer = candidate;
        }
        sPos = ss;
        tPos = tt;
        if (s.charAt(s.length() - 1) == t.charAt(t.length() - 1)) {
            if (sPos.size() > tPos.size()) {
                sPos.add(s.length());
            } else {
                tPos.add(t.length());
            }
        }
        a = sPos.isEmpty() ? 0 : sPos.get(sPos.size() / 2);
        b = tPos.isEmpty() ? 0 : tPos.get(tPos.size() / 2);
        candidate = solve(s, t, a, b);
        if (candidate != null && (answer == null || candidate.size() < answer.size())) {
            answer = candidate;
        }
        a = sPos.isEmpty() ? 0 : sPos.get((sPos.size() - 1) / 2);
        b = tPos.isEmpty() ? 0 : tPos.get((tPos.size() - 1) / 2);
        candidate = solve(s, t, a, b);
        if (candidate != null && (answer == null || candidate.size() < answer.size())) {
            answer = candidate;
        }
        a = sPos.isEmpty() ? 0 : sPos.get((sPos.size()) / 2);
        b = tPos.isEmpty() ? 0 : tPos.get((tPos.size() - 1) / 2);
        candidate = solve(s, t, a, b);
        if (candidate != null && (answer == null || candidate.size() < answer.size())) {
            answer = candidate;
        }
        a = sPos.isEmpty() ? 0 : sPos.get((sPos.size() - 1) / 2);
        b = tPos.isEmpty() ? 0 : tPos.get((tPos.size()) / 2);
        candidate = solve(s, t, a, b);
        if (candidate != null && (answer == null || candidate.size() < answer.size())) {
            answer = candidate;
        }
        out.printLine(answer.size());
        for (IntIntPair pair : answer) {
            out.printLine(pair.first, pair.second);
        }
    }

    protected List<IntIntPair> solve(String s, String t, int a, int b) {
        IntList sPos;
        IntList tPos;
        List<IntIntPair> answer = new ArrayList<>();
        if (a != 0 || b != 0) {
            answer.add(new IntIntPair(a, b));
            String ns = t.substring(0, b) + s.substring(a);
            t = s.substring(0, a) + t.substring(b);
            s = ns;
        }
        sPos = new IntArrayList();
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != s.charAt(i - 1)) {
                sPos.add(i);
            }
        }
        tPos = new IntArrayList();
        for (int i = 1; i < t.length(); i++) {
            if (t.charAt(i) != t.charAt(i - 1)) {
                tPos.add(i);
            }
        }
        if (s.charAt(s.length() - 1) == t.charAt(t.length() - 1)) {
            if (sPos.size() < tPos.size()) {
                sPos.add(s.length());
            } else {
                tPos.add(t.length());
            }
        }
        if (max(sPos.size(), tPos.size()) >= answerSize) {
            return null;
        }
        if (sPos.size() < tPos.size()) {
            sPos.inPlaceReverse();
            while (sPos.size() < tPos.size()) {
                sPos.add(0);
            }
            sPos.inPlaceReverse();
        }
        if (tPos.size() < sPos.size()) {
            tPos.inPlaceReverse();
            while (tPos.size() < sPos.size()) {
                tPos.add(0);
            }
            tPos.inPlaceReverse();
        }
        boolean direct = true;
        for (int i = sPos.size() - 1; i >= 0; i--) {
            if (direct) {
                answer.add(new IntIntPair(sPos.get(i), tPos.get(i)));
            } else {
                answer.add(new IntIntPair(tPos.get(i), sPos.get(i)));
            }
            direct = !direct;
        }
        answerSize = Math.min(answerSize, answer.size());
        return answer;
    }
}
