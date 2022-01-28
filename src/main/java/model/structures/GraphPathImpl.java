package model.structures;

import model.simulatedannealing.Simulator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class GraphPathImpl implements GraphPath {
    final static Logger logger = LoggerFactory.getLogger(Simulator.class);
    private Graph graph;
    private static Random random = new Random();
    private ArrayList<Node> path;

    public GraphPathImpl(Graph graph) {
        path = new ArrayList<>();
        this.graph = graph;
    }

    public GraphPathImpl(Graph graph, ArrayList<Node> path) {
        this.path = path;
        this.graph = graph;
    }

    public static void setRandom(Random theRandom)
    {
        random = theRandom;
    }

    @Override
    public void addToPath(Node node) {
        Node theNewNode = new Node(node);

        path.add(theNewNode);
    }

    @Override
    public String toString() {
        return "GraphPathImpl{" +
                "path=" + path +
                '}';
    }

    @Override
    public Node getRandomNode() {
        int positionInPath = Math.max(1, random.nextInt(path.size()));

        return path.get(positionInPath);
    }

    @Override
    public Node getRandomNodeDifferentThat(int nodeId) {

        Node n = getRandomNode();

        if (n.getId() == nodeId)
            return getRandomNodeDifferentThat(nodeId);

        return n;
    }

    @Override
    public GraphPath getCopyWithSwappedNodes(int nodeId1, int nodeId2) {
        return graph.getPathWithSwappedNodes(this, nodeId1, nodeId2);

    }

    public int getSumOfWages() {
        return this.graph.getEdgeSumForNodeIdList(this.getNodeIds());
    }

    private List<Integer> getNodeIds() {
        return path.stream().map(Node::getId).collect(Collectors.toList());
    }

    @Override
    public int getSize() {
        return path.size();
    }

    @Override
    public Node getFirstNode() {
        return path.get(0);
    }

    @Override
    public Node getSecondNode() {
        return path.get(1);
    }

    @Override
    public void removeFirstNode() {
        path.remove(0);
    }

    @Override
    public int getFirstEdge() {
        return graph.getEdgeBetweenNodes(path.get(0), path.get(1));
    }

    public Node getLastNode() {
        return path.get(path.size() - 1);
    }

    @Override
    public int getIllnessCases() {
        int illnessCases = 0;

        for (Node n : path)
            illnessCases += n.getIllnessCases();

        return illnessCases;
    }

    @Override
    public List<Node> getPath() {
        return path;
    }

    @Override
    public void updateGraph(Graph graph) {
        this.graph = graph;
    }

    @Override
    public int getEdgeBetweenNodes(Node node1, Node node2) {
        return this.graph.getEdgeBetweenNodes(node1, node2);
    }

    @Override
    public int predictIllnessCases() {
        int deliveryTime = path.get(0).getDeliveryTime();
        int predictedIllnessCases = path.get(0).predictIllnessCases(deliveryTime);

        for(int i = 1; i < path.size(); i++)
        {
            deliveryTime += graph.getEdgeBetweenNodes(path.get(i - 1), path.get(i));
            predictedIllnessCases += path.get(i).predictIllnessCases(deliveryTime);
        }

        return predictedIllnessCases;
    }

    @Override
    public int getLastEdge() {
        if (path.size() > 2) {
            throw new RuntimeException("You shouldn't get last wage from path bigger then two nodes");
        }

        return graph.getEdgeBetweenNodes(path.get(path.size() - 2), path.get(path.size() - 1));
    }
}
