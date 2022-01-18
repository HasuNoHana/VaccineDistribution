import model.generator.ModelGenerator;
import model.io.JSONModelWriter;
import model.structures.Model;

public class Main {

    public static void main(String[] args){
        long seedM1 = -8931950316686792133L;
        long seedM2 = 2705643145072468196L;
        long seedM3 = 8064995938733697569L;

        //Model m1 = ModelGenerator.generateModel(100, 4, 5, seedM1);
        //Model m2 = ModelGenerator.generateModel(100000, 50, 10, seedM2);
        Model m3 = ModelGenerator.generateModel(100000, 100, 15, seedM3);

        //JSONModelWriter.writeToJSON(m1, "model1.json");
        //JSONModelWriter.writeToJSON(m2, "model2.json");
        JSONModelWriter.writeToJSON(m3, "model3.json");
    }
}
