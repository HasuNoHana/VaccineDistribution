package model.structures;

import model.generator.ModelGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class GraphImpl implements Graph {

    private HashMap<Integer, Node> nodes;
    private int[][] adjacencyMatrix;
    private Random randomForAdjacencyMatrix;
    private int edgeWageSum;
    private int graphSize;
    private int iterationStep;
    private final long defaultSeedForWages = 8064995938733697569L;

    public GraphImpl(long seedForWages, long seedForHabitats, int edgeWageSum, int minimalNumberOfHabitats, int maximumNumberOfHabitats, int graphSize, int infectingParameter)
    {
        this.graphSize = graphSize;
        this.edgeWageSum = edgeWageSum;
        randomForAdjacencyMatrix = new Random(seedForWages);
        adjacencyMatrix = ModelGenerator.generateAdjacencyMatrix(graphSize, edgeWageSum, randomForAdjacencyMatrix);
        generatePeople(seedForHabitats, minimalNumberOfHabitats, maximumNumberOfHabitats, graphSize, infectingParameter);
        iterationStep = 0;
    }

    private GraphImpl(int[][] adjacencyMatrix, HashMap<Integer, Node> nodes)
    {
        this.adjacencyMatrix = adjacencyMatrix;
        this.nodes = nodes;
        randomForAdjacencyMatrix = new Random();
    }

    public void setIterationStep(int iterationStep)
    {
        this.iterationStep = iterationStep;
    }

    public int getIterationStep()
    {
        return iterationStep;
    }

    public GraphImpl(ArrayList<Node> nodeArrayList, int[][] adjacencyMatrix)
    {
        this.adjacencyMatrix = adjacencyMatrix;
        graphSize = nodeArrayList.size();

        nodes = new HashMap<>();
        edgeWageSum = 0;

        for (Node node : nodeArrayList)
            nodes.put(node.getId(), node);

        for (int[] matrix : adjacencyMatrix)
            for (int j = 0; j < adjacencyMatrix.length; j++)
                edgeWageSum += matrix[j];

        edgeWageSum /= 2;
        randomForAdjacencyMatrix = new Random(); //TODO check what value should be used here


    }

    private void generatePeople(long seedForHabitats, int minimalNumberOfHabitats, int maximumNumberOfHabitats, int graphSize, int infectingParameter)
    {
        Random random = new Random(seedForHabitats);
        nodes = new HashMap<>();

        for(int i = 0; i < graphSize; i++)
        {
            //generowanie nodow
            nodes.put(i, new Node(i, Math.min(maximumNumberOfHabitats, random.nextInt((maximumNumberOfHabitats - minimalNumberOfHabitats)) + minimalNumberOfHabitats), infectingParameter));
        }
    }

    public void nextStep(int iterationNumber)
    {
        //aktualizacja zarazonych w nodach
        for(Node n : nodes.values())
            n.updateIllnessCases(iterationNumber);

        //zmiana wartosci adjacencyMatrix
        adjacencyMatrix = ModelGenerator.generateAdjacencyMatrix(graphSize, edgeWageSum, randomForAdjacencyMatrix);
    }

    public int getWage(int firstNodeId, int secondNodeId)
    {
        return adjacencyMatrix[firstNodeId][secondNodeId];
    }

    public Graph getSubgraphWithUnvisitedNodes()
    {
        HashMap<Integer, Node> subgraphNodes = new HashMap<>();

        for(Node n : nodes.values())
        {
            if(!n.getIsVaccinated())
            {
                subgraphNodes.put(n.getId(), n);
            }
        }

        return new GraphImpl(adjacencyMatrix, subgraphNodes);
    }

    public Node getNode(int id)
    {
        return nodes.get(id);
    }

    public HashMap<Integer, Node> getNodes()
    {
        return nodes;
    }

    public int[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    @Override
    public GraphPath getRandomPath() {
        ArrayList<Node> pathNodes = new ArrayList<>();

        for(Node n : nodes.values())
            if(!n.getIsVaccinated())
                pathNodes.add(new Node(n));

        Collections.shuffle(pathNodes);

        GraphPathImpl graphPath = new GraphPathImpl(adjacencyMatrix);

        for(int i = 0; i < pathNodes.size(); i++)
            graphPath.addToPath(pathNodes.get(i), i != 0 ? getWage(pathNodes.get(i).getId(), graphPath.getLastNode().getId()) : 0);

        return graphPath;
    }

    @Override
    public Graph getUpdatedGraphWithoutNode(Node nodeToBeRemoved) {
        nodeToBeRemoved.visit(iterationStep);
        nextStep(iterationStep + 1);

        return getSubgraphWithUnvisitedNodes();
    }
}
