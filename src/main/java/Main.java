import model.configuration.Configuration;
import model.io.ConfigurationReader;
import model.io.ResultsWriter;
import model.simulatedannealing.SimulationResult;
import model.simulationrunner.SimulationRunner;
import model.statistics.GraphStatistics;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Main {
    private static void writeResult(SimulationResult sR, String path) throws FileNotFoundException {
        GraphStatistics gS = sR.getFinalGraphStatistics();

        String s = sR.getOptimalPath().getSumOfWeights() + ";" + gS.getResidentsNumber() + ";" + gS.getIllnessCases() + ";" + gS.getVaccinated();
        try (PrintWriter out = new PrintWriter(path)) {
            out.println(s);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        String[] fileNames = new String[]
                {
                        "n10i2k100w0.0ill1.0",
                        "n10i2k100w0.5ill0.5",
                        "n10i2k100w1.0ill0.0",
                        "n10i2k500w0.0ill1.0",
                        "n10i2k500w0.5ill0.5",
                        "n10i2k500w1.0ill0.0",
                        "n10i2k1000w0.0ill1.0",
                        "n10i2k1000w0.5ill0.5",
                        "n10i2k1000w1.0ill0.0",
                        "n10i6k100w0.0ill1.0",
                        "n10i6k100w0.5ill0.5",
                        "n10i6k100w1.0ill0.0",
                        "n10i6k500w0.0ill1.0",
                        "n10i6k500w0.5ill0.5",
                        "n10i6k500w1.0ill0.0",
                        "n10i6k1000w0.0ill1.0",
                        "n10i6k1000w0.5ill0.5",
                        "n10i6k1000w1.0ill0.0",
                        "n50i2k100w0.0ill1.0",
                        "n50i2k100w0.5ill0.5",
                        "n50i2k100w1.0ill0.0",
                        "n50i2k500w0.0ill1.0",
                        "n50i2k500w0.5ill0.5",
                        "n50i2k500w1.0ill0.0",
                        "n50i2k1000w0.0ill1.0",
                        "n50i2k1000w0.5ill0.5",
                        "n50i2k1000w1.0ill0.0",
                        "n50i6k100w0.0ill1.0",
                        "n50i6k100w0.5ill0.5",
                        "n50i6k100w1.0ill0.0",
                        "n50i6k500w0.0ill1.0",
                        "n50i6k500w0.5ill0.5",
                        "n50i6k500w1.0ill0.0",
                        "n50i6k1000w0.0ill1.0",
                        "n50i6k1000w0.5ill0.5",
                        "n50i6k1000w1.0ill0.0",
                        "n100i2k100w0.0ill1.0",
                        "n100i2k100w0.5ill0.5",
                        "n100i2k100w1.0ill0.0",
                        "n100i2k500w0.0ill1.0",
                        "n100i2k500w0.5ill0.5",
                        "n100i2k500w1.0ill0.0",
                        "n100i2k1000w0.0ill1.0",
                        "n100i2k1000w0.5ill0.5",
                        "n100i2k1000w1.0ill0.0",
                        "n100i6k100w0.0ill1.0",
                        "n100i6k100w0.5ill0.5",
                        "n100i6k100w1.0ill0.0",
                        "n100i6k500w0.0ill1.0",
                        "n100i6k500w0.5ill0.5",
                        "n100i6k500w1.0ill0.0",
                        "n100i6k1000w0.0ill1.0",
                        "n100i6k1000w0.5ill0.5",
                        "n100i6k1000w1.0ill0.0"
                };

        for(String s : fileNames)
        {
            Configuration config = ConfigurationReader.readModelFromJson("src/main/resources/config/" + s + ".json");
            SimulationResult simulationResult = SimulationRunner.runSimulation(config);
            ResultsWriter.writeResultsToFile(simulationResult, 10, "src/main/resources/results/" + s + ".txt");

        }
    }
}
