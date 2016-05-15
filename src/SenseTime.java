import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;


public class SenseTime {
	public static String add_persion(String filepath,String name) throws ClientProtocolException, IOException, JSONException
	{
		//face_id(�����Ψһ��־),name��Ҫ������ݿ⡣
		
		add_face add_face = new add_face();
		String image_id=face.get_image_id(filepath);
		String face_id=info.get_face_id(image_id);
		String faceset_id="ac95021306cb4814a59d7cfc0ffa76c0";
		add_face.add_face_to_face_set(faceset_id, face_id);
		
		return face_id;
		
	}
	
	public static String is_who(String filepath) throws ClientProtocolException, IOException, JSONException
	{
		String image_id=face.get_image_id(filepath);
		String face_id=info.get_face_id(image_id);
		String line = face.search("ac95021306cb4814a59d7cfc0ffa76c0", face_id);
		System.out.println("aaaaaaa");
		System.out.println(line);
		JSONObject jsonObject = new JSONObject(line);
		String candidates= jsonObject.getString("candidates");  
		char [] arrays=candidates.toCharArray();
		char [] array=new char [arrays.length-2]; 
		for(int x=1;x<arrays.length-1;x++)
		{
			array[x-1]=arrays[x];
		}
		line=new String(array);
		JSONObject jsonObject1 = new JSONObject(line);
		candidates= jsonObject.getString("candidates");
		String[] lines = candidates.split(":");
		String face_id1=lines[1].replaceAll("\",\"confidence\"", "");
		face_id1=lines[1].replaceAll("\"", "");
		lines=face_id1.split(",");
		System.out.println(lines[0]);
		return lines[0];
	}
	public static void main(String [] args) throws Exception, JSONException
	{
	Tureing t = new Tureing();
	String ask="你好";
	byte [] bytes=ask.getBytes("GB2312");
	ask=new String(bytes,"UTF-8");
	
	System.out.println(t.getEncoding(ask));
	String s=t.ask(ask);
	System.out.println(s);
	}
}
