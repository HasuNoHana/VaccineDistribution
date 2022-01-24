package model.structures;

import model.simulatedAnnealing.Simulator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.stream.Collectors;

public class GraphPathImpl implements GraphPath {
    final static Logger logger = LoggerFactory.getLogger(Simulator.class);

    private ArrayList<Node> path;
    private ArrayList<Integer> weights;
    private static Random random = new Random(8064995938733697569L);
    public GraphPathImpl() {
        path = new ArrayList<>();
        weights = new ArrayList<>();
    }

    private GraphPathImpl(ArrayList<Integer> weights, ArrayList<Node> path)
    {
        this.weights = weights;
        this.path = path;
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
//        logger.debug("Adding following node to path {}", node);
        addToPath(node, 0);
    }

    public ArrayList<Node> getPath() {
        return path;
    }

    @Override
    public String toString() {
        return "GraphPathImpl{" +
                "path=" + path +
                '}';
    }

    @Override
    public Node getRandomNode() {
        //Random random = new Random();

        int positionInPath = Math.max(1, random.nextInt(path.size()));

        return path.get(positionInPath);
    }

    @Override
    public Node getRandomNodeDifferentThat(int nodeId) {

        Node n = getRandomNode();

        if(n.getId() == nodeId)
            return getRandomNodeDifferentThat(nodeId);

        return n;
    }

    public GraphPath getCopyWithSwappedNodes(int nodeId1, int nodeId2, int[][] adjacencyMatrix) {

        ArrayList<Node> nodeArrayList = new ArrayList<>();

        for (Node n : path)
            nodeArrayList.add(new Node(n));

        ArrayList<Node> nodesToSwap = nodeArrayList.
                stream().
                filter(n -> n.getId() == nodeId1 || n.getId() == nodeId2).
                collect(Collectors.toCollection(ArrayList::new));

        if(nodeArrayList.indexOf(nodesToSwap.get(0)) == 0 || nodeArrayList.indexOf(nodesToSwap.get(1)) == 0)
            throw new RuntimeException("TODO POPRAW MNIE PATRYK!"); //xdddddddddd

        if(nodesToSwap.size() == 2)
            Collections.swap(nodeArrayList, nodeArrayList.indexOf(nodesToSwap.get(0)), nodeArrayList.indexOf(nodesToSwap.get(1)));

        int sumOfWages = 0;
        ArrayList<Integer> wages = new ArrayList<>();

        for (int i = 1; i < nodeArrayList.size(); i++)
        {
            int wage = adjacencyMatrix[nodeArrayList.get(i).getId()][nodeArrayList.get(i - 1).getId()];
            sumOfWages += wage;
            wages.add(wage);
        }

        return new GraphPathImpl(wages, nodeArrayList);
    }

    public int getSumOfWages()
    {
        int sum = 0;

        for(Integer i : weights)
            sum += i;

        return sum;
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

        for(Node n : path)
            illnessCases += n.getIllnessCases();

        return illnessCases;
    }

    @Override
    public int getLastEdge() {
        /*if (weights.size() > 1) {
            throw new RuntimeException("You shouldn't get last wage from path bigger then two nodes");// TODO fix this implementation
        }*/

        return weights.get(weights.size() - 1);
    }
}
