package model.structures;

import java.util.*;
import java.util.stream.Collectors;

import static model.structures.GraphPathImpl.logger;

public class GraphImpl implements Graph {

    private final HashMap<Integer, Node> nodes;
    private AdjacencyMatrix adjacencyMatrix;
    private final EdgesChangeStrategy edgesChangeStrategy;
    private static Random randomForShuffle = new Random();

    private GraphImpl(AdjacencyMatrix adjacencyMatrix, HashMap<Integer, Node> nodes, EdgesChangeStrategy edgesChangeStrategy) {
        this.adjacencyMatrix = adjacencyMatrix;
        this.nodes = nodes;
        this.edgesChangeStrategy = edgesChangeStrategy;
    }

    public GraphImpl(List<Node> nodeList, AdjacencyMatrix adjacencyMatrix, EdgesChangeStrategy edgesChangeStrategy) {
        this.adjacencyMatrix = adjacencyMatrix;

        nodes = new HashMap<>();

        for (Node node : nodeList)
            nodes.put(node.getId(), node);

        this.edgesChangeStrategy = edgesChangeStrategy;
    }

    private void updateNodes(int minutes)
    {
        for(Node node : nodes.values())
            node.updateNodeStatistics(minutes);
    }

    public static void setRandomForShuffle(Random random)
    {
        randomForShuffle = random;
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
            throw new RuntimeException("");

        if (nodesToSwap.size() == 2)
            Collections.swap(nodeArrayList, nodeArrayList.indexOf(nodesToSwap.get(0)), nodeArrayList.indexOf(nodesToSwap.get(1)));


        return new GraphPathImpl(this, nodeArrayList);
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

    public Graph getSubgraphWithUnvisitedNodes() {
        HashMap<Integer, Node> subgraphNodes = new HashMap<>();

        for (Node n : nodes.values()) {
            if (!n.getIsVaxDelivered()) {
                subgraphNodes.put(n.getId(), n);
            }
        }

        return new GraphImpl(adjacencyMatrix, subgraphNodes, this.edgesChangeStrategy);
    }

    @Override
    public GraphPath getRandomPath() {
        ArrayList<Node> pathNodes = new ArrayList<>();

        for (Node n : nodes.values())
            if (!n.getIsVaxDelivered())
                pathNodes.add(new Node(n));

        Collections.shuffle(pathNodes, randomForShuffle);

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

    @Override
    public Graph getUpdatedGraphWithoutNode(Node nodeToBeRemoved, int minutes) {
        this.adjacencyMatrix = this.edgesChangeStrategy.updateEdges(this.adjacencyMatrix);
        logger.info("Updating Matrix to " + this.adjacencyMatrix);

        return getSubgraphWithUnvisitedNodes();
    }
}
