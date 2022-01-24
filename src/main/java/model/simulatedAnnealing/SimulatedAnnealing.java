package model.simulatedAnnealing;

import model.structures.GraphPath;

public class SimulatedAnnealing {
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
            currentPath = doOneStepOfSimulatedAnnealing(currentPath,temperature, wageMatrix);
        }

        return currentPath;
    }

    private GraphPath doOneStepOfSimulatedAnnealing(GraphPath currentPath, double temperature, int[][] wageMatrix) {
        int nodeId1 = currentPath.getRandomNode().getId();
        int nodeId2 = currentPath.getRandomNodeDifferentThat(nodeId1).getId();

        GraphPath swapedPath = currentPath.getCopyWithSwappedNodes(nodeId1, nodeId2, wageMatrix);
        int swapedPathCost = costFunction.evaluate(swapedPath);
        int currentPathCost = costFunction.evaluate(currentPath);

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
