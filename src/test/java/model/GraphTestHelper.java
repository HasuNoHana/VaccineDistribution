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
        AdjacencyMatrix adjactiveMatrix = new AdjacencyMatrix();
        adjactiveMatrix.setEdge(NODE_0.getId(), NODE_1.getId(), EDGE_WEIGHT_01);
        adjactiveMatrix.setEdge(NODE_0.getId(), NODE_2.getId(), EDGE_WEIGHT_02);
        adjactiveMatrix.setEdge(NODE_0.getId(), NODE_3.getId(), EDGE_WEIGHT_03);
        adjactiveMatrix.setEdge(NODE_1.getId(), NODE_2.getId(), EDGE_WEIGHT_12);
        adjactiveMatrix.setEdge(NODE_1.getId(), NODE_3.getId(), EDGE_WEIGHT_13);
        adjactiveMatrix.setEdge(NODE_2.getId(), NODE_3.getId(), EDGE_WEIGHT_23);

        DummyChangeStrategy constantSumOfEdgesStrategy = new DummyChangeStrategy();
        GraphImpl graph = new GraphImpl(nodes, adjactiveMatrix, constantSumOfEdgesStrategy);
        return graph;
    }

    protected AdjacencyMatrix getBeforeAdjactencyMatrix() {
        AdjacencyMatrix beforeAdjactiveMatrix = new AdjacencyMatrix();
        beforeAdjactiveMatrix.setEdge(NODE_0.getId(), NODE_1.getId(), EDGE_WEIGHT_01);
        beforeAdjactiveMatrix.setEdge(NODE_0.getId(), NODE_2.getId(), EDGE_WEIGHT_02);
        beforeAdjactiveMatrix.setEdge(NODE_0.getId(), NODE_3.getId(), EDGE_WEIGHT_03);
        beforeAdjactiveMatrix.setEdge(NODE_1.getId(), NODE_2.getId(), EDGE_WEIGHT_12);
        beforeAdjactiveMatrix.setEdge(NODE_1.getId(), NODE_3.getId(), EDGE_WEIGHT_13);
        beforeAdjactiveMatrix.setEdge(NODE_2.getId(), NODE_3.getId(), EDGE_WEIGHT_23);
        return beforeAdjactiveMatrix;
    }

    protected AdjacencyMatrix getAfterAdjactencyMatrix() {
        AdjacencyMatrix afterAdjactiveMatrix = new AdjacencyMatrix();
        afterAdjactiveMatrix.setEdge(NODE_0.getId(), NODE_1.getId(), EDGE_WEIGHT_01);
        afterAdjactiveMatrix.setEdge(NODE_0.getId(), NODE_2.getId(), EDGE_WEIGHT_02);
        afterAdjactiveMatrix.setEdge(NODE_0.getId(), NODE_3.getId(), EDGE_WEIGHT_03);
        afterAdjactiveMatrix.setEdge(NODE_1.getId(), NODE_2.getId(), EDGE_WEIGHT_12_NEW);
        afterAdjactiveMatrix.setEdge(NODE_1.getId(), NODE_3.getId(), EDGE_WEIGHT_13_NEW);
        afterAdjactiveMatrix.setEdge(NODE_2.getId(), NODE_3.getId(), EDGE_WEIGHT_23);
        return afterAdjactiveMatrix;
    }

    protected GraphPathImpl getSimplePath(GraphImpl graph) {
        GraphPathImpl simpleGraphPath = new GraphPathImpl(graph);
        simpleGraphPath.addToPath(NODE_0);
        simpleGraphPath.addToPath(NODE_2);
        simpleGraphPath.addToPath(NODE_1);
        simpleGraphPath.addToPath(NODE_3);
        return simpleGraphPath;
    }

    protected AdjacencyMatrix getFirstAdjactencyMatrix() {
        AdjacencyMatrix afterAdjactiveMatrix = new AdjacencyMatrix();
        afterAdjactiveMatrix.setEdge(NODE_0.getId(), NODE_1.getId(), EDGE_5WEIGHT_01);
        afterAdjactiveMatrix.setEdge(NODE_0.getId(), NODE_2.getId(), EDGE_5WEIGHT_02);
        afterAdjactiveMatrix.setEdge(NODE_0.getId(), NODE_3.getId(), EDGE_5WEIGHT_03);
        afterAdjactiveMatrix.setEdge(NODE_0.getId(), NODE_4.getId(), EDGE_5WEIGHT_04);
        afterAdjactiveMatrix.setEdge(NODE_1.getId(), NODE_2.getId(), EDGE_5WEIGHT_12);
        afterAdjactiveMatrix.setEdge(NODE_1.getId(), NODE_3.getId(), EDGE_5WEIGHT_13);
        afterAdjactiveMatrix.setEdge(NODE_1.getId(), NODE_4.getId(), EDGE_5WEIGHT_14);
        afterAdjactiveMatrix.setEdge(NODE_2.getId(), NODE_3.getId(), EDGE_5WEIGHT_23);
        afterAdjactiveMatrix.setEdge(NODE_2.getId(), NODE_4.getId(), EDGE_5WEIGHT_24);
        afterAdjactiveMatrix.setEdge(NODE_3.getId(), NODE_4.getId(), EDGE_5WEIGHT_34);
        return afterAdjactiveMatrix;
    }

    protected AdjacencyMatrix getSecondAdjactencyMatrix() {
        AdjacencyMatrix afterAdjactiveMatrix = new AdjacencyMatrix();
//        afterAdjactiveMatrix.setEdge(NODE_0.getId(), NODE_1.getId(), EDGE_5WEIGHT_01);
//        afterAdjactiveMatrix.setEdge(NODE_0.getId(), NODE_2.getId(), EDGE_5WEIGHT_02);
//        afterAdjactiveMatrix.setEdge(NODE_0.getId(), NODE_3.getId(), EDGE_5WEIGHT_03);
//        afterAdjactiveMatrix.setEdge(NODE_0.getId(), NODE_4.getId(), EDGE_5WEIGHT_04);
        afterAdjactiveMatrix.setEdge(NODE_1.getId(), NODE_2.getId(), EDGE_5WEIGHT_12);
        afterAdjactiveMatrix.setEdge(NODE_1.getId(), NODE_3.getId(), EDGE_5WEIGHT_13_NEW);
        afterAdjactiveMatrix.setEdge(NODE_1.getId(), NODE_4.getId(), EDGE_5WEIGHT_14);
        afterAdjactiveMatrix.setEdge(NODE_2.getId(), NODE_3.getId(), EDGE_5WEIGHT_23);
        afterAdjactiveMatrix.setEdge(NODE_2.getId(), NODE_4.getId(), EDGE_5WEIGHT_24);
        afterAdjactiveMatrix.setEdge(NODE_3.getId(), NODE_4.getId(), EDGE_5WEIGHT_34);
        return afterAdjactiveMatrix;
    }

    protected AdjacencyMatrix getThirdAdjactencyMatrix() {
        AdjacencyMatrix afterAdjactiveMatrix = new AdjacencyMatrix();
//        afterAdjactiveMatrix.setEdge(NODE_0.getId(), NODE_1.getId(), EDGE_5WEIGHT_01);
//        afterAdjactiveMatrix.setEdge(NODE_0.getId(), NODE_2.getId(), EDGE_5WEIGHT_02);
//        afterAdjactiveMatrix.setEdge(NODE_0.getId(), NODE_3.getId(), EDGE_5WEIGHT_03);
//        afterAdjactiveMatrix.setEdge(NODE_0.getId(), NODE_4.getId(), EDGE_5WEIGHT_04);
//        afterAdjactiveMatrix.setEdge(NODE_1.getId(), NODE_2.getId(), EDGE_5WEIGHT_12);
//        afterAdjactiveMatrix.setEdge(NODE_1.getId(), NODE_3.getId(), EDGE_5WEIGHT_13_NEW);
//        afterAdjactiveMatrix.setEdge(NODE_1.getId(), NODE_4.getId(), EDGE_5WEIGHT_14);
        afterAdjactiveMatrix.setEdge(NODE_2.getId(), NODE_3.getId(), EDGE_5WEIGHT_23);
        afterAdjactiveMatrix.setEdge(NODE_2.getId(), NODE_4.getId(), EDGE_5WEIGHT_24);
        afterAdjactiveMatrix.setEdge(NODE_3.getId(), NODE_4.getId(), EDGE_5WEIGHT_34_NEW);
        return afterAdjactiveMatrix;
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
