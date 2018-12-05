
package thecaffeinecoders.investifybackend;

import android.app.Activity;
import android.view.View;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

/** Pull data from Crescit */
public class Crescit {


    public static CompanyData getObj(Activity a, View view) throws IOException {


        int currMnth = 0;

        String currMnthValue = null,currYear = null;

        String name = ("Crescit");

        String url = "http://www.crescit.se/ ";

        String description = "We are an independent fund company that is under the supervision of the Swedish Financial Supervisory Authority." +
                "The daily operations are conducted at Birger Jarlsgatan 8 in Stockholm since April 2013, where we manage the hedge fund Crescit." +
                "Since April 2014 Crescit Asset Management AB has the new AIFMD-permission. The fund passed 1 billion of assets under management during it’s first operating year.";

        String logoLink = ("https://firebasestorage.googleapis.com/v0/b/investify-2019.appspot.com/o/logotype.png?alt=media&token=b19f4103-ebfb-43ae-b696-51ac92d8b6b2");

        /* Supply the Crescit company performance data source from where the data has to be pulled
        using jsoup library */
        Document document = Jsoup.connect("http://www.crescit.se/en/performance/").get();
        String CrescitPerformance = document.select("tbody").text();

        HashMap<String, ArrayList<String>> perfValues = new HashMap<>();

        String[] sl = CrescitPerformance.split(" ");

       // get performance values of last 9 months that are provided for 2013
        for (int i = 26 ; i < 36; i += 14) {

            ArrayList<String> strMonthsList = new ArrayList<>();


            String year = (sl[i]);
            strMonthsList.add("0");
            strMonthsList.add("0");
            strMonthsList.add("0");
            strMonthsList.add(sl[i + 1].replace("%","").replace(",","."));;
            strMonthsList.add(sl[i + 2].replace("%","").replace(",","."));;
            strMonthsList.add(sl[i + 3].replace("%","").replace(",","."));;
            strMonthsList.add(sl[i + 4].replace("%","").replace(",","."));;
            strMonthsList.add(sl[i + 5].replace("%","").replace(",","."));;
            strMonthsList.add(sl[i + 6].replace("%","").replace(",","."));;
            strMonthsList.add(sl[i + 7].replace("%","").replace(",","."));;
            strMonthsList.add(sl[i + 8].replace("%","").replace(",","."));;
            strMonthsList.add(sl[i + 9].replace("%","").replace(",","."));;

            perfValues.put(year, strMonthsList);

        }

        //get performance values of previous 4 years
        for (int i = 37 ; i < 92; i += 14) {

            ArrayList<String> strMonthsList = new ArrayList<>();

            String year = (sl[i]);
            strMonthsList.add(sl[i + 1].replace("%","").replace(",","."));;
            strMonthsList.add(sl[i + 2].replace("%","").replace(",","."));;
            strMonthsList.add(sl[i + 3].replace("%","").replace(",","."));;
            strMonthsList.add(sl[i + 4].replace("%","").replace(",","."));;
            strMonthsList.add(sl[i + 5].replace("%","").replace(",","."));;
            strMonthsList.add(sl[i + 6].replace("%","").replace(",","."));;
            strMonthsList.add(sl[i + 7].replace("%","").replace(",","."));;
            strMonthsList.add(sl[i + 8].replace("%","").replace(",","."));;
            strMonthsList.add(sl[i + 9].replace("%","").replace(",","."));;
            strMonthsList.add(sl[i + 10].replace("%","").replace(",","."));;
            strMonthsList.add(sl[i + 11].replace("%","").replace(",","."));;
            strMonthsList.add(sl[i + 12].replace("%","").replace(",","."));;

            perfValues.put(year, strMonthsList);

        }
        //get performance values of 2018
        for (int i = 93 ; i < 101; i += 14) {

            Calendar cal = Calendar.getInstance();
            currMnth = cal.get(Calendar.MONTH);
            int currYearInt = cal.get(Calendar.YEAR);
            int currDayOfYear = cal.get(Calendar.DAY_OF_YEAR);
            currYear = String.valueOf(cal.get(Calendar.YEAR));

            ArrayList<String> strMonthsList = new ArrayList<>();

            String year = (sl[i]);
            if(currDayOfYear >= 39){strMonthsList.add(sl[i + 1].replace(",",".").replace("%",""));}else{strMonthsList.add("0");}
            if(currDayOfYear >= 67){strMonthsList.add(sl[i + 2].replace(",",".").replace("%",""));}else{strMonthsList.add("0");}
            if(currDayOfYear >= 98){strMonthsList.add(sl[i + 3].replace(",",".").replace("%",""));}else{strMonthsList.add("0");}
            if(currDayOfYear >= 128){strMonthsList.add(sl[i + 4].replace(",",".").replace("%",""));}else{strMonthsList.add("0");}
            if(currDayOfYear >= 159){strMonthsList.add(sl[i + 5].replace(",",".").replace("%",""));}else{strMonthsList.add("0");}
            if(currDayOfYear >= 189){strMonthsList.add(sl[i + 6].replace(",",".").replace("%",""));}else{strMonthsList.add("0");}
            if(currDayOfYear >= 220){strMonthsList.add(sl[i + 7].replace(",",".").replace("%",""));}else{strMonthsList.add("0");}
            if(currDayOfYear >= 251){strMonthsList.add(sl[i + 8].replace(",",".").replace("%",""));}else{strMonthsList.add("0");}
            if(currDayOfYear >= 281){strMonthsList.add(sl[i + 9].replace(",",".").replace("%",""));}else{strMonthsList.add("0");}
            if(currDayOfYear >= 312){strMonthsList.add(sl[i + 10].replace(",",".").replace("%",""));}else{strMonthsList.add("0");}
            if(currDayOfYear >= 342){strMonthsList.add(sl[i + 11].replace(",",".").replace("%",""));}else{strMonthsList.add("0");}
            if(currDayOfYear >= 8 && currYearInt==2019){strMonthsList.add(sl[i + 12].replace(",",".").replace("%",""));}else{strMonthsList.add("0");}

            // Take the current month data from the Arraylist.

            for(int cnt=0;cnt<strMonthsList.size();cnt++)
            {
                if (currMnth == cnt)
                {
                    currMnthValue = strMonthsList.get(cnt);
                }
            }

            perfValues.put(year, strMonthsList);

        }

        /* Update  only the  current month data into firebase */

        final CompanyData companyData = new CompanyData(description,logoLink,url,perfValues);

        if (view.getId() == R.id.button2){

            UpdateFirebase.updateMonth(name,perfValues,currYear, currMnthValue,currMnth, a);

        }
        /* Update all the data into firebase */

        else if (view.getId() == R.id.button3){
            UpdateFirebase.update(name,companyData,a);
        }

        return companyData;
    }


}
