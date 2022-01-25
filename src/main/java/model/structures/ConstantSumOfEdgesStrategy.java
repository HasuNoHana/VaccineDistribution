package model.structures;

import java.util.Random;

public class ConstantSumOfEdgesStrategy implements EdgesChangeStrategy {
    private Random randomForAdjacencyMatrix;

    public ConstantSumOfEdgesStrategy(long randomForAdjacencyMatrix) {
        this.randomForAdjacencyMatrix = new Random(randomForAdjacencyMatrix);
    }

    @Override
    public AdjacencyMatrix updateEdges(AdjacencyMatrix adjacencyMatrix) {
        return new AdjacencyMatrix(AdjacencyMatrixGenerator.generateAdjacencyMatrix(adjacencyMatrix.getNumberOfNodes(), adjacencyMatrix.getSumOfEdges(), randomForAdjacencyMatrix));
    }
}
