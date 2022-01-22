package model.simulatedAnnealing;

import model.structures.GrapPath;

public class SimulationResult {
    public SimulationResult() { //TODO this should work. This costructor should be removed
    }

    //TODO Powinno zawierac jakies info o tym ile ludzi zachorowalo itp
    private final GrapPath optimalPath;

    //uaktualnic o info o chorych
    public SimulationResult(GrapPath currentPath) {
        optimalPath = currentPath;
    }

    public GrapPath getOptimalPath() {
            return optimalPath;
    }
}
