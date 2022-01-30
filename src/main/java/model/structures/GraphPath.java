package model.structures;

import java.util.List;

public interface GraphPath {
    void addToPath(Node node);

    Node getRandomNode();

    Node getRandomNodeDifferentThat(int nodeId1);

    GraphPath getCopyWithSwappedNodes(int nodeId1, int nodeId2);

    int getSumOfWages();

    int getSize();

    Node getFirstNode();

    Node getSecondNode();

    int getFirstEdge();

    void removeFirstNode();

    int getLastEdge();

    Node getLastNode();

    int getIllnessCases();

    List<Node> getPath();

    void updateGraph(Graph graph);

    int getEdgeBetweenNodes(Node node1, Node node2);

    int predictIllnessCases();
}