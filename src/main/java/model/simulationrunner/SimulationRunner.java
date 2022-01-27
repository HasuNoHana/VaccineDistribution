package model.simulationrunner;

import model.configuration.Configuration;
import model.factory.GraphFactory;
import model.simulatedannealing.*;
import model.structures.Graph;

import java.util.Random;

public class SimulationRunner {

    public SimulationResult runSimulation(Configuration configuration)
    {
        Graph graph = GraphFactory.buildGraph(configuration);
        var simulatedAnnealing = new SimulatedAnnealing(new CostFunctionWagesAndIll(configuration.getWeightParameterForCostFunction(), configuration.getIllnessCasesParameterForCostFunction())
        , configuration.getkMax(), new Random(configuration.getSeedForSimulatedAnnealing()));

        Simulator simulator = new  Simulator(graph, simulatedAnnealing, true);

        return simulator.simulate();
    }
}
