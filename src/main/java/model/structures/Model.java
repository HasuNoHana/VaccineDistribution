package model.structures;

public class Model {

    public int getNodesAmount() {
        return nodesAmount;
    }

    public int getIterationAmount() {
        return iterationAmount;
    }

    public int[][][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public void setNodesAmount(int nodesAmount) {
        this.nodesAmount = nodesAmount;
    }

    public void setIterationAmount(int iterationAmount) {
        this.iterationAmount = iterationAmount;
    }

    private int nodesAmount;
    private int iterationAmount;
    private int[][][] adjacencyMatrix;

    public Model(int nodesAmount, int iterationAmount)
    {
        this.nodesAmount = nodesAmount;
        this.iterationAmount = iterationAmount;
    }

    public void setAdjacencyMatrix(int[][][] inputArray)
    {
        adjacencyMatrix = inputArray;
    }


    public int getEdgeValue(int iterationAmount, int nodeNumber1, int nodeNumber2)
    {
        return adjacencyMatrix[iterationAmount][nodeNumber1][nodeNumber2];
    }

}
