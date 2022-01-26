package model.structures;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class AdjacencyMatrixGeneratorTest {

    @Test
    public void generationOfMatrix3x3Test() {
        Random random = new Random(-8931950316686792133L);

        int edgesWeightSum = 10 * 150;

        int[][] actualAdjacencyMatrix = AdjacencyMatrixGenerator.generateAdjacencyMatrix(3, edgesWeightSum, random);

        int[][] expected = new int[][]{
                {0, 457, 523},
                {457, 0, 520},
                {523, 520, 0}
        };

        int actualEdgesWeightSum = 0;

        assert actualAdjacencyMatrix != null;

        for (int[] i : actualAdjacencyMatrix)
            for (int j : i)
                actualEdgesWeightSum += j;

        actualEdgesWeightSum /= 2;

        assertEquals(edgesWeightSum, actualEdgesWeightSum);
        assertArrayEquals(expected, actualAdjacencyMatrix);
    }

    @Test
    public void generationOfMatrix4x4Test() {
        Random random = new Random(2705643145072468196L);

        int edgesWeightSum = 3 * 7 * 5 * 2 + 271;

        int[][] actualAdjacencyMatrix = AdjacencyMatrixGenerator.generateAdjacencyMatrix(4, edgesWeightSum, random);

        int[][] expected = new int[][]{
                {0, 87, 79, 80},
                {87, 0, 69, 83},
                {79, 69, 0, 83},
                {80, 83, 83, 0}
        };

        int actualEdgesWeightSum = 0;

        assert actualAdjacencyMatrix != null;

        for (int[] i : actualAdjacencyMatrix)
            for (int j : i)
                actualEdgesWeightSum += j;

        actualEdgesWeightSum /= 2;

        assertEquals(edgesWeightSum, actualEdgesWeightSum);
        assertArrayEquals(expected, actualAdjacencyMatrix);
    }

    @Test
    public void generationOfMatrix5x5Test() {
        Random random = new Random(8064995938733697569L);

        int edgesWeightSum = 3 * 7 * 9 * 11 *  2 + 271;

        int[][] actualAdjacencyMatrix = AdjacencyMatrixGenerator.generateAdjacencyMatrix(5, edgesWeightSum, random);

        int[][] expected = new int[][]{
                {0, 444, 432, 458, 412},
                {444, 0, 439, 454, 441},
                {432, 439, 0, 438, 472},
                {458, 454, 438, 0, 439},
                {412, 441, 472, 439, 0}

        };

        int actualEdgesWeightSum = 0;

        assert actualAdjacencyMatrix != null;
        for (int[] i : actualAdjacencyMatrix)
            for (int j : i)
                actualEdgesWeightSum += j;

        actualEdgesWeightSum /= 2;

        assertEquals(edgesWeightSum, actualEdgesWeightSum);
        assertArrayEquals(expected, actualAdjacencyMatrix);
    }
}
