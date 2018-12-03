
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

        /* Supply the Excaliburfonder company performance data source from where the data has to be pulled
        using jsoup library */
        Document document = Jsoup.connect("https://excaliburfonder.se/en/funds/excalibur/performance/").get();
        String ExcaliburPerformance = document.select("tbody").text();

        HashMap<String, ArrayList<String>> perfValues = new HashMap<>();


        String[] sl = ExcaliburPerformance.split(" ");

        //get performance values of 2018
        for (int i = 13 ; i < 21; i += 14) {

            ArrayList<String> strMonthsList = new ArrayList<>();

            Calendar cal = Calendar.getInstance();
            currMnth = cal.get(Calendar.MONTH);
            int currYearInt = cal.get(Calendar.YEAR);
            currYear = String.valueOf(cal.get(Calendar.YEAR));

            String year = (sl[i]);
            if(currMnth >= 1){strMonthsList.add(sl[i + 1].replace(",","."));}else{strMonthsList.add("0");}
            if(currMnth >= 2){strMonthsList.add(sl[i + 2].replace(",","."));}else{strMonthsList.add("0");}
            if(currMnth >= 3){strMonthsList.add(sl[i + 3].replace(",","."));}else{strMonthsList.add("0");}
            if(currMnth >= 4){strMonthsList.add(sl[i + 4].replace(",","."));}else{strMonthsList.add("0");}
            if(currMnth >= 5){strMonthsList.add(sl[i + 5].replace(",","."));}else{strMonthsList.add("0");}
            if(currMnth >= 6){strMonthsList.add(sl[i + 6].replace(",","."));}else{strMonthsList.add("0");}
            if(currMnth >= 7){strMonthsList.add(sl[i + 7].replace(",","."));}else{strMonthsList.add("0");}
            if(currMnth >= 8){strMonthsList.add(sl[i + 8].replace(",","."));}else{strMonthsList.add("0");}
            if(currMnth >= 9){strMonthsList.add(sl[i + 9].replace(",","."));}else{strMonthsList.add("0");}
            if(currMnth >= 10){strMonthsList.add(sl[i + 10].replace(",","."));}else{strMonthsList.add("0");}
            if(currMnth == 11){strMonthsList.add(sl[i + 11].replace(",","."));}else{strMonthsList.add("0");}
            if(currMnth == 0 && currYearInt==2019){strMonthsList.add(sl[i + 12].replace(",","."));}else{strMonthsList.add("0");}

            perfValues.put(year, strMonthsList);

            //* Take the current month data from the Arraylist

            for(int cnt=0;cnt<strMonthsList.size();cnt++)
            {
                if (currMnth == cnt)
                {
                    currMnthValue = strMonthsList.get(cnt);
                }
            }

        }

        //get performance values of previous 5 years
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
            UpdateFirebase.update(name,companyData,a);
        }

        return companyData;
    }

    }




