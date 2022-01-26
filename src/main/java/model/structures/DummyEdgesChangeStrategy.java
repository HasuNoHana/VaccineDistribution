package model.structures;

public class DummyEdgesChangeStrategy implements EdgesChangeStrategy {
    @Override
    public AdjacencyMatrix updateEdges(AdjacencyMatrix adjacencyMatrix) {
        return adjacencyMatrix;
    }
}
