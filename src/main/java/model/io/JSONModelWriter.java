package model.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.structures.Model;

import java.io.File;
import java.io.IOException;

public class JSONModelWriter {

    public static void writeToJSON(Model model, String pathToFile)
    {
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writeValue(new File(pathToFile), model);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
