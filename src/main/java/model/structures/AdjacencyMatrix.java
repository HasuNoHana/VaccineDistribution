package model.structures;

public class AdjacencyMatrix {
    private int[][] adjacanceMatrix;

    public AdjacencyMatrix(int[][] adjacanceMatrix) {
        this.adjacanceMatrix = adjacanceMatrix;
    }

    public AdjacencyMatrix() {
        this.adjacanceMatrix = new int[100][100]; //TODO check if is automatilcy growing
    }

    public int getEdgeWeight(int firstNodeId, int secondNodeId) {
        return adjacanceMatrix[firstNodeId][secondNodeId];
    }

    public int[][] getMatrix() {
        return adjacanceMatrix;  //TODO remove me
    }

    public void setEdge(int firstNodeId, int secondNodeId, int weight) {
        adjacanceMatrix[firstNodeId][secondNodeId] = weight;
        adjacanceMatrix[secondNodeId][firstNodeId] = weight;
    }
}
