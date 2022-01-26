package model.structures;

public class AdjacencyMatrix {
    private int[][] adjacencyMatrix;

    public AdjacencyMatrix(int[][] adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
    }

    public AdjacencyMatrix(int numberOfNodes) {
        this.adjacencyMatrix = new int[numberOfNodes][numberOfNodes]; //TODO check if is automatilcy growing
    }

    public int getEdgeWeight(int firstNodeId, int secondNodeId) {
        return adjacencyMatrix[firstNodeId][secondNodeId];
    }

    public int[][] getMatrix() {
        return adjacencyMatrix;  //TODO remove me
    }

    public void setEdge(int firstNodeId, int secondNodeId, int weight) {
        adjacencyMatrix[firstNodeId][secondNodeId] = weight;
        adjacencyMatrix[secondNodeId][firstNodeId] = weight;
    }

    public int getNumberOfNodes() {
        return this.adjacencyMatrix.length;
    }

    public int getSumOfEdges() {
        int sum = 0;
        for (int[] matrix : adjacencyMatrix) {
            for (int j = 0; j < adjacencyMatrix.length; j++) {
                sum += matrix[j];
            }
        }
        return sum / 2;
    }

    @Override
    public String toString() {
        StringBuilder matrix = new StringBuilder();
        for (int[] values : adjacencyMatrix) {
            for (int j = 0; j < adjacencyMatrix.length; j++) {
                matrix.append(values[j]).append(" ");
            }
            matrix.append("\n");
        }
        return "AdjacencyMatrix{" + "\n" +
                matrix +
                '}';
    }
}
