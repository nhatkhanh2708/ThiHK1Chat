package ClientServer;

import Event.CallEvent;
import Utils.Constants;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    private static Client instance;
    private Socket socket = null;
    private BufferedReader read = null;
    private BufferedWriter write = null;
    private String localhost = "127.0.0.1";
    private String myUser = "";
    private Gson gson = new Gson();

    public static Client getInstance() {
        if (instance == null) {
            instance = new Client();
        }
        return instance;
    }

    public void connect() {
        try {
            this.socket = new Socket(this.localhost, 12000);
            this.read = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.write = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
            System.out.println("Client: Connected !");
            this.thread.start();
        } catch (Exception e) {
            System.out.println("Connect Failed !");
        }
    }

    Thread thread = new Thread() {
        @Override
        public void run() {
            while (true) {
                try {
                    String messageInput = read.readLine();
                    if (messageInput != null) {
                        MessageDto msgInputDto = convertJSon2MessDto(messageInput);
                        switch (msgInputDto.getType()) {

                            case Constants.LOGIN:
                                if (msgInputDto.getMessage().equals(Constants.LOGIN_SUCCESSED)) {
                                    System.out.println("Client: login successed");
                                    CallEvent.getInstance().getiLogin().loginSuccessed();
                                    sendFindUser();
                                }
                                else if(msgInputDto.getMessage().equals(Constants.LOGIN_FAILED)){
                                    System.out.println("Client: login failed");
                                    CallEvent.getInstance().getiLogin().loginFailed();
                                }
                                break;

                            case Constants.FIND_USER:
                                CallEvent.getInstance().getiLobby().showSuggestChat(msgInputDto);
                                break;
                                
                            case Constants.AGREE_SUGGEST:
                                CallEvent.getInstance().getiLobby().agreeSuggest(msgInputDto);
                                break;
                                
                            case Constants.WAITING:
                                CallEvent.getInstance().getiLobby().waiting();
                                break;
                                
                            case Constants.CLIENT_2_CLIENT:
                                CallEvent.getInstance().getiChat().recivedMessage(msgInputDto);
                                break;
                                
                            case Constants.ESCAPE_CHAT:
                                CallEvent.getInstance().getiLobby().escapeChat();
                                sendFindUser();
                                break;
                        }
                    }
                } catch (IOException ex) {
                    break;
                }
            }
            Close();
        }
    };
    
    public void sendLogin(String username){
        sendMessage(new MessageDto("", "", username, Constants.LOGIN));
    }
    
    public void sendFindUser(){
        sendMessage(new MessageDto("", "", "", Constants.FIND_USER));
    }
    
    public void sendAgreeSuggest(String userchat){
        sendMessage(new MessageDto("", "", userchat, Constants.AGREE_SUGGEST));        
    }
    
    public void sendCLient2Client(String userchat, String contentmsg){
        sendMessage(new MessageDto(this.getMyUser(), userchat, contentmsg, Constants.CLIENT_2_CLIENT));
    }
    
    public void sendEscapeChat(String contentmsg){
        sendMessage(new MessageDto("", "", contentmsg, Constants.ESCAPE_CHAT));
    }
    
    public void sendExitWithinChat(String contentmsg){
        sendMessage(new MessageDto("", "", contentmsg, Constants.EXIT_WITHIN_CHAT));
    }
    
    public void sendExitApp(){
        sendMessage(new MessageDto("", "", "", Constants.EXIT));
    }

    public void sendMessage(MessageDto messdto) {
        try {
            this.write.write(convertMessDto2JSon(messdto));
            this.write.newLine();
            this.write.flush();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Close() {
        try {
            this.write.close();
            this.read.close();
            this.socket.close();
            System.out.println("Client: Closed !");
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public MessageDto convertJSon2MessDto(String mess) {
        return gson.fromJson(mess, MessageDto.class);
    }

    public String convertMessDto2JSon(Object object) {
        return gson.toJson(object);
    }

    public String getMyUser() {
        return myUser;
    }

    public void setMyUser(String myUser) {
        this.myUser = myUser;
    }

}
