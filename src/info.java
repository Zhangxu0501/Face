import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
public class info {
public static final String api_id = "b1d9354c55244df7afa05c784a75ba16";
public static final String api_secret = "a7148f89bac542d0be3aa5256ea87a03";
//public static final String image_id = "c7a4d40495c44e8581b146ca416d72f2";
public static final String GET_URL = "https://v1-api.visioncloudapi.com/info/image";
public static final String GET_URL1 = "https://v1-api.visioncloudapi.com/info/faceset";


//返回face_ids
public static String get_face_ids(String faceset_id) {
HttpClient httpClient = new DefaultHttpClient();
String getURL = GET_URL1 + "?api_id=" +api_id+"&api_secret="+api_secret+"&faceset_id="+faceset_id;
String url = getURL;
HttpGet httpGet = new HttpGet(url);
try{
HttpResponse response = httpClient.execute(httpGet);
if (response.getStatusLine().getStatusCode() == 200) {
HttpEntity entity = response.getEntity();
BufferedReader reader = new BufferedReader(
new InputStreamReader(entity.getContent()));
String line = reader.readLine();
System.out.println(line);
JSONObject jsonObject = new JSONObject(line);
String face_ids= jsonObject.getString("face_ids");  //必须有
return face_ids;
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
}

//返回face_id
public static String get_face_id(String image_id) {
HttpClient httpClient = new DefaultHttpClient();
String getURL = GET_URL + "?api_id=" +api_id+"&api_secret="+api_secret+"&image_id="+image_id+"&with_faces=1";
String url = getURL;
HttpGet httpGet = new HttpGet(url);
try{
HttpResponse response = httpClient.execute(httpGet);
if (response.getStatusLine().getStatusCode() == 200) {
HttpEntity entity = response.getEntity();
BufferedReader reader = new BufferedReader(
new InputStreamReader(entity.getContent()));
String line = reader.readLine();
System.out.println(line);

JSONObject jsonObject = new JSONObject(line);
line= jsonObject.getString("faces");  //必须有

char [] arrays=line.toCharArray();
char [] array=new char [arrays.length-2]; 
for(int x=1;x<arrays.length-1;x++)
{
	array[x-1]=arrays[x];
}
line=new String(array);
System.out.println("line"+line);
JSONObject jsonObject1 = new JSONObject(line);
String face_id=jsonObject1.getString("face_id");
System.out.println(face_id);
return face_id;
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
return null;
}
}