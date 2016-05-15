import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class faceset {
	public static final String api_id = "b1d9354c55244df7afa05c784a75ba16";
	public static final String api_secret = "a7148f89bac542d0be3aa5256ea87a03";
	public static final String POST_URL ="https://v1-api.visioncloudapi.com/faceset/create";
	public static final String POST_URL1 ="https://v1-api.visioncloudapi.com/faceset/add_face";
	
	//返回faceset_id
	public static  String get_faceset_id(String name) {
	HttpClient httpClient = new DefaultHttpClient();
	String url = POST_URL;
	HttpPost httpPost = new HttpPost(url);
	NameValuePair pair1 = new BasicNameValuePair("api_id", api_id);
	NameValuePair pair2 = new BasicNameValuePair("api_secret", api_secret);
	NameValuePair pair3 = new BasicNameValuePair("name", name);
	ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
	pairs.add(pair1);
	pairs.add(pair2);
	pairs.add(pair3);
	try {
	HttpEntity requestEntity = new UrlEncodedFormEntity(pairs);
	httpPost.setEntity(requestEntity);
	try{
	HttpResponse response = httpClient.execute(httpPost);
	if (response.getStatusLine().getStatusCode() == 200) {
	HttpEntity entity = response.getEntity();
	BufferedReader reader = new BufferedReader(
	new InputStreamReader(entity.getContent()));
	String line = reader.readLine();
	
	JSONObject jsonObject = new JSONObject(line);
	String person_id= jsonObject.getString("faceset_id");  //必须有
	System.out.println(person_id);
	return person_id;
	}else{
	HttpEntity r_entity = response.getEntity();
	String responseString = EntityUtils.toString(r_entity);
	System.out.println("错误码是："+response.getStatusLine().getStatusCode()+""+response.getStatusLine().getReasonPhrase());
	System.out.println("出错原因是："+responseString);
	return null;
	// 你需要根据出错的原因判断错误信息，并修改
	}
	}catch (Exception e){
	e.printStackTrace();
	return null;
	}
	} catch (Exception e) {
	e.printStackTrace();
	return null;
	}
	}
}
