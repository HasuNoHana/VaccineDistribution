package model.simulatedAnnealing;

import model.structures.GraphPath;
import model.structures.PathHistory;

public class CostFunctionWagesAndIll implements CostFunction {
    private final double weightsParameter;
    private final double illParameter;

    public CostFunctionWagesAndIll(double weightsParameter, double illParameter) {
        this.weightsParameter = weightsParameter;
        this.illParameter = illParameter;
    }

    @Override
    public int evaluate(GraphPath graphPath) {
        return (int) (weightsParameter * graphPath.getSumOfWages() + illParameter * graphPath.getIllnessCases());
    }

    @Override
    public int evaluate(PathHistory pathHistory) {
        return (int) (weightsParameter * pathHistory.getSumOfWages() + illParameter * pathHistory.getIllnessCases());
    }
}
