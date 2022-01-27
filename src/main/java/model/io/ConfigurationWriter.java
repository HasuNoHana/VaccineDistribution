package model.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.configuration.Configuration;

import java.io.File;
import java.io.IOException;

public class ConfigurationWriter {
    public static void writeToJSON(Configuration model, String pathToFile)
    {
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writeValue(new File(pathToFile), model);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
