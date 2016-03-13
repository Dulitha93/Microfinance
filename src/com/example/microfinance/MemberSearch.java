package com.example.microfinance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MemberSearch extends Activity {
	
	static ArrayList<HashMap<String, String>> member_map_list;
	static ProgressDialog mProgressDialog;
	
	static Context CON;
	
	static ArrayAdapter<String> adapter;
	static ArrayList<String> user_name_list = new ArrayList<String>();
	static HashMap<String, String> user_hash_map = new HashMap<String, String>();
	static ArrayList<String> original_data = new ArrayList<String>();
	static ListView mem_list;
	
	static List<Map<String, String>> listItem = new ArrayList<Map<String, String>>();
	static List<Map<String, String>> original_listItem = new ArrayList<Map<String, String>>();
	
	static String mem_id;
	
	static RelativeLayout no_result_layout;
	    
	
	private boolean isNetworkAvailable() {
	    ConnectivityManager connectivityManager 
	          = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.member_search_layout);
		CON=this;
		
			EditText search_input = (EditText) findViewById(R.id.inputSearch);
			mem_list = (ListView) findViewById(R.id.listall);
			no_result_layout    = (RelativeLayout) this.findViewById(R.id.no_result_layout);
		    TextView no_result_message =(TextView) this.findViewById(R.id.no_result_message);
		    
		  
		
		   mProgressDialog = new ProgressDialog(this);
	 	   mProgressDialog.setIndeterminate(false);
	 	   mProgressDialog.setCancelable(false);
	 	   mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
	 	   mProgressDialog.setMessage("Loading Members...");
	 	 
		
			 if(isNetworkAvailable()){
		        	
				 new MemberSearchTask().doInBackground("n/a");
	        }else{
	        final AlertDialog.Builder builder = new AlertDialog.Builder(CON);
	                builder.setMessage("MicroFinance app needs an active data connection to continue")
	                      .setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
	                          public void onClick(DialogInterface dialog, int id) {
	                       	   if(isNetworkAvailable()){
	                       		new MemberSearchTask().doInBackground("n/a");
	                       	   }else{
	                       	   builder.show();
	                       	   }
	                             
	                          }
	                      })
	                     ;
	                
	                builder.show();
	        }

			 search_input.addTextChangedListener(new TextWatcher() {

						public void afterTextChanged(Editable arg0) {
							// TODO Auto-generated method stub
							
						}

						public void beforeTextChanged(CharSequence arg0, int arg1,
								int arg2, int arg3) {
							// TODO Auto-generated method stub
							
						}

						public void onTextChanged(CharSequence arg0, int arg1, int arg2,
								int arg3) {
						//	MemberSearch.no_result_layout.setVisibility(View.INVISIBLE);
					
							String charText=arg0.toString();
							 charText = charText.toLowerCase();
							 listItem.clear();
							 
						        if (charText.length() == 0) {
						        	listItem.addAll(original_listItem);
						        } else {
						            for (Map<String, String> user_map: original_listItem) {
						            	
						                if (user_map.get("ID").toLowerCase().contains(charText)) {
						                	listItem.add(user_map);
						                }
						            }
						        }
						        final String[] fromMapKey = new String[] {"Name", "ID"};
						  		final int[] toLayoutId = new int[] {android.R.id.text1, android.R.id.text2};
						  		  
						        SimpleAdapter simple_adapter = new SimpleAdapter(MemberSearch.CON,listItem,android.R.layout.simple_list_item_2,fromMapKey,toLayoutId);
						       /*
						    	if(simple_adapter.getCount()==0){
					  				MemberSearch.no_result_layout.setVisibility(View.VISIBLE);
					  			}
						    	else{
						    		MemberSearch.no_result_layout.setVisibility(View.INVISIBLE);
						    	}
					  			*/
						       
						        mem_list.setAdapter(simple_adapter);
							// TODO Auto-generated method stub
						
						} 
			        	
			        
			            
			                    });
			 
			 mem_list.setOnItemClickListener(new OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					mem_id = ((TextView)(view.findViewById(android.R.id.text2))).getText().toString();
					UserProfile.mem_id=mem_id;
					Intent intent= new Intent(MemberSearch.this,UserProfile.class);
	            	startActivity(intent);	  
					
					
				}
				 
			 });
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.member_search, menu);
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
