/* Pull data from LYNX */


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

public class Lynx {


    public static CompanyData getObj(Activity a, View view) throws IOException {

        /* Assign Company name,description,Logo Link values.

         */
        String name = ("Lynx");
        String description = ("Lynx Asset Management was founded in 1999 and is today one of the world's leading firms in model-based asset management." +
                "Our investment process is entirely systematic and based on proprietary developed models that identify trends and other patterns in financial markets. Our objective is to deliver high risk-adjusted returns with low correlation to traditional asset classes.");

        String logoLink = ("https://firebasestorage.googleapis.com/v0/b/investify-2019.appspot.com/o/logo.png?alt=media&token=07416edb-1144-437a-a09c-3abb5499816d");

        /* Initialize the current month  current year and current Month values
         */

        int currMnth = 0;

        String currMnthValue = null,currYear = null;

        /* Supply the Lynx company performance data source from where the data has to be pulled
         */

        String Lynx = "http://www.lynxhedge.se/avkastning--man";



        Document document = Jsoup.connect(Lynx).get();
        String LynxPerformance = document.select("tbody").text();

        HashMap<String, ArrayList<String>> perfValues = new HashMap<>();


        String[] sl = LynxPerformance.split(" ");

        //Iterate through 1st 10 months of 2018

        for (int i = 252 ; i < 266; i += 14) {

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

        //* get the current month value of year 2018.

            Calendar cal = Calendar.getInstance();
            currMnth = cal.get(Calendar.MONTH);
            currYear = String.valueOf(cal.get(Calendar.YEAR));

            for(int cnt=0;cnt<strMonthsList.size();cnt++)
            {
                if (currMnth == cnt)
                {
                    currMnthValue = strMonthsList.get(cnt);
                }
            }

        }

        //* Pull the previous year data

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