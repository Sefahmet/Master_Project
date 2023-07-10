package com.master_porject.Controller;

import com.master_porject.DataHolder.MyDataSingleton;
import com.master_porject.Entity.Edge;
import com.master_porject.Entity.Node;
import com.master_porject.Entity.Weight;
import com.master_porject.Service.ShortestPathService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jgrapht.GraphPath;
import org.locationtech.jts.geom.Coordinate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/shortestPath")
public class WeightedShortestPathController {
    private final ShortestPathService shortestPathService;
    @GetMapping("/getWithcoordinates")
    public ResponseEntity<List<Coordinate>> shortestPathGetter(@Valid @RequestParam double lat1,
                                             @Valid @RequestParam double lon1,
                                             @Valid @RequestParam double lat2,
                                             @Valid @RequestParam double lon2,
                                             @Valid @RequestParam double w1,
                                             @Valid @RequestParam double w2,
                                             @Valid @RequestParam double w3,
                                             @Valid @RequestParam double w4) throws IOException {
        Weight weight = new Weight(w1,w2,w3,w4);
        Coordinate p1 = shortestPathService.LatLon2EN(lon1, lat1);
        Coordinate p2 = shortestPathService.LatLon2EN(lon2, lat2);

        GraphPath<Node, Edge> shortestpath = shortestPathService.getBestPath(p1, p2, weight);
        List<Coordinate> coordinateList = new ArrayList<>();
        List<Edge> edgeList = shortestpath.getEdgeList();
        if (edgeList.size()>0) {
            for (Edge e : edgeList) {
                coordinateList.add(e.getStart_point());
            }
            coordinateList.add(edgeList.get(edgeList.size() - 1).getEnd_point());


            return new ResponseEntity(coordinateList, HttpStatus.OK);
        }else{
            return new ResponseEntity(null,HttpStatus.BAD_REQUEST);
        }
    }

}
