package model.structures;

public class DummyChangeStrategy implements EdgesChangeStrategy {
    @Override
    public AdjacencyMatrix updateEdges(AdjacencyMatrix adjacencyMatrix) {
        return adjacencyMatrix;
    }
}
