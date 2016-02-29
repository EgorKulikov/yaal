package on2016_02.on2016_02_28_Grand_Prix_of_Bashkortostan_2016.H___Hierarchy;



import net.egork.collections.map.CPPMap;
import net.egork.collections.map.Counter;
import net.egork.datetime.Date;
import net.egork.generated.collections.pair.IntIntPair;
import net.egork.misc.Factory;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;

import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskH {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        NavigableSet<Employee> all = new TreeSet<>();
        Map<Integer, NavigableSet<Employee>> departments = new CPPMap<>(new Factory<NavigableSet<Employee>>() {
            @Override
            public NavigableSet<Employee> create() {
                return new TreeSet<>();
            }
        });
        Map<Integer, List<Employee>> byId = new CPPMap<>(new Factory<List<Employee>>() {
            @Override
            public List<Employee> create() {
                return new ArrayList<>();
            }
        });
        Counter<Integer> localId = new Counter<>();
        for (int i = 0; i < n; i++) {
            int t = in.readInt();
            int d = in.readInt();
            NavigableSet<Employee> department = departments.get(d);
            if (t == 1) {
                String name = in.readString();
                String date = in.readString();
                int dOB = Integer.parseInt(date.substring(6)) * 10000 + Integer.parseInt(date.substring(3, 5)) * 100 +
                        Integer.parseInt(date.substring(0, 2));
                localId.add(d);
                Employee employee = new Employee(d, dOB, (int)(long)localId.get(d), name);
                all.add(employee);
                department.add(employee);
                byId.get(d).add(employee);
            } else {
                int k = in.readInt();
                Employee employee = byId.get(d).get(k - 1);
                all.remove(employee);
                department.remove(employee);
            }
            out.printLine(getFirstName(all), getFirstName(department));
        }
    }

    protected String getFirstName(NavigableSet<Employee> department) {
        return department.isEmpty() ? "Vacant" : department.first().name;
    }

    static class Employee implements Comparable<Employee> {
        int department;
        int dOB;
        int localId;
        String name;

        public Employee(int department, int dOB, int localId, String name) {
            this.department = department;
            this.dOB = dOB;
            this.localId = localId;
            this.name = name;
        }

        @Override
        public int compareTo(Employee o) {
            if (dOB != o.dOB) {
                return dOB - o.dOB;
            }
            if (department != o.department) {
                return department - o.department;
            }
            return localId - o.localId;
        }
    }
}
