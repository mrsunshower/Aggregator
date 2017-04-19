package com.mrsunshower.aggregator.model;

import com.mrsunshower.aggregator.vo.Vacancy;
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
        List<Vacancy> vacancies = new ArrayList<>();
        int page = 0;
        Document document;
        Elements elements;
        try {
            while(((document = getDocument(searchString, page++)) != null) && !(elements = document.select("[data-qa=vacancy-serp__vacancy]")).isEmpty()) {
                for (Element element : elements) {
                    String title = element.select("[data-qa=vacancy-serp__vacancy-title]").first().text();
                    String url = element.select("[data-qa=vacancy-serp__vacancy-title]").first().attr("href");
                    String city = element.select("[data-qa=vacancy-serp__vacancy-address]").first().text();
                    String companyName = element.select("[data-qa=vacancy-serp__vacancy-employer]").first().text();
                    String siteName = "http://hh.ua/";

                    String salary = "";
                    Element elementSalary = element.select("[data-qa=vacancy-serp__vacancy-compensation]").first();
                    if (elementSalary != null) {
                        salary = elementSalary.text();
                    }

                    Vacancy vacancy = new Vacancy();
                    vacancy.setTitle(title);
                    vacancy.setUrl(url);
                    vacancy.setCity(city);
                    vacancy.setCompanyName(companyName);
                    vacancy.setSiteName(siteName);
                    vacancy.setSalary(salary);

                    vacancies.add(vacancy);
                }
            }
        } catch (IOException e) {}

        return vacancies;
    }

    protected Document getDocument(String searchString, int page) throws IOException {
        String url = String.format(URL_FORMAT, searchString, page);
        Document document = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36 OPR/43.0.2442.1144").referrer("none").get();

        return document;
    }
}