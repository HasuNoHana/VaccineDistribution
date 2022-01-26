package model.structures;

import model.simulatedAnnealing.CostFunction;

import java.util.ArrayList;
import java.util.List;

public class PathHistory {
    private ArrayList<Node> nodes;
    private ArrayList<Integer> edges;
    private CostFunction costFunction;

    public PathHistory(CostFunction costFunction) {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
        this.costFunction = costFunction;
    }

    public void addNode(Node node) {
        nodes.add(new Node(node));
    }

    public void addNodeAndEdge(Node node, int edge) {
        nodes.add(new Node(node));
        edges.add(edge);
    }

    public Node getLastNode() {
        return nodes.get(nodes.size() - 1);
    }

    public List<Node> getPath() {
        return this.nodes;
    }

    public int evaluate() {
        return this.costFunction.evaluate(this);
    }

    public int getSumOfWages() {
        int sum = 0;

        for (Integer edge : edges) {
            sum += edge;
        }
        return sum;
    }

    public int getIllnessCases() {
        int illnessCases = 0;

        for (Node n : nodes)
            illnessCases += n.getIllnessCases();

        return illnessCases;
    }
}
