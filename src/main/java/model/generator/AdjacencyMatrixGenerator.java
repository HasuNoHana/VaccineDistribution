package model.generator;

import java.util.Arrays;
import java.util.Random;

public class AdjacencyMatrixGenerator {

    public static int[][] generateAdjacencyMatrix(int nodesNumber, int edgeWageSum, Random random)
    {
        int edgesNumber = (nodesNumber * (nodesNumber - 1)) / 2;

        if(edgeWageSum < edgesNumber)
            return null;

        int[][] adjacencyMatrix = new int[nodesNumber][nodesNumber];

        //setting 0 value in the matrix diagonal

        for(int i = 0; i < adjacencyMatrix.length; i++)
            adjacencyMatrix[i][i] = 0;

        int valuesQuantity = nodesNumber * (nodesNumber - 1);
        valuesQuantity = (int) Math.ceil((float) valuesQuantity / 2);

        int[] values = new int[valuesQuantity];

        Arrays.fill(values, 1);

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
