package socket;
 
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
 
/**
 * 聊天室服务器
 * @author Lok
 *
 */
public class Server {
	private ServerSocket server;
	/**
	 * 运行在服务端的ServerSocket主要有两个作用
	 * 1:向系统申请服务端口,客户端就是通过这个端口与服务端程序建立连接的
	 * 2:监听服端口,一旦一个客户端建立连接就可以通过这个Socket实例与该客户端交互了
	 */
	public Server(){
		try {
			/*
			 * 实例化ServerSocket时需要指定申请的服务端口,如果该端口被系统的其他程序使用时会抛出异常
			 */
			System.out.println("正在启用服务端...");
			server = new ServerSocket(8088);
			System.out.println("服务端启动完毕!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void start(){
		try {
			/**
			 * Scoket accept()
			 * 该方法是一个阻塞方法,调用后就"卡住了",此时开始等待客户端的连接,一旦一个客户端建立连接,
			 * 此时该方法会立即返回一个Socket实例,通过这个Socket就可以实现与该客户端交互了
			 */
			System.out.println("等待客户端连接...");
			Socket socket = server.accept();
			System.out.println("一个客户端连接了!");
			/*
			 * InputStream getInputStream() 
			 * Socket的该方法获得的输入流读取的是远端计算机发送过来的字节
			 */
			InputStream in = socket.getInputStream(); 
			InputStreamReader isr = new InputStreamReader(in,"utf-8");
			BufferedReader br = new BufferedReader(isr);
//			Scanner scan = new Scanner(System.in);
			while(true){
			String message = br.readLine();
			System.out.println("客户端说:" + message);
			if(message.equals("exit")){
				br.close();
				return;
			}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		Server server = new Server();
		server.start();
		
	}
}