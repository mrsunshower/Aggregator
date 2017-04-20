package com.mrsunshower.aggregator.view;

import com.mrsunshower.aggregator.Controller;
import com.mrsunshower.aggregator.model.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by KenTerror on 21.04.2017.
 */
public class JobServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jobs.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        List<Provider> providerList = new ArrayList<>();
        String site1 = req.getParameter("site1");
        String site2 = req.getParameter("site2");
        String city = req.getParameter("city");

        if (site1 != null) {
            providerList.add(new Provider(new HHStrategy()));
        }
        if (site2 != null) {
            providerList.add(new Provider(new MoikrugStrategy()));
        }

        JSPView jspView = new JSPView();
        Provider[] providers = providerList.toArray(new Provider[providerList.size()]);
        Model model = new Model(jspView, providers);
        Controller controller = new Controller(model);
        jspView.setController(controller);
        jspView.userCitySelectEmulationMethod(city);

        req.getRequestDispatcher("/jobs.jsp").forward(req, resp);
    }
}
