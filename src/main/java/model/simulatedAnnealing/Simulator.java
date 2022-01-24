package model.simulatedAnnealing;

import model.structures.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Simulator {
    final static Logger logger = LoggerFactory.getLogger(Simulator.class);

    private Graph graph;
    private final SimulatedAnnealing simulatedAnnealing;
    private GraphPath optimalPath = new GraphPathImpl();

    public Simulator(Graph graph, SimulatedAnnealing simulatedAnnealing) {
        this.graph= graph;
        this.simulatedAnnealing = simulatedAnnealing;
    }

    public SimulationResult simulate() {
        logger.info("Start simulation");
        GraphPath currentPath = graph.getRandomPath(); //TODO first node of this path cant be changed during simulation. It should be chosen use some heuristic e.g. node with lowest edge wage in whole graph
        logger.debug("Random generatet path is following {}", currentPath);
        optimalPath.addToPath(currentPath.getFirstNode());
        logger.info("Optimal path is {}", optimalPath);

        graph = graph.getUpdatedGraphWithoutNode(currentPath.getFirstNode());

        while (!stopSimulation(currentPath)) {
            currentPath = simulatedAnnealing.findOptimaPath(currentPath, ((GraphImpl) graph).getAdjacencyMatrix());

            optimalPath.addToPath(currentPath.getSecondNode(), currentPath.getFirstEdge());
            logger.info("Optimal path is {}", optimalPath);

            Node removedNode = currentPath.getFirstNode();
            Graph currentGraph = graph.getUpdatedGraphWithoutNode(removedNode);
            graph = currentGraph;
            currentPath.removeFirstNode();
        }
        optimalPath.addToPath(currentPath.getLastNode(), currentPath.getLastEdge());
        logger.info("Optimal path is {}", optimalPath);

        return new SimulationResult(optimalPath);
    }

    private boolean stopSimulation(GraphPath currentPath) {
        if (currentPath.getSize()<=2){
            return true;
        }
        return false;
    }
}
