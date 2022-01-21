import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    public static void main(String[] args) throws IOException {
        System.out.println("Server BMS (povered by Krasnov)");
        BufferedReader atClient = null;
        PrintWriter    toClient= null;
        ServerSocket serverSoket = null;
        Socket       clientSoket = null;
        String         input,output;
        input="";
        // create server socket


        try {
            serverSoket = new ServerSocket(8080);
            System.out.println("Socked accept");
        } catch (IOException e) {
            System.out.println("Port error");
            System.exit(-1);
        }

        try {
            System.out.print("Waiting client...");
            clientSoket= serverSoket.accept();
            System.out.println("Client connected");
        } catch (IOException e) {
            System.out.println("Error connection");
            System.exit(-1);
        }
        atClient  = new BufferedReader(new
                InputStreamReader(clientSoket.getInputStream()));
        toClient = new PrintWriter(clientSoket.getOutputStream(),true);

        System.out.println("Wait for messages...");
        while ((input = atClient.readLine()) != null) {
            if (input.equalsIgnoreCase("exit")) break;
            System.out.println(input);
            StringTokenizer lexema = new StringTokenizer(input, ";");
            int i=0;
            String[] otvet =new String[10];
            String out="";
            while (lexema.hasMoreTokens()) {

                otvet[i]=lexema.nextToken();
                System.out.println((i+1)+"-ая лекскма: "+otvet[i]);
                if (out==""){
                    out=otvet[i];
                }else{
                    out=otvet[i]+";"+out;
                }
                i=i+1;
            }
            toClient.println("Server: " + out);
        }

        toClient.close();
        atClient.close();
        clientSoket.close();
        serverSoket.close();
    }
}