package model.simulatedAnnealing;

import model.graphfactory.GraphFactory;
import model.structures.GraphImpl;
import model.structures.GraphPath;
import model.structures.GraphPathImpl;
import model.structures.Node;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class SimulatorTest {

    @Test
    public void shouldFindOptimalPathInSimpleGraph() {
//        given
        Node node1 = new Node(0, 0, 0);
        Node node2 = new Node(1, 0, 0);
        Node node3 = new Node(2, 0, 0);

        GraphFactory graphFactory = new GraphFactory();
        int edgeWeight12 = 10;
        int edgeWeight23 = 10;
        int edgeWeight13 = 100000;
        graphFactory.addNode(node1);
        graphFactory.addNode(node2);
        graphFactory.addNode(node3);
        graphFactory.addWage(0, 1, edgeWeight12);
        graphFactory.addWage(1, 2, edgeWeight23);
        graphFactory.addWage(0, 2, edgeWeight13);


        GraphImpl graph = (GraphImpl) graphFactory.build();
        GraphPathImpl simpleGraphPath = new GraphPathImpl(graph.getAdjacencyMatrix()); //TODO this constructor should be removed
        simpleGraphPath.addToPath(node1);
        simpleGraphPath.addToPath(node2, edgeWeight12);
        simpleGraphPath.addToPath(node3, edgeWeight23);

//        Mockito.when(graph.getRandomPath()).thenReturn(simpleGraphPath);

        int kmax = 100;
        CostFunction costFunction = new CostFunctionGraphWages();
        SimulatedAnnealing simulatedAnnealing = new SimulatedAnnealing(costFunction, kmax);
        Simulator simulator = new Simulator(graph, simulatedAnnealing);

        //when
        SimulationResult simulationResult = simulator.simulate();

        //then
        GraphPath optimalPath = simulationResult.getOptimalPath();
        assertEquals(edgeWeight12 + edgeWeight23, optimalPath.getSumOfWages());
    }

    @Test
    public void shouldCorrectlyFindPathInSimulatedAneeling() {
//        given
        Node node1 = new Node(0, 0, 0);
        Node node2 = new Node(1, 0, 0);
        Node node3 = new Node(2, 0, 0);

        GraphFactory graphFactory = new GraphFactory();
        int edgeWeight12 = 10;
        int edgeWeight23 = 10;
        int edgeWeight13 = 100000;
        GraphPathImpl simpleGraphPath = new GraphPathImpl(graph.getAdjacencyMatrix()); //TODO this constructor should be removed
        simpleGraphPath.addToPath(node1);
        simpleGraphPath.addToPath(node2, edgeWeight12);
        simpleGraphPath.addToPath(node3, edgeWeight23);


        int kmax = 100;
        CostFunction costFunction = new CostFunctionGraphWages();
        SimulatedAnnealing simulatedAnnealing = new SimulatedAnnealing(costFunction, kmax);
        simulatedAnnealing.findShortestCicle()

        //when
        SimulationResult simulationResult = simulator.simulate();

        //then
        GraphPath optimalPath = simulationResult.getOptimalPath();
        assertEquals(edgeWeight12 + edgeWeight23, optimalPath.getSumOfWages());
    }
}