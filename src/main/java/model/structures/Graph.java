package model.structures;

public interface Graph {
    GraphPath getSimplePath();

    /**
     * Returns graph G` with updated edges and number of ill people per node. G` should have one node less (nodeToBeRemoved).
     * @param nodeToBeRemoved node to be removed
     * @return updated Graph
     */
    Graph getUpdatedGraphWithoutNode(Node nodeToBeRemoved);
}
