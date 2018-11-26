
/* Pull data from Excaliburfonder */

package thecaffeinecoders.investifybackend;

import android.app.Activity;
import android.view.View;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Excaliburfonder {

    public static CompanyData getObj(Activity a, View view) throws IOException{


        int currMnth = 0;

        String currMnthValue = null,currYear = null;

        /* Assign Company name,description,Logo Link
         */

        String name = ("Excaliburfonder");

        String url = "https://excaliburfonder.se/en/";

        String description = "It is a small independent fund management company that manages, administers and controls the Excalibur and Trude hedge funds." +
                "With the help of international contacts and a well-established network within the banking industry, together with the use of independent analysis from well renowned firms such as BCA, Roubini Global Economics, Pantheon Macroeconomics, SGH Macro Advisors and Medley Global Advisors, we have laid the foundation for successful asset management.";


        String logoLink = ("https://firebasestorage.googleapis.com/v0/b/investify-2019.appspot.com/o/excalibur_fonder.png?alt=media&token=f190c5c5-2674-480b-914e-5a2855bd9a02");

        String Excalibur = "https://excaliburfonder.se/en/funds/excalibur/performance/";


        Document document = Jsoup.connect(Excalibur).get();

        String ExcaliburPerformance = document.select("tbody").text();

        HashMap<String, ArrayList<String>> perfValues = new HashMap<>();



        String[] sl = ExcaliburPerformance.split(" ");

        //iterate through 1st 10 months of 2018
        for (int i = 13 ; i < 21; i += 14) {

            ArrayList<String> strMonthsList = new ArrayList<>();


            String year = (sl[i]);
            strMonthsList.add(sl[i + 1].replace(",","."));
            strMonthsList.add(sl[i + 2].replace(",","."));
            strMonthsList.add(sl[i + 3].replace(",","."));
            strMonthsList.add(sl[i + 4].replace(",","."));
            strMonthsList.add(sl[i + 5].replace(",","."));
            strMonthsList.add(sl[i + 6].replace(",","."));
            strMonthsList.add(sl[i + 7].replace(",","."));
            strMonthsList.add(sl[i + 8].replace(",","."));
            strMonthsList.add(sl[i + 9].replace(",","."));
            strMonthsList.add(sl[i + 10].replace(",","."));
            strMonthsList.add("0".replace(",","."));
            strMonthsList.add("0".replace(",","."));

            perfValues.put(year, strMonthsList);


            Calendar cal = Calendar.getInstance();
            currMnth = cal.get(Calendar.MONTH);
            currYear = String.valueOf(cal.get(Calendar.YEAR));


            //* Take the current month data from the Arraylist

            for(int cnt=0;cnt<strMonthsList.size();cnt++)
            {
                if (currMnth == cnt)
                {
                    currMnthValue = strMonthsList.get(cnt);
                }
            }

        }

        for (int i = 25 ; i < 85; i += 14) {

            ArrayList<String> strMonthsList = new ArrayList<>();

            String year = (sl[i]);
            strMonthsList.add(sl[i + 1].replace(",","."));
            strMonthsList.add(sl[i + 2].replace(",","."));
            strMonthsList.add(sl[i + 3].replace(",","."));
            strMonthsList.add(sl[i + 4].replace(",","."));
            strMonthsList.add(sl[i + 5].replace(",","."));
            strMonthsList.add(sl[i + 6].replace(",","."));
            strMonthsList.add(sl[i + 7].replace(",","."));
            strMonthsList.add(sl[i + 8].replace(",","."));
            strMonthsList.add(sl[i + 9].replace(",","."));
            strMonthsList.add(sl[i + 10].replace(",","."));
            strMonthsList.add(sl[i + 11].replace(",","."));
            strMonthsList.add(sl[i + 12].replace(",","."));



            perfValues.put(year, strMonthsList);

        }



        /* Update  only the  current month data into firebase */

        final CompanyData companyData = new CompanyData(description,logoLink,url,perfValues);

        if (view.getId() == R.id.button2){

            UpdateFirebase.updateMonth(name,perfValues,currYear, currMnthValue,currMnth, a);

        }
        /* Update data into firebase */

        else if (view.getId() == R.id.button3){
            UpdateFirebase.update(name,description,logoLink,url,companyData,a);
        }

        return companyData;
    }

    }




