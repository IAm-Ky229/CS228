package cs228hw4.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MyDiGraphConstructor<V> implements DiGraph<V> {
	HashMap<V, Integer> destinations = new HashMap<>();
	HashMap<V, Integer> costs = new HashMap<>();

	public class Edge {
		V src;
		V dest;
		int weight;

		public Edge(V src, V dest, int weight) {
			this.src = src;
			this.dest = dest;
			this.weight = weight;
		}
	}

	public class GraphIterator implements Iterator<V> {
		int pos;

		public GraphIterator() {
			pos = -1;
		}

		@Override
		public boolean hasNext() {
			if (pos + 1 < vertices.size()) {
				return true;
			}
			return false;
		}

		@Override
		public V next() {
			if (hasNext()) {
				return vertices.get(++pos);
			}
			throw new IndexOutOfBoundsException();
		}
	}

	int V;
	HashMap<V, ArrayList<Edge>> EdgeMaps;
	ArrayList<V> vertices;

	public MyDiGraphConstructor(int V) {
		this.V = V;
		EdgeMaps = new HashMap<>();
		vertices = new ArrayList<>();
	}

	public void addVertex(V vertex) {
		if ((vertices.size() < V) && !(vertices.contains(vertex))) {
			vertices.add(vertex);
		}
	}

	public void addEdge(V src, V dest, int cost) {
		ArrayList<Edge> edges = new ArrayList<>();
		int found = -1;
		for (int i = 0; i < vertices.size(); i++) {
			if (vertices.get(i).equals(src)) {
				found = i;
			}
		}
		if (found != -1) {
			edges = EdgeMaps.get(vertices.get(found));
			if (edges == null) {
				edges = new ArrayList<>();
			}
			Edge toAdd = new Edge(src, dest, cost);
			edges.add(toAdd);
			EdgeMaps.put(vertices.get(found), edges);
		}
	}

	@Override
	public Set<? extends V> getNeighbors(V vertex) {
		if (vertex == null) {
			throw new IllegalArgumentException();
		}
		HashSet<V> ret = new HashSet<>();
		int found = -1;
		for (int i = 0; i < vertices.size(); i++) {
			if (vertices.get(i).equals(vertex)) {
				found = i;
			}
		}
		if (found != -1) {
			ArrayList<Edge> edges = new ArrayList<>();
			edges = EdgeMaps.get(vertices.get(found));
			if (edges != null) {
				for (int j = 0; j < edges.size(); j++) {
					ret.add(edges.get(j).dest);
				}
			}
		} else {
			throw new IllegalArgumentException();
		}
		return ret;
	}

	@Override
	public int getEdgeCost(V start, V end) {
		if (start == null || end == null) {
			throw new IllegalArgumentException();
		}
		int edgeCost = 0;
		int found = -1;
		for (int i = 0; i < vertices.size(); i++) {
			if (vertices.get(i).equals(start)) {
				found = i;
			}
		}
		if (found != -1) {
			ArrayList<Edge> edges = new ArrayList<>();
			edges = EdgeMaps.get(vertices.get(found));
			int found2 = -1;
			if (edges != null) {
				for (int j = 0; j < edges.size(); j++) {
					if (edges.get(j).dest.equals(end)) {
						found2 = j;
					}
				}
			}
			if (found2 != -1) {
				if (edges != null) {
					edgeCost = edges.get(found2).weight;
				}
			} else {
				throw new IllegalArgumentException();
			}
		} else {
			throw new IllegalArgumentException();
		}
		return edgeCost;
	}

	public boolean contains(V object) {
		return vertices.contains(object);
	}

	@Override
	public int numVertices() {
		return vertices.size();
	}

	@Override
	public Iterator<V> iterator() {
		return new GraphIterator();
	}

}
