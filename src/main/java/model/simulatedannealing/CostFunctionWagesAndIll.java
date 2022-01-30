package model.simulatedannealing;

import model.structures.GraphPath;
import model.structures.PathHistory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CostFunctionWagesAndIll implements CostFunction {
    final static Logger logger = LoggerFactory.getLogger(CostFunctionWagesAndIll.class);
    private final double weightsParameter;
    private final double illParameter;

    public CostFunctionWagesAndIll(double weightsParameter, double illParameter) {
        this.weightsParameter = weightsParameter;
        this.illParameter = illParameter;
    }

    @Override
    public int evaluate(GraphPath graphPath) {
        double partFromWages = weightsParameter * graphPath.getSumOfWages();
        double partFromIllness = illParameter * graphPath.predictIllnessCases();

        logger.warn("Cost function from wages: {} from illnes: {}", partFromWages, partFromIllness);
        return (int) (partFromWages + partFromIllness);
    }

    @Override
    public int evaluate(PathHistory pathHistory) {
        return (int) (weightsParameter * pathHistory.getSumOfWeights() + illParameter * pathHistory.getIllnessCases());
    }
}
