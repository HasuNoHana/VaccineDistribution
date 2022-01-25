package model.simulatedAnnealing;

import model.GraphTestHelper;
import model.structures.*;
import model.testHelpers.ModuloStrategy;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class SimulatorTest extends GraphTestHelper {

    public static final int KMAX = 100;

    @Test
    public void shouldFindOptimalPathIn5NodeDynamicGraph() {
//        given
        List<Node> nodes = List.of(NODE_0, NODE_1, NODE_2, NODE_3, NODE_4);
        AdjacencyMatrix firstAdjactiveMatrix = getFirstAdjactencyMatrix();

        GraphImpl graph = new GraphImpl(nodes, firstAdjactiveMatrix, new ModuloStrategy());

        GraphPathImpl beforeGraphPath = getSimple5Path(graph);

        GraphImpl graphSpyded = Mockito.spy(graph);
        when(graphSpyded.getRandomPath()).thenReturn(beforeGraphPath);

        CostFunction costFunction = new CostFunctionGraphWages();
        SimulatedAnnealing simulatedAnnealing = new SimulatedAnnealing(costFunction, KMAX);
        Simulator simulator = new Simulator(graphSpyded, simulatedAnnealing);

        //when
        SimulationResult simulationResult = simulator.simulate();

        //then
        PathHistory optimalPath = simulationResult.getOptimalPath();
        assertEquals(EDGE_5WEIGHT_01 + EDGE_5WEIGHT_13_NEW + EDGE_5WEIGHT_34_NEW + EDGE_5WEIGHT_24, optimalPath.getSumOfWages());
        assertEquals(0, optimalPath.getPath().get(0).getId());
        assertEquals(1, optimalPath.getPath().get(1).getId());
        assertEquals(3, optimalPath.getPath().get(2).getId());
        assertEquals(4, optimalPath.getPath().get(3).getId());
        assertEquals(2, optimalPath.getPath().get(4).getId());
    }

    @Test
    public void shouldFindOptimalPathInDynamicGraph() {
//        given
        List<Node> nodes = List.of(NODE_0, NODE_1, NODE_2, NODE_3);
        AdjacencyMatrix beforeAdjactiveMatrix = getBeforeAdjactencyMatrix();
        AdjacencyMatrix afterAdjactiveMatrix = getAfterAdjactencyMatrix();

        EdgesChangeStrategy dummyChangeStrategy = Mockito.mock(EdgesChangeStrategy.class);
        when(dummyChangeStrategy.updateEdges(any())).thenReturn(afterAdjactiveMatrix);
        GraphImpl graph = new GraphImpl(nodes, beforeAdjactiveMatrix, dummyChangeStrategy);

        GraphPathImpl beforeGraphPath = getSimplePath(graph);

        GraphImpl graphSpyded = Mockito.spy(graph);
        when(graphSpyded.getRandomPath()).thenReturn(beforeGraphPath);

        CostFunction costFunction = new CostFunctionGraphWages();
        SimulatedAnnealing simulatedAnnealing = new SimulatedAnnealing(costFunction, KMAX);
        Simulator simulator = new Simulator(graphSpyded, simulatedAnnealing);

        //when
        SimulationResult simulationResult = simulator.simulate();

        //then
        PathHistory optimalPath = simulationResult.getOptimalPath();
        assertEquals(EDGE_WEIGHT_01 + EDGE_WEIGHT_23 + EDGE_WEIGHT_13_NEW, optimalPath.getSumOfWages());
        assertEquals(0, optimalPath.getPath().get(0).getId());
        assertEquals(1, optimalPath.getPath().get(1).getId());
        assertEquals(3, optimalPath.getPath().get(2).getId());
        assertEquals(2, optimalPath.getPath().get(3).getId());
    }

    @Test
    public void shouldFindOptimalPathInStaticGraph() {
//        given
        GraphImpl graph = getStaticGraph();
        GraphPathImpl simpleGraphPath = getSimplePath(graph);

        GraphImpl graphSpyded = Mockito.spy(graph);
        when(graphSpyded.getRandomPath()).thenReturn(simpleGraphPath);

        CostFunction costFunction = new CostFunctionGraphWages();
        SimulatedAnnealing simulatedAnnealing = new SimulatedAnnealing(costFunction, KMAX);
        Simulator simulator = new Simulator(graphSpyded, simulatedAnnealing);

        //when
        SimulationResult simulationResult = simulator.simulate();

        //then
        PathHistory optimalPath = simulationResult.getOptimalPath();
        assertEquals(EDGE_WEIGHT_01 + EDGE_WEIGHT_12 + EDGE_WEIGHT_23, optimalPath.getSumOfWages());
    }

    @Test
    public void shouldFindOptimalPathInStaticGraphWithTreakyEdges() {
//        given

        List<Node> nodes = List.of(NODE_0, NODE_1, NODE_2, NODE_3);

        AdjacencyMatrix adjactiveMatrix = new AdjacencyMatrix(4);
        adjactiveMatrix.setEdge(NODE_0.getId(), NODE_1.getId(), 1);
        adjactiveMatrix.setEdge(NODE_0.getId(), NODE_2.getId(), 4);
        adjactiveMatrix.setEdge(NODE_0.getId(), NODE_3.getId(), 5);
        adjactiveMatrix.setEdge(NODE_1.getId(), NODE_2.getId(), 2);
        adjactiveMatrix.setEdge(NODE_1.getId(), NODE_3.getId(), 1);
        adjactiveMatrix.setEdge(NODE_2.getId(), NODE_3.getId(), 3);

        EdgesChangeStrategy dummyChangeStrategy = Mockito.mock(EdgesChangeStrategy.class);
        when(dummyChangeStrategy.updateEdges(any())).thenReturn(adjactiveMatrix);
        GraphImpl graph = new GraphImpl(nodes, adjactiveMatrix, dummyChangeStrategy);

        GraphPathImpl simpleGraphPath = getSimplePath(graph);

        GraphImpl graphSpyded = Mockito.spy(graph);
        when(graphSpyded.getRandomPath()).thenReturn(simpleGraphPath);

        CostFunction costFunction = new CostFunctionGraphWages();
        SimulatedAnnealing simulatedAnnealing = new SimulatedAnnealing(costFunction, KMAX);
        Simulator simulator = new Simulator(graphSpyded, simulatedAnnealing);

        //when
        SimulationResult simulationResult = simulator.simulate();

        //then
        PathHistory optimalPath = simulationResult.getOptimalPath();
        assertEquals(adjactiveMatrix.getEdgeWeight(0, 1) + adjactiveMatrix.getEdgeWeight(1, 3) + adjactiveMatrix.getEdgeWeight(3, 2), optimalPath.getSumOfWages());
    }

}