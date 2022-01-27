package model.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.configuration.Configuration;

import java.io.File;
import java.io.IOException;

public class ConfigurationReader {

    public static Configuration readModelFromJson(String path)
    {
        ObjectMapper mapper = new ObjectMapper();
        Configuration configuration = null;

        try {
            configuration = mapper.readValue(new File(path), Configuration.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return configuration;
    }
}
