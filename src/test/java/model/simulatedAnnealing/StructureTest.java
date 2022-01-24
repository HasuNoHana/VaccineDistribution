package model.simulatedAnnealing;

import model.graphfactory.GraphFactory;
import model.structures.GraphImpl;
import model.structures.GraphPath;
import model.structures.GraphPathImpl;
import model.structures.Node;
import org.junit.Test;

public class StructureTest {
    @Test
    public void name() {
        //        given
        Node node0 = new Node(0, 0, 0);
        Node node1 = new Node(1, 0, 0);
        Node node2 = new Node(2, 0, 0);

        GraphFactory graphFactory = new GraphFactory();
        int edgeWeight01 = 10;
        int edgeWeight12 = 10;
        int edgeWeight02 = 20;
        graphFactory.addNode(node0);
        graphFactory.addNode(node1);
        graphFactory.addNode(node2);
        graphFactory.addWeight(0, 1, edgeWeight01);
        graphFactory.addWeight(1, 2, edgeWeight12);
        graphFactory.addWeight(0, 2, edgeWeight02);

        GraphImpl graph = (GraphImpl) graphFactory.build();
        GraphPathImpl simpleGraphPath = new GraphPathImpl();
        simpleGraphPath.addToPath(node0);
        simpleGraphPath.addToPath(node1, edgeWeight01);
        simpleGraphPath.addToPath(node2, edgeWeight12);

        int nodeId1 = 1;
        int nodeId2 = 2;

        GraphPath swappedPath = simpleGraphPath.getCopyWithSwappedNodes(nodeId1, nodeId2, graph.getAdjacencyMatrix());

        System.out.println("cos");
//        Assert.assertEquals();
    }
}
