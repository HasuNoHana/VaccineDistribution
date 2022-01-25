package model.simulatedAnnealing;

import model.structures.Graph;
import model.structures.GraphPath;
import model.structures.Node;
import model.structures.PathHistory;

import static model.simulatedAnnealing.SimulatedAnnealing.logger;


public class Simulator {

    private Graph graph;
    private final SimulatedAnnealing simulatedAnnealing;
    private final PathHistory optimalPath;

    public Simulator(Graph graph, SimulatedAnnealing simulatedAnnealing) {
        this.graph= graph;
        this.simulatedAnnealing = simulatedAnnealing;
        this.optimalPath = new PathHistory();
    }

    public SimulationResult simulate() {
        logger.info("Start simulation");
        GraphPath currentPath = graph.getRandomPath(); //TODO first node of this path cant be changed during simulation. It should be chosen use some heuristic e.g. node with lowest edge wage in whole graph
        logger.debug("Random generatet path is following {}", currentPath);
        optimalPath.addNode(currentPath.getFirstNode());
        logger.info("Optimal path is {}", optimalPath);

        graph = graph.getUpdatedGraphWithoutNode(currentPath.getFirstNode());

        while (!stopSimulation(currentPath)) {
            currentPath = simulatedAnnealing.findOptimaPath(currentPath);

            optimalPath.addNodeAndEdge(currentPath.getSecondNode(), currentPath.getEdgeBetweenNodes(optimalPath.getLastNode(), currentPath.getSecondNode()));
            logger.info("Optimal path is {}", optimalPath);

            Node removedNode = currentPath.getFirstNode();
            Graph currentGraph = graph.getUpdatedGraphWithoutNode(removedNode);
            graph = currentGraph;
            currentPath.removeFirstNode();
            currentPath.updateGraph(graph);
        }
        optimalPath.addNodeAndEdge(currentPath.getLastNode(), currentPath.getEdgeBetweenNodes(optimalPath.getLastNode(), currentPath.getSecondNode()));
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
