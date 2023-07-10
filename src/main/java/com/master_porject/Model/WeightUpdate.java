package com.master_porject.Model;

import com.master_porject.DataHolder.MyDataSingleton;
import com.master_porject.Entity.Edge;
import com.master_porject.Entity.Node;
import com.master_porject.Entity.Weight;
import com.master_porject.Reader.ShapeFileReader;
import com.master_porject.WeightDecider.Calculator;
import org.jgrapht.Graph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Iterator;

public class WeightUpdate {
    public static MyDataSingleton graphUpdater(MyDataSingleton data) throws IOException {
        Logger logger = LoggerFactory.getLogger(WeightUpdate.class);
        Graph<Node, Edge> graph = data.getGraphFeatures().getGraph();
        Iterator<Edge> iterator = graph.edgeSet().iterator();
        ;
        while(iterator.hasNext()){
            Edge edge = iterator.next();
            graph.setEdgeWeight(edge.getU(),edge.getV(), Calculator.weightCalculator(edge,data.getWeight()));

        }
        logger.info("Graph Weight is updated");
        data.getGraphFeatures().setGraph(graph);
        return data;
    }
}
