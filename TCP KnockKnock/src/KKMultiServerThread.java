import java.net.*;
import java.io.*;

public class KKMultiServerThread extends Thread {
    private Socket socket = null;

    public KKMultiServerThread(Socket socket) {
        super("KKMultiServerThread");
        this.socket = socket;
    }
    
    public void run() {

        try (
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(
                    socket.getInputStream()));
        ) {
        	BufferedReader stdIn =
                    new BufferedReader(new InputStreamReader(System.in));
                String fromClient;
                String fromUser;

                 while ((fromClient = in.readLine()) != null)  { 
                   
                     fromUser = stdIn.readLine();
                     if (fromUser != null) {
                        System.out.println("Client: " + fromUser);
                        out.println(fromUser);
                        
                        System.out.println("Server: " + fromClient);
                    if (fromClient.equals("Bye."))
                        break; 
                     }        
                 }
        }
             catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } 
    }
