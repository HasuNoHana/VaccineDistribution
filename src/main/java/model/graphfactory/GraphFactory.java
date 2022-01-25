package model.graphfactory;

import model.structures.AdjacencyMatrix;
import model.structures.Graph;
import model.structures.GraphImpl;
import model.structures.Node;

import java.util.ArrayList;

public class GraphFactory {

    private AdjacencyMatrix adjacencyMatrix;
    private ArrayList<Node> nodeArrayList;

    public GraphFactory() {
        adjacencyMatrix = new AdjacencyMatrix();
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
        return new GraphImpl(nodeArrayList, adjacencyMatrix);
    }
}