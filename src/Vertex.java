public class Vertex implements Identifiable<String>, Comparable<Vertex> {
    private final String name;

    public Vertex(String name) {
        this.name = name;
    }

    @Override
    public String getId() {
        return name;
    }

    @Override
    public int compareTo(Vertex o) {
        return name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return name;
    }
}
