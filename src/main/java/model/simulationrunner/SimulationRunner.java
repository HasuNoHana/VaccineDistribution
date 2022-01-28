package model.simulationrunner;

import model.configuration.Configuration;
import model.factory.GraphFactory;
import model.simulatedannealing.*;
import model.structures.Graph;
import model.structures.GraphImpl;
import model.structures.GraphPathImpl;

import java.util.Random;

public class SimulationRunner {

    public static SimulationResult runSimulation(Configuration configuration)
    {
        GraphImpl.setRandomForShuffle(new Random(configuration.getSeedForShuffleFunction()));
        GraphPathImpl.setRandom(new Random(configuration.getSeedForRandomNode()));
        Graph graph = GraphFactory.buildGraph(configuration);
        var simulatedAnnealing = new SimulatedAnnealing(new CostFunctionWagesAndIll(configuration.getWeightParameterForCostFunction(), configuration.getIllnessCasesParameterForCostFunction())
        , configuration.getkMax(), new Random(configuration.getSeedForSimulatedAnnealing()));

        Simulator simulator = new  Simulator(graph, simulatedAnnealing, true);

        return simulator.simulate();
    }
}
