package model.structures;

import java.util.Random;

public class DefaultEdgesStrategy implements EdgesChangeStrategy {
    private final Random randomForAdjacencyMatrix;

    public DefaultEdgesStrategy(long seed) {
        this.randomForAdjacencyMatrix = new Random(seed);
    }

    @Override
    public AdjacencyMatrix updateEdges(AdjacencyMatrix adjacencyMatrix) {
        return new AdjacencyMatrix(AdjacencyMatrixGenerator.generateAdjacencyMatrix(adjacencyMatrix.getNumberOfNodes(), adjacencyMatrix.getSumOfEdges(), randomForAdjacencyMatrix));
    }
}
