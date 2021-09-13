package cs228hw4.graph;

public class MyDiGraphConstructorTester {
	public static void main(String[] args) {
		MyDiGraphConstructor<String> testGraph = new MyDiGraphConstructor<>(10);

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

		testGraph.addEdge("A", "C", 4);
		testGraph.addEdge("A", "J", 1);
		testGraph.addEdge("A", "D", 10);
		testGraph.addEdge("D", "B", 2);
		testGraph.addEdge("G", "J", 3);
		testGraph.addEdge("J", "G", 6);
		testGraph.addEdge("B", "I", 7);
		testGraph.addEdge("B", "D", 9);

		System.out.println("FROM A TO C, SHOULD BE 4:" + testGraph.getEdgeCost("A", "C"));
		System.out.println("FROM D TO B, SHOULD BE 2:" + testGraph.getEdgeCost("D", "B"));
		System.out.println("FROM G TO J, SHOULD BE 3:" + testGraph.getEdgeCost("G", "J"));

		System.out.println("NEIGHBORS OF A, SHOULD BE C, J, D:" + testGraph.getNeighbors("A"));
		System.out.println("NEIGHBORS OF B, SHOULD BE I, D:" + testGraph.getNeighbors("B"));
		System.out.println("NEIGHBORS OF D, SHOULD BE B:" + testGraph.getNeighbors("D"));
		
		System.out.println(testGraph.destinations.toString());
	}

}
