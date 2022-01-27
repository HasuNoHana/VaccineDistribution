package model.io;

import model.simulatedannealing.SimulationResult;
import model.statistics.GraphStatistics;
import model.structures.Node;
import model.structures.PathHistory;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ResultsWriter {
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

    private static String getGraphStatisticString(GraphStatistics gS)
    {
        StringBuilder sB = new StringBuilder();

        sB.append(gS.getMinute()).append(" ").
                append(gS.getResidentsNumber()).append(" ").
                append(gS.getIllnessCases()).append(" ").
                append(gS.getHealthyResidents()).append(" ").
                append(gS.getVaccinated());

        return sB.toString();
    }

    public static void writeResultsToFile(SimulationResult simulationResult, int timeGap, String path) throws FileNotFoundException {
        StringBuilder stringBuilder = new StringBuilder();
        PathHistory thePathHistory = simulationResult.getOptimalPath();

        stringBuilder.append("Optimal path [nodeId(time to the node):").append("\n");
        stringBuilder.append(getOptimalPathString(thePathHistory.getEdges(), thePathHistory.getNodes()));
        stringBuilder.append("\nTotal time of delivery: ").append(thePathHistory.getSumOfWeights()).append("\n");
        stringBuilder.append("The cost function value: ").append(thePathHistory.evaluate()).append("\n");
        stringBuilder.append("Statistics: ").append("\n");
        stringBuilder.append("minute residentsNumber illnessCases healthyResidents vaccinated").append("\n");

        int time = 0;

        do
        {
            stringBuilder.append(getGraphStatisticString(thePathHistory.getGraphStatisticsForTheTime(Math.min(time, thePathHistory.getSumOfWeights())))).append("\n");
            time += timeGap;

        }while (time < thePathHistory.getSumOfWeights());

        try (PrintWriter out = new PrintWriter(path)){
            out.println(stringBuilder.toString());
        }
    }
}
