package com.master_porject.DataHolder;

import com.master_porject.Entity.GraphFeatures;
import com.master_porject.Entity.Weight;
import lombok.Getter;
import lombok.Setter;
import org.jgrapht.GraphPath;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class MyDataSingleton {
    @Getter @Setter private GraphFeatures graphFeatures;
    @Getter @Setter private Weight weight;

    @Autowired
    public MyDataSingleton() throws IOException {
        this.graphFeatures = GraphFeatures.getInstance();
        this.weight = Weight.getInstance();
    }
}
