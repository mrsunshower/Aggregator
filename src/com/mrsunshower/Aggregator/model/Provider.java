package com.mrsunshower.Aggregator.model;

import com.mrsunshower.Aggregator.vo.Vacancy;

import java.util.List;

/**
 * Created by KenTerror on 14.03.2017.
 */
public class Provider {
    private Strategy strategy;

    public Provider(Strategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public List<Vacancy> getJavaVacancies(String searchString){
        return strategy.getVacancies(searchString);}
}
