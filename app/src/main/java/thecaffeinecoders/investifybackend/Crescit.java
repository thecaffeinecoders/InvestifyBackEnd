package thecaffeinecoders.investifybackend;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Crescit {


    public static CompanyData getObj() throws IOException {
        String name = ("Crescit");
        String description = ("Big Asset Management");

        String Crescit = "http://www.crescit.se/en/performance/";
        Document document = Jsoup.connect(Crescit).get();

        String CrescitPerformance = document.select("tbody").text();

        HashMap<String, ArrayList<String>> perfValues = new HashMap<>();



        String[] sl = CrescitPerformance.split(" ");

       // iterate through last 9 months of 2013
        for (int i = 26 ; i < 36; i += 14) {

            ArrayList<String> strMonthsList = new ArrayList<>();

            String year = (sl[i]);
            strMonthsList.add("0".replace("%",""));
            strMonthsList.add("0".replace("%",""));
            strMonthsList.add("0".replace("%",""));
            strMonthsList.add(sl[i + 1].replace("%",""));
            strMonthsList.add(sl[i + 2].replace("%",""));
            strMonthsList.add(sl[i + 3].replace("%",""));
            strMonthsList.add(sl[i + 4].replace("%",""));
            strMonthsList.add(sl[i + 5].replace("%",""));
            strMonthsList.add(sl[i + 6].replace("%",""));
            strMonthsList.add(sl[i + 7].replace("%",""));
            strMonthsList.add(sl[i + 8].replace("%",""));
            strMonthsList.add(sl[i + 9].replace("%",""));



            perfValues.put(year, strMonthsList);

        }

        for (int i = 37 ; i < 92; i += 14) {

            ArrayList<String> strMonthsList = new ArrayList<>();

            String year = (sl[i]);
            strMonthsList.add(sl[i + 1].replace("%",""));
            strMonthsList.add(sl[i + 2].replace("%",""));
            strMonthsList.add(sl[i + 3].replace("%",""));
            strMonthsList.add(sl[i + 4].replace("%",""));
            strMonthsList.add(sl[i + 5].replace("%",""));
            strMonthsList.add(sl[i + 6].replace("%",""));
            strMonthsList.add(sl[i + 7].replace("%",""));
            strMonthsList.add(sl[i + 8].replace("%",""));
            strMonthsList.add(sl[i + 9].replace("%",""));
            strMonthsList.add(sl[i + 10].replace("%",""));
            strMonthsList.add(sl[i + 11].replace("%",""));
            strMonthsList.add(sl[i + 12].replace("%",""));



            perfValues.put(year, strMonthsList);

        }
        //iterate through 1st 10 months of 2018
        for (int i = 93 ; i < 101; i += 14) {

            ArrayList<String> strMonthsList = new ArrayList<>();

            String year = (sl[i]);
            strMonthsList.add(sl[i + 1].replace("%",""));
            strMonthsList.add(sl[i + 2].replace("%",""));
            strMonthsList.add(sl[i + 3].replace("%",""));
            strMonthsList.add(sl[i + 4].replace("%",""));
            strMonthsList.add(sl[i + 5].replace("%",""));
            strMonthsList.add(sl[i + 6].replace("%",""));
            strMonthsList.add(sl[i + 7].replace("%",""));
            strMonthsList.add(sl[i + 8].replace("%",""));
            strMonthsList.add(sl[i + 9].replace("%",""));
            strMonthsList.add(sl[i + 10].replace("%",""));
            strMonthsList.add("0".replace("%",""));
            strMonthsList.add("0".replace("%",""));



            perfValues.put(year, strMonthsList);

        }




        CompanyData companyData = new CompanyData(name, description, perfValues);
        return companyData;
    }


}
