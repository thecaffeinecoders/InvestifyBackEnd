
package thecaffeinecoders.investifybackend;


import android.app.Activity;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.*;

/** Pull data from LYNX */
public class Lynx {


    public static CompanyData getObj(Activity a, View view) throws IOException {

        /* Assign Company name,description,Logo Link values.

         */
        String name = ("Lynx");
        String url = "http://www.lynxhedge.se/";

        String description = ("Lynx Asset Management was founded in 1999 and is today one of the world's leading firms in model-based asset management." +
                "Our investment process is entirely systematic and based on proprietary developed models that identify trends and other patterns in financial markets. Our objective is to deliver high risk-adjusted returns with low correlation to traditional asset classes.");

        String logoLink = ("https://firebasestorage.googleapis.com/v0/b/investify-2019.appspot.com/o/logo.png?alt=media&token=07416edb-1144-437a-a09c-3abb5499816d");

        /* Initialize the current month  current year and current Month values
         */

        int currMnth = 0;

        String currMnthValue = null,currYear = null;

        /* Supply the Lynx company performance data source from where the data has to be pulled
        using jsoup library */
        Document document = Jsoup.connect("http://www.lynxhedge.se/avkastning--man").get();
        String LynxPerformance = document.select("tbody").text();

        HashMap<String, ArrayList<String>> perfValues = new HashMap<>();

        String[] sl = LynxPerformance.split(" ");

        //get performance values of 2018

        for (int i = 252 ; i < 266; i += 14) {

            Calendar cal = Calendar.getInstance();
            currMnth = cal.get(Calendar.MONTH);
            int currYearInt = cal.get(Calendar.YEAR);
            int currDayOfYear = cal.get(Calendar.DAY_OF_YEAR);
            currYear = String.valueOf(cal.get(Calendar.YEAR));

            ArrayList<String> strMonthsList = new ArrayList<>();

            String year = (sl[i]);
            if(currDayOfYear >= 39){strMonthsList.add(sl[i + 1].replace(",","."));}else{strMonthsList.add("0");}
            if(currDayOfYear >= 67){strMonthsList.add(sl[i + 2].replace(",","."));}else{strMonthsList.add("0");}
            if(currDayOfYear >= 98){strMonthsList.add(sl[i + 3].replace(",","."));}else{strMonthsList.add("0");}
            if(currDayOfYear >= 128){strMonthsList.add(sl[i + 4].replace(",","."));}else{strMonthsList.add("0");}
            if(currDayOfYear >= 159){strMonthsList.add(sl[i + 5].replace(",","."));}else{strMonthsList.add("0");}
            if(currDayOfYear >= 189){strMonthsList.add(sl[i + 6].replace(",","."));}else{strMonthsList.add("0");}
            if(currDayOfYear >= 220){strMonthsList.add(sl[i + 7].replace(",","."));}else{strMonthsList.add("0");}
            if(currDayOfYear >= 251){strMonthsList.add(sl[i + 8].replace(",","."));}else{strMonthsList.add("0");}
            if(currDayOfYear >= 281){strMonthsList.add(sl[i + 9].replace(",","."));}else{strMonthsList.add("0");}
            if(currDayOfYear >= 312){strMonthsList.add(sl[i + 10].replace(",","."));}else{strMonthsList.add("0");}
            if(currDayOfYear >= 342){strMonthsList.add(sl[i + 11].replace(",","."));}else{strMonthsList.add("0");}
            if(currMnth == 0 && currYearInt==2019){strMonthsList.add(sl[i + 12].replace(",","."));}else{strMonthsList.add("0");}

            perfValues.put(year, strMonthsList);

        // get the current month value of year 2018.

            for(int cnt=0;cnt<strMonthsList.size();cnt++)
            {
                if (currMnth == cnt)
                {
                    currMnthValue = strMonthsList.get(cnt);
                }
            }

        }

        //get performance values of previous 5 years
        for (int i = 182; i < 252; i += 14) {

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