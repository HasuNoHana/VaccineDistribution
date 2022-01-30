import model.configuration.Configuration;
import model.io.ConfigurationReader;
import model.simulatedannealing.SimulationResult;
import model.simulationrunner.SimulationRunner;
import model.statistics.GraphStatistics;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class Main {
    private static void writeResult(SimulationResult sR, String path) throws FileNotFoundException {
        GraphStatistics gS = sR.getFinalGraphStatistics();

        String s = sR.getOptimalPath().getSumOfWeights() + ";" + gS.getResidentsNumber() + ";" + gS.getIllnessCases() + ";" + gS.getVaccinated();
        try (PrintWriter out = new PrintWriter(path)) {
            out.println(s);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        List<String> filenames = List.of("n1", "n2", "n3", "n4", "n5");
        for (String fileName :
                filenames) {
            for (int i = 0; i < 10; i++) {
                args = new String[]{"/home/zuznanna/IdeaProjects/VaccineDistribution/src/main/resources/config/" + fileName + ".json", "out/" + fileName + ".txt"};

                Configuration config = ConfigurationReader.readModelFromJson(args[0]);

                config.seedForRandomNode += i * 10000000;
                config.seedForShuffleFunction += i * 10000000;

                SimulationResult simulationResult = SimulationRunner.runSimulation(config);
                String outPathName = args[1] + String.valueOf(i);
                writeResult(simulationResult, outPathName);
            }


        }

//        ResultsWriter.writeResultsToFile(simulationResult, timeGap, args[1]);
    }
}
