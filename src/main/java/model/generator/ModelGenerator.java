package model.generator;

import model.structures.Model;

import java.util.Random;

public class ModelGenerator {

    private static int[][] generateMatrix(Random random, int maxEdgeWage, int nodesNumber)
    {
        int[][] matrix = new int[nodesNumber][nodesNumber];

        for(int i = 0; i < nodesNumber; i++)
            for(int j = i; j < nodesNumber; j++)
            {
                if(i == j)
                    matrix[i][j] = 0;
                else
                {
                    int value = random.nextInt(maxEdgeWage) + 1;

                    matrix[i][j] = value;
                    matrix[j][i] = value;
                }
            }

        return matrix;
    }

    public static Model generateModel(int iterationsNumber, int nodesNumber, int maxEdgeWage, long seed)
    {
        Random random = new Random(seed);

        int[][][] adjacencyMatrix = new int[iterationsNumber][nodesNumber][nodesNumber];

        for(int i = 0; i < iterationsNumber; i++)
            adjacencyMatrix[i] = generateMatrix(random, maxEdgeWage, nodesNumber);

        Model model = new Model(nodesNumber, iterationsNumber);
        model.setAdjacencyMatrix(adjacencyMatrix);

        return model;
    }
}
