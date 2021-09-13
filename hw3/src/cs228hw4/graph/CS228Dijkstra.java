package cs228hw4.graph;

import java.util.Set;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;

/**
 * Implementation of Dijkstra's shortest path algorithm.
 * 
 * @author Kylus Pettit-Ehler
 *
 * @param <V> The type of object that will be stored in graph nodes.
 */
public class CS228Dijkstra<V> implements Dijkstra<V> {

	ArrayList<V> OPEN;
	ArrayList<V> CLOSED;
	ArrayList<V> DISCOVERED;
	DiGraph<V> graph;
	HashMap<V, Integer> distances;
	HashMap<V, V> predecessors;
	V startingVertex;

	/**
	 * Constructor that takes in a graph to be processed
	 * 
	 * @param graph The graph to run Dijkstra's on
	 */
	public CS228Dijkstra(DiGraph<V> graph) {
		this.graph = graph;
	}

	/**
	 * Run Dijkstra's from a selected start node. If the node provided to the run
	 * method is null or the graph does not contain the start node, a
	 * RuntimeException is thrown.
	 */
	@Override
	public void run(V start) {
		startingVertex = start;
		OPEN = new ArrayList<>();
		CLOSED = new ArrayList<>();
		DISCOVERED = new ArrayList<>();
		distances = new HashMap<>();
		predecessors = new HashMap<>();
		if (start == null) {
			System.out.println("Starting node cannot be null in the run operation.");
			throw new RuntimeException();
		}
		Iterator<V> iter = graph.iterator();
		boolean containsStart = false;
		while (iter.hasNext()) {
			if (iter.next().equals(start)) {
				containsStart = true;
			}
		}
		if (!containsStart) {
			System.out.println("The given node within run does not exist.");
			throw new RuntimeException();
		}
		// Assign infinity and null values to distances and predecessors
		iter = graph.iterator();
		while (iter.hasNext()) {
			V temp = iter.next();
			distances.put(temp, Integer.MAX_VALUE);
			predecessors.put(temp, null);
		}
		V a = start;
		V aPrev = null;
		OPEN.add(a);
		// Assign the start node appropriate values
		predecessors.put(a, null);
		distances.put(a, 0);
		// While the open array isn't empty
		while (!OPEN.isEmpty()) {
			// Find the minimum edge path to the next node
			V assign = OPEN.get(0);
			if (!(aPrev == null)) {
				int min = Integer.MAX_VALUE;
				try {
					min = graph.getEdgeCost(aPrev, OPEN.get(0));
				} catch (IllegalArgumentException e) {
					// There is not an edge between these nodes
				}
				for (int s = 0; s < OPEN.size(); s++) {
					try {
						if (graph.getEdgeCost(aPrev, OPEN.get(s)) < min) {
							assign = OPEN.get(s);
							min = graph.getEdgeCost(aPrev, OPEN.get(s));
						}
					} catch (IllegalArgumentException e) {
						// There is not an edge between these nodes
					}
				}
			}
			a = assign;
			// Add processed nodes to CLOSED and remove them from OPEN
			CLOSED.add(a);
			OPEN.remove(a);
			// Observe neighbors of the processed node
			if (a != null) {
				Set<? extends V> neighbors = graph.getNeighbors(a);
				Iterator<? extends V> iter2 = neighbors.iterator();
				while (iter2.hasNext()) {
					V temp = iter2.next();
					// if the neighbor isn't DISCOVERED
					if (!DISCOVERED.contains(temp)) {
						// Add it to OPEN and DISCOVERED
						OPEN.add(temp);
						DISCOVERED.add(temp);
					}
					// if the edge cost from the current node to the newly discovered neighbor is
					// less than what is already assigned, overwrite it
					int dist2 = graph.getEdgeCost(a, temp);
					if (distances.get(a) + dist2 < distances.get(temp)) {
						distances.put(temp, distances.get(a) + dist2);
						predecessors.put(temp, a);
					}
				}
			}
			// Be sure to keep track of the previous node to determine which node to go to
			// next
			aPrev = a;
		}
	}

	/**
	 * Get the shortest path from the start node to the node given as an argument
	 * after Dijkstra's has been run. If the node is null or not contained in the
	 * graph, or any other error with finding a predecessor occurs, a
	 * RuntimeException in thrown().
	 */
	@Override
	public List<V> getShortestPath(V vertex) {
		if (vertex == null) {
			System.out.println("Given node was null in getShortestPath operation.");
			throw new RuntimeException();
		}
		Iterator<? extends V> iter = graph.iterator();
		ArrayList<V> nodePath = new ArrayList<>();
		Stack<V> reverseOrder = new Stack<>();
		V start = null;
		while (iter.hasNext()) {
			V temp = iter.next();
			if (temp.equals(vertex)) {
				start = temp;
				break;
			}
		}
		if (start == null) {
			System.out.println("Couldn't find the given node in getShortestPath operation.");
			throw new RuntimeException();
		}
		V trace = start;
		if (predecessors.get(start) == null) {
			// Unless we are asking for the shortest distance to the first node, throw
			// exception
			if (vertex.equals(startingVertex)) {
				nodePath.add(startingVertex);
				return nodePath;
			}
			System.out.println(
					"An error occurred in finding a predecessor (getShortestPath). A predecessor may not exist.");
			throw new RuntimeException();
		}
		// Find all predecessors of the start node to the node that Dijkstra's was run
		// on
		while (trace != null) {
			reverseOrder.push(trace);
			trace = predecessors.get(trace);
		}
		// Reverse the predecessors
		int stackSize = reverseOrder.size();
		for (int j = 0; j < stackSize; j++) {
			nodePath.add(reverseOrder.pop());
		}
		return nodePath;
	}

	/**
	 * Get the shortest distance from the start node to the node given in the
	 * argument after Dijkstra's has been run. If the node is null or not contained
	 * in the graph, or any other error with a predecessor is encountered, a
	 * RuntimeException is thrown.
	 */
	@Override
	public int getShortestDistance(V vertex) {
		if (vertex == null) {
			System.out.println("Given start node was null in getShortestDistance operation.");
			throw new RuntimeException();
		}
		Iterator<? extends V> iter = graph.iterator();
		int cost = 0;
		V start = null;
		while (iter.hasNext()) {
			V temp = iter.next();
			if (temp.equals(vertex)) {
				start = temp;
				break;
			}
		}
		if (start == null) {
			System.out.println("Couldn't find the given node in getShortestDistance operation.");
			throw new RuntimeException();
		}
		V trace = start;
		if (predecessors.get(start) == null) {
			// Unless we are asking for the shortest distance to the first node, throw
			// exception
			if (vertex.equals(startingVertex)) {
				return 0;
			}
			// The node is not connected to any other nodes
			System.out.println(
					"An error occurred in finding a predecessor (getShortestDistance). A predecessor may not exist.");
			throw new RuntimeException();
		}
		V prevTrace = predecessors.get(start);
		// Find all predecessors from the start node to the node that was run with
		// Dijkstra's, adding edge weights along the path
		while (prevTrace != null) {
			cost += graph.getEdgeCost(prevTrace, trace);
			prevTrace = predecessors.get(prevTrace);
			trace = predecessors.get(trace);
		}
		return cost;
	}
}