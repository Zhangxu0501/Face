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


public class add_face {
	public static final String api_id = "b1d9354c55244df7afa05c784a75ba16";
	public static final String api_secret = "a7148f89bac542d0be3aa5256ea87a03";
	public static final String POST_URL1 ="https://v1-api.visioncloudapi.com/faceset/add_face";

	public  void add_face_to_face_set(String faceset_id,String face_id) {
		HttpClient httpClient = new DefaultHttpClient();
		String url = POST_URL1;
		HttpPost httpPost = new HttpPost(url);
		NameValuePair pair1 = new BasicNameValuePair("api_id", api_id);
		NameValuePair pair2 = new BasicNameValuePair("api_secret", api_secret);
		NameValuePair pair3 = new BasicNameValuePair("faceset_id", faceset_id);
		NameValuePair pair4 = new BasicNameValuePair("face_id", face_id);
		ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(pair1);
		pairs.add(pair2);
		pairs.add(pair3);
		pairs.add(pair4);
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
		System.out.println(line);
		}else{
		HttpEntity r_entity = response.getEntity();
		String responseString = EntityUtils.toString(r_entity);
		System.out.println("错误码是："+response.getStatusLine().getStatusCode()+""+response.getStatusLine().getReasonPhrase());
		System.out.println("出错原因是："+responseString);
		// 你需要根据出错的原因判断错误信息，并修改
		}
		}catch (Exception e){
		e.printStackTrace();
		}
		} catch (Exception e) {
		e.printStackTrace();
		}
		}
}
