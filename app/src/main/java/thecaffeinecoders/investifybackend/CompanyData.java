package thecaffeinecoders.investifybackend;

import java.util.ArrayList;
import java.util.HashMap;

public class CompanyData {
    String name ;
    String description ;
    String logoLInk;
    HashMap<String, ArrayList<String>> perfValues ;

    public CompanyData(String description,String logoLInk,HashMap<String, ArrayList<String>> perfValues) {
        this.description =description;
        this.perfValues  = perfValues;
        this.logoLInk = logoLInk;

    }


}

