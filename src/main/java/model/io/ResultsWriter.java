package model.io;

import model.simulatedannealing.SimulationResult;
import model.statistics.GraphStatistics;
import model.statistics.NodeStatistics;
import model.structures.Node;
import model.structures.PathHistory;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ResultsWriter {

    private static double calculatePercentage(double v1, double v2)
    {
        return (v1 / v2) * 100;
    }

    private static String getOptimalPathString(ArrayList<Integer> edges, ArrayList<Node> nodes)
    {
        StringBuilder s = new StringBuilder();

        for(int i = 0; i < nodes.size(); i++)
        {
            s.append(nodes.get(i).getId());
            s.append("(");
            s.append(i != 0 ? edges.get(i - 1) : 0);
            s.append(")");

            if(i != nodes.size() - 1)
                s.append(" -> ");

        }

        return s.toString();
    }

    private static String getGraphStatisticsStringInOneLine(GraphStatistics gS)
    {

        return gS.getMinute() + " " +
                gS.getResidentsNumber() + " " +
                gS.getIllnessCases() + " " +
                gS.getHealthyResidents() + " " +
                gS.getVaccinated();
    }

    private static String getGraphStatisticsString(GraphStatistics gS)
    {

        return "Residents: " + gS.getResidentsNumber() + "\n" +
                "Illness cases: " + gS.getIllnessCases() + " --> " + "[" + calculatePercentage(gS.getIllnessCases(), gS.getResidentsNumber()) + "%]" + "\n" +
                "Healthy residents: " + gS.getHealthyResidents() + " --> " + "[" + calculatePercentage(gS.getHealthyResidents(), gS.getResidentsNumber()) + "%]" + "\n" +
                "Vaccinated residents: " + gS.getVaccinated() + " --> " + "[" + calculatePercentage(gS.getVaccinated(), gS.getResidentsNumber()) + "%]";
    }

    private static String getNodeStatisticsString(NodeStatistics nS)
    {
        return nS.getNodeId() + " " + nS.getResidentsNumber() + " " + nS.getIllnessCases() + " " + nS.getHealthyResidents() + " " + nS.getVaccinated();
    }

    public static void writeResultsToFile(SimulationResult simulationResult, int timeGap, String path) throws FileNotFoundException {
        StringBuilder stringBuilder = new StringBuilder();
        PathHistory thePathHistory = simulationResult.getOptimalPath();

        stringBuilder.append("Optimal path [nodeId(time to the node):").append("\n");
        stringBuilder.append(getOptimalPathString(thePathHistory.getEdges(), thePathHistory.getNodes()));
        stringBuilder.append("\nTotal time of delivery: ").append(thePathHistory.getSumOfWeights()).append("\n");
        stringBuilder.append("The cost function value: ").append(thePathHistory.evaluate()).append("\n").append("\n");
        stringBuilder.append("Statistics at the end of simulation: ").append("\n");
        stringBuilder.append(getGraphStatisticsString(thePathHistory.getGraphStatisticsForTheTime(thePathHistory.getSumOfWeights()))).append("\n");
        stringBuilder.append("\n").append("Statistics of each node: ").append("\n");
        stringBuilder.append("id residentsNumber illnessCases healthyResidents vaccinated").append("\n");

        for(Node n : thePathHistory.getPath())
            stringBuilder.append(getNodeStatisticsString(n.getNodeStatsAtTime(thePathHistory.getSumOfWeights()))).append("\n");

        stringBuilder.append("\n").append("Statistics step by step: ").append("\n");
        stringBuilder.append("minute residentsNumber illnessCases healthyResidents vaccinated").append("\n");

        int time = 0;

        do
        {
            stringBuilder.append(getGraphStatisticsStringInOneLine(thePathHistory.getGraphStatisticsForTheTime(Math.min(time, thePathHistory.getSumOfWeights())))).append("\n");
            time += timeGap;

        }while (time < thePathHistory.getSumOfWeights());

        try (PrintWriter out = new PrintWriter(path)){
            out.println(stringBuilder.toString());
        }
    }
}
