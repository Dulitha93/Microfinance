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
import android.widget.Toast;


import com.example.microfinance.JsonUrl;

import com.example.microfinance.jsonParser;

public class GroupDetailsAdapterTask extends AsyncTask<String,Integer,ArrayAdapter<String>>{
    
	ArrayList<String> dialog_list = new ArrayList<String>();
	HashMap<String, String> dialog_salon = new HashMap<String, String>();
	static ArrayList<HashMap<String, String>> group_details_map_list;

		@Override
		protected ArrayAdapter<String> doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			
			 if (android.os.Build.VERSION.SDK_INT > 9) {
     		 
		        	
	  	        	StrictMode.ThreadPolicy policy = 
	  	        	        new StrictMode.ThreadPolicy.Builder().permitAll().build();
	  	        	StrictMode.setThreadPolicy(policy);
	  	        	}
	  	     		group_details_map_list = new ArrayList<HashMap<String, String>>();
	  	     			jsonParser json = new jsonParser(MainActivity.CON);
	  	     		    json.SetUrl("http://104.236.206.83:3000/group.summary");
	                    String Jsondata= json.ExecuteRequest();
	                    Jsondata = "{\"data\":"+Jsondata+"}";
	  	     		JSONArray jsonArray=	json.ConvertToJsonobj(Jsondata);
	  	     		
	  			//XMLParser parser = new XMLParser(this);
	  			//String xml = parser.getXmlFromUrl(URL); // getting XML from URL
	  			//Document doc = parser.getDomElement(xml); // getting DOM element
	  			
	  			//NodeList nl = doc.getElementsByTagName("DoctorInfo");
	  			// looping through all song nodes <song>
	  			for (int i = 0; i <jsonArray.length(); i++) {
	  				// creating new HashMap
	  				 JSONObject jsonObject;
	  				try {
	  					jsonObject = jsonArray.getJSONObject(i);
	  			
	  				HashMap<String, String> map = new HashMap<String, String>();
	  				//Element e = (Element) nl.item(i);
	  				// adding each child node to HashMap key => value
	  				map.put(Group.KEY_ID, jsonObject.getString( Group.KEY_ID));
	  				map.put( Group.KEY_PRODUCT_ID, jsonObject.getString( Group.KEY_PRODUCT_ID));
	  				map.put( Group.KEY_BALANCE, jsonObject.getString( Group.KEY_BALANCE));
	  				
	  				group_details_map_list.add(map);
	  				} catch (JSONException e) {
	  					// TODO Auto-generated catch block
	  					e.printStackTrace();
	  				}
	  				// adding HashList to ArrayList
	  				
	  			}
	  		
	  			
	  			// Getting adapter by passing xml data ArrayList
	  			GroupProfile.group_details_map_list= group_details_map_list;
	  			
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
