package com.example.microfinance;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.StrictMode;
import android.view.View;
import android.widget.ArrayAdapter;

public class MemberPaymentTask extends AsyncTask<String,Integer,ArrayAdapter<String>>{
    


		@Override
		protected ArrayAdapter<String> doInBackground(String... arg0) {
			
			// TODO Auto-generated method stub
		//	MemberPayment.mProgressDialog.show();  
			MemberPayment.message_view.setText("Sending Details...");
			MemberPayment.message_view.setVisibility(View.VISIBLE);
			
			 if (android.os.Build.VERSION.SDK_INT > 9) {
     		 
		        	
	  	        	StrictMode.ThreadPolicy policy = 
	  	        	        new StrictMode.ThreadPolicy.Builder().permitAll().build();
	  	        	StrictMode.setThreadPolicy(policy);
	  	        	}
			 			
			           // String jsonString = "{\"id\":230,\"amount\":200,\"code\":04}";
			            String url = "http://104.236.206.83:3000/transaction";
			        
	  	     			jsonParserPOST json = new jsonParserPOST(MainActivity.CON);
	  	     		    json.SetUrl(url);
	  	     		    json.setHeaders("id", MemberPayment.mem_id);
	  	     		    json.setHeaders("amount",  MemberPayment.amount);
	  	     		    json.setHeaders("code", "04");
	  	     		    
	                    String Jsondata= json.ExecuteRequest();
	                 //   Jsondata = "{\"data\":"+Jsondata+"}";
	                    JSONObject jsonObj;
	                    String new_jsonData=" ";
						try {
							jsonObj = new JSONObject(Jsondata);
							 new_jsonData=  jsonObj.get("end").toString();
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}catch(NullPointerException e){
							e.printStackTrace();
						}
	  	                  
			
	  	     //		JSONArray jsonArray=	json.ConvertToJsonobj(Jsondata);
	                    
	       //   String done_str = "\"Done\"\\n" ;        
	  	      if(new_jsonData.equals("1")){
	  	    //	 MemberPayment.mProgressDialog.dismiss();  
	  	    //	MemberPayment.showToast();
	  	    	MemberPayment.message_view.setText("Payment Successful!");
	  	      }else{
	  	    	MemberPayment.message_view.setText("Payment Failed.Try Again");
	  	    	MemberPayment.pay_button.setEnabled(true);
	  	      }
	  	    
	  		
	  	
	  			//UserProfile.user_details_map_list=user_details_map_list;
	  			 //HashMap<String, String> i = user_details_map_list.get(0);
	  			 //UserProfile.mem_name.setText(i.get(User.KEY_FNAME)+" "+ i.get(User.KEY_LNAME));
	  		//	UserProfile.group_id_t_view.setText(i.get(User.KEY_GROUP_ID));
	  	//	UserProfile.setDetails();
	  	       
	  			
			return null;
		}

		@Override
		protected void onPostExecute(ArrayAdapter<String> result) {
			// TODO Auto-generated method stub
		//	GroupProfile.linlaHeaderProgress.setVisibility(View.GONE);
	
			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {
		//	GroupProfile.linlaHeaderProgress.setVisibility(View.VISIBLE);
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

}
