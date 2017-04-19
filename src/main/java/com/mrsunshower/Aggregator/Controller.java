package com.mrsunshower.aggregator;

import com.mrsunshower.aggregator.model.Model;

/**
 * Created by KenTerror on 14.03.2017.
 */
public class Controller {
    Model model;

    public Controller(Model model) {
        if (model == null) throw new IllegalArgumentException();
        this.model = model;
    }

    public void onCitySelect(String cityName) {
        model.selectCity(cityName);
    }
}