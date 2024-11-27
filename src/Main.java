public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Simple Undirected Graph demo program\n");

        SimpleGraph<Vertex,String> graph0 = new SUGraph<>();
        graph0.addVertex(new Vertex("A"));
        graph0.addVertex(new Vertex("B"));
        graph0.addVertex(new Vertex("C"));
        graph0.addVertex(new Vertex("C"));
        graph0.addEdge("A", "B");
        graph0.addEdge("A", "C");
        graph0.addEdge("A", "C");
        graph0.addEdge("B", "D");
        graph0.addEdge("B", "E");
        System.out.println(graph0);
        System.out.println(graph0.getAdjacencyReport());
        System.out.println("isConnected = " + graph0.isConnected() + "\n");

        graph0.addVertex(new Vertex("D"));
        graph0.addVertex(new Vertex("E"));
        graph0.addVertex(new Vertex("F"));
        graph0.addEdge("A", "D");
        graph0.addEdge("C", "D");
        graph0.addEdge("E", "F");
        System.out.println(graph0);
        System.out.println(graph0.getAdjacencyReport());
        System.out.println("isConnected = " + graph0.isConnected() + "\n");

        // Build a graph with vertices that are uniquely identified by a String identifier
        SimpleGraph<Vertex,String> graph = new SUGraph.Builder<Vertex,String>()
                .addVertices(new Vertex("A"), new Vertex("B"), new Vertex("C"), new Vertex("D"), new Vertex("E"))
                .addEdges("A", "B","C")
                .addEdges("B", "C","D","E")
                .addEdges("C", "D","E")
                .addEdges("D", "E")
                .build();

        System.out.println(graph);
        System.out.println(graph.getAdjacencyReport());
        System.out.println("isConnected = " + graph.isConnected() + "\n");

        graph.addVertex(new Vertex("E"));       // duplicate test
        graph.addVertex(new Vertex("F"));
        graph.addVertex(new Vertex("G"));
        graph.addVertex(new Vertex("H"));
        graph.addEdge("F", "G");
        graph.addEdge("G", "H");
        graph.addEdge("H", "I");                // non-existing vertex test

        System.out.println(graph);
        System.out.println(graph.getAdjacencyReport());
        System.out.println("isConnected = " + graph.isConnected() + "\n");

    }
}