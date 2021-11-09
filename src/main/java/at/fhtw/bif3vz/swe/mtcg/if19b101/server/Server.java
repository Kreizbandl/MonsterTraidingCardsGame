package at.fhtw.bif3vz.swe.mtcg.if19b101.server;

import at.fhtw.bif3vz.swe.mtcg.if19b101.card.Card;
import at.fhtw.bif3vz.swe.mtcg.if19b101.card.MonsterCard;
import at.fhtw.bif3vz.swe.mtcg.if19b101.card.SpellCard;
import at.fhtw.bif3vz.swe.mtcg.if19b101.enumeration.ElementType;
import at.fhtw.bif3vz.swe.mtcg.if19b101.enumeration.MonsterType;

import java.util.*;
import java.io.*;//client-server
import java.net.*;//client-server

public class Server {
    private static final int PORT = 6543;
    ServerSocket serverSocket;
    Socket socket;//wird probleme machen mit threads...

    public static void main(String[] args) throws Exception{
        //dummy data...
        /*Map credentials = new HashMap();
        credentials.put("Max", "1234");
        credentials.put("Nina", "0000");
        credentials.put("Marley", "1997");*/
        String username = "Max";
        String password = "1234";

        Server server = new Server();
        server.waitConnetion();
        Message message;

        label:
        do{
            message = server.recvMessage();
            switch(message.getCommand()){
                case "quit":
                    //quit loop
                    break label;
                case "log":
                    //login check
                    System.out.println(message.getUsername() + message.getPassword());
                    if(message.getUsername().equals(username) && message.getPassword().equals(password)){
                        message.setError("login success");
                    }else{
                        message.setError("login failure");
                    }
                    server.sendMessage(message);
                    break;
                case "aquire":
                    //aquire package

                    /*Package pack = server.getPackageFromServer();
                    server.sendPackage(pack);*/
                    break;
                default:
                    //unknown command
                    System.out.println("Unknown command... '?' or 'help'");
                    break;
            }
        }while(true);

        server.closeServer();
    }

    public Server() throws Exception{
        serverSocket = new ServerSocket(PORT);
    }
    public void waitConnetion() throws Exception{
        socket = serverSocket.accept();//ab hier threads...
    }
    public Message recvMessage() throws Exception{//recv msg
        ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
        return (Message)inStream.readObject();
    }
        /*public Package recvPackage() throws Exception{//recv msg
            ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
            //Message msg = (Message)inStream.readObject();
            //System.out.println(recvMessage.username + recvMessage.password);
            return (Package)inStream.readObject();
        }*/
    public void sendMessage(Message message) throws Exception{//send msg
        ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
        outStream.writeObject(message);//problem sending different kinds of objects?
    }
        /*public void sendPackage(Package pack) throws Exception{//send msg
            ObjectOutputStream ouStream = new ObjectOutputStream(socket.getOutputStream());
            //Message sendMessage = new Message("Username","Password");
            ouStream.writeObject(pack);
        }*/
    public void closeServer() throws Exception{
        serverSocket.close();
    }

    private Card generateRandomCard(){
        Random rand = new Random();

        Integer[] damages = {10,20,40};
        List<Integer> damageList = Arrays.asList(damages);
        int dInt = damageList.get(rand.nextInt(damageList.size()));

        List<ElementType> eleTypeList = new ArrayList<>();
            eleTypeList.addAll(Arrays.asList(ElementType.values()));
        ElementType eType = eleTypeList.get(rand.nextInt(eleTypeList.size()));

        if(rand.nextInt(2) == 0){
            return new SpellCard(dInt, eType);
        }else{
            List<MonsterType> monTypeList = new ArrayList<>();
                for (MonsterType m:MonsterType.values()) {
                    if(m.equals(MonsterType.ICHMAGSNICHTSABERSPELL)){
                        continue;
                    }
                    monTypeList.add(m);
                }
            MonsterType mType = monTypeList.get(rand.nextInt(monTypeList.size()));
            return new MonsterCard(dInt, eType, mType);
        }
    }

    public Package getPackageFromServer(){
        List<Card> packageCards = new ArrayList<>();
        for(int i = 0;i < 5;i++){
            packageCards.add(this.generateRandomCard());
        }
        Package pack = new Package();
        pack.addCards(packageCards);
        return pack;
    }
}
