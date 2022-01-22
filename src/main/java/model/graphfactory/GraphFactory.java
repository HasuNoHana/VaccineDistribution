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

    public void addWage(int firstNodeId, int secondNodeId, int wage)
    {
        if(adjacencyMatrix == null)
            adjacencyMatrix = new int[nodeArrayList.size()][nodeArrayList.size()];

        adjacencyMatrix[firstNodeId][secondNodeId] = wage;
        adjacencyMatrix[secondNodeId][firstNodeId] = wage;
    }

    Graph build()
    {
        int i = 0;

        for(Node node : nodeArrayList)
            node.setId(i++);

        return new GraphImpl(nodeArrayList, adjacencyMatrix);
    }
}