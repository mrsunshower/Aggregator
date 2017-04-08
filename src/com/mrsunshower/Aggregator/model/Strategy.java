package com.mrsunshower.Aggregator.model;

import com.mrsunshower.Aggregator.vo.Vacancy;

import java.util.List;

/**
 * Created by KenTerror on 14.03.2017.
 */
public interface Strategy {
    List<Vacancy> getVacancies(String searchString);
}
