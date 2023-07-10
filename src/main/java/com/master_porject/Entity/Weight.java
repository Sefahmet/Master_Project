package com.master_porject.Entity;

import com.master_porject.Model.GraphCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.locationtech.jts.geom.Coordinate;

import java.io.IOException;

import static com.master_porject.WeightDecider.Calculator.defaultWeightCalculator;

@AllArgsConstructor
public class Weight {


    @Getter @Setter private Double length_weight;
    @Getter @Setter private Double slope_weight;
    @Getter @Setter private Double max_speed_weight_weight;
    @Getter @Setter private Double turning_cost_weight;
    @Getter @Setter private static Weight instance;
    public static Weight getInstance() throws IOException {
        if (instance == null){
            instance = new Weight(0.25,0.25,0.25,0.25);
        }
        return instance;
    }

}
