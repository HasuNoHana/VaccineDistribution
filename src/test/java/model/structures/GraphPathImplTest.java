package model.structures;

import model.GraphTestHelper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GraphPathImplTest extends GraphTestHelper {

    @Test
    public void shouldSwapTwoNodes() {

        GraphImpl graph = getGraph();

        GraphPathImpl simpleGraphPath = new GraphPathImpl(graph);
        simpleGraphPath.addToPath(NODE_0);
        simpleGraphPath.addToPath(NODE_2, EDGE_WEIGHT_02);
        simpleGraphPath.addToPath(NODE_1, EDGE_WEIGHT_12);
        simpleGraphPath.addToPath(NODE_3, EDGE_WEIGHT_13);

        GraphPath newPath = simpleGraphPath.getCopyWithSwappedNodes(NODE_2.getId(), NODE_3.getId());

        assertEquals(newPath.getSumOfWages(), EDGE_WEIGHT_03 + EDGE_WEIGHT_13 + EDGE_WEIGHT_12);
    }

}