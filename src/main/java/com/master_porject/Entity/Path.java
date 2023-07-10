package com.master_porject.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.locationtech.jts.geom.Coordinate;

import java.util.List;

@AllArgsConstructor
public class Path {
    @Getter @Setter List<Coordinate> coordinates;
    @Getter @Setter Weight weight;
}
