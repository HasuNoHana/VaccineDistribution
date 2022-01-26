package model.structures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static model.structures.GraphPathImpl.logger;

public class GraphImpl implements Graph {

    private final HashMap<Integer, Node> nodes;
    private AdjacencyMatrix adjacencyMatrix;
    private int iterationStep;
    private EdgesChangeStrategy edgesChangeStrategy;

    private GraphImpl(AdjacencyMatrix adjacencyMatrix, HashMap<Integer, Node> nodes, EdgesChangeStrategy edgesChangeStrategy) {
        this.adjacencyMatrix = adjacencyMatrix;
        this.nodes = nodes;
        this.iterationStep = 0;
        this.edgesChangeStrategy = edgesChangeStrategy;
    }

    public GraphImpl(List<Node> nodeArrayList, AdjacencyMatrix adjacencyMatrix, EdgesChangeStrategy edgesChangeStrategy) {
        this.adjacencyMatrix = adjacencyMatrix;

        nodes = new HashMap<>();

        for (Node node : nodeArrayList)
            nodes.put(node.getId(), node);

        this.iterationStep = 0;
        this.edgesChangeStrategy = edgesChangeStrategy;
    }

    @Override
    public GraphPath getPathWithSwappedNodes(GraphPath graphPath, int nodeId1, int nodeId2) {
        ArrayList<Node> nodeArrayList = new ArrayList<>();

        for (Node n : graphPath.getPath())
            nodeArrayList.add(new Node(n));

        ArrayList<Node> nodesToSwap = nodeArrayList.
                stream().
                filter(n -> n.getId() == nodeId1 || n.getId() == nodeId2).
                collect(Collectors.toCollection(ArrayList::new));

        if (nodeArrayList.indexOf(nodesToSwap.get(0)) == 0 || nodeArrayList.indexOf(nodesToSwap.get(1)) == 0)
            throw new RuntimeException("TODO POPRAW MNIE PATRYK!"); //xdddddddddd

        if (nodesToSwap.size() == 2)
            Collections.swap(nodeArrayList, nodeArrayList.indexOf(nodesToSwap.get(0)), nodeArrayList.indexOf(nodesToSwap.get(1)));

        ArrayList<Integer> weights = new ArrayList<>();

        for (int i = 1; i < nodeArrayList.size(); i++) {
            int weight = adjacencyMatrix.getMatrix()[nodeArrayList.get(i).getId()][nodeArrayList.get(i - 1).getId()];
            weights.add(weight);
        }

        return new GraphPathImpl(this, weights, nodeArrayList) {
        };
    }

    @Override
    public int getEdgeSumForNodeIdList(List<Integer> graphPath) {
        int sum = 0;
        for (int i = 0; i < graphPath.size() - 1; i++) {
            Integer firstNodeId = graphPath.get(i);
            Integer secondNodeId = graphPath.get(i + 1);
            sum += adjacencyMatrix.getEdgeWeight(firstNodeId, secondNodeId);
        }
        return sum;
    }

    @Override
    public int getEdgeBetweenNodes(Node node, Node node1) {
        return adjacencyMatrix.getEdgeWeight(node.getId(), node1.getId());
    }

    /*public int getWage(int firstNodeId, int secondNodeId) {
        return adjacencyMatrix.getEdgeWeight(firstNodeId, secondNodeId);
    }*/

    public Graph getSubgraphWithUnvisitedNodes() {
        HashMap<Integer, Node> subgraphNodes = new HashMap<>();

        for (Node n : nodes.values()) {
            if (!n.getIsVaccinated()) {
                subgraphNodes.put(n.getId(), n);
            }
        }

        return new GraphImpl(adjacencyMatrix, subgraphNodes, this.edgesChangeStrategy);
    }

    @Override
    public GraphPath getRandomPath() {
        ArrayList<Node> pathNodes = new ArrayList<>();

        for (Node n : nodes.values())
            if (!n.getIsVaccinated())
                pathNodes.add(new Node(n));

        Collections.shuffle(pathNodes);

        GraphPathImpl graphPath = new GraphPathImpl(this);

        for (Node pathNode : pathNodes) graphPath.addToPath(pathNode);

        return graphPath;
    }

    @Override
    public Graph getUpdatedGraphWithoutNode(Node nodeToBeRemoved) {
        this.adjacencyMatrix = this.edgesChangeStrategy.updateEdges(this.adjacencyMatrix);
        logger.info("Updating Matrix to " + this.adjacencyMatrix);
        return getSubgraphWithUnvisitedNodes();
    }
}
