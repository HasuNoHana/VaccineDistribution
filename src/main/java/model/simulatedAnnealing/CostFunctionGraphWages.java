package model.simulatedAnnealing;

import model.structures.GraphPath;

public class CostFunctionGraphWages implements CostFunction {
    @Override
    public int evaluate(GraphPath graphPath) {
        return graphPath.getSumOfWages();
    }
}
