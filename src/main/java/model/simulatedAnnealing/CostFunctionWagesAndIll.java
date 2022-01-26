package model.simulatedAnnealing;

import model.structures.GraphPath;
import model.structures.PathHistory;

public class CostFunctionWagesAndIll implements CostFunction {
    private double wagesParameter;
    private double illParameter;

    @Override
    public int evaluate(GraphPath graphPath) {
        return (int) (wagesParameter * graphPath.getSumOfWages() + illParameter * graphPath.getIllnessCases());
    }

    @Override
    public int evaluate(PathHistory pathHistory) {
        return (int) (wagesParameter * pathHistory.getSumOfWages() + illParameter * pathHistory.getIllnessCases());
    }
}
