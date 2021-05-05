package socket;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        String ip = "127.0.0.1";
        int port = 8888;

        Socket sock = null;
        DataInputStream dis = null;

        try {
            sock = new Socket(ip,port);

            InputStream in = sock.getInputStream();
            dis = new DataInputStream(in);

            System.out.println(dis.readUTF());
            System.out.println(dis.readUTF());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                sock.close();
                dis.close();
            } catch ( Exception e) {
                e.printStackTrace();
            }
        }
    }
}
