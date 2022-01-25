package model.structures;

import model.GraphTestHelper;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class GraphPathImplTest extends GraphTestHelper {

    @Test
    public void shouldSwapTwoNodes() {

        GraphImpl graph = getStaticGraph();

        GraphPathImpl simpleGraphPath = new GraphPathImpl(graph);
        simpleGraphPath.addToPath(NODE_0);
        simpleGraphPath.addToPath(NODE_2);
        simpleGraphPath.addToPath(NODE_1);
        simpleGraphPath.addToPath(NODE_3);

        GraphPath newPath = simpleGraphPath.getCopyWithSwappedNodes(NODE_2.getId(), NODE_3.getId());

        assertEquals(newPath.getSumOfWages(), EDGE_WEIGHT_03 + EDGE_WEIGHT_13 + EDGE_WEIGHT_12);
    }

    @Test
    public void checkIfPathChangesWhenGraphIsChanged() {
        List<Node> nodes = List.of(NODE_0, NODE_1, NODE_2, NODE_3);
        AdjacencyMatrix beforeAdjactiveMatrix = getBeforeAdjactencyMatrix();
        AdjacencyMatrix afterAdjactiveMatrix = getAfterAdjactencyMatrix();

        EdgesChangeStrategy dummyChangeStrategy = Mockito.mock(EdgesChangeStrategy.class);
        when(dummyChangeStrategy.updateEdges(any())).thenReturn(afterAdjactiveMatrix);
        GraphImpl graph = new GraphImpl(nodes, beforeAdjactiveMatrix, dummyChangeStrategy);

        GraphPathImpl beforeGraphPath = getSimplePath(graph);

        assertEquals(EDGE_WEIGHT_02 + EDGE_WEIGHT_13 + EDGE_WEIGHT_12, beforeGraphPath.getSumOfWages());
        //when
        graph.getUpdatedGraphWithoutNode(NODE_0);
        //then
        assertEquals(EDGE_WEIGHT_02 + EDGE_WEIGHT_12_NEW + EDGE_WEIGHT_13_NEW, beforeGraphPath.getSumOfWages());
    }
}