package model.structures;

import java.util.HashMap;
import java.util.Objects;

public class Model {

    private int nodesAmount;
    private int iterationAmount;

    private class Triple
    {
        int iterationNumber;
        int nodeNumber1;
        int nodeNumber2;

        Triple()
        {
            iterationNumber = -1;
            nodeNumber1 = -1;
            nodeNumber2 = -1;
        }

        Triple(int iterationNumber, int nodeNumber1, int nodeNumber2)
        {
            this.iterationNumber = iterationNumber;
            this.nodeNumber1 = nodeNumber1;
            this.nodeNumber2 = nodeNumber2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Triple triple = (Triple) o;
            return iterationNumber == triple.iterationNumber && nodeNumber1 == triple.nodeNumber1 && nodeNumber2 == triple.nodeNumber2;
        }

        @Override
        public int hashCode() {
            return Objects.hash(iterationNumber, nodeNumber1, nodeNumber2);
        }
    }

    private HashMap<Triple, Integer> adjacencyMatrix;

    public Model(int nodesAmount, int iterationAmount)
    {
        this.nodesAmount = nodesAmount;
        this.iterationAmount = iterationAmount;
    }

    public void setAdjacencyMatrix(int[] inputArray)
    {
        if(inputArray.length != (iterationAmount * (Math.pow(nodesAmount, 2) / 2)))
            System.out.println();


    }

    public int getEdgeValue(int iterationAmount, int nodeNumber1, int nodeNumber2)
    {
        return adjacencyMatrix.get(new Triple(iterationAmount, nodeNumber1, nodeNumber2));
    }

}
