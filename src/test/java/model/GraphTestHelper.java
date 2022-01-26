package model;

import model.structures.*;

import java.util.List;

public class GraphTestHelper {
    public static final int EDGE_WEIGHT_01 = 1;
    public static final int EDGE_WEIGHT_12 = 2;
    public static final int EDGE_WEIGHT_23 = 3;
    public static final int EDGE_WEIGHT_02 = 20;
    public static final int EDGE_WEIGHT_03 = 30;
    public static final int EDGE_WEIGHT_13 = 10;
    public static final int EDGE_WEIGHT_13_NEW = 1;
    public static final int EDGE_WEIGHT_12_NEW = 7;

    public static final int EDGE_5WEIGHT_01 = 1;
    public static final int EDGE_5WEIGHT_02 = 20;
    public static final int EDGE_5WEIGHT_03 = 30;
    public static final int EDGE_5WEIGHT_04 = 40;
    public static final int EDGE_5WEIGHT_12 = 21;
    public static final int EDGE_5WEIGHT_13 = 31;
    public static final int EDGE_5WEIGHT_13_NEW = 1;
    public static final int EDGE_5WEIGHT_14 = 41;
    public static final int EDGE_5WEIGHT_23 = 32;
    public static final int EDGE_5WEIGHT_24 = 42;
    public static final int EDGE_5WEIGHT_34 = 43;
    public static final int EDGE_5WEIGHT_34_NEW = 1;

    public static final Node NODE_0 = new Node(0, 0, 0);
    public static final Node NODE_1 = new Node(1, 0, 0);
    public static final Node NODE_2 = new Node(2, 0, 0);
    public static final Node NODE_3 = new Node(3, 0, 0);
    public static final Node NODE_4 = new Node(4, 0, 0);

    protected GraphImpl getStaticGraph() {
        List<Node> nodes = List.of(NODE_0, NODE_1, NODE_2, NODE_3);
        AdjacencyMatrix adjacencyMatrix = new AdjacencyMatrix(4);
        adjacencyMatrix.setEdge(NODE_0.getId(), NODE_1.getId(), EDGE_WEIGHT_01);
        adjacencyMatrix.setEdge(NODE_0.getId(), NODE_2.getId(), EDGE_WEIGHT_02);
        adjacencyMatrix.setEdge(NODE_0.getId(), NODE_3.getId(), EDGE_WEIGHT_03);
        adjacencyMatrix.setEdge(NODE_1.getId(), NODE_2.getId(), EDGE_WEIGHT_12);
        adjacencyMatrix.setEdge(NODE_1.getId(), NODE_3.getId(), EDGE_WEIGHT_13);
        adjacencyMatrix.setEdge(NODE_2.getId(), NODE_3.getId(), EDGE_WEIGHT_23);

        DummyEdgesChangeStrategy constantSumOfEdgesStrategy = new DummyEdgesChangeStrategy();

        return new GraphImpl(nodes, adjacencyMatrix, constantSumOfEdgesStrategy);
    }

    protected AdjacencyMatrix getBeforeAdjacencyMatrix() {
        AdjacencyMatrix beforeAdjacencyMatrix = new AdjacencyMatrix(4);

        beforeAdjacencyMatrix.setEdge(NODE_0.getId(), NODE_1.getId(), EDGE_WEIGHT_01);
        beforeAdjacencyMatrix.setEdge(NODE_0.getId(), NODE_2.getId(), EDGE_WEIGHT_02);
        beforeAdjacencyMatrix.setEdge(NODE_0.getId(), NODE_3.getId(), EDGE_WEIGHT_03);
        beforeAdjacencyMatrix.setEdge(NODE_1.getId(), NODE_2.getId(), EDGE_WEIGHT_12);
        beforeAdjacencyMatrix.setEdge(NODE_1.getId(), NODE_3.getId(), EDGE_WEIGHT_13);
        beforeAdjacencyMatrix.setEdge(NODE_2.getId(), NODE_3.getId(), EDGE_WEIGHT_23);

        return beforeAdjacencyMatrix;
    }

    protected AdjacencyMatrix getAfterAdjacencyMatrix() {
        AdjacencyMatrix afterAdjacencyMatrix = new AdjacencyMatrix(4);

        afterAdjacencyMatrix.setEdge(NODE_0.getId(), NODE_1.getId(), EDGE_WEIGHT_01);
        afterAdjacencyMatrix.setEdge(NODE_0.getId(), NODE_2.getId(), EDGE_WEIGHT_02);
        afterAdjacencyMatrix.setEdge(NODE_0.getId(), NODE_3.getId(), EDGE_WEIGHT_03);
        afterAdjacencyMatrix.setEdge(NODE_1.getId(), NODE_2.getId(), EDGE_WEIGHT_12_NEW);
        afterAdjacencyMatrix.setEdge(NODE_1.getId(), NODE_3.getId(), EDGE_WEIGHT_13_NEW);
        afterAdjacencyMatrix.setEdge(NODE_2.getId(), NODE_3.getId(), EDGE_WEIGHT_23);

        return afterAdjacencyMatrix;
    }

    protected GraphPathImpl getSimplePath(GraphImpl graph) {
        GraphPathImpl simpleGraphPath = new GraphPathImpl(graph);

        simpleGraphPath.addToPath(NODE_0);
        simpleGraphPath.addToPath(NODE_2);
        simpleGraphPath.addToPath(NODE_1);
        simpleGraphPath.addToPath(NODE_3);

        return simpleGraphPath;
    }

    protected AdjacencyMatrix getFirstAdjacencyMatrix() {
        AdjacencyMatrix afterAdjacencyMatrix = new AdjacencyMatrix(5);

        afterAdjacencyMatrix.setEdge(NODE_0.getId(), NODE_1.getId(), EDGE_5WEIGHT_01);
        afterAdjacencyMatrix.setEdge(NODE_0.getId(), NODE_2.getId(), EDGE_5WEIGHT_02);
        afterAdjacencyMatrix.setEdge(NODE_0.getId(), NODE_3.getId(), EDGE_5WEIGHT_03);
        afterAdjacencyMatrix.setEdge(NODE_0.getId(), NODE_4.getId(), EDGE_5WEIGHT_04);
        afterAdjacencyMatrix.setEdge(NODE_1.getId(), NODE_2.getId(), EDGE_5WEIGHT_12);
        afterAdjacencyMatrix.setEdge(NODE_1.getId(), NODE_3.getId(), EDGE_5WEIGHT_13);
        afterAdjacencyMatrix.setEdge(NODE_1.getId(), NODE_4.getId(), EDGE_5WEIGHT_14);
        afterAdjacencyMatrix.setEdge(NODE_2.getId(), NODE_3.getId(), EDGE_5WEIGHT_23);
        afterAdjacencyMatrix.setEdge(NODE_2.getId(), NODE_4.getId(), EDGE_5WEIGHT_24);
        afterAdjacencyMatrix.setEdge(NODE_3.getId(), NODE_4.getId(), EDGE_5WEIGHT_34);

        return afterAdjacencyMatrix;
    }

    protected GraphPathImpl getSimple5Path(GraphImpl graph) {
        GraphPathImpl simpleGraphPath = new GraphPathImpl(graph);

        simpleGraphPath.addToPath(NODE_0);
        simpleGraphPath.addToPath(NODE_2);
        simpleGraphPath.addToPath(NODE_1);
        simpleGraphPath.addToPath(NODE_4);
        simpleGraphPath.addToPath(NODE_3);

        return simpleGraphPath;
    }
}
