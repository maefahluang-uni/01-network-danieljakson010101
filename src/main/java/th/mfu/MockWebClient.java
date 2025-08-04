package th.mfu;
import java.io.*;
import java.net.*;

// call mockup server at port 8080
public class MockWebClient {
    public static void main(String[] args) {
        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        
        try {
            // Create a socket to connect to the web server on port 8080
            socket = new Socket("localhost", 8080);
            System.out.println("Connected to server on port 8080");
            
            // Create input and output streams for the socket
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            // Send an HTTP GET request to the web server
            String request = "GET / HTTP/1.1\r\nHost: localhost\r\n\r\n";
            out.print(request);
            out.flush();
            System.out.println("HTTP GET request sent");
            
            // Read the response from the web server and print out to console
            String responseLine;
            System.out.println("\n--- Server Response ---");
            while ((responseLine = in.readLine()) != null) {
                System.out.println(responseLine);
                // Break if we encounter an empty line after headers (end of response)
                if (responseLine.isEmpty()) {
                    // Read the body content if any
                    while ((responseLine = in.readLine()) != null) {
                        System.out.println(responseLine);
                    }
                    break;
                }
            }
            
        } catch (UnknownHostException e) {
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
