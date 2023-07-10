package com.master_porject.Service;

import com.master_porject.DataHolder.MyDataSingleton;
import com.master_porject.Entity.Edge;
import com.master_porject.Entity.Node;
import com.master_porject.Entity.Weight;
import com.master_porject.Model.GraphCreator;
import com.master_porject.Model.WeightUpdate;
import geotrellis.proj4.CRS;
import geotrellis.proj4.Transform;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.locationtech.jts.geom.Coordinate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import scala.Tuple2;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import static com.master_porject.WeightDecider.Calculator.*;

@Service
public class ShortestPathService {
    public GraphPath<Node, Edge> getBestPath(Coordinate start, Coordinate end, Weight weight) throws IOException {

        try{
            MyDataSingleton data = new MyDataSingleton();
            List<Node> vertex = getClosestNode(start, end);
            Node startPoint = vertex.get(0);
            Node endPoint = vertex.get(1);


            data.setWeight(weight);
            data = WeightUpdate.graphUpdater(data);
            return calculateShortestPath(startPoint,endPoint,data);




        }catch (Exception e) {
            throw new IOException();
        }
    }
    public GraphPath<Node, Edge> calculateShortestPath(Node start, Node end, MyDataSingleton data) throws IOException {
        Logger logger = LoggerFactory.getLogger(GraphCreator.class);

        // Calculate Shortest Path
        Graph<Node, Edge> graph = data.getGraphFeatures().getGraph();
        DijkstraShortestPath<Node, Edge> shortestPathAlg = new DijkstraShortestPath<>(graph);
        logger.info("Shortest path algorithm set as DijkstraShortestPath");
        GraphPath<Node, Edge> shortestPath = shortestPathAlg.getPath(start, end);

        logger.info("Shortest path calculated from " + start.getOsmid() + " to " + end.getOsmid() + ": " + shortestPath.toString());
        return shortestPath;
    }
    public static Coordinate LatLon2EN (double lat,double lon){

        CRS epsg3044 = CRS.fromEpsgCode(3044);
        CRS wgs84 = CRS.fromEpsgCode(4326);
        var fromWgs84 = Transform.apply(wgs84, epsg3044);
        Tuple2<Object, Object> latlon2EN = fromWgs84.apply(lat  , lon);
        return new Coordinate((double) latlon2EN._1(),(double) latlon2EN._2());


    }
}
