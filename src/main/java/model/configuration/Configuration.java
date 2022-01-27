package model.configuration;

public class Configuration {
    private final int nodesNumber;
    private final long seedForResidentsGeneration;
    private final long seedForAdjacencyMatrix;
    private final long seedForSimulatedAnnealing;
    private final int minimalNumberOfResidents;
    private final int maximalNumberOfResidents;

    public Configuration(int nodesNumber, long seedForResidentsGeneration, long seedForAdjacencyMatrix, long seedForSimulatedAnnealing, int minimalNumberOfResidents, int maximalNumberOfResidents)
    {
        this.nodesNumber = nodesNumber;
        this.seedForResidentsGeneration = seedForResidentsGeneration;
        this.seedForAdjacencyMatrix = seedForAdjacencyMatrix;
        this.seedForSimulatedAnnealing = seedForSimulatedAnnealing;
        this.minimalNumberOfResidents = minimalNumberOfResidents;
        this.maximalNumberOfResidents = maximalNumberOfResidents;
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
}
