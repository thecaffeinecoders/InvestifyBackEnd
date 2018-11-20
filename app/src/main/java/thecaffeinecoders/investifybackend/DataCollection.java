package thecaffeinecoders.investifybackend;

import java.io.IOException;
import java.util.ArrayList;

/*f
*/
public class DataCollection {

    static ArrayList<CompanyData> listObjAllCompanies = new ArrayList<>();

    public static void main(String[] args) throws Exception {
/*

        CompanyData objLynx = Lynx.getObj();
        CompanyData objExcaliburfonder = Excaliburfonder.getObj();
        CompanyData objCrescit = Crescit.getObj();

        listObjAllCompanies.add(objLynx);
        listObjAllCompanies.add(objExcaliburfonder);
        listObjAllCompanies.add(objCrescit);
*/

        listObjAllCompanies.add(Lynx.getObj());
        listObjAllCompanies.add(Excaliburfonder.getObj());
        listObjAllCompanies.add(Crescit.getObj());

    }
}
