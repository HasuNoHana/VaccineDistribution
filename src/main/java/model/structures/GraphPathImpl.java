package model.structures;

import model.simulatedAnnealing.Simulator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GraphPathImpl implements GraphPath {
    final static Logger logger = LoggerFactory.getLogger(Simulator.class);
    private Graph graph;

    private ArrayList<Node> path;
    private ArrayList<Integer> weights;

    public GraphPathImpl(Graph graph) {
        path = new ArrayList<>();
        weights = new ArrayList<>();
        this.graph = graph;
    }

    public GraphPathImpl(Graph graph, ArrayList<Integer> weights, ArrayList<Node> path) {
        this.weights = weights;
        this.path = path;
        this.graph = graph;
    }

    @Override
    public void addToPath(Node node, int weight) {
        Node theNewNode = new Node(node);

        path.add(theNewNode);
        if(weight != 0)
            weights.add(weight);
    }

    @Override
    public void addToPath(Node node) {
        addToPath(node, 0);
    }

    @Override
    public String toString() {
        return "GraphPathImpl{" +
                "path=" + path +
                '}';
    }

    @Override
    public Node getRandomNode() {
        Random random = new Random();

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
        return weights.stream().mapToInt(Integer::intValue).sum();
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
        weights.remove(0);
    }

    @Override
    public int getFirstEdge() {
        return weights.get(0);
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
    public int getLastEdge() {
        if (weights.size() > 1) {
            throw new RuntimeException("You shouldn't get last wage from path bigger then two nodes");// TODO fix this implementation
        }

        return weights.get(weights.size() - 1);
    }
}
