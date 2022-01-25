package model.structures;

public class AdjacencyMatrix {
    private int[][] adjacanceMatrix;

    public AdjacencyMatrix(int[][] adjacanceMatrix) {
        this.adjacanceMatrix = adjacanceMatrix;
    }

    public AdjacencyMatrix() {
        this.adjacanceMatrix = new int[5][5]; //TODO check if is automatilcy growing
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

    public int getNumberOfNodes() {
        return this.adjacanceMatrix.length;
    }

    public int getSumOfEdges() {
        int counter = 0;
        for (int i = 0; i < adjacanceMatrix.length; i++) {
            for (int j = 0; j < adjacanceMatrix.length; j++) {
                counter += adjacanceMatrix[i][j];
            }
        }
        return counter / 2;
    }

    @Override
    public String toString() {
        String matrix = "";
        for (int i = 0; i < adjacanceMatrix.length; i++) {
            for (int j = 0; j < adjacanceMatrix.length; j++) {
                matrix += adjacanceMatrix[i][j] + " ";
            }
            matrix += "\n";
        }
        return "AdjacencyMatrix{" + "\n" +
                matrix +
                '}';
    }
}
