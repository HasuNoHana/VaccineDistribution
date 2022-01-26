package model.simulatedAnnealing;

import model.structures.GraphPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimulatedAnnealing {
    final static Logger logger = LoggerFactory.getLogger(Simulator.class);

    private final CostFunction costFunction;
    private final int kMax;

    public SimulatedAnnealing(CostFunction costFunction, int kMax) {
        this.costFunction = costFunction;
        this.kMax = kMax;
    }

    /**
     * Finds (semi) optimal path using simulated annealing algorithm.
     *
     * @param pathFromPreviousStep path to start optimization with. If graph don't change it should return same path
     * @return optimal path
     */
    public GraphPath findOptimaPath(GraphPath pathFromPreviousStep) {
        GraphPath currentPath = pathFromPreviousStep;

        double temperature;
        for (int k = 0; k < kMax; k++) {
            temperature = (1.0 - (k + 1.0) / kMax);
            currentPath = doOneStepOfSimulatedAnnealing(currentPath, temperature);
        }

        return currentPath;
    }

    private GraphPath doOneStepOfSimulatedAnnealing(GraphPath currentPath, double temperature) {
        int nodeId1 = currentPath.getRandomNode().getId();
        int nodeId2 = currentPath.getRandomNodeDifferentThat(nodeId1).getId();

        GraphPath swappedPath = currentPath.getCopyWithSwappedNodes(nodeId1, nodeId2);
        int currentPathCost = costFunction.evaluate(currentPath);
        logger.debug("Current path is {}", currentPath);
        logger.debug("Current cost function is {}", currentPathCost);

        int swappedPathCost = costFunction.evaluate(swappedPath);
        logger.debug("Swapped path is {}", swappedPath);
        logger.debug("New cost function is {}", swappedPathCost);

        if (swappedPathCost < currentPathCost) {
            currentPath = swappedPath;
        } else {
            double p = Math.random();
            if (p < Math.exp(-(swappedPathCost - currentPathCost) / temperature)) {
                currentPath = swappedPath;
            }
        }
        return currentPath;
    }

    public CostFunction getCostFunction() {
        return costFunction;
    }
}
