import java.io.*;
import java.net.*;
public class MyClient{
    public static void main(String[] args) throws Exception{
        // establish connection with server on localhost(local ip address) and port no 6666
        Socket s = new Socket("localhost",6666);
        System.out.println("Connetion is Established...........");
        DataInputStream din = new DataInputStream(s.getInputStream()); // Input stream for socket
        DataOutputStream dout = new DataOutputStream(s.getOutputStream()); // output stream for socket
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // take input from keyboard to buffer
        String str ="",str2 = "";
        System.out.println("You are connected with server start chatting ");
        // Runs till user says stop
        while(!str.equals("stop")){
            str=br.readLine();      // read keyboard input using buffer's method readLine()
            dout.writeUTF(str);     // write to output stream
            dout.flush();           // flush delivers data to other side here other side is server
            str2=din.readUTF();     // reads data from server
            System.out.println("Server says: "+ str2);// prints data from server
        }
        //closes all
        br.close();
        s.close();
    }
}