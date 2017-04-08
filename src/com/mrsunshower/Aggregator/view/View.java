package com.mrsunshower.Aggregator.view;

import com.mrsunshower.Aggregator.Controller;
import com.mrsunshower.Aggregator.vo.Vacancy;

import java.util.List;

/**
 * Created by KenTerror on 15.03.2017.
 */
public interface View {
    void update(List<Vacancy> vacancies);
    void setController(Controller controller);
}
