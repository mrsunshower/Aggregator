package com.mrsunshower.Aggregator.view;

import com.mrsunshower.Aggregator.Controller;
import com.mrsunshower.Aggregator.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
import java.util.List;

/**
 * Created by KenTerror on 15.03.2017.
 */
public class HtmlView implements View {
    private final String filePath = "./src/" + this.getClass().getPackage().getName().replace('.', '/') + "/vacancies.html";
    //private final String filePath = ".\\4.JavaCollections\\src\\com\\javarush\\task\\task28\\task2810\\view" + "\\vacancies.html";

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
        doc.getElementsByAttributeValue("class","vacancy").remove();
        for (Vacancy vacancy : vacancies)
        {
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
        } catch (IOException e) {
            e.printStackTrace();
            content = "Some exception occurred";
        }
        return content;
    }

    private void updateFile(String content){
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));
            bufferedWriter.write(content);
            bufferedWriter.close();
        } catch (IOException e) {}
    }

    protected Document getDocument() throws IOException {
        return Jsoup.parse(new File(filePath), "UTF-8");
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod(){
        controller.onCitySelect("Odessa");
    }
}