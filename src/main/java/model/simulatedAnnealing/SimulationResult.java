package model.simulatedAnnealing;

import model.structures.GraphPath;

public class SimulationResult {
    public SimulationResult() { //TODO this should work. This costructor should be removed
    }

    //TODO Powinno zawierac jakies info o tym ile ludzi zachorowalo itp
    private GraphPath optimalPath;

    //uaktualnic o info o chorych
    public SimulationResult(GraphPath currentPath) {
        optimalPath = currentPath;
    }

    public GraphPath getOptimalPath() {
        return optimalPath;
    }
}
