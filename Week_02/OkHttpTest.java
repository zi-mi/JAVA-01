package ning.geek.httpHelper;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpTest {
	
	private static String REQUEST_URL = "http://localhost:8801";
	private static String REQUEST_URL1 = "http://localhost:8802";
	private static String REQUEST_URL2 = "http://localhost:8803";
	
	public static void main(String[] args) throws IOException {
		
		System.out.println(get(REQUEST_URL));
		System.out.println(get(REQUEST_URL1));
		System.out.println(get(REQUEST_URL2));
		
	}
	
	private static String get(String requestUrl) throws IOException{
		OkHttpClient client = new OkHttpClient();

	    Request request = new Request.Builder()
	            .url(requestUrl)
	            .build();
	    
	    Response response = client.newCall(request).execute();
	    if (!response.isSuccessful()) {
	        throw new IOException("服务器端错误: " + response);
	    }
	 
	    Headers responseHeaders = response.headers();
	    for (int i = 0; i < responseHeaders.size(); i++) {
	        System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
	    }
	 
	    return response.body().string();
	}
	
}
