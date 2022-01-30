package model.configuration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Configuration {
    private final int nodesNumber;
    private final long seedForResidentsGeneration;
    private final long seedForAdjacencyMatrix;
    private final long seedForSimulatedAnnealing;
    private final double infectionParameter;
    public long seedForShuffleFunction;
    private final int minimalNumberOfResidents;
    private final int maximalNumberOfResidents;
    public long seedForRandomNode;
    private final int edgeWeightSum;
    private final int kMax;
    private final double weightParameterForCostFunction;
    private final double illnessCasesParameterForCostFunction;

    @JsonCreator
    public Configuration(@JsonProperty("nodesNumber") int nodesNumber, @JsonProperty("seedForResidentsGeneration") long seedForResidentsGeneration, @JsonProperty("seedForAdjacencyMatrix") long seedForAdjacencyMatrix,
                         @JsonProperty("seedForSimulatedAnnealing") long seedForSimulatedAnnealing, @JsonProperty("seedForShuffleFunction") long seedForShuffleFunction,
                         @JsonProperty("seedForRandomNode") long seedForRandomNode,
                         @JsonProperty("minimalNumberOfResidents") int minimalNumberOfResidents,
                         @JsonProperty("maximalNumberOfResidents") int maximalNumberOfResidents, @JsonProperty("infectionParameter") double infectionParameter,
                         @JsonProperty("edgeWeightSum") int edgeWeightSum, @JsonProperty("kMax") int kMax,
                         @JsonProperty("weightParameterForCostFunction") double weightParameterForCostFunction, @JsonProperty("illnessCasesParameterForCostFunction") double illnessCasesParameterForCostFunction)
    {
        this.nodesNumber = nodesNumber;
        this.seedForResidentsGeneration = seedForResidentsGeneration;
        this.seedForAdjacencyMatrix = seedForAdjacencyMatrix;
        this.seedForSimulatedAnnealing = seedForSimulatedAnnealing;
        this.seedForShuffleFunction = seedForShuffleFunction;
        this.seedForRandomNode = seedForRandomNode;
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

    public long getSeedForShuffleFunction() {
        return seedForShuffleFunction;
    }

    public long getSeedForRandomNode() {
        return seedForRandomNode;
    }

    public int getMinimalNumberOfResidents() {
        return minimalNumberOfResidents;
    }

    public int getMaximalNumberOfResidents() {
        return maximalNumberOfResidents;
    }

    public double getInfectionParameter() {
        return infectionParameter;
    }

    public int getEdgeWeightSum()
    {
        return edgeWeightSum;
    }

    public int getkMax() {
        return kMax;
    }

    public double getWeightParameterForCostFunction() {
        return weightParameterForCostFunction;
    }

    public double getIllnessCasesParameterForCostFunction() {
        return illnessCasesParameterForCostFunction;
    }
}
