package model.simulatedAnnealing;

import model.structures.GraphPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimulatedAnnealing {
    final static Logger logger = LoggerFactory.getLogger(Simulator.class);

    private final CostFunction costFunction;
    private final int kmax;

    public SimulatedAnnealing(CostFunction costFunction, int kmax) {
        this.costFunction = costFunction;
        this.kmax = kmax;
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
        for (int k = 0; k < kmax; k++) {
            temperature = (1.0 - (k + 1.0) / kmax);
            currentPath = doOneStepOfSimulatedAnnealing(currentPath, temperature);
        }

        return currentPath;
    }

    private GraphPath doOneStepOfSimulatedAnnealing(GraphPath currentPath, double temperature) {
        int nodeId1 = currentPath.getRandomNode().getId();
        int nodeId2 = currentPath.getRandomNodeDifferentThat(nodeId1).getId();

        GraphPath swapedPath = currentPath.getCopyWithSwappedNodes(nodeId1, nodeId2);
        int currentPathCost = costFunction.evaluate(currentPath);
        logger.debug("Current path is {}", currentPath);
        logger.debug("Current cost function is {}", currentPathCost);

        int swapedPathCost = costFunction.evaluate(swapedPath);
        logger.debug("Swapped path is {}", swapedPath);
        logger.debug("New cost function is {}", swapedPathCost);

        if (swapedPathCost < currentPathCost) {
            currentPath = swapedPath;
        } else {
            double p = Math.random();
            if (p < Math.exp(-(swapedPathCost - currentPathCost) / temperature)) {
                currentPath = swapedPath;
            }
        }
        return currentPath;
    }
}
