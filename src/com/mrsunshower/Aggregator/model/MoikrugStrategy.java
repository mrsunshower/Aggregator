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
        List<Vacancy> vacancies = new ArrayList<>();
        int page = 1;
        Document document;
        Elements elements;
        try {
            while((document = getDocument(searchString, page++)) != null && !(elements = document.getElementsByClass("job")).isEmpty()) {
                for (Element element : elements) {
                    String siteName = "https://moikrug.ru";

                    String title = "";
                    String url = "";
                    Element titleElement = element.getElementsByClass("title").first();
                    if (titleElement != null) {
                        title = titleElement.getElementsByTag("a").first().text();
                        url = siteName + titleElement.getElementsByTag("a").first().attr("href");
                    }

                    String companyName = "";
                    Element companyNameElement = element.getElementsByClass("company_name").first();
                    if(companyNameElement != null) {
                        companyName = companyNameElement.getElementsByTag("a").first().text();
                    }

                    String city = "";
                    Element cityElement = element.getElementsByClass("location").first();
                    if (cityElement != null) {
                        city = cityElement.getElementsByTag("a").first().text();
                    }

                    String salary = "";
                    Element salaryElement = element.getElementsByClass("count").first();
                    if (salaryElement != null) {
                        salary = salaryElement.text();
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
        String url = String.format(URL_FORMAT, page, searchString);
        Document document = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36 OPR/43.0.2442.1144").referrer("none").get();

        return document;
    }
}
