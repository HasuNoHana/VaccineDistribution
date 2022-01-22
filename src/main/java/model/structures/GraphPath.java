package model.structures;

public interface GraphPath {
    int getRandomNode();//TODO should never return first node

    int getRandomNodeDiffrentThat(int nodeId1);//TODO should never return first node

    GraphPath getCopyWithSwapedNodes(int nodeId1, int nodeId2);// TODO this method should throw exception when first node in path is being swapped

    int getSumOfWages();

    int getSize();

    Node getFirstNode();
}
