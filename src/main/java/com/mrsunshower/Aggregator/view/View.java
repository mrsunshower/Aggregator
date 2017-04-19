package com.mrsunshower.aggregator.view;

import com.mrsunshower.aggregator.Controller;
import com.mrsunshower.aggregator.vo.Vacancy;

import java.util.List;

/**
 * Created by KenTerror on 15.03.2017.
 */
public interface View {
    void update(List<Vacancy> vacancies);
    void setController(Controller controller);
}
