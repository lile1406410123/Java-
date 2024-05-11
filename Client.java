package socket;
 
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
 
/**
 * 聊天室客户端
 * @author Lok
 *
 */
public class Client {
    /*
     * java.net.socket 套接字
     * Socket封装了TCP协议传输数据的细节,使得我们可以通过两条流的读写完成与远端计算机的数据交互
     */
    private Socket socket;
    
    public Client(){
        try {
            /**
             * 实例化Socket的过程就是连接服务端的过程
             * 参数1:服务端的IP地址
             * 参数2:服务端程序打开的端口
             * 
             * 我们通过IP可以找到网络上的服务端所在计算机,通过端口可以找到该计算机上运行的服务端应用程序从而建立连接
             */
            System.out.println("正在连接服务端...");
            socket = new Socket("localhost",8088);
            System.out.println("已连接服务端!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
     * 客户端开始工作的方法
     */
    public void start(){
        try {
            /*
             * Socket提供的方法
             * OutputStream getOutputStream()  通过该方法获取的输入流的写出的字节会通过网络发送给远端计算机
             */
            OutputStream out = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(out,"utf-8");
            BufferedWriter bw = new BufferedWriter(osw);
            PrintWriter pw = new PrintWriter(bw,true);
            Scanner scan = new Scanner(System.in);
            System.out.println("输入发给服务器的信息:");
            while(true){
            String str = scan.next();
            pw.println(str);
            if(str.equals("exit")){
                pw.close();
                return;
            }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args){
        Client client = new Client();
        client.start();
        
    }
}