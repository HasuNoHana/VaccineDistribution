package model.simulatedAnnealing;

import model.structures.GraphPath;
import model.structures.PathHistory;

public class CostFunctionGraphWages implements CostFunction {
    @Override
    public int evaluate(GraphPath graphPath) {
        return graphPath.getSumOfWages();
    }

    @Override
    public int evaluate(PathHistory pathHistory) {
        return pathHistory.getSumOfWages();
    }
}
