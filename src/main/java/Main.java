import model.configuration.Configuration;
import model.io.ConfigurationReader;
import model.io.ResultsWriter;
import model.simulatedannealing.SimulationResult;
import model.simulationrunner.SimulationRunner;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        if(args.length != 3)
        {
            System.out.println("Wrong amount of program arguments");
            return;
        }

        Configuration config = ConfigurationReader.readModelFromJson(args[0]);

        SimulationResult simulationResult = SimulationRunner.runSimulation(config);

        int timeGap = Integer.parseInt(args[1]);

        ResultsWriter.writeResultsToFile(simulationResult, timeGap, args[2]);
    }
}
