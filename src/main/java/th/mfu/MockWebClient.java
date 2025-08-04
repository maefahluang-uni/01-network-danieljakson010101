package th.mfu;

import java.io.*;
import java.net.*;

// call mockup server at port 8080
public class MockWebClient {
    public static void main(String[] args) {

        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try{
        // TODO: Create a socket to connect to the web server on port 8080
            socket = new Socket ("localhost", 8080);
            System.out.println("Connected to server on port 8080");
            
        // :TODO Create input and output streams for the socket
            out =new PrintWriter(socket.getOutputStream(), true);
            in = new BufferefReader (new InputStreamReader (socket.getInputStream()));

        // TODO: send an HTTP GET request to the web server
        String request = "GET / HTTP/1.1\r\nHost: localhost\r\n\r\n";
            out.print(request);
            out.flush();
            System.out.println("HTTP GET request send");

        // Read the response from the web server and print out to console
            String responseLine;
            System.out.println("\n--ServerResponse --");
            while ((responseLine = in.readLine()) !=null) {
                System.of.println(responseLine);
                if (responseLine.isEmpty()){
                    while ((responseLine = in.readLine())! = null){
                    System.out.println(responseLine);
                    }
                    break;
                }
            }
        }catch (UnknownHostException e) {
            System.err.println("Unknown host: localhost");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("I/O error occurred when connecting to server");
            e.printStackTrace();
        } finally {

            // Close the socket and streams
            try {
                if (out != null) out.close();
                if (in != null) in.close();
                if (socket != null) socket.close();
                System.out.println("Connection closed");
            } catch (IOException e) {
                System.err.println("Error closing resources");
                e.printStackTrace();
            }
        }
    }
}
