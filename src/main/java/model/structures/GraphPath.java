package model.structures;

public interface GraphPath {
    void addToPath(Node node, int wage);

    void addToPath(Node node);

    Node getRandomNode();

    Node getRandomNodeDifferentThat(int nodeId1);

    GraphPath getCopyWithSwappedNodes(int nodeId1, int nodeId2, int[][] adjacencyMatrix);

    int getSumOfWages();

    int getSize();

    Node getFirstNode();

    Node getSecondNode();

    int getFirstEdge();

    void removeFirstNode();
}
