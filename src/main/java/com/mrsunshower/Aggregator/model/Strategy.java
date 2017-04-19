package com.mrsunshower.aggregator.model;

import com.mrsunshower.aggregator.vo.Vacancy;

import java.util.List;

/**
 * Created by KenTerror on 14.03.2017.
 */
public interface Strategy {
    List<Vacancy> getVacancies(String searchString);
}
