package model.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.structures.Model;

import java.io.File;
import java.io.IOException;

public class JSONModelReader {

    public Model readModelFromJson(String path)
    {
        ObjectMapper mapper = new ObjectMapper();
        Model model = null;

        try {
            model = mapper.readValue(new File(path), Model.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return model;
    }

}
