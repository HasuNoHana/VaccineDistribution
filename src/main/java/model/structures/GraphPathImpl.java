package model.structures;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import java.util.stream.Collectors;

public class GraphPathImpl implements GraphPath {

    private ArrayList<Node> path;
    private int sumOfWages;
    private ArrayList<Integer> wages;
    private int[][] adjacencyMatrix;

    public GraphPathImpl(int[][] adjacencyMatrix)
    {
        path = new ArrayList<>();
        wages = new ArrayList<>();
        sumOfWages = 0;
        this.adjacencyMatrix = adjacencyMatrix;
    }

    private GraphPathImpl(int[][] adjacencyMatrix, int sumOfWages, ArrayList<Integer> wages, ArrayList<Node> path)
    {
        this.adjacencyMatrix = adjacencyMatrix;
        this.sumOfWages = sumOfWages;
        this.wages = wages;
        this.path = path;
    }

    public void addToPath(Node node, int wage)
    {
        Node theNewNode = new Node(node);

        path.add(theNewNode);
        wages.add(wage);
        sumOfWages += wage;
    }

    public ArrayList<Node> getPath() {
        return path;
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

        if(n.getId() == nodeId)
            return getRandomNodeDifferentThat(nodeId);

        return n;
    }

    public GraphPath getCopyWithSwappedNodes(int nodeId1, int nodeId2) throws Exception {

        ArrayList<Node> nodeArrayList = new ArrayList<>();

        for(Node n : path)
            nodeArrayList.add(new Node(n));

        ArrayList<Node> nodesToSwap = nodeArrayList.
                stream().
                filter(n -> n.getId() == nodeId1 || n.getId() == nodeId2).
                collect(Collectors.toCollection(ArrayList::new));

        if(nodeArrayList.indexOf(nodesToSwap.get(0)) == 0 || nodeArrayList.indexOf(nodesToSwap.get(1)) == 0)
            throw new Exception("");

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

        return new GraphPathImpl(adjacencyMatrix, sumOfWages, wages, nodeArrayList);
    }

    public int getSumOfWages()
    {
        return sumOfWages;
    }

    @Override
    public int getSize() {
        return path.size();
    }

    @Override
    public Node getFirstNode() {
        return path.get(0);
    }

    public Node getLastNode()
    {
        return path.get(path.size() - 1);
    }

    public int getWageOfNode(Node n)
    {
        return wages.get(path.indexOf(n));
    }
}
