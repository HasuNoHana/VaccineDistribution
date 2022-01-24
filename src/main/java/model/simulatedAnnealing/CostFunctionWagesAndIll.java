package model.simulatedAnnealing;

import model.structures.GraphPath;

public class CostFunctionWagesAndIll implements CostFunction {
    private double wagesParameter;
    private double illParameter;

    @Override
    public int evaluate(GraphPath graphPath) {
        return (int) (wagesParameter * graphPath.getSumOfWages() + illParameter * graphPath.getIllsCost());
    }
}
