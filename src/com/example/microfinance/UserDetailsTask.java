package com.example.microfinance;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.StrictMode;
import android.view.View;
import android.widget.ArrayAdapter;

public class UserDetailsTask extends AsyncTask<String,Integer,ArrayAdapter<String>>{
    
	ArrayList<String> dialog_list = new ArrayList<String>();
	HashMap<String, String> dialog_salon = new HashMap<String, String>();
	static ArrayList<HashMap<String, String>> user_details_map_list;

		@Override
		protected ArrayAdapter<String> doInBackground(String... arg0) {
			
			// TODO Auto-generated method stub
			UserProfile.mProgressDialog.show();  
			
			 if (android.os.Build.VERSION.SDK_INT > 9) {
     		 
		        	
	  	        	StrictMode.ThreadPolicy policy = 
	  	        	        new StrictMode.ThreadPolicy.Builder().permitAll().build();
	  	        	StrictMode.setThreadPolicy(policy);
	  	        	}
	  	     		user_details_map_list = new ArrayList<HashMap<String, String>>();
	  	     			jsonParser json = new jsonParser(MainActivity.CON);
	  	     		    json.SetUrl("http://104.236.206.83:3000/find/"+ UserProfile.mem_id);
	                    String Jsondata= json.ExecuteRequest();
	                    Jsondata = "{\"data\":"+Jsondata+"}";
			
	  	     //		JSONArray jsonArray=	json.ConvertToJsonobj(Jsondata);
	  	     		
	  	          try {
	  	        	if(Jsondata != null && !Jsondata.isEmpty()){
	  	            JSONObject jsonObj = new JSONObject(Jsondata);
	  	                  String new_jsonData=  jsonObj.get("data").toString();
	  	            if (!new_jsonData.equals("0")){
	  	            	 JSONObject jsonObject = new JSONObject(new_jsonData);
	  	            	 
	  	            	HashMap<String, String> map = new HashMap<String, String>();
		  				//Element e = (Element) nl.item(i);
		  				// adding each child node to HashMap key => value
		  				map.put(User.KEY_FNAME, jsonObject.getString( User.KEY_FNAME));
		  				map.put( User.KEY_LNAME, jsonObject.getString( User.KEY_LNAME));
		  				map.put( User.KEY_ID, jsonObject.getString( User.KEY_ID));
		  				map.put( User.KEY_AREA, jsonObject.getString(User.KEY_AREA));
		  				map.put( User.KEY_GROUP_ID, jsonObject.getString(User.KEY_GROUP_ID));
		  				map.put( User.KEY_DUE_DATE, jsonObject.getString(User.KEY_DUE_DATE));
		  				map.put( User.KEY_BALANCE, jsonObject.getString(User.KEY_BALANCE));
		  				map.put( User.KEY_ADDRESS, jsonObject.getString(User.KEY_ADDRESS));
		  				map.put( User.KEY_TELEPHONE, jsonObject.getString(User.KEY_TELEPHONE));
		  				map.put( User.KEY_PRODUCT_ID, jsonObject.getString(User.KEY_PRODUCT_ID));
		  				
		  				user_details_map_list.add(map);

	  	            }
	  	        	}
	  	        	else{
	  	        		
	  	        	}
	  	        } catch (JSONException e) {
	  	            // TODO Auto-generated catch block
	  	            e.printStackTrace();
	  	        }
	  	    
	  		
	  	
	  			UserProfile.user_details_map_list=user_details_map_list;
	  			 //HashMap<String, String> i = user_details_map_list.get(0);
	  			 //UserProfile.mem_name.setText(i.get(User.KEY_FNAME)+" "+ i.get(User.KEY_LNAME));
	  		//	UserProfile.group_id_t_view.setText(i.get(User.KEY_GROUP_ID));
	  		UserProfile.setDetails();
	  			
			return null;
		}

		@Override
		protected void onPostExecute(ArrayAdapter<String> result) {
			// TODO Auto-generated method stub
			GroupProfile.linlaHeaderProgress.setVisibility(View.GONE);
	
			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {
			GroupProfile.linlaHeaderProgress.setVisibility(View.VISIBLE);
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

}
