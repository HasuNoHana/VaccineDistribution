package model.structures;

import java.util.List;

public interface Graph {
    GraphPath getRandomPath();

    /**
     * Returns graph G` with updated edges and number of ill people per node. G` should have one node less (nodeToBeRemoved).
     *
     * @param nodeToBeRemoved node to be removed
     * @return updated Graph
     */
    Graph getUpdatedGraphWithoutNode(Node nodeToBeRemoved);

    GraphPath getPathWithSwappedNodes(GraphPath graphPath, int nodeId1, int nodeId2);

    int getEdgeSumForNodeIdList(List<Integer> graphPath);

    int getEdgeBetweenNodes(Node node, Node node1);

    Graph getUpdatedGraphWithoutNode(Node nodeToBeRemoved, int minutes);
}
