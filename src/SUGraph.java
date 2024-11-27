import java.util.*;
import java.util.stream.Collectors;

public class SUGraph<V extends Identifiable<ID>, ID> implements SimpleGraph<V, ID> {
    private final Map<ID, V> vertices = new HashMap<>();
    private final Map<V, Set<V>> neighbours = new HashMap<>();

    public static class Builder<V extends Identifiable<ID>, ID> implements SimpleGraphBuilder<V, ID> {
        private final SUGraph<V,ID> suGraph = new SUGraph<>();

        @Override
        public SimpleGraphBuilder<V, ID> addVertices(V... vertices) {
            for (V vertex : vertices) {
                this.suGraph.addVertex(vertex);
            }
            return this;
        }

        @Override
        public SimpleGraphBuilder<V, ID> addEdges(V vertex, V... neighbours) {
            for (V neighbour : neighbours) {
                this.suGraph.addEdge(vertex, neighbour);
            }
            return this;
        }

        @Override
        public SimpleGraphBuilder<V, ID> addEdges(ID vertexId, ID... neighbourIds) {
            V vertex = this.suGraph.vertices.get(vertexId);
            for (ID neighbourId : neighbourIds) {
                V neighbour = this.suGraph.vertices.get(neighbourId);
                this.suGraph.addEdge(vertex, neighbour);
            }
            return this;
        }

        @Override
        public SimpleGraph<V, ID> build() {
            return suGraph;
        }
    }

    // TODO
    @Override
    public boolean addVertex(V vertex) {
        if (this.vertices.putIfAbsent(vertex.getId(), vertex) == null) {
            this.neighbours.put(vertex, new HashSet<>());
            return true;
        }
        return false;
    }

    // TODO
    @Override
    public V getVertex(ID id) {
        return vertices.get(id);
    }

    // TODO
    @Override
    public boolean addEdge(V vertex1, V vertex2) {
        Set<V> neighbours1 = this.neighbours.get(vertex1);
        Set<V> neighbours2 = this.neighbours.get(vertex2);

        if (neighbours1 == null || neighbours2 == null) {
            return false;
        }

        return neighbours1.add(vertex2) && neighbours2.add(vertex1);
    }

    // TODO
    @Override
    public boolean addEdge(ID vertexId1, ID vertexId2) {
        V vertex1 = this.vertices.get(vertexId1);
        V vertex2 = this.vertices.get(vertexId2);
        return this.addEdge(vertex1, vertex2);
    }

    // TODO
    @Override
    public Collection<V> getVertices() {
        return vertices.values();
    }

    // TODO
    @Override
    public Collection<V> getNeighbours(V vertex) {
        return neighbours.get(vertex);
    }

    // TODO
    @Override
    public Collection<V> getNeighbours(ID vertexId) {
        V vertex = getVertex(vertexId);
        return getNeighbours(vertex);
    }

    // TODO
    @Override
    public int getNumVertices() {
        return vertices.size();
    }

    // TODO
    @Override
    public int getNumEdges() {
        return neighbours.values().stream()
                .mapToInt(Set::size)
                .sum();
    }

    @Override
    public String getAdjacencyReport() {
        return this.getVertices().stream()
                .map(v -> v.getId().toString() + ": " + this.getNeighbours(v).toString())
                .collect(Collectors.joining("\n", "", ""));
    }

    @Override
    public boolean isConnected() {
        V vertex = this.getVertices().stream().findAny().orElse(null);
        return vertex != null &&
                this.countConnected(vertex, new HashSet<>()) == this.getNumVertices();

    }

    private int countConnected(V intermediate, Set<V> visited) {
        if (visited.contains(intermediate)) return 0;
        visited.add(intermediate);
        int count = 1;
        for (V neighbour : this.getNeighbours(intermediate)) {
            count += countConnected(neighbour, visited);
        }
        return count;
    }

    @Override
    public String toString() {
        return String.format("SUGraph with %d vertices and %d neighbour connections",
                this.getNumVertices(), this.getNumEdges());
    }
}
