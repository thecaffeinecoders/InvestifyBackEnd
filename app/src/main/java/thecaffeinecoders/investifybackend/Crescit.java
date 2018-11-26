
/* Pull data from Crescit */

package thecaffeinecoders.investifybackend;

import android.app.Activity;
import android.view.View;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Crescit {


    public static CompanyData getObj(Activity a, View view) throws IOException {


        int currMnth = 0;

        String currMnthValue = null,currYear = null;

        String name = ("Crescit");
        String description = ("Big Asset Management");
        String logoLink = ("https://firebasestorage.googleapis.com/v0/b/investify-2019.appspot.com/o/logotype.png?alt=media&token=b19f4103-ebfb-43ae-b696-51ac92d8b6b2");


        String Crescit = "http://www.crescit.se/en/performance/";
        Document document = Jsoup.connect(Crescit).get();

        String CrescitPerformance = document.select("tbody").text();

        HashMap<String, ArrayList<String>> perfValues = new HashMap<>();



        String[] sl = CrescitPerformance.split(" ");

       // iterate through last 9 months of 2013
        for (int i = 26 ; i < 36; i += 14) {

            ArrayList<String> strMonthsList = new ArrayList<>();


            String year = (sl[i]);
            strMonthsList.add("0".replace("%","").replace(",","."));
            strMonthsList.add("0".replace("%","").replace(",","."));
            strMonthsList.add("0".replace("%","").replace(",","."));;
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
        //iterate through 1st 10 months of 2018
        for (int i = 93 ; i < 101; i += 14) {

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
            strMonthsList.add("0".replace("%","").replace(",","."));;
            strMonthsList.add("0".replace("%","").replace(",","."));;


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

            perfValues.put(year, strMonthsList);

        }


        /* Update  only the  current month data into firebase */

        final CompanyData companyData = new CompanyData(description,logoLink,perfValues);

        if (view.getId() == R.id.button2){

            UpdateFirebase.updateMonth(name,perfValues,currYear, currMnthValue,currMnth, a);

        }
        /* Update data into firebase */

        else if (view.getId() == R.id.button3){
            UpdateFirebase.update(name,description,logoLink,companyData,a);
        }

        return companyData;
    }


}
