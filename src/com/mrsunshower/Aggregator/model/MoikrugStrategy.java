package com.mrsunshower.Aggregator.model;

import com.mrsunshower.Aggregator.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by KenTerror on 16.03.2017.
 */

public class MoikrugStrategy implements Strategy {
    private static final String URL_FORMAT = "https://moikrug.ru/vacancies?page=%d&q=java+%s";
    //private static final String URL_FORMAT = "http://93.190.137.51/testdata/big28data%d%s.html";


    @Override
    public List<Vacancy> getVacancies(String searchString) {
        return null;
    }
}
