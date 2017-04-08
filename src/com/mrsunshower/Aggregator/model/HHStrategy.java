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
 * Created by KenTerror on 14.03.2017.
 */
public class HHStrategy implements Strategy {
    private static final String URL_FORMAT = "http://hh.ua/search/vacancy?text=java+%s&page=%d";
    //private static final String URL_FORMAT = "http://93.190.137.51/testdata/big%s%d8data.html";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        return null;
    }
}