package com.example.microfinance;



import java.util.ArrayList;
import java.util.HashMap;









import android.app.Activity;
import android.app.AlertDialog;
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
import android.widget.Toast;

public class GroupProfile extends Activity {
	
	static String group_id;
	static Context CON;
	

	
	
	static LinearLayout linlaHeaderProgress;
	static ListView service_list;
	static ArrayList<HashMap<String, String>> group_details_map_list;
	private boolean isNetworkAvailable() {
		    ConnectivityManager connectivityManager 
		          = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
		}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actvity_group_profile);
		
		CON = this;
		
		TextView group_id_t_view = (TextView) findViewById(R.id.group_id);
		TextView product_id_t_view = (TextView) findViewById(R.id.product_id);
		TextView due_date_t_view = (TextView) findViewById(R.id.due_date);
		TextView due_amount_t_view = (TextView) findViewById(R.id.due_amount);
		
		Button payment_button = (Button) findViewById(R.id.payment_button);
		
		linlaHeaderProgress = (LinearLayout) findViewById(R.id.linlaHeaderProgress);
		
		GroupProfile.linlaHeaderProgress.setVisibility(View.VISIBLE);
		
		 if(isNetworkAvailable()){
	        	
			 new GroupDetailsAdapterTask().doInBackground("n/a");
        }else{
        final AlertDialog.Builder builder = new AlertDialog.Builder(CON);
                builder.setMessage("SalonSpa needs an active data connection to continue")
                      .setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                          public void onClick(DialogInterface dialog, int id) {
                       	   if(isNetworkAvailable()){
                       		new GroupDetailsAdapterTask().doInBackground("n/a");
                       	   }else{
                       	   builder.show();
                       	   }
                             
                          }
                      })
                     ;
                
                builder.show();
        }
		 
		 	boolean group_exists_flag = false;
			for(HashMap<String, String> i:group_details_map_list){
				
				if(i.get(Group.KEY_ID).equals(group_id)){
					group_exists_flag = true;
					group_id_t_view.setText(i.get(Group.KEY_ID));
					product_id_t_view.setText(i.get(Group.KEY_PRODUCT_ID));
			//		due_date_t_view.setText(i.get("Due Date"));
					due_amount_t_view.setText(i.get(Group.KEY_BALANCE));
				}
				
			
			}
			GroupProfile.linlaHeaderProgress.setVisibility(View.GONE);
			
			if(!group_exists_flag){
				 	Toast.makeText(CON, "Please Enter a valid Group ID", Toast.LENGTH_LONG).show();
				   Intent intent= new Intent(GroupProfile.this,MainActivity.class);
            	   startActivity(intent);	 
			}
			
			payment_button.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					MemberPayment.group_id=group_id;
					Intent intent= new Intent(GroupProfile.this,MemberPayment.class);
	            	   startActivity(intent);	 
				}});
		 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.group_profile, menu);
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
