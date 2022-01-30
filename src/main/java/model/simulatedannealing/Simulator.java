package model.simulatedannealing;

import model.structures.Graph;
import model.structures.GraphPath;
import model.structures.Node;
import model.structures.PathHistory;

import static model.simulatedannealing.SimulatedAnnealing.logger;


public class Simulator {

    private Graph graph;
    private final SimulatedAnnealing simulatedAnnealing;
    private final PathHistory optimalPath;
    private final boolean realSimulationMode;

    public Simulator(Graph graph, SimulatedAnnealing simulatedAnnealing) {
        this.graph= graph;
        this.simulatedAnnealing = simulatedAnnealing;
        this.optimalPath = new PathHistory(simulatedAnnealing.getCostFunction());
        this.realSimulationMode = false;
    }

    public Simulator(Graph graph, SimulatedAnnealing simulatedAnnealing, boolean realSimulationMode)
    {
        this.graph= graph;
        this.simulatedAnnealing = simulatedAnnealing;
        this.optimalPath = new PathHistory(simulatedAnnealing.getCostFunction());
        this.realSimulationMode = realSimulationMode;
    }

    private SimulationResult simulateInDefaultWay() {
        logger.info("Start simulation");
        GraphPath currentPath = graph.getRandomPath();
        logger.debug("Random generated path is following {}", currentPath);
        optimalPath.addNode(currentPath.getFirstNode());
        logger.info("Optimal path is {}", optimalPath);

        graph = graph.getUpdatedGraphWithoutNode(currentPath.getFirstNode());

        while (!stopSimulation(currentPath)) {
            currentPath = simulatedAnnealing.findOptimaPath(currentPath);

            optimalPath.addNodeAndEdge(currentPath.getSecondNode(), currentPath.getEdgeBetweenNodes(optimalPath.getLastNode(), currentPath.getSecondNode()));
            logger.info("Optimal path is {}", optimalPath);

            Node removedNode = currentPath.getFirstNode();
            graph = graph.getUpdatedGraphWithoutNode(removedNode);
            currentPath.removeFirstNode();
            currentPath.updateGraph(graph);
        }
        optimalPath.addNodeAndEdge(currentPath.getLastNode(), currentPath.getEdgeBetweenNodes(optimalPath.getLastNode(), currentPath.getSecondNode()));
        logger.info("Optimal path is {}", optimalPath);

        return new SimulationResult(optimalPath);
    }

    private SimulationResult simulateInRealSimulationMode()
    {
        logger.info("Start simulation");
        GraphPath currentPath = graph.getRandomPath();
        logger.debug("Random generated path is following {}", currentPath);
        currentPath.getFirstNode().deliverVaccines(0);
        optimalPath.addNode(currentPath.getFirstNode());
        logger.info("Optimal path is {}", optimalPath);

        graph = graph.getUpdatedGraphWithoutNode(currentPath.getFirstNode());

        while (!stopSimulation(currentPath)) {
            currentPath = simulatedAnnealing.findOptimaPath(currentPath);

            optimalPath.addNodeAndEdge(currentPath.getSecondNode(), currentPath.getEdgeBetweenNodes(optimalPath.getLastNode(), currentPath.getSecondNode()));
            logger.info("Optimal path is {}", optimalPath);

            Node removedNode = currentPath.getFirstNode();
            graph = graph.getUpdatedGraphWithoutNode(removedNode);
            currentPath.removeFirstNode();
            currentPath.updateGraph(graph);
        }
        optimalPath.addNodeAndEdge(currentPath.getLastNode(), currentPath.getEdgeBetweenNodes(optimalPath.getLastNode(), currentPath.getSecondNode()));
        optimalPath.getLastNode().deliverVaccines(optimalPath.getSumOfWeights());
        logger.info("Optimal path is {}", optimalPath);

        return new SimulationResult(optimalPath);
    }

    public SimulationResult simulate()
    {
        if(realSimulationMode)
            return simulateInRealSimulationMode();
        else
            return simulateInDefaultWay();
    }

    private boolean stopSimulation(GraphPath currentPath) {
        return currentPath.getSize() <= 2;
    }
}
