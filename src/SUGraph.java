import java.util.*;

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
        return 0;
    }

    @Override
    public String getAdjacencyReport() {
        return "";
    }

    @Override
    public boolean isConnected() {
        return false;
    }
}
