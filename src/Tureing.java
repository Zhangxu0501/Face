import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;


public class Tureing {
	public static String ask(String s)
	{
		HttpClient httpClient = new DefaultHttpClient();
		String url = "http://www.tuling123.com/openapi/api";
		HttpPost httpPost = new HttpPost(url);
		
		NameValuePair key = new BasicNameValuePair("key","7486312636912ce0a97a729374f7e980");
		NameValuePair info = new BasicNameValuePair("info",s);
		NameValuePair user = new BasicNameValuePair("userid","123456");
		//NameValuePair loc = new BasicNameValuePair("loc","");
		ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(key);
		pairs.add(info);
		pairs.add(user);
		//pairs.add(loc);
		
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
		}
		
		}catch (Exception e){
		e.printStackTrace();
		}
		} catch (Exception e) {
		e.printStackTrace();
		}
		
		return null;
		}
	
    public  static String getEncoding(String str) {      
        String encode = "GB2312";      
       try {      
           if (str.equals(new String(str.getBytes(encode), encode))) {      
                String s = encode;      
               return s;      
            }      
        } catch (Exception exception) {      
        }      
        encode = "ISO-8859-1";      
       try {      
           if (str.equals(new String(str.getBytes(encode), encode))) {      
                String s1 = encode;      
               return s1;      
            }      
        } catch (Exception exception1) {      
        }      
        encode = "UTF-8";      
       try {      
           if (str.equals(new String(str.getBytes(encode), encode))) {      
                String s2 = encode;      
               return s2;      
            }      
        } catch (Exception exception2) {      
        }      
        encode = "GBK";      
       try {      
           if (str.equals(new String(str.getBytes(encode), encode))) {      
                String s3 = encode;      
               return s3;      
            }      
        } catch (Exception exception3) {      
        }      
       return "";      
    }      
    public static String changeCharset(String str, String oldCharset, String newCharset)
    		   throws UnsupportedEncodingException {
    		  if (str != null) {
    		   //鐢ㄦ棫鐨勫瓧绗︾紪鐮佽В鐮佸瓧绗︿覆銆傝В鐮佸彲鑳戒細鍑虹幇寮傚父銆�
    		   byte[] bs = str.getBytes(oldCharset);
    		   //鐢ㄦ柊鐨勫瓧绗︾紪鐮佺敓鎴愬瓧绗︿覆
    		   return new String(bs, newCharset);
    		  }
    		  return null;
    		 }
    public static void main(String [] args[])
    {
    	ask("aaa");
    }
}
