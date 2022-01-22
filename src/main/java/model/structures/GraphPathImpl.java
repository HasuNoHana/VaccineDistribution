package model.structures;

import java.util.ArrayList;

public class GraphPathImpl implements GraphPath {

    private ArrayList<Node> path;
    private int sumOfWages;
    private ArrayList<Integer> wages;

    public GraphPathImpl()
    {
        path = new ArrayList<>();
        wages = new ArrayList<>();
        sumOfWages = 0;
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

    public int getSumOfWages()
    {
        return sumOfWages;
    }

    public int getWageOfNode(Node n)
    {
        return wages.get(path.indexOf(n));
    }
}
