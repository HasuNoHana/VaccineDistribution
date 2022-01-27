package model.configuration;

public class Configuration {
    private final int nodesNumber;
    private final long seedForResidentsGeneration;
    private final long seedForAdjacencyMatrix;
    private final long seedForSimulatedAnnealing;
    private final int minimalNumberOfResidents;
    private final int maximalNumberOfResidents;
    private final int infectionParameter;
    private final int edgeWeightSum;
    private final int kMax;
    private final int weightParameterForCostFunction;
    private final int illnessCasesParameterForCostFunction;

    public Configuration(int nodesNumber, long seedForResidentsGeneration, long seedForAdjacencyMatrix,
                         long seedForSimulatedAnnealing, int minimalNumberOfResidents, int maximalNumberOfResidents,
                         int infectionParameter, int edgeWeightSum, int kMax,
                         int weightParameterForCostFunction, int illnessCasesParameterForCostFunction)
    {
        this.nodesNumber = nodesNumber;
        this.seedForResidentsGeneration = seedForResidentsGeneration;
        this.seedForAdjacencyMatrix = seedForAdjacencyMatrix;
        this.seedForSimulatedAnnealing = seedForSimulatedAnnealing;
        this.minimalNumberOfResidents = minimalNumberOfResidents;
        this.maximalNumberOfResidents = maximalNumberOfResidents;
        this.infectionParameter = infectionParameter;
        this.edgeWeightSum = edgeWeightSum;
        this.kMax = kMax;
        this.weightParameterForCostFunction = weightParameterForCostFunction;
        this.illnessCasesParameterForCostFunction = illnessCasesParameterForCostFunction;
    }

    public int getNodesNumber() {
        return nodesNumber;
    }

    public long getSeedForResidentsGeneration() {
        return seedForResidentsGeneration;
    }

    public long getSeedForAdjacencyMatrix() {
        return seedForAdjacencyMatrix;
    }

    public long getSeedForSimulatedAnnealing() {
        return seedForSimulatedAnnealing;
    }

    public int getMinimalNumberOfResidents() {
        return minimalNumberOfResidents;
    }

    public int getMaximalNumberOfResidents() {
        return maximalNumberOfResidents;
    }

    public int getInfectionParameter()
    {
        return infectionParameter;
    }

    public int getEdgeWeightSum()
    {
        return edgeWeightSum;
    }

    public int getkMax() {
        return kMax;
    }

    public int getWeightParameterForCostFunction() {
        return weightParameterForCostFunction;
    }

    public int getIllnessCasesParameterForCostFunction() {
        return illnessCasesParameterForCostFunction;
    }
}
