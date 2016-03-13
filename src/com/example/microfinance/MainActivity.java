package com.example.microfinance;

import com.example.microfinance.R;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;



//Toast.makeText(getApplicationContext(), "msg msg", Toast.LENGTH_SHORT).show();

public class MainActivity extends Activity {
	static Context CON;
	
	AlertDialog builder;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final Context context = this;
		  CON=this;
	//	  Toast.makeText(CON, "2", Toast.LENGTH_SHORT).show();

		RelativeLayout payment_layout = (RelativeLayout) findViewById(R.id.payment_layout);
    	RelativeLayout group_layout = (RelativeLayout) findViewById(R.id.group_layout);
    	RelativeLayout member_layout = (RelativeLayout) findViewById(R.id.member_layout);
    	
    	final String [] payment_options = {"Payment for Groups","Payment for Members"};
    	
    
    	        
    	payment_layout.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent next_intent= new Intent(MainActivity.this,MemberPayment.class);
     		   startActivity(next_intent);
     		   /*
			    AlertDialog.Builder builder = new AlertDialog.Builder(context);
			    builder.setTitle("Pick Payment Option")
			           .setItems(payment_options, new DialogInterface.OnClickListener() {
			               public void onClick(DialogInterface dialog, int which) {
			            	   
			            	   if(which == 0){
			            		   Intent next_intent= new Intent(MainActivity.this,GroupPayment.class);
			            		   startActivity(next_intent);
			            	   }
			            	   else if(which == 1){
			            		   Intent next_intent= new Intent(MainActivity.this,MemberPayment.class);
			            		   startActivity(next_intent);
			            	   }
			               // The 'which' argument contains the index position
			               // of the selected item
			           }
			    });
			     builder.create();
			     builder.show();
			     
			     */
				
			}
    		
    		
    	});
    	group_layout.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			    AlertDialog.Builder builder = new AlertDialog.Builder(context);
			    builder.setTitle("Enter Group ID");
			   
			    LayoutInflater inflater = getLayoutInflater();
				final View view = inflater.inflate(R.layout.group_id_dialog_layout, null);
				final EditText group_id_view = (EditText) view.findViewById(R.id.group_id);
			    // Inflate and set the layout for the dialog
			    // Pass null as the parent view because its going in the dialog layout
			    builder.setView(view);
			   builder.setPositiveButton("Search", new DialogInterface.OnClickListener() {
	               @Override
	               public void onClick(DialogInterface dialog, int id) {
	               
	            	   
	            	   GroupProfile.group_id = group_id_view.getText().toString();
	            	//   GroupProfile.group_id = "COL02";
	            	   Intent intent= new Intent(MainActivity.this,GroupProfile.class);
	            	   startActivity(intent);	            	   
	               }
	           });
		        builder.show();
				
			}
    		
    		
    	});
    	
    	member_layout.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				   Intent intent= new Intent(MainActivity.this,MemberSearch.class);
            	   startActivity(intent);	
				/*
			    AlertDialog.Builder builder = new AlertDialog.Builder(context);
			    builder.setTitle("Enter Member ID");
			   
			    LayoutInflater inflater = getLayoutInflater();
				final View view = inflater.inflate(R.layout.group_id_dialog_layout, null);
				final EditText group_id_view = (EditText) view.findViewById(R.id.group_id);
			    // Inflate and set the layout for the dialog
			    // Pass null as the parent view because its going in the dialog layout
			    builder.setView(view);
			   builder.setPositiveButton("Search", new DialogInterface.OnClickListener() {
	               @Override
	               public void onClick(DialogInterface dialog, int id) {
	               
	            	   
	            	 //  GroupProfile.group_id = group_id_view.getText().toString();
	            	   UserProfile.mem_id = "COL02";
	            	   Intent intent= new Intent(MainActivity.this,UserProfile.class);
	            	   startActivity(intent);	            	   
	               }
	           });
		        builder.show();
				*/
			}
    		
    		
    	});
    	
    	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
