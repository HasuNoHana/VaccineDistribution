package model.simulatedannealing;

import model.structures.PathHistory;

public class SimulationResult {

    //TODO Powinno zawierac jakies info o tym ile ludzi zachorowalo itp
    private PathHistory optimalPath;

    //uaktualnic o info o chorych
    public SimulationResult(PathHistory currentPath) {
        optimalPath = currentPath;
    }

    public PathHistory getOptimalPath() {
        return optimalPath;
    }
}
