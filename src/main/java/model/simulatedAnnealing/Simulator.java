package model.simulatedAnnealing;

import model.structures.Graph;
import model.structures.GraphPath;
import model.structures.GraphPathImpl;
import model.structures.Node;


public class Simulator {

    private final Graph graph;
    private final SimulatedAnnealing simulatedAnnealing;
    private GraphPath optimalPath = new GraphPathImpl();

    public Simulator(Graph graph, SimulatedAnnealing simulatedAnnealing) {
        this.graph= graph;
        this.simulatedAnnealing = simulatedAnnealing;
    }

    public SimulationResult simulate() {

        GraphPath currentPath = graph.getRandomPath(); //TODO first node of this path cant be changed during simulation. It should be chosen use some heuristic e.g. node with lowest edge wage in whole graph
        optimalPath.addToPath(currentPath.getFirstNode());

        while (!stopSimulation(currentPath)) {
            currentPath = simulatedAnnealing.findShortestCicle(currentPath);

            optimalPath.addToPath(currentPath.getSecondNode(), currentPath.getFirstEdge());

            Node removedNode = currentPath.getFirstNode();
            Graph currentGraph = graph.getUpdatedGraphWithoutNode(removedNode);
        }
        return new SimulationResult(optimalPath);
    }

    private boolean stopSimulation(GraphPath currentPath) {
        if (currentPath.getSize()<=2){
            return true;
        }
        return false;
    }
}
