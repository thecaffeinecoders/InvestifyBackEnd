package thecaffeinecoders.investifybackend;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Excaliburfonder {

    public static CompanyData getObj() throws IOException{
        String name = ("Excaliburfonder");
        String description = ("Fund company under supervision of the Swedish Financial Supervisory Authority");

        String Excalibur = "https://excaliburfonder.se/en/funds/excalibur/performance/";
        Document document = Jsoup.connect(Excalibur).get();

        String ExcaliburPerformance = document.select("tbody").text();

        HashMap<String, ArrayList<String>> perfValues = new HashMap<>();



        String[] sl = ExcaliburPerformance.split(" ");

        //iterate through 1st 10 months of 2018
        for (int i = 13 ; i < 21; i += 14) {

            ArrayList<String> strMonthsList = new ArrayList<>();

            String year = (sl[i]);
            strMonthsList.add(sl[i + 1]);
            strMonthsList.add(sl[i + 2]);
            strMonthsList.add(sl[i + 3]);
            strMonthsList.add(sl[i + 4]);
            strMonthsList.add(sl[i + 5]);
            strMonthsList.add(sl[i + 6]);
            strMonthsList.add(sl[i + 7]);
            strMonthsList.add(sl[i + 8]);
            strMonthsList.add(sl[i + 9]);
            strMonthsList.add(sl[i + 10]);
            strMonthsList.add("0");
            strMonthsList.add("0");

            perfValues.put(year, strMonthsList);

        }

        for (int i = 25 ; i < 85; i += 14) {

            ArrayList<String> strMonthsList = new ArrayList<>();

            String year = (sl[i]);
            strMonthsList.add(sl[i + 1]);
            strMonthsList.add(sl[i + 2]);
            strMonthsList.add(sl[i + 3]);
            strMonthsList.add(sl[i + 4]);
            strMonthsList.add(sl[i + 5]);
            strMonthsList.add(sl[i + 6]);
            strMonthsList.add(sl[i + 7]);
            strMonthsList.add(sl[i + 8]);
            strMonthsList.add(sl[i + 9]);
            strMonthsList.add(sl[i + 10]);
            strMonthsList.add(sl[i + 11]);
            strMonthsList.add(sl[i + 12]);



            perfValues.put(year, strMonthsList);

        }


        CompanyData companyData = new CompanyData(name, description, perfValues);

        return companyData;
    }


}


