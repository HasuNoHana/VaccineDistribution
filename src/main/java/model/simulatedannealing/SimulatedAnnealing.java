package model.simulatedannealing;

import model.structures.GraphPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class SimulatedAnnealing {
    final static Logger logger = LoggerFactory.getLogger(Simulator.class);

    private final CostFunction costFunction;
    private final int kMax;
    private final Random random;

    public SimulatedAnnealing(CostFunction costFunction, int kMax) {
        this.costFunction = costFunction;
        this.kMax = kMax;
        random = null;
    }

    public SimulatedAnnealing(CostFunction costFunction, int kMax, Random random)
    {
        this.random = random;
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
//            temperature = (1.0 - (k + 1.0) / kMax);
            temperature = 100.0 * (1.0 - (k * 1.0) / kMax);
            logger.debug("Temperature is {}", temperature);
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

        if (swappedPathCost <= currentPathCost) {
            currentPath = swappedPath;
        } else {
            double p = (random == null) ? Math.random() : random.nextDouble();
            if (p < Math.exp(-(swappedPathCost - currentPathCost) / temperature)) {
                currentPath = swappedPath;
                logger.info("More costyl path was chosen. Cost function difference is {}", swappedPathCost - currentPathCost);
            }
        }
        return currentPath;
    }

    public CostFunction getCostFunction() {
        return costFunction;
    }
}
