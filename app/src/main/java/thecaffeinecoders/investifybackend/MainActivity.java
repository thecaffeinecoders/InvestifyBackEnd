
/* Backend Application for Investify  to update the Firebase database! */

package thecaffeinecoders.investifybackend;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.jsoup.Jsoup;
import org.w3c.dom.Document;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    /* Update the currernt Month values of the company like LYNX,Excaliburfonder,Crescit*
        Run a parrarel processing to fetch data from data source
     */

    public void onClickBtn(final View view) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    CompanyData objLynx = Lynx.getObj(MainActivity.this,view);
                    CompanyData objexca = Excaliburfonder.getObj(MainActivity.this,view);
                    CompanyData objCrescit = Crescit.getObj(MainActivity.this,view);

                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    /* Update  the  entire database for all the companies ! */

    public void onClickBt(final View view) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    CompanyData objLynx = Lynx.getObj(MainActivity.this,view);
                    CompanyData objexca = Excaliburfonder.getObj(MainActivity.this,view);
                    CompanyData objCrescit = Crescit.getObj( MainActivity.this,view);

                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
