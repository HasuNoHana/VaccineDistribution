package model;

import model.structures.AdjacencyMatrix;
import model.structures.GraphImpl;
import model.structures.Node;

import java.util.List;

public class GraphTestHelper {
    public static final int EDGE_WEIGHT_01 = 1;
    public static final int EDGE_WEIGHT_12 = 2;
    public static final int EDGE_WEIGHT_23 = 3;
    public static final int EDGE_WEIGHT_02 = 20;
    public static final int EDGE_WEIGHT_03 = 30;
    public static final int EDGE_WEIGHT_13 = 10;
    public static final Node NODE_0 = new Node(0, 0, 0);
    public static final Node NODE_1 = new Node(1, 0, 0);
    public static final Node NODE_2 = new Node(2, 0, 0);
    public static final Node NODE_3 = new Node(3, 0, 0);

    protected GraphImpl getGraph() {
        List<Node> nodes = List.of(NODE_0, NODE_1, NODE_2, NODE_3);
        AdjacencyMatrix adjactiveMatrix = new AdjacencyMatrix();
        adjactiveMatrix.setEdge(NODE_0.getId(), NODE_1.getId(), EDGE_WEIGHT_01);
        adjactiveMatrix.setEdge(NODE_0.getId(), NODE_2.getId(), EDGE_WEIGHT_02);
        adjactiveMatrix.setEdge(NODE_0.getId(), NODE_3.getId(), EDGE_WEIGHT_03);
        adjactiveMatrix.setEdge(NODE_1.getId(), NODE_2.getId(), EDGE_WEIGHT_12);
        adjactiveMatrix.setEdge(NODE_1.getId(), NODE_3.getId(), EDGE_WEIGHT_13);
        adjactiveMatrix.setEdge(NODE_2.getId(), NODE_3.getId(), EDGE_WEIGHT_23);

        GraphImpl graph = new GraphImpl(nodes, adjactiveMatrix);
        return graph;
    }
}
