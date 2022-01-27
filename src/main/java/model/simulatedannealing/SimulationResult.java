package model.simulatedannealing;

import model.statistics.GraphStatistics;
import model.structures.PathHistory;

public class SimulationResult {
    private final PathHistory optimalPath;
    private final GraphStatistics finalGraphStatistics;

    //uaktualnic o info o chorych
    public SimulationResult(PathHistory currentPath) {
        optimalPath = currentPath;
        finalGraphStatistics = optimalPath.getGraphStatisticsForTheTime(optimalPath.getSumOfWeights());
    }

    public PathHistory getOptimalPath() {
        return optimalPath;
    }

    public GraphStatistics getFinalGraphStatistics()
    {
        return finalGraphStatistics;
    }
}
