package model.structures;

import java.util.ArrayList;

public class GraphPath {

    ArrayList<Node> path;

    public GraphPath()
    {
        path = new ArrayList<>();
    }

    public void addToPath(Node node)
    {
        path.add(node);
    }

    public ArrayList<Node> getPath() {
        return path;
    }
}
