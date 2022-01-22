package model.structures;

public interface GraphPath {
    Node getRandomNode();//should never return first node

    Node getRandomNodeDifferentThat(int nodeId1);//should never return first node

    GraphPath getCopyWithSwappedNodes(int nodeId1, int nodeId2, int[][] adjacencyMatrix) throws Exception;//this method should throw exception when first node in path is being swapped

    int getSumOfWages();

    int getSize();

    Node getFirstNode();
}
