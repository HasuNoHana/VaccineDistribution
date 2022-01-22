package model.structures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.stream.Collectors;

public class GraphPathImpl implements GraphPath {

    private ArrayList<Node> nodesInPath;
    private int sumOfWages;
    private ArrayList<Integer> wages;

    public GraphPathImpl()
    {
        nodesInPath = new ArrayList<>();
        wages = new ArrayList<>();
        sumOfWages = 0;
    }

    private GraphPathImpl(int sumOfWages, ArrayList<Integer> wages, ArrayList<Node> nodesInPath)
    {
        this.sumOfWages = sumOfWages;
        this.wages = wages;
        this.nodesInPath = nodesInPath;
    }

    public void addToPath(Node node, int wage)
    {
        Node theNewNode = new Node(node);

        nodesInPath.add(theNewNode);
        if(wage != 0)
            wages.add(wage);
        sumOfWages += wage;
    }

    public void addToPath(Node node)
    {
        addToPath(node, 0);
    }

    public ArrayList<Node> getNodesInPath() {
        return nodesInPath;
    }

    @Override
    public Node getRandomNode() {
        Random random = new Random();

        int positionInPath = Math.max(1, random.nextInt(nodesInPath.size()));

        return nodesInPath.get(positionInPath);
    }

    @Override
    public Node getRandomNodeDifferentThat(int nodeId) {

        Node n = getRandomNode();

        if(n.getId() == nodeId)
            return getRandomNodeDifferentThat(nodeId);

        return n;
    }

    public GraphPath getCopyWithSwappedNodes(int nodeId1, int nodeId2, int[][] adjacencyMatrix) throws Exception {

        ArrayList<Node> nodeArrayList = new ArrayList<>();

        for(Node n : nodesInPath)
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

        return new GraphPathImpl(sumOfWages, wages, nodeArrayList);
    }

    public int getSumOfWages()
    {
        return sumOfWages;
    }

    @Override
    public int getSize() {
        return nodesInPath.size();
    }

    @Override
    public Node getFirstNode() {
        return nodesInPath.get(0);
    }

    public Node getLastNode()
    {
        return nodesInPath.get(nodesInPath.size() - 1);
    }

    public int getWageAfterTheNode(Node node)
    {
        if(nodesInPath.contains(node))
        {
            int nodeIndexInArray = nodesInPath.indexOf(node);

            if(nodeIndexInArray + 1 >= wages.size())
                return -1;

            return wages.get(nodeIndexInArray + 1);
        }
        else
            return -1;
    }
}
