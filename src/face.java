import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class face {
public static final String api_id = "b1d9354c55244df7afa05c784a75ba16";
public static final String api_secret = "a7148f89bac542d0be3aa5256ea87a03";
public static final String POST_URL = "https://v1-api.visioncloudapi.com/face/detection";


//search，未完待续。


public static final String POST_URL1 ="https://v1-api.visioncloudapi.com/face/search";
public static String search(String faceset_id,String face_id) {
HttpClient httpClient = new DefaultHttpClient();
String url = POST_URL1;
HttpPost httpPost = new HttpPost(url);
NameValuePair pair1 = new BasicNameValuePair("api_id", api_id);
NameValuePair pair2 = new BasicNameValuePair("api_secret", api_secret);
NameValuePair pair3 = new BasicNameValuePair("face_id", face_id);
NameValuePair pair4 = new BasicNameValuePair("faceset_id", faceset_id);
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
return line;
}else{
HttpEntity r_entity = response.getEntity();
String responseString = EntityUtils.toString(r_entity);
System.out.println("错误码是："+response.getStatusLine().getStatusCode()+""+response.getStatusLine().getReasonPhrase());
System.out.println("出错原因是："+responseString);
// 你需要根据出错的原因判断错误信息，并修改
return null;
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


//返回imageid
public static String get_image_id(String filepath) throws ClientProtocolException, IOException, JSONException {
HttpClient httpclient = new DefaultHttpClient();
HttpPost post = new HttpPost(POST_URL);
FileBody fileBody = new FileBody(new File(filepath));
StringBody id = new StringBody(api_id);
StringBody secret = new StringBody(api_secret);
MultipartEntity entity = new MultipartEntity();
entity.addPart("file", fileBody);
entity.addPart("api_id", id);
entity.addPart("api_secret", secret);
post.setEntity(entity);
HttpResponse response = httpclient.execute(post);
if (response.getStatusLine().getStatusCode() == 200) {
HttpEntity entitys = response.getEntity();
BufferedReader reader = new BufferedReader(
new InputStreamReader(entitys.getContent()));
String line = reader.readLine();
JSONObject jsonObject = new JSONObject(line);
String image_id= jsonObject.getString("image_id");  //必须有
System.out.println(image_id);
System.out.println(image_id);
return image_id;
}else{
HttpEntity r_entity = response.getEntity();
String responseString = EntityUtils.toString(r_entity);
System.out.println("错误码是："+response.getStatusLine().getStatusCode()+""+response.getStatusLine().getReasonPhrase());
System.out.println("出错原因是："+responseString);
return null;
// 你需要根据出错的原因判断错误信息，并修改
}
}
}