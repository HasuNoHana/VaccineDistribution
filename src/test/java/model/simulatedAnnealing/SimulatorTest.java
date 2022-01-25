package model.simulatedAnnealing;

import model.GraphTestHelper;
import model.graphfactory.GraphFactory;
import model.structures.GraphImpl;
import model.structures.GraphPath;
import model.structures.GraphPathImpl;
import model.structures.Node;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class SimulatorTest extends GraphTestHelper {

    public static final int KMAX = 100;

    @Test
    public void shouldFindOptimalPathInSimpleGraph() {
//        given
        GraphImpl graph = getGraph();
        GraphPathImpl simpleGraphPath = new GraphPathImpl(graph);
        simpleGraphPath.addToPath(NODE_0);
        simpleGraphPath.addToPath(NODE_2, EDGE_WEIGHT_02);
        simpleGraphPath.addToPath(NODE_1, EDGE_WEIGHT_12);
        simpleGraphPath.addToPath(NODE_3, EDGE_WEIGHT_13);

        GraphImpl graphSpyded = Mockito.spy(graph);
        when(graphSpyded.getRandomPath()).thenReturn(simpleGraphPath);

        CostFunction costFunction = new CostFunctionGraphWages();
        SimulatedAnnealing simulatedAnnealing = new SimulatedAnnealing(costFunction, KMAX);
        Simulator simulator = new Simulator(graphSpyded, simulatedAnnealing);

        //when
        SimulationResult simulationResult = simulator.simulate();

        //then
        GraphPath optimalPath = simulationResult.getOptimalPath();
        assertEquals(EDGE_WEIGHT_01 + EDGE_WEIGHT_12 + EDGE_WEIGHT_23, optimalPath.getSumOfWages());
    }

    @Test
    public void shouldFindOptimalPathInSimpleGraphWithTreakyEdges() {
//        given

        List<Node> nodes = List.of(NODE_0, NODE_1, NODE_2, NODE_3);

        int[][] adjacencyMatrix = {
                {0, 1, 4, 5},
                {1, 0, 2, 1},
                {4, 2, 0, 3},
                {5, 1, 3, 0}
        };

        GraphFactory graphFactory = new GraphFactory();

        for (Node n : nodes)
            graphFactory.addNode(n);

        graphFactory.setAdjacanceMatrix(adjacencyMatrix);
        GraphImpl graph = (GraphImpl) graphFactory.build();

        GraphPathImpl simpleGraphPath = new GraphPathImpl(graph);
        simpleGraphPath.addToPath(nodes.get(0));
        simpleGraphPath.addToPath(nodes.get(2), adjacencyMatrix[0][2]);
        simpleGraphPath.addToPath(nodes.get(1), adjacencyMatrix[01][2]);
        simpleGraphPath.addToPath(nodes.get(3), adjacencyMatrix[1][3]);

        GraphImpl graphSpyded = Mockito.spy(graph);
        when(graphSpyded.getRandomPath()).thenReturn(simpleGraphPath);

        CostFunction costFunction = new CostFunctionGraphWages();
        SimulatedAnnealing simulatedAnnealing = new SimulatedAnnealing(costFunction, KMAX);
        Simulator simulator = new Simulator(graphSpyded, simulatedAnnealing);

        //when
        SimulationResult simulationResult = simulator.simulate();

        //then
        GraphPath optimalPath = simulationResult.getOptimalPath();
        assertEquals(adjacencyMatrix[0][1] + adjacencyMatrix[1][3] + adjacencyMatrix[3][2], optimalPath.getSumOfWages());
    }

}