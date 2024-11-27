import java.util.*;
import java.util.stream.Collectors;

public class SUGraph<V extends Identifiable<ID>, ID> implements SimpleGraph<V, ID> {
    private final Map<ID, V> vertices = new HashMap<>();
    private final Map<V, Set<V>> neighbours = new HashMap<>();

    public static class Builder<V extends Identifiable<ID>, ID> implements SimpleGraphBuilder<V, ID> {
        // create a num graph under construction
        private final SUGraph<V,ID> suGraph = new SUGraph<>();

        @Override
        public SimpleGraphBuilder<V, ID> addVertices(V... vertices) {
            return null;
        }

        @Override
        public SimpleGraphBuilder<V, ID> addEdges(V vertex, V... neighbours) {
            return null;
        }

        @Override
        public SimpleGraphBuilder<V, ID> addEdges(ID vertexId, ID... neighbourIds) {
            return null;
        }

        @Override
        public SimpleGraph<V, ID> build() {
            return null;
        }
    }


    @Override
    public boolean addVertex(V vertex) {
        return false;
    }

    @Override
    public V getVertex(ID id) {
        return null;
    }

    @Override
    public boolean addEdge(V vertex1, V vertex2) {
        return false;
    }

    @Override
    public boolean addEdge(ID vertexId1, ID vertexId2) {
        return false;
    }

    @Override
    public Collection<V> getVertices() {
        return List.of();
    }

    @Override
    public Collection<V> getNeighbours(V vertex) {
        return List.of();
    }

    @Override
    public Collection<V> getNeighbours(ID vertexId) {
        return List.of();
    }

    @Override
    public int getNumVertices() {
        return 0;
    }

    @Override
    public int getNumEdges() {
        return this.neighbours.values().stream()
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
