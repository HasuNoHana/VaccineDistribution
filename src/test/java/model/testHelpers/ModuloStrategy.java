package model.testHelpers;

import model.structures.AdjacencyMatrix;
import model.structures.EdgesChangeStrategy;
import model.structures.Node;

public class ModuloStrategy implements EdgesChangeStrategy {
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
    private int i = 0;

    public ModuloStrategy() {
        this.i = -1;
    }

    protected AdjacencyMatrix getSecondAdjacencyMatrix() {
        AdjacencyMatrix afterAdjacencyMatrix = new AdjacencyMatrix(5);
        afterAdjacencyMatrix.setEdge(NODE_0.getId(), NODE_1.getId(), EDGE_5WEIGHT_01);
        afterAdjacencyMatrix.setEdge(NODE_0.getId(), NODE_2.getId(), EDGE_5WEIGHT_02);
        afterAdjacencyMatrix.setEdge(NODE_0.getId(), NODE_3.getId(), EDGE_5WEIGHT_03);
        afterAdjacencyMatrix.setEdge(NODE_0.getId(), NODE_4.getId(), EDGE_5WEIGHT_04);
        afterAdjacencyMatrix.setEdge(NODE_1.getId(), NODE_2.getId(), EDGE_5WEIGHT_12);
        afterAdjacencyMatrix.setEdge(NODE_1.getId(), NODE_3.getId(), EDGE_5WEIGHT_13_NEW);
        afterAdjacencyMatrix.setEdge(NODE_1.getId(), NODE_4.getId(), EDGE_5WEIGHT_14);
        afterAdjacencyMatrix.setEdge(NODE_2.getId(), NODE_3.getId(), EDGE_5WEIGHT_23);
        afterAdjacencyMatrix.setEdge(NODE_2.getId(), NODE_4.getId(), EDGE_5WEIGHT_24);
        afterAdjacencyMatrix.setEdge(NODE_3.getId(), NODE_4.getId(), EDGE_5WEIGHT_34);
        return afterAdjacencyMatrix;
    }

    protected AdjacencyMatrix getThirdAdjacencyMatrix() {
        AdjacencyMatrix afterAdjacencyMatrix = new AdjacencyMatrix(5);
        afterAdjacencyMatrix.setEdge(NODE_0.getId(), NODE_1.getId(), EDGE_5WEIGHT_01);
        afterAdjacencyMatrix.setEdge(NODE_0.getId(), NODE_2.getId(), EDGE_5WEIGHT_02);
        afterAdjacencyMatrix.setEdge(NODE_0.getId(), NODE_3.getId(), EDGE_5WEIGHT_03);
        afterAdjacencyMatrix.setEdge(NODE_0.getId(), NODE_4.getId(), EDGE_5WEIGHT_04);
        afterAdjacencyMatrix.setEdge(NODE_1.getId(), NODE_2.getId(), EDGE_5WEIGHT_12);
        afterAdjacencyMatrix.setEdge(NODE_1.getId(), NODE_3.getId(), EDGE_5WEIGHT_13_NEW);
        afterAdjacencyMatrix.setEdge(NODE_1.getId(), NODE_4.getId(), EDGE_5WEIGHT_14);
        afterAdjacencyMatrix.setEdge(NODE_2.getId(), NODE_3.getId(), EDGE_5WEIGHT_23);
        afterAdjacencyMatrix.setEdge(NODE_2.getId(), NODE_4.getId(), EDGE_5WEIGHT_24);
        afterAdjacencyMatrix.setEdge(NODE_3.getId(), NODE_4.getId(), EDGE_5WEIGHT_34_NEW);
        return afterAdjacencyMatrix;
    }

    @Override
    public AdjacencyMatrix updateEdges(AdjacencyMatrix adjacencyMatrix) {
        if (i % 2 == 0) {
            i++;
            return getSecondAdjacencyMatrix();
        }
        i++;
        return getThirdAdjacencyMatrix();
    }
}
