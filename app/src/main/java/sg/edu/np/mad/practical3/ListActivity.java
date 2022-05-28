package sg.edu.np.mad.practical3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListActivity extends AppCompatActivity {
    private String TAG = "List Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Log.v(TAG,"On Create!");
        List<User> userList = new ArrayList<>();
        ImageView imageButton = findViewById(R.id.imageView);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            //Refer to week 3 activity for another way of create Alert Dialog
            public void onClick(View v) {
                Log.v(TAG, "Image Pressed");
                AlertDialog alertDialog = new AlertDialog.Builder(ListActivity.this).create();
                alertDialog.setTitle("Profile");
                alertDialog.setMessage("MADness");
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Close",
                    new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int which){
                            dialog.dismiss();
                        }
                    });
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "View",
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int which){
                                for (int i = 0; i < 20; i++) {
                                    long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
                                    int userID = (int)number;
                                    String userName = "Name" + userID;
                                    String userDescription = "Description" + number;
                                    User user = new User();
                                    user.setId(userID);
                                    user.setName(userName);
                                    user.setDescription(userDescription);
                                    user.setFollowed(false);
                                    userList.add(user);
                                }
                                Intent myIntent = new Intent(ListActivity.this,MainActivity2.class);
                                Bundle args = new Bundle();
                                args.putSerializable("userList", (Serializable) userList);
                                myIntent.putExtra("Bundle",args);
                                startActivity(myIntent);

                                //If got multiple data to pass, eg pass username and password to next activity
                                //Use Bundle myBundle = new Bundle();
                                //myBundle.PutString("Username", user.getUsername());
                                //myBundle.Put String("Password", user.getPassword());
                                //Intent myIntent = new Intent(ListActivity.this,MainActivity.class);
                                //myIntent.putExtras(myBundle);  **Take note of extras and extra
                                //If putExtra(s) means passing multiple data, if putExtra means passing one data
                            }
                        });
                alertDialog.show();
            }
        });
    }
}