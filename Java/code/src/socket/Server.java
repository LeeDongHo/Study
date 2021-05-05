package socket;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        int port = 8888;
        try {
            serverSocket = new ServerSocket(port);

            Socket sock = serverSocket.accept();

            OutputStream out = sock.getOutputStream();
            DataOutputStream dos = new DataOutputStream(out);

            dos.writeUTF("[서버 연결 성공]");

            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
            String nowTime = sdf.format(date);
            dos.writeUTF("[현재시간] : " + nowTime);

            sock.close();
            dos.close();
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch ( Exception e ) {
                e.printStackTrace();
            }
        }

    }
}
