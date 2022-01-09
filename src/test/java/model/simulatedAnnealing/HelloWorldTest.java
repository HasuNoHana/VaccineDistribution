package model.simulatedAnnealing;


import org.junit.Assert;
import org.junit.Test;

public class HelloWorldTest {

    @Test
    public void helloWorld() {
        HelloWorld.hello();
        Assert.assertTrue(true);
    }

    @Test
    public void name2() {
        HelloWorld helloWorld = new HelloWorld();
        int a = 2;
    Assert.assertEquals(2, a);
    }
}
