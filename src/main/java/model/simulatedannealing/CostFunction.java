package model.simulatedannealing;

import model.structures.GraphPath;
import model.structures.PathHistory;

public interface CostFunction {

    int evaluate(GraphPath graphPath);

    int evaluate(PathHistory pathHistory);

}
