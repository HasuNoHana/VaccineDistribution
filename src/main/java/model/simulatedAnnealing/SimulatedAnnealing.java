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


    public GraphPath findShortestCicle(GraphPath pathFromPreviousStep, int[][] wageMatrix) {
        GraphPath currentPath = pathFromPreviousStep;

        double temperature;
        for (int k = 0; k < kmax; k++) {
            temperature = (1.0 - (k + 1.0) / kmax);
            currentPath = doOneStepOfSimulatedAnnealing(currentPath, temperature, wageMatrix);
        }

        return currentPath;
    }

    private GraphPath doOneStepOfSimulatedAnnealing(GraphPath currentPath, double temperature, int[][] wageMatrix) {
        int nodeId1 = currentPath.getRandomNode().getId();
        int nodeId2 = currentPath.getRandomNodeDifferentThat(nodeId1).getId();

        GraphPath swapedPath = currentPath.getCopyWithSwappedNodes(nodeId1, nodeId2, wageMatrix);
        int currentPathCost = costFunction.evaluate(currentPath);
        logger.debug("Current cost function is {}", currentPathCost);

        int swapedPathCost = costFunction.evaluate(swapedPath);
        logger.debug("New cost function is {}", swapedPathCost);

        if (swapedPathCost < currentPathCost) {
            currentPath = swapedPath;
        } else {
            double p = Math.random();
            if (p > Math.exp(-(swapedPathCost - currentPathCost) / temperature)) {
                currentPath = swapedPath;
            }
        }
        return currentPath;
    }
}
