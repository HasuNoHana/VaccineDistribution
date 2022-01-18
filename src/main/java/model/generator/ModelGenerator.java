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

    public static int[][] generateAdjacencyMatrix(int nodesNumber, int edgeWageSum, Random random)
    {
        int edgesNumber = (nodesNumber * (nodesNumber - 1)) / 2;

        if(edgeWageSum < edgesNumber)
            return null;

        int[][] adjacencyMatrix = new int[edgesNumber][edgesNumber];

        //setting 0 value in the matrix diagonal

        for(int i = 0; i < adjacencyMatrix.length; i++)
            adjacencyMatrix[i][i] = 0;

        int valuesQuantity = edgesNumber * (edgesNumber - 1);
        valuesQuantity = (int) Math.ceil((float) valuesQuantity / 2);

        int[] values = new int[valuesQuantity];

        for(int v : values)
            v = 1;

        int leftNumbers = edgeWageSum - valuesQuantity;

        while (leftNumbers > 0)
        {
            int currentValueToAdd = random.nextInt(valuesQuantity);
            values[currentValueToAdd] += 1;

            leftNumbers--;
        }

        //sent
        int k = 0;
        for(int i = 0; i < nodesNumber; i++)
            for(int j = i; j < nodesNumber; j++)
            {
                if(i != j) {
                    adjacencyMatrix[i][j] = values[k];
                    adjacencyMatrix[j][i] = values[k];
                    k++;
                }
            }


        return adjacencyMatrix;
    }
}
