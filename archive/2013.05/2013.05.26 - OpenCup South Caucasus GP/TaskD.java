package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class TaskD {
	class Vertex {
		List<Vertex> adj = new ArrayList<Vertex>();
		Vertex killedVia;
		int remDeg;
		Vertex nextOne;
		Vertex prevOne;
		Vertex nextMany;
		Vertex prevMany;

		public void insertIt() {
			if (nextOne != null) {
				nextOne.prevOne = prevOne;
				prevOne.nextOne = nextOne;
				nextOne = null;
				prevOne = null;
			}
			if (nextMany != null) {
				nextMany.prevMany = prevMany;
				prevMany.nextMany = nextMany;
				prevMany = null;
				nextMany = null;
			}
			if (remDeg == 0) return;
			if (remDeg == 1) {
				nextOne = sentinelOne.nextOne;
				prevOne = sentinelOne;
				nextOne.prevOne = this;
				prevOne.nextOne = this;
			} else {
				nextMany = sentinelMany.nextMany;
				prevMany = sentinelMany;
				nextMany.prevMany = this;
				prevMany.nextMany = this;
			}
		}

		public void kill(Vertex via) {
			if (killedVia != null) throw new RuntimeException();
			killedVia = via;
			for (Vertex v : adj)
				if (v.killedVia == null) {
					--v.remDeg;
					--remDeg;
					v.insertIt();
				}
			if (remDeg != 0) throw new RuntimeException();
			insertIt();
		}

		public void restore(Vertex via) {
			if (killedVia != via) throw new RuntimeException();
			killedVia = null;
			for (Vertex v : adj)
				if (v.killedVia == null) {
					++v.remDeg;
					++remDeg;
					v.insertIt();
				}
			insertIt();
		}
	}
	
	Vertex sentinelOne;
	Vertex sentinelMany;
	int res;
	
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		int k = in.readInt();
		Vertex[] v = new Vertex[n];
		for (int i = 0; i < n; ++i) {
			v[i] = new Vertex();
		}
		for (int i = 0; i < m; ++i) {
			Vertex a = v[in.readInt() - 1];
			Vertex b = v[in.readInt() - 1];
			a.adj.add(b);
			b.adj.add(a);
		}
		sentinelOne = new Vertex();
		sentinelOne.nextOne = sentinelOne;
		sentinelOne.prevOne = sentinelOne;
		sentinelMany = new Vertex();
		sentinelMany.nextMany = sentinelMany;
		sentinelMany.prevMany = sentinelMany;
		for (Vertex vv : v) {
			vv.remDeg = vv.adj.size();
			vv.insertIt();
		}
		res = k + 1;
		rec(0);
		out.printLine(res > k ? "Impossible" : ("" + res));
    }

	private void rec(int spent) {
		if (spent >= res) return;
		if (sentinelOne.nextOne == sentinelOne && sentinelMany.nextMany == sentinelMany) {
			res = spent;
			return;
		}
		if (spent + 1 >= res) return;
		if (sentinelOne.nextOne != sentinelOne) {
			Vertex leaf = sentinelOne.nextOne;
			Vertex adj = null;
			for (Vertex v : leaf.adj)
				if (v.killedVia == null) {
					adj = v;
					break;
				}
			adj.kill(adj);
			rec(spent + 1);
			adj.restore(adj);
			return;
		}
		{
			Vertex some = sentinelMany.nextMany;
			some.kill(some);
			rec(spent + 1);
			some.restore(some);
			int cnt = 0;
			for (Vertex v : some.adj)
				if (v.killedVia == null) {
					v.kill(some);
					++cnt;
					if (spent + cnt >= res) break;
				}
			rec(spent + cnt);
			for (Vertex v : some.adj)
				if (v.killedVia == some) {
					v.restore(some);
					--cnt;
				}
			if (cnt != 0) throw new RuntimeException();
		}
	}
}
