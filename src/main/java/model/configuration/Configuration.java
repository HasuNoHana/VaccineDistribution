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

    public Configuration(int nodesNumber, long seedForResidentsGeneration, long seedForAdjacencyMatrix,
                         long seedForSimulatedAnnealing, int minimalNumberOfResidents, int maximalNumberOfResidents,
                         int infectionParameter, int edgeWeightSum)
    {
        this.nodesNumber = nodesNumber;
        this.seedForResidentsGeneration = seedForResidentsGeneration;
        this.seedForAdjacencyMatrix = seedForAdjacencyMatrix;
        this.seedForSimulatedAnnealing = seedForSimulatedAnnealing;
        this.minimalNumberOfResidents = minimalNumberOfResidents;
        this.maximalNumberOfResidents = maximalNumberOfResidents;
        this.infectionParameter = infectionParameter;
        this.edgeWeightSum = edgeWeightSum;
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
}
