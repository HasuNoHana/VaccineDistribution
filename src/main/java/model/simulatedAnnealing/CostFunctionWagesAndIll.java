package model.simulatedAnnealing;

import model.structures.GraphPath;

public class CostFunctionWagesAndIll implements CostFunction {
    private double wagesParameter;
    private double illParameter;

    public CostFunctionWagesAndIll(double wagesParameter, double illParameter)
    {
        this.wagesParameter = wagesParameter;
        this.illParameter = illParameter;
    }

    @Override
    public int evaluate(GraphPath graphPath) {
        return (int) (wagesParameter * graphPath.getSumOfWages() + illParameter * graphPath.getIllnessCases());
    }
}
