package net.egork;

import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.collections.intcollection.IntSet;

import java.util.Arrays;

public class CliqueGraph {
    public long calcSum(int N, int[] V, int[] sizes) {
		IntList[] inCliques = new IntList[N];
		IntList[] cliques = new IntList[sizes.length];
		for (int i = 0; i < N; i++) {
			inCliques[i] = new IntArrayList();
		}
		for (int i = 0; i < sizes.length; i++) {
			cliques[i] = new IntArrayList();
		}
		int at = 0;
		for (int i = 0; i < sizes.length; i++) {
			for (int j = at; j < at + sizes[i]; j++) {
				inCliques[V[j]].add(i);
				cliques[i].add(V[j]);
			}
			at += sizes[i];
		}
		boolean[] wasClique = new boolean[sizes.length];
		boolean[] wasVertex = new boolean[N];
		int[] distance = new int[sizes.length];
		int[] queue = new int[sizes.length];
		long answer = 0;
		for (int i = 0; i < N; i++) {
			Arrays.fill(wasClique, false);
			Arrays.fill(wasVertex, false);
			wasVertex[i] = true;
			int size = 0;
			for (int j = 0; j < inCliques[i].size(); j++) {
				int clique = inCliques[i].get(j);
				wasClique[clique] = true;
				distance[clique] = 1;
				queue[size++] = clique;
			}
			for (int j = 0; j < size; j++) {
				int clique = queue[j];
				for (int k = 0; k < cliques[clique].size(); k++) {
					int vertex = cliques[clique].get(k);
					if (wasVertex[vertex]) {
						continue;
					}
					wasVertex[vertex] = true;
					if (vertex > i) {
						answer += distance[clique];
					}
					for (int l = 0; l < inCliques[vertex].size(); l++) {
						int nextClique = inCliques[vertex].get(l);
						if (!wasClique[nextClique]) {
							wasClique[nextClique] = true;
							queue[size++] = nextClique;
							distance[nextClique] = distance[clique] + 1;
						}
					}
				}
			}
		}
		return answer;
    }
}
