package thecaffeinecoders.investifybackend;

import android.app.Activity;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/* Update The current Month value in Firebase

 */

public class UpdateFirebase {

    public static void updateMonth(final String name, final HashMap<String, ArrayList<String>> prefValues, final String currYear, final String curMnthValue
            , final int currMnth, final Activity a)
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getReference( );

        final String Mnth = String.valueOf(currMnth);

        ref.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Map<String, Object> childUpdates = new HashMap<>();

                        childUpdates.put(Mnth,curMnthValue);

                        ref.child("CompanyData").child(name).child("perfValues").child(currYear).updateChildren(childUpdates);
                        Toast.makeText(a, "Current Monthly value updated to Firebase",Toast.LENGTH_SHORT).show();
                       // Toast.makeText()


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }
                });
    }

    /* Entire company details like Company Name,Description Logoling All years Monthly profit into FireBase

     */

    public static void update(String name, String description, String logoLink, CompanyData companyData, final Activity a)

    {
    final CompanyData lv_companyData = companyData;

    final String companyname = name;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference ref = database.getReference( );

        ref.addListenerForSingleValueEvent(
                new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            Map<String, Object> childUpdates = new HashMap<>();

             childUpdates.put(companyname, lv_companyData);
             ref.child("CompanyData").updateChildren(childUpdates);
            Toast.makeText(a, "Updated the Database !",Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            //handle databaseError
        }
    });


}
}
