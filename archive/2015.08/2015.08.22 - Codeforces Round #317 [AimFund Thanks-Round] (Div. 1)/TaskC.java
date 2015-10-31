package net.egork;

import net.egork.collections.map.EHashMap;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;

public class TaskC {
    static class Formula implements Ready {
        Map<Variable, Boolean> type = new EHashMap<>();
        boolean satisfied;

        @Override
        public boolean ready() {
            return type.size() <= 1;
        }
    }

    static class Variable implements Ready {
        Formula first;
        Formula second;

        boolean value;

        void addFormula(Formula formula) {
            if (first == null) {
                first = formula;
            } else {
                second = formula;
            }
        }

        void removeFormula(Formula formula) {
            if (first == formula) {
                first = second;
                second = null;
            } else if (second == formula) {
                second = null;
            }
        }

        @Override
        public boolean ready() {
            return second == null || first.type.get(this).equals(second.type.get(this));
        }
    }

    interface Ready {
        boolean ready();
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int[][] v = new int[n][];
        for (int i = 0; i < n; i++) {
            v[i] = IOUtils.readIntArray(in, in.readInt());
        }
        Formula[] formulas = new Formula[n];
        Variable[] variables = new Variable[m];
        for (int i = 0; i < m; i++) {
            variables[i] = new Variable();
        }
        for (int i = 0; i < n; i++) {
            formulas[i] = new Formula();
            for (int j = 0; j < v[i].length; j++) {
                int id = Math.abs(v[i][j]) - 1;
                formulas[i].type.put(variables[id], v[i][j] > 0);
                variables[id].addFormula(formulas[i]);
            }
        }
        Queue<Ready> queue = new ArrayDeque<>();
        int processed = 0;
        for (int i = 0; i < n; i++) {
            if (formulas[i].ready()) {
                queue.add(formulas[i]);
            }
        }
        for (int i = 0; i < m; i++) {
            if (variables[i].ready()) {
                queue.add(variables[i]);
            }
        }
        while (true) {
            while (!queue.isEmpty()) {
                Ready current = queue.poll();
                if (current instanceof Formula) {
                    Formula formula = (Formula)current;
                    if (formula.satisfied) {
                        continue;
                    }
                    if (formula.type.size() == 0) {
                        out.printLine("NO");
                        return;
                    }
                    formula.satisfied = true;
                    Variable variable = formula.type.keySet().iterator().next();
                    variable.value = formula.type.get(variable);
                    if (variable.ready()) {
                        variable.removeFormula(formula);
                    } else {
                        variable.removeFormula(formula);
                        Formula other = variable.first;
                        other.type.remove(variable);
                        variable.removeFormula(other);
                        if (other.ready()) {
                            queue.add(other);
                        }
                    }
                } else {
                    Variable variable = (Variable) current;
                    if (variable.first == null) {
                        continue;
                    }
                    if (!variable.first.satisfied) {
                        variable.value = variable.first.type.get(variable);
                        variable.first.satisfied = true;
                        for (Variable other : variable.first.type.keySet()) {
                            if (other != variable) {
                                other.removeFormula(variable.first);
                                variable.first.type.remove(other);
                                queue.add(other);
                            }
                        }
                    }
                    if (variable.second != null && !variable.second.satisfied) {
                        variable.value = variable.second.type.get(variable);
                        variable.second.satisfied = true;
                        for (Variable other : variable.second.type.keySet()) {
                            if (other != variable) {
                                other.removeFormula(variable.second);
                                variable.second.type.remove(other);
                                queue.add(other);
                            }
                        }
                    }
                }
            }
            while (processed < n && formulas[processed].satisfied) {
                processed++;
            }
            if (processed == n) {
                break;
            }
            Formula formula = formulas[processed];
            Variable variable = formula.type.keySet().iterator().next();
            for (Variable other : formula.type.keySet()) {
                if (other != variable) {
                    formula.type.remove(other);
                    other.removeFormula(formula);
                    queue.add(other);
                }
            }
            queue.add(formula);
        }
        out.printLine("YES");
        for (int i = 0; i < m; i++) {
            out.print(variables[i].value ? 1 : 0);
        }
        out.printLine();
    }
}
