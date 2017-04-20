package com.mrsunshower.aggregator;

import com.mrsunshower.aggregator.model.HHStrategy;
import com.mrsunshower.aggregator.model.Model;
import com.mrsunshower.aggregator.model.MoikrugStrategy;
import com.mrsunshower.aggregator.model.Provider;
import com.mrsunshower.aggregator.view.HtmlView;

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
        htmlView.userCitySelectEmulationMethod("Киев");
    }
}