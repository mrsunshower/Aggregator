package com.mrsunshower.Aggregator;

import com.mrsunshower.Aggregator.model.HHStrategy;
import com.mrsunshower.Aggregator.model.Model;
import com.mrsunshower.Aggregator.model.MoikrugStrategy;
import com.mrsunshower.Aggregator.model.Provider;
import com.mrsunshower.Aggregator.view.HtmlView;

/**
 * Created by KenTerror on 14.03.2017.
 */
public class Aggregator {
    public static void main(String[] args) {
        HtmlView htmlView = new HtmlView();
        Provider hh = new Provider(new HHStrategy());
        Provider moi = new Provider(new MoikrugStrategy());
        Model model = new Model(htmlView, hh, moi);
        Controller controller = new Controller(model);
        htmlView.setController(controller);
        htmlView.userCitySelectEmulationMethod();
    }
}