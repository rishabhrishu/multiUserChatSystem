/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rishabh Rishu
 */
import java.io.*;
import java.net.*;
import java.util.*;

public class ServerClass {

    BufferedReader reader;
    InputStreamReader isreader;
    StringParser sp;
    HashMap<OutputStream, String> clientList = new HashMap<OutputStream, String>();
    HashMap<String, OutputStream> clientList2 = new HashMap<String, OutputStream>();

    public static void main(String[] args) {
        new ServerClass().go();
    }

    public void go() {
        try {
            ServerSocket sock = new ServerSocket(5000);
            while (true) {
                Socket clientSocket = sock.accept();
                System.out.println("Connection Established");
                clientList.put(clientSocket.getOutputStream(), "dummy");
                Thread t2 = new Thread(new OnlineListUpdator());
                t2.start();
                Thread t1 = new Thread(new ClientHandler(clientSocket));
                t1.start();

            }
        } catch (Exception ex) {
        
        }

    }
    public class OnlineListUpdator implements Runnable {
        
        public void run(){
            while (true){
                try {

                    String s = "#ListOfOnlineUsers";
                    List clients = new ArrayList(clientList2.keySet());
                    Iterator it = clients.iterator();
                    while(it.hasNext()){
                        s = s+" "+(String)it.next() ;
                    }
                    broadcast(s);
                    Thread.sleep(1000);                    
                } catch(Exception ex) {
                
                }
                
            }
        }
    }
        

    public class ClientHandler implements Runnable {

        BufferedReader reader;
        Socket sock;
        String screenName;

        public ClientHandler(Socket clientSocket) {
            try {
                sock = clientSocket;
                InputStreamReader isreader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(isreader);
                screenName = reader.readLine();
                synchronized (this) {
                    clientList.put(sock.getOutputStream(), screenName);
                    clientList2.put(screenName, sock.getOutputStream());
                }
            } catch (Exception ex) {
                
            }
        }

        @Override
        public void run() {
            String message;
            try {
                while ((message = reader.readLine()) != null) {
                    newBroadcast(message);
                }
            } catch (Exception ex) {
                System.out.println("Someone left the room");

            }
        }
    }

    public void broadcast(String s) {
        List clients = new ArrayList(clientList2.values());
        Iterator it = clients.iterator();
        while (it.hasNext()) {
            try {
                PrintWriter writer = new PrintWriter((OutputStream) it.next());
                writer.println(s);
                writer.flush();
            } catch (Exception ex) {
            }
            
        }
    }

    public synchronized void newBroadcast(String s) {

        int status;
        String toUser;
        String msgToSend;
        String screenName;
        OutputStream os = null;
        sp = new StringParser();
        sp.setIncomingString(s);
        sp.tokenize();
        status = sp.getStatus();
        toUser = sp.getToUser();
        msgToSend = sp.getMsgToSend();
        screenName = sp.getScreenName();
        PrintWriter writer = new PrintWriter((OutputStream)clientList2.get(screenName));
        
        switch (status) {
            case 0:
                broadcast(screenName + " : " + msgToSend);
                break;

            case 1:
                writer.println(msgToSend);
                writer.flush();
                break;

            case 2:
                clientList2.remove(screenName);
                broadcast(screenName + " has left the chatroom");
                break;

            case 3:
                if (clientList2.containsKey(toUser)) {
                    PrintWriter newwriter = new PrintWriter((OutputStream) clientList2.get(toUser));
                    newwriter.println("(PRIVATE MESSAGE) from:" + screenName + " : " + msgToSend);
                    newwriter.flush();
                    writer.println("(PRIVATE MESSAGE) to:"+toUser+" : "+msgToSend);
                    writer.flush();
                    
                } else {
                    writer.println("#Message not Sent : "+toUser+" is offline.");
                    writer.flush();
                }
                break;
                
            case 4 :
                broadcast(msgToSend);
                break;
                
            default:
                writer.println("Message sending failed. "+msgToSend);
                writer.flush();
                break;
        }
    }
}
