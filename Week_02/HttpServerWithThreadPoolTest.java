package ning.geek.nio;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServerWithThreadPoolTest {

	public static void main(String[] args) throws IOException {
		
		ExecutorService executorService = Executors.newFixedThreadPool(30);
		final ServerSocket serverSocket = new ServerSocket(8803);
		while(true){
			final Socket socket = serverSocket.accept();
			executorService.execute(()->service(socket));
		}
	}
	
	private static void service(Socket socket){
		
		try {
			Thread.sleep(20);
			PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
			printWriter.println("HTTP/1.1 200 OK");
			printWriter.println("Content-Type:text/html;charset=utf-8");
			String body = "hi nio";
			printWriter.println("Content-Length:"+body.getBytes().length);
			printWriter.println();
			printWriter.write(body);
			printWriter.close();
			socket.close();
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
	}
}
