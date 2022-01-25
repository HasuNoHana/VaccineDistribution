package model.structures;

import java.util.*;
import java.util.stream.Collectors;

public class GraphImpl implements Graph {

    private final HashMap<Integer, Node> nodes;
    private final AdjacencyMatrix adjacencyMatrix;
    private final Random randomForAdjacencyMatrix;
    private final long defaultSeedForWages = 8064995938733697569L;

    private GraphImpl(AdjacencyMatrix adjacencyMatrix, HashMap<Integer, Node> nodes) {
        this.adjacencyMatrix = adjacencyMatrix;
        this.nodes = nodes;
        randomForAdjacencyMatrix = new Random();
    }

    public GraphImpl(List<Node> nodeArrayList, AdjacencyMatrix adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;

        nodes = new HashMap<>();

        for (Node node : nodeArrayList)
            nodes.put(node.getId(), node);

        randomForAdjacencyMatrix = new Random(defaultSeedForWages); //TODO check what value should be used here


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

        int sumOfWages = 0;
        ArrayList<Integer> wages = new ArrayList<>();

        for (int i = 1; i < nodeArrayList.size(); i++) {
            int wage = adjacencyMatrix.getMatrix()[nodeArrayList.get(i).getId()][nodeArrayList.get(i - 1).getId()];
            sumOfWages += wage;
            wages.add(wage);
        }

        return new GraphPathImpl(this, wages, nodeArrayList) {
        };
    }

    public int getWage(int firstNodeId, int secondNodeId) {
        return adjacencyMatrix.getEdgeWeight(firstNodeId, secondNodeId);
    }

    public Graph getSubgraphWithUnvisitedNodes() {
        HashMap<Integer, Node> subgraphNodes = new HashMap<>();

        for (Node n : nodes.values()) {
            if (!n.getIsVaccinated()) {
                subgraphNodes.put(n.getId(), n);
            }
        }

        return new GraphImpl(adjacencyMatrix, subgraphNodes);
    }

    @Override
    public GraphPath getRandomPath() {
        ArrayList<Node> pathNodes = new ArrayList<>();

        for (Node n : nodes.values())
            if (!n.getIsVaccinated())
                pathNodes.add(new Node(n));

        Collections.shuffle(pathNodes);

        GraphPathImpl graphPath = new GraphPathImpl(this);

        for (int i = 0; i < pathNodes.size(); i++)
            graphPath.addToPath(pathNodes.get(i), i != 0 ? getWage(pathNodes.get(i).getId(), graphPath.getLastNode().getId()) : 0);

        return graphPath;
    }

    @Override
    public Graph getUpdatedGraphWithoutNode(Node nodeToBeRemoved) {

        return getSubgraphWithUnvisitedNodes();
    }
}
