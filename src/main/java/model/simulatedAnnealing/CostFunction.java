package model.simulatedAnnealing;

import model.structures.GraphPath;

public interface CostFunction {

    int evaluate(GraphPath graphPath);

}
