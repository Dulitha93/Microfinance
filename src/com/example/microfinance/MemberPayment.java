package com.example.microfinance;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MemberPayment extends Activity {
	
	TextView t_mem_id;
	static TextView t_group_id;
	TextView t_amount;
	static Button pay_button;
	static TextView message_view;
	
	static String mem_id;
	static String group_id=null;
	static String amount;
	
	static Context CON;
	static ProgressDialog mProgressDialog;
	static Toast toast;
	
	 AlertDialog.Builder builder;
	 
	
	private boolean isNetworkAvailable() {
	    ConnectivityManager connectivityManager 
	          = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}
	
	public static void showToast(){
		toast.makeText(CON, "Payment Completed", Toast.LENGTH_LONG);
	}
	

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mem_pay);
		
		CON=this;
		
		t_mem_id = (TextView) findViewById(R.id.mem_id);
		t_group_id = (TextView) findViewById(R.id.group_id);
		t_amount = (TextView) findViewById(R.id.amount);
		message_view = (TextView) findViewById(R.id.message);
		pay_button = (Button) findViewById(R.id.pay_button);
		
		if(!group_id.isEmpty()){
			t_group_id.setText(group_id);
		}
		
		message_view.setVisibility(View.INVISIBLE);
		t_mem_id.setText(mem_id);
		
		
		
		
		
 	   
 	   mProgressDialog = new ProgressDialog(this);
 	   mProgressDialog.setIndeterminate(false);
 	   mProgressDialog.setCancelable(false);
 	   mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
 	   mProgressDialog.setMessage("Sending Payment Details...");
 //	   mProgressDialog.show();  
		
		
		pay_button.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pay_button.setEnabled(false);
				
				mem_id = t_mem_id.getText().toString().replaceAll("\\s+","");
				group_id = t_group_id.getText().toString();
				amount = t_amount.getText().toString().replaceAll("\\s+","");
				
				boolean flag = false;
				int test2;
				try{
					int test = Integer.parseInt(mem_id);
					test2 = Integer.parseInt(amount);
					
					if(test2<0){
						flag=true;
					}
					
				}catch(Exception e){
					
					flag = true;
				}
				
			   if(!flag){
			   
				 if(isNetworkAvailable()){
			        	
					 new MemberPaymentTask().doInBackground("n/a");
		        }else{
		        final AlertDialog.Builder builder = new AlertDialog.Builder(CON);
		                builder.setMessage("MicroFinance app needs an active data connection to continue")
		                      .setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
		                          public void onClick(DialogInterface dialog, int id) {
		                       	   if(isNetworkAvailable()){
		                       		new MemberPaymentTask().doInBackground("n/a");
		                       	   }else{
		                       	   builder.show();
		                       	   }
		                             
		                          }
		                      })
		                     ;
		                
		                builder.show();
		        }
				
			   }
			   else{
				   Toast.makeText(CON, "Please enter valid amounts", Toast.LENGTH_SHORT).show();
				   t_mem_id.setText(" ");
				   t_group_id.setText(" ");
				   t_amount.setText(" ");
				   pay_button.setEnabled(true);
			   }
				
			}
			
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.member_payment, menu);
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
		if (id == R.id.new_payment) {
			pay_button.setEnabled(true);
			t_mem_id.setText(" ");
			t_group_id.setText(" ");
			t_amount.setText(" ");
		}
		return super.onOptionsItemSelected(item);
	}
}
