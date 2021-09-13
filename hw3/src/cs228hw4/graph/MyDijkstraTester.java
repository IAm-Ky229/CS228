package cs228hw4.graph;

public class MyDijkstraTester {
	public static void main(String[] args) {
		MyDiGraphConstructor<String> testGraph = new MyDiGraphConstructor<>(15);

		testGraph.addVertex("A");
		testGraph.addVertex("B");
		testGraph.addVertex("C");
		testGraph.addVertex("D");
		testGraph.addVertex("E");
		testGraph.addVertex("F");
		testGraph.addVertex("G");
		testGraph.addVertex("H");
		testGraph.addVertex("I");
		testGraph.addVertex("J");
		testGraph.addVertex("K");
		testGraph.addVertex("L");

		testGraph.addEdge("A", "B", 2);
		testGraph.addEdge("B", "C", 3);
		testGraph.addEdge("C", "D", 5);
		testGraph.addEdge("D", "E", 2);
		testGraph.addEdge("C", "E", 9);
		testGraph.addEdge("E", "F", 1);
		testGraph.addEdge("E", "G", 3);
		testGraph.addEdge("C", "H", 7);
		testGraph.addEdge("E", "I", 10);
		testGraph.addEdge("G", "J", 11);
		testGraph.addEdge("I", "H", 2);
		testGraph.addEdge("I", "J", 5);
		testGraph.addEdge("I", "L", 4);
		testGraph.addEdge("L", "J", 0);
		testGraph.addEdge("I", "K", 9);
		testGraph.addEdge("K", "H", 3);

		CS228Dijkstra<String> testDijkstra = new CS228Dijkstra<>(testGraph);
		testDijkstra.run("A");

		System.out.println("SHORTEST DISTANCE TO E, SHOULD BE 12: " + testDijkstra.getShortestDistance("E"));
		System.out.println("SHORTEST DISTANCE TO F, SHOULD BE 13: " + testDijkstra.getShortestDistance("F"));
		System.out.println("SHORTEST DISTANCE TO C, SHOULD BE 5: " + testDijkstra.getShortestDistance("C"));

		System.out.println("SHORTEST DISTANCE TO K, SHOULD BE 31: " + testDijkstra.getShortestDistance("K"));
		System.out.println("SHORTEST DISTANCE TO J, SHOULD BE 26: " + testDijkstra.getShortestDistance("J"));
		System.out.println("SHORTEST DISTANCE TO I, SHOULD BE 22: " + testDijkstra.getShortestDistance("I"));

		System.out.println("SHORTEST DISTANCE TO A, SHOULD BE 0: " + testDijkstra.getShortestDistance("A"));

		System.out.println("SHORTEST PATH TO E, SHOULD BE A, B, C, D, E: " + testDijkstra.getShortestPath("E"));
		System.out.println("SHORTEST PATH TO F, SHOULD BE A, B, C, D, E, F: " + testDijkstra.getShortestPath("F"));
		System.out.println("SHORTEST PATH TO C, SHOULD BE A, B, C: " + testDijkstra.getShortestPath("C"));

		System.out.println("SHORTEST PATH TO K, SHOULD BE A, B, C, D, E, I, K: " + testDijkstra.getShortestPath("K"));
		System.out
				.println("SHORTEST PATH TO J, SHOULD BE A, B, C, D, E, I, L, J: " + testDijkstra.getShortestPath("J"));
		System.out.println("SHORTEST PATH TO I, SHOULD BE A, B, C, D, E, I: " + testDijkstra.getShortestPath("I"));

		System.out.println("SHORTEST PATH TO A, SHOULD BE A: " + testDijkstra.getShortestPath("A"));

		MyDiGraphConstructor<String> testGraph2 = new MyDiGraphConstructor<>(10);

		testGraph2.addVertex("1");
		testGraph2.addVertex("2");
		testGraph2.addVertex("3");
		testGraph2.addVertex("4");
		testGraph2.addVertex("5");

		testGraph2.addEdge("1", "2", 1);
		testGraph2.addEdge("2", "3", 1);
		testGraph2.addEdge("3", "4", 1);
		testGraph2.addEdge("4", "5", 5);
		testGraph2.addEdge("1", "3", 3);
		testGraph2.addEdge("3", "5", 3);
		testGraph2.addEdge("1", "5", 2);

		CS228Dijkstra<String> testDijkstra2 = new CS228Dijkstra<>(testGraph2);
		testDijkstra2.run("1");
		// testDijkstra2.run(null);

		System.out.println("SHORTEST DISTANCE TO 2, SHOULD BE 1: " + testDijkstra2.getShortestDistance("2"));
		System.out.println("CORRESPONDING SHORTEST PATH: " + testDijkstra2.getShortestPath("2"));

		System.out.println("SHORTEST DISTANCE TO 3, SHOULD BE 2: " + testDijkstra2.getShortestDistance("3"));
		System.out.println("CORRESPONDING SHORTEST PATH: " + testDijkstra2.getShortestPath("3"));

		System.out.println("SHORTEST DISTANCE TO 4, SHOULD BE 3: " + testDijkstra2.getShortestDistance("4"));
		System.out.println("CORRESPONDING SHORTEST PATH: " + testDijkstra2.getShortestPath("4"));

		System.out.println("SHORTEST DISTANCE TO 5, SHOULD BE 2: " + testDijkstra2.getShortestDistance("5"));
		System.out.println("CORRESPONDING SHORTEST PATH: " + testDijkstra2.getShortestPath("5"));

		System.out.println("SHORTEST DISTANCE TO 1, SHOULD BE 0: " + testDijkstra2.getShortestDistance("1"));
		System.out.println("CORRESPONDING SHORTEST PATH: " + testDijkstra2.getShortestPath("1"));

	}
}
