package model.graphfactory;

import model.structures.Graph;
import model.structures.GraphImpl;
import model.structures.Node;

import java.util.ArrayList;

public class GraphFactory {

    private int[][] adjacencyMatrix;
    private ArrayList<Node> nodeArrayList;

    public void addNode(Node node)
    {
        if(nodeArrayList == null)
            nodeArrayList = new ArrayList<>();

        nodeArrayList.add(node);
    }

    public void addWeight(int firstNodeId, int secondNodeId, int weight)
    {
        if(adjacencyMatrix == null)
            adjacencyMatrix = new int[nodeArrayList.size()][nodeArrayList.size()];

        adjacencyMatrix[firstNodeId][secondNodeId] = weight;
        adjacencyMatrix[secondNodeId][firstNodeId] = weight;
    }

    public void addAdjacencyMatrix(int[][] adjacencyMatrix)
    {
        this.adjacencyMatrix = adjacencyMatrix;
    }

    public Graph build() {
        int i = 0;

        for (Node node : nodeArrayList)
            node.setId(i++);

        return new GraphImpl(nodeArrayList, adjacencyMatrix);
    }
}