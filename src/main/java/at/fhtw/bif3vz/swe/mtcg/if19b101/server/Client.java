package at.fhtw.bif3vz.swe.mtcg.if19b101.server;

import java.io.*;
import java.net.Socket;
//vl in neues projekt verschieben (MTCG-Client...)
/*public class Client {
    private static final int PORT = 6543;
    private static final String IP = "127.0.0.1";
    Socket socket;

    public static void main(String[] args) throws Exception{//not needed... test only
        Client client = new Client();

        client.sendMessage(new Message("Manuel"));

        Message message = client.recvMessage();
            System.out.println(message.username + message.password);

        client.closeClient();
    }

    public Client() throws Exception{//define socket
        this.socket = new Socket(IP,PORT);
    }

    public void sendMessage(Message message) throws Exception{//send msg
        ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
        outStream.writeObject(message);
    }

    public Message recvMessage() throws Exception{//recv msg
        ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
        return (Message)inStream.readObject();
    }

    public void closeClient()throws Exception{
        socket.close();
    }
}*/
