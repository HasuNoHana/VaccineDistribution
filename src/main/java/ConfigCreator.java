import model.configuration.Configuration;
import model.io.ConfigurationWriter;

public class ConfigCreator {

    private static String getFileName(int nodesNumber, int infectionParameter, int kMax, double weight, double illness)
    {
        return "n" + nodesNumber + "i" + infectionParameter + "k" + kMax + "w" + weight + "ill" + illness + ".json";
    }

    public static void main(String[] args)
    {
        int nodesNumber = 100;
        long seedForResidentsGeneration = -724328867052683071L;
        long seedForAdjacencyMatrix = -6504547273722910555L;
        long seedForSimulatedAnnealing = -692506129728491388L;
        long seedForShuffleFunction = 2681551058434197453L;
        long seedForRandomNode = -7226282432067347614L;
        int minimalNumberOfResidents = 10000;
        int maximalNumberOfResidents = 100000;
        int infectionParameter = 2;
        int edgeWeightSum = nodesNumber * (nodesNumber - 1) * 71;
        int kMax = 100;
        int[] kMaxes = new int[] {100, 500, 1000};

        for(int i = 0; i < 3; i++) {

            kMax = kMaxes[i];
            double weightParameterForCostFunction = 0.5;
            double illnessCasesParameterForCostFunction = 0.5;

            ConfigurationWriter.writeToJSON(
                    new Configuration(nodesNumber, seedForResidentsGeneration, seedForAdjacencyMatrix,
                            seedForSimulatedAnnealing, seedForShuffleFunction, seedForRandomNode, minimalNumberOfResidents, maximalNumberOfResidents,
                            infectionParameter, edgeWeightSum, kMax, weightParameterForCostFunction, illnessCasesParameterForCostFunction),
                    getFileName(nodesNumber, infectionParameter, kMax, weightParameterForCostFunction, illnessCasesParameterForCostFunction));

            weightParameterForCostFunction = 0.0;
            illnessCasesParameterForCostFunction = 1;

            ConfigurationWriter.writeToJSON(
                    new Configuration(nodesNumber, seedForResidentsGeneration, seedForAdjacencyMatrix,
                            seedForSimulatedAnnealing, seedForShuffleFunction, seedForRandomNode, minimalNumberOfResidents, maximalNumberOfResidents,
                            infectionParameter, edgeWeightSum, kMax, weightParameterForCostFunction, illnessCasesParameterForCostFunction),
                    getFileName(nodesNumber, infectionParameter, kMax, weightParameterForCostFunction, illnessCasesParameterForCostFunction));

            weightParameterForCostFunction = 1;
            illnessCasesParameterForCostFunction = 0.0;

            ConfigurationWriter.writeToJSON(
                    new Configuration(nodesNumber, seedForResidentsGeneration, seedForAdjacencyMatrix,
                            seedForSimulatedAnnealing, seedForShuffleFunction, seedForRandomNode, minimalNumberOfResidents, maximalNumberOfResidents,
                            infectionParameter, edgeWeightSum, kMax, weightParameterForCostFunction, illnessCasesParameterForCostFunction),
                    getFileName(nodesNumber, infectionParameter, kMax, weightParameterForCostFunction, illnessCasesParameterForCostFunction));

        }
    }
}
