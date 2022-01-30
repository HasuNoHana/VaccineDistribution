package model.factory;

import model.configuration.Configuration;
import model.structures.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GraphFactory {

    private static List<Node> generateNodes(long seedForResidents, int minimalNumberOfHabitats, int maximumNumberOfHabitats, int nodeNumber, double infectionParameter) {
        Random random = new Random(seedForResidents);
        ArrayList<Node> nodes = new ArrayList<>();

        for (int i = 0; i < nodeNumber; i++)
            nodes.add(new Node(i, Math.min(maximumNumberOfHabitats, random.nextInt((maximumNumberOfHabitats - minimalNumberOfHabitats)) + minimalNumberOfHabitats), infectionParameter));


        return nodes;
    }

    public static Graph buildGraph(Configuration configuration)
    {
        List<Node> nodes = generateNodes(configuration.getSeedForResidentsGeneration(), configuration.getMinimalNumberOfResidents(), configuration.getMaximalNumberOfResidents(), configuration.getNodesNumber(), configuration.getInfectionParameter());

        var edgesChangeStrategy = new DefaultEdgesStrategy(configuration.getSeedForAdjacencyMatrix());

        var adjacencyMatrix = new AdjacencyMatrix(AdjacencyMatrixGenerator.generateAdjacencyMatrix(configuration.getNodesNumber(), configuration.getEdgeWeightSum(), new Random(configuration.getSeedForAdjacencyMatrix())));

        return new GraphImpl(nodes, adjacencyMatrix, edgesChangeStrategy);
    }

}
