import model.simulatedAnnealing.CostFunction;
import model.simulatedAnnealing.CostFunctionWagesAndIll;
import model.simulatedAnnealing.SimulatedAnnealing;
import model.simulatedAnnealing.Simulator;
import model.structures.GraphImpl;
import model.structures.GraphPath;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        long[] seeds = new long[]{
                -8931950316686792133L, //[0]
                2705643145072468196L,  //[1]
                8064995938733697569L,  //[2]
                -6944608733223062413L, //[3]
                -5766877550838449490L, //[4]
                3027202832058222816L,  //[5]
                3417175317364711039L,  //[6]
                6933459693421122103L,  //[7]
                -3908367742274119484L, //[8]
                217418625260834620L    //[9]
        };

        GraphImpl firstGraph = new GraphImpl(seeds[0], seeds[1], 50, 100, 400, 4, 2);
        GraphImpl secondGraph = new GraphImpl(seeds[2], seeds[3], 100, 100, 400, 5, 2);
        GraphImpl thirdGraph = new GraphImpl(seeds[4], seeds[5], 200, 100, 400, 4, 3);

        GraphImpl[] graphs = new GraphImpl[]{firstGraph, secondGraph, thirdGraph};

        int kMax = 100;
        double[][] parameters = new double[][]{
                {1.0, 0.0},
                {0.5, 0.5},
                {0.0, 1.0}
        };

        for (GraphImpl graph : graphs) {
            for (double[] parameter : parameters) {
                CostFunction costFunction = new CostFunctionWagesAndIll(parameter[0], parameter[1]);
                SimulatedAnnealing simulatedAnnealing = new SimulatedAnnealing(costFunction, kMax);
                Simulator simulator = new Simulator(graph, simulatedAnnealing);
                GraphPath optimalPath = simulator.simulate().getOptimalPath();
            }
        }
    }
}
