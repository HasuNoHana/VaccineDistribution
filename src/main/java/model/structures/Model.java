package model.structures;

public class Model {

    private final int nodesAmount;
    private final int iterationAmount;
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
