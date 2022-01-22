package model.simulatedAnnealing;

import model.structures.Graph;
import model.structures.GraphPath;
import model.structures.Node;


public class Simulator {

    private final Graph graph;
    private final SimulatedAnnealing simulatedAnnealing;

    public Simulator(Graph graph, SimulatedAnnealing simulatedAnnealing) {
        this.graph= graph;
        this.simulatedAnnealing = simulatedAnnealing;
    }

    public SimulationResult simulate() {

        GraphPath currentPath = graph.getSimplePath();

        while(!stopSimulation(currentPath))
        {
            Node removedNode = currentPath.getFirstNode();
            Graph currentGraph = graph.getUpdatedGraphWithoutNode(removedNode);
            currentPath = simulatedAnnealing.findShortestCicle(currentPath);
        }
        return new SimulationResult(); //TODO add results
    }

    private boolean stopSimulation(GraphPath currentPath) {
        if (currentPath.getSize()<=2){
            return true;
        }
        return false;
    }
}
