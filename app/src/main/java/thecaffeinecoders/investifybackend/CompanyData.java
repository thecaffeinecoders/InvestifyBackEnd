package thecaffeinecoders.investifybackend;

import java.util.ArrayList;
import java.util.HashMap;

public class CompanyData {
    String name ;
    String description ;
    HashMap<String, ArrayList<String>> perfValues ;

    public CompanyData(String name,String description,HashMap<String, ArrayList<String>> perfValues) {
        this.name= name;
        this.description =description;
        this.perfValues  = perfValues;

    }


}

