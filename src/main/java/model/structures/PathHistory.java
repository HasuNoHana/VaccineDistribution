package model.structures;

import java.util.ArrayList;
import java.util.List;

public class PathHistory {
    private ArrayList<Node> nodes;
    private ArrayList<Integer> edges;

    public PathHistory() {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
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

    public int getSumOfWages() {
        int count = 0;
        for (int i = 0; i < edges.size(); i++) {
            count += edges.get(i);
        }
        return count;
    }
}
