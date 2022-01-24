package model.simulatedAnnealing;

import model.graphfactory.GraphFactory;
import model.structures.GraphImpl;
import model.structures.GraphPath;
import model.structures.GraphPathImpl;
import model.structures.Node;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class SimulatorTest {

    @Test
    public void shouldFindOptimalPathInSimpleGraph() {
//        given
        Node node0 = new Node(0, 0, 0);
        Node node1 = new Node(1, 0, 0);
        Node node2 = new Node(2, 0, 0);
        Node node3 = new Node(3, 0, 0);

        GraphFactory graphFactory = new GraphFactory();
        int edgeWeight01 = 1;
        int edgeWeight12 = 2;
        int edgeWeight23 = 3;
        int edgeWeight02 = 20;
        int edgeWeight03 = 30;
        int edgeWeight13 = 10;

        graphFactory.addNode(node0);
        graphFactory.addNode(node1);
        graphFactory.addNode(node2);
        graphFactory.addNode(node3);
        graphFactory.addWage(0, 1, edgeWeight01);
        graphFactory.addWage(0, 2, edgeWeight02);
        graphFactory.addWage(0, 3, edgeWeight03);
        graphFactory.addWage(1, 2, edgeWeight12);
        graphFactory.addWage(1, 3, edgeWeight13);
        graphFactory.addWage(2, 3, edgeWeight23);

        GraphImpl graph = (GraphImpl) graphFactory.build();
        GraphPathImpl simpleGraphPath = new GraphPathImpl(); //TODO this constructor should be removed
        simpleGraphPath.addToPath(node0);
        simpleGraphPath.addToPath(node2, edgeWeight02);
        simpleGraphPath.addToPath(node1, edgeWeight12);
        simpleGraphPath.addToPath(node3, edgeWeight13);

        GraphImpl graphSpyded = Mockito.spy(graph);
        when(graphSpyded.getRandomPath()).thenReturn(simpleGraphPath);

        int kmax = 100;
        CostFunction costFunction = new CostFunctionGraphWages();
        SimulatedAnnealing simulatedAnnealing = new SimulatedAnnealing(costFunction, kmax);
        Simulator simulator = new Simulator(graphSpyded, simulatedAnnealing);

        //when
        SimulationResult simulationResult = simulator.simulate();

        //then
        GraphPath optimalPath = simulationResult.getOptimalPath();
        assertEquals(edgeWeight01 + edgeWeight12 + edgeWeight23, optimalPath.getSumOfWages());
    }

    @Test
    public void shouldFindOptimalPathInSimpleGraphWithTreakyEdges() {
//        given
        Node node0 = new Node(0, 0, 0);
        Node node1 = new Node(1, 0, 0);
        Node node2 = new Node(2, 0, 0);
        Node node3 = new Node(3, 0, 0);

        GraphFactory graphFactory = new GraphFactory();
        int edgeWeight01 = 1;
        int edgeWeight12 = 2;
        int edgeWeight23 = 3;
        int edgeWeight02 = 4;
        int edgeWeight03 = 5;
        int edgeWeight13 = 1;

        graphFactory.addNode(node0);
        graphFactory.addNode(node1);
        graphFactory.addNode(node2);
        graphFactory.addNode(node3);
        graphFactory.addWage(0, 1, edgeWeight01);
        graphFactory.addWage(0, 2, edgeWeight02);
        graphFactory.addWage(0, 3, edgeWeight03);
        graphFactory.addWage(1, 2, edgeWeight12);
        graphFactory.addWage(1, 3, edgeWeight13);
        graphFactory.addWage(2, 3, edgeWeight23);

        GraphImpl graph = (GraphImpl) graphFactory.build();
        GraphPathImpl simpleGraphPath = new GraphPathImpl(); //TODO this constructor should be removed
        simpleGraphPath.addToPath(node0);
        simpleGraphPath.addToPath(node2, edgeWeight02);
        simpleGraphPath.addToPath(node1, edgeWeight12);
        simpleGraphPath.addToPath(node3, edgeWeight13);

        GraphImpl graphSpyded = Mockito.spy(graph);
        when(graphSpyded.getRandomPath()).thenReturn(simpleGraphPath);

        int kmax = 100;
        CostFunction costFunction = new CostFunctionGraphWages();
        SimulatedAnnealing simulatedAnnealing = new SimulatedAnnealing(costFunction, kmax);
        Simulator simulator = new Simulator(graphSpyded, simulatedAnnealing);

        //when
        SimulationResult simulationResult = simulator.simulate();

        //then
        GraphPath optimalPath = simulationResult.getOptimalPath();
        assertEquals(edgeWeight01 + edgeWeight12 + edgeWeight23, optimalPath.getSumOfWages());
    }
}