import java.io.*;
import java.net.*;
import java.lang.String;

public class Main {
    public static void main(String[] args) throws IOException {
        InetAddress addr = InetAddress.getByName(null);

        System.out.println("address = " + addr);
        System.out.println("Client start");

        Socket fromserver = null;

        System.out.println("Connecting to: "+addr);
        try {
            fromserver = new Socket(addr, 8080);
        } catch (IOException e){
            System.exit(-1);
        }
        BufferedReader atServer  = new
                BufferedReader(new
                    InputStreamReader(fromserver.getInputStream()));
        PrintWriter    toServer = new
                PrintWriter(fromserver.getOutputStream(),true);
        try (BufferedReader clientBuffer = new
                BufferedReader(new InputStreamReader(System.in))) {

            String fuser, fserver;

            while ((fuser = clientBuffer.readLine()) != null) {
                toServer.println(fuser);
                fserver = atServer.readLine();
                System.out.println(fserver);

                if (fuser.equalsIgnoreCase("close")) break;
                if (fuser.equalsIgnoreCase("exit")) break;
            }

            toServer.close();
            atServer.close();
            clientBuffer.close();
        }
        fromserver.close();
    }
}