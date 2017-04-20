package com.mrsunshower.aggregator.view;

import com.mrsunshower.aggregator.Controller;
import com.mrsunshower.aggregator.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Created by KenTerror on 19.04.2017.
 */
public class JSPView implements View {

    private final String templateFile = "../webapps/ROOT/backup.html";
    private final String jspFile = "../webapps/ROOT/jobs.jsp";

    private Controller controller;

    @Override
    public void update(List<Vacancy> vacancies) {
        try {
            updateFile(getUpdatedFileContent(vacancies));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies) {
        String content;
        try {
            Document doc = getDocument();
            Element element = doc.getElementsByClass("template").first();
            Element cloneElement = element.clone();
            cloneElement.removeClass("template").removeAttr("style");
            doc.getElementsByAttributeValue("class", "vacancy").remove();
            for (Vacancy vacancy : vacancies) {
                Element vac = cloneElement.clone();
                vac.getElementsByAttributeValue("class", "city").get(0).text(vacancy.getCity());
                vac.getElementsByAttributeValue("class", "companyName").get(0).text(vacancy.getCompanyName());
                vac.getElementsByAttributeValue("class", "salary").get(0).text(vacancy.getSalary());
                Element link = vac.getElementsByTag("a").get(0);
                link.text(vacancy.getTitle());
                link.attr("href", vacancy.getUrl());
                element.before(vac.outerHtml());
            }
            content = doc.html();
            content = "<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\"%>" + content;
        } catch (IOException e) {
            e.printStackTrace();
            content = "Some exception occurred";
        }
        return content;
    }

    private void updateFile(String content) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(jspFile), StandardCharsets.UTF_8));
            bufferedWriter.write(content);
            bufferedWriter.close();
        } catch (IOException e) {
        }
    }

    protected Document getDocument() throws IOException {
        return Jsoup.parse(new File(templateFile), "UTF-8");
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod(String city) {
        controller.onCitySelect(city);
    }
}
