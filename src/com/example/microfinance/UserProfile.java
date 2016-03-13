package com.example.microfinance;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class UserProfile extends Activity {
	
	static String mem_id;
	static Context CON;
	
	static ArrayAdapter<String> adapter;
	static ArrayList<String> dialog_list = new ArrayList<String>();
	HashMap<String, String> dialog_service = new HashMap<String, String>();
	static ArrayList<String> original_data = new ArrayList<String>();
	
	static TextView mem_name;
	static TextView mem_id_t_view ;
	static TextView group_id_t_view;
	static TextView due_date_t_view;
	static TextView due_amount_t_view;
	static TextView address_t_view;
	static TextView telephone_t_view;
	Button payment_button;
	
	
	
	static ProgressDialog mProgressDialog;
	static LinearLayout linlaHeaderProgress;
	static ListView service_list;
	static ArrayList<HashMap<String, String>> user_details_map_list;
	private boolean isNetworkAvailable() {
		    ConnectivityManager connectivityManager 
		          = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
		}
	
	public static void  setDetails(){
		 HashMap<String, String> i = user_details_map_list.get(0);
		 mem_name.setText(i.get(User.KEY_FNAME)+" "+ i.get(User.KEY_LNAME));
	     group_id_t_view.setText(i.get(User.KEY_GROUP_ID));
	     mem_id_t_view.setText(i.get(User.KEY_ID));
	     due_date_t_view.setText(i.get(User.KEY_DUE_DATE));
	     address_t_view.setText(i.get(User.KEY_ADDRESS));
	     telephone_t_view.setText(i.get(User.KEY_TELEPHONE));
	     due_amount_t_view.setText(i.get(User.KEY_BALANCE));
	   
	     mProgressDialog.dismiss();
	  //   linlaHeaderProgress.setVisibility(View.GONE);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_member_profile);
		
		CON = this;
		
		 mem_name = (TextView) findViewById(R.id.mem_name);
		 mem_id_t_view = (TextView) findViewById(R.id.mem_id);
		 group_id_t_view = (TextView) findViewById(R.id.group_id);
		 due_date_t_view = (TextView) findViewById(R.id.due_date);
		 due_amount_t_view = (TextView) findViewById(R.id.due_amount);
		 address_t_view = (TextView) findViewById(R.id.address);
		 telephone_t_view = (TextView) findViewById(R.id.telephone);
		 payment_button = (Button) findViewById(R.id.payment_button);
		 
		
		linlaHeaderProgress = (LinearLayout) findViewById(R.id.linlaHeaderProgress);
		
	//	linlaHeaderProgress.setVisibility(View.VISIBLE);
		
		   mProgressDialog = new ProgressDialog(this);
	 	   mProgressDialog.setIndeterminate(true);
	 	   mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
	 	   mProgressDialog.setMessage("Loading User Details...");
	 	   
		
		 if(isNetworkAvailable()){
	        	
			 new UserDetailsTask().doInBackground("n/a");
        }else{
        final AlertDialog.Builder builder = new AlertDialog.Builder(CON);
                builder.setMessage("SalonSpa needs an active data connection to continue")
                      .setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                          public void onClick(DialogInterface dialog, int id) {
                       	   if(isNetworkAvailable()){
                       		new UserDetailsTask().doInBackground("n/a");
                       	   }else{
                       	   builder.show();
                       	   }
                              // FIRE ZE MISSILES!
                          }
                      })
                     ;
                // Create the AlertDialog object and return it
                builder.show();
        }
		 
		
	     linlaHeaderProgress.setVisibility(View.GONE);
	     
	     payment_button.setOnClickListener( new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MemberPayment.mem_id = UserProfile.mem_id;
				Intent intent= new Intent(UserProfile.this,MemberPayment.class);
         	   startActivity(intent);	  
				
			}});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_profile, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
