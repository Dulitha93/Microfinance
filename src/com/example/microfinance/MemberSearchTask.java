package com.example.microfinance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import android.os.AsyncTask;
import android.os.StrictMode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;

public class MemberSearchTask extends AsyncTask<String,Integer,ArrayAdapter<String>>{
    
	ArrayList<String> user_name_list = new ArrayList<String>();
	HashMap<String, String> user_hash_map = new HashMap<String, String>();
	static ArrayList<HashMap<String, String>> member_map_list;


		@Override
		protected ArrayAdapter<String> doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			
			 if (android.os.Build.VERSION.SDK_INT > 9) {
     		 
		        	
	  	        	StrictMode.ThreadPolicy policy = 
	  	        	        new StrictMode.ThreadPolicy.Builder().permitAll().build();
	  	        	StrictMode.setThreadPolicy(policy);
	  	        	}
	  	     		member_map_list = new ArrayList<HashMap<String, String>>();
	  	     			jsonParser json = new jsonParser(MainActivity.CON);
	  	     		    json.SetUrl("http://104.236.206.83:3000/users");
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
	  				map.put(User.KEY_ID, jsonObject.getString( User.KEY_ID));
	  				map.put(User.KEY_FNAME, jsonObject.getString( User.KEY_FNAME));
	  				map.put(User.KEY_LNAME, jsonObject.getString( User.KEY_LNAME));
	  				map.put(User.KEY_BALANCE, jsonObject.getString( User.KEY_BALANCE));
	  				map.put(User.KEY_AREA, jsonObject.getString( User.KEY_AREA));
	  				map.put(User.KEY_DUE_DATE, jsonObject.getString( User.KEY_DUE_DATE));
	  				
	  				member_map_list.add(map);
	  				} catch (JSONException e) {
	  					// TODO Auto-generated catch block
	  					e.printStackTrace();
	  				}
	  				// adding HashList to ArrayList
	  				
	  			}
	  		    final List<Map<String, String>> listItem = new ArrayList<Map<String, String>>();
	  		    
	  		    for(HashMap<String, String> user_map:member_map_list){
	  		    	 final Map<String, String> list_item_map = new HashMap<String, String>();
	  		    	list_item_map.put("Name", user_map.get(User.KEY_FNAME)+ " "+ user_map.get(User.KEY_LNAME));
	  		    	list_item_map.put("ID",user_map.get(User.KEY_ID) );
	  		    	
	  		    	listItem.add(list_item_map);
	  		    	
	  		    }
	  		  final String[] fromMapKey = new String[] {"Name", "ID"};
	  		  final int[] toLayoutId = new int[] {android.R.id.text1, android.R.id.text2};
	  			
	  		    SimpleAdapter simple_adapter = new SimpleAdapter(MemberSearch.CON,listItem,android.R.layout.simple_list_item_2,fromMapKey,toLayoutId);
	  		   
	  		    MemberSearch.listItem.addAll(listItem);
	  			MemberSearch.original_listItem.addAll(listItem);
	  			if(simple_adapter.getCount()==0){
	  				MemberSearch.no_result_layout.setVisibility(View.VISIBLE);
	  			}
	  		    
	  		
	  			MemberSearch.mem_list.setAdapter(simple_adapter);
	  			
	  			// Getting adapter by passing xml data ArrayList
	  			
	  		//	MemberSearch.mem_list.setAdapter(adapter);
	  			
	  			// Getting adapter by passing xml data ArrayList
	  			
	  			
	  			
	  			
			return null;
		}

		@Override
		protected void onPostExecute(ArrayAdapter<String> result) {
			// TODO Auto-generated method stub
			MemberSearch.mProgressDialog.dismiss();
	
			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {
			MemberSearch.mProgressDialog.show();
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

}
