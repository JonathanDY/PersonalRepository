import java.net.*;
import java.io.*;

public class ChatServerThread extends Thread {
    private Socket socket = null;

    public ChatServerThread (Socket socket) {
        super("ChatServerThread");
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
                         
                        
                        System.out.println("Server: " + fromClient);
                    if (fromClient.equals(null))
                        break; 
                    
                    fromUser = stdIn.readLine();
                     if (fromUser != null) {
                        System.out.println("Client: " + fromUser);
                        out.println(fromUser);
                        
                        out.println(fromClient);//auto flushing 
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
