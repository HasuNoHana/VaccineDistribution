package model.graphfactory;

import model.structures.*;

import java.util.ArrayList;

public class GraphFactory {

    private AdjacencyMatrix adjacencyMatrix;
    private ArrayList<Node> nodeArrayList;
    private EdgesChangeStrategy edgesChangeStrategy;

    public GraphFactory(long wagesSeed) {
        adjacencyMatrix = new AdjacencyMatrix();
        edgesChangeStrategy = new ConstantSumOfEdgesStrategy(wagesSeed);
    }

    public void addNode(Node node) {
        if (nodeArrayList == null)
            nodeArrayList = new ArrayList<>();

        nodeArrayList.add(node);
    }

    public void setAdjacanceMatrix(int[][] adjacencyMatrix) {
        this.adjacencyMatrix = new AdjacencyMatrix(adjacencyMatrix);
    }

    public Graph build() {
        return new GraphImpl(nodeArrayList, adjacencyMatrix, edgesChangeStrategy);
    }
}