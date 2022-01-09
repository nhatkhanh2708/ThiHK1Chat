package ClientServer;

import Utils.Constants;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Worker implements Runnable {

    private BufferedReader read;
    private BufferedWriter write;
    private Socket socket;
    private Gson gson = new Gson();
    private String user = "";
    private boolean isWaiting;
    private List<String> listSuggested = new ArrayList<>();

    public Worker(Socket socket) throws Exception {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            read = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            write = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            boolean isStop = false;
            while (!isStop) {
                try {
                    String messageInput = read.readLine();
                    if (messageInput != null) {
                        MessageDto msgInputDto = convertJSon2MessDto(messageInput);
                        switch (msgInputDto.getType()) {

                            case Constants.LOGIN:
                                this.CheckLogin(msgInputDto);
                                break;

                            case Constants.FIND_USER:
                                this.FindUser();
                                break;

                            case Constants.AGREE_SUGGEST:
                                this.AgreeSuggest(msgInputDto);
                                break;

                            case Constants.CLIENT_2_CLIENT:
                                this.SendMsgClient2Client(msgInputDto);
                                break;

                            case Constants.ESCAPE_CHAT:
                                this.EscapeChat(msgInputDto);
                                this.FindUser();
                                break;
                                
                            case Constants.EXIT_WITHIN_CHAT:
                                this.EscapeChat(msgInputDto);
                                isStop = true;
                                break;

                            case Constants.EXIT:
                                isStop = true;
                                break;
                        }
                    }
                } catch (IOException ex) {
                    break;
                }
            }
            Close();
        } catch (IOException ex) {
            Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void CheckLogin(MessageDto msgInputDto) throws IOException {
        boolean isExistUser = false;
        MessageDto msgResponse = new MessageDto();
        msgResponse.setFromUser("");
        msgResponse.setToUser("");
        msgResponse.setType(Constants.LOGIN);
        for (Worker worker : Server.listUser) {
            if (msgInputDto.getMessage().equals(worker.getUser())) {
                isExistUser = true;
                msgResponse.setMessage(Constants.LOGIN_FAILED);
                System.out.println("Worker: login failed");
                break;
            }
        }
        if (!isExistUser) {
            this.setUser(msgInputDto.getMessage());
            msgResponse.setMessage(Constants.LOGIN_SUCCESSED);
            System.out.println("Worker: " + this.getUser() + " login successed");
        }
        this.sendMessage(msgResponse);
    }

    private void FindUser() throws IOException {
        boolean isFind = false;
        for (Worker worker : Server.listUser) {

            if (!worker.getUser().equals("")
                    && !worker.getUser().equals(this.getUser())
                    && worker.isWaiting == true
                    && !listSuggested.contains(worker.getUser())) {

                listSuggested.add(worker.getUser());
                isFind = true;
                this.sendMessage(new MessageDto("", "", worker.getUser(), Constants.FIND_USER));
                break;
            }
        }

        if (!isFind) {
            this.setWaiting(true);
            this.sendMessage(new MessageDto("", "", "", Constants.WAITING));
        }
    }

    private void AgreeSuggest(MessageDto msgInputDto) throws IOException {
        boolean isFind = false;
        for (Worker worker : Server.listUser) {
            if (worker.getUser().equals(msgInputDto.getMessage())
                    && worker.isWaiting == true) {

                worker.sendMessage(new MessageDto("", "", this.getUser(), Constants.AGREE_SUGGEST));
                worker.refreshListSuggested();
                worker.setWaiting(false);
                isFind = true;
                break;
            }
        }
        if (isFind) {
            this.refreshListSuggested();
            this.setWaiting(false);
            this.sendMessage(new MessageDto("", "", msgInputDto.getMessage(), Constants.AGREE_SUGGEST));
        } else {
            this.FindUser();
        }
    }

    private void SendMsgClient2Client(MessageDto msgInputDto) throws IOException {
        System.out.println("Worker: " + msgInputDto.getFromUser() + " sent " + msgInputDto.getToUser() + ": " + msgInputDto.getMessage());
        for (Worker worker : Server.listUser) {
            if (msgInputDto.getToUser().equals(worker.getUser())) {
                worker.sendMessage(msgInputDto);
                break;
            }
        }
    }

    private void EscapeChat(MessageDto msgInputDto) throws IOException {
        for (Worker worker : Server.listUser) {
            if (worker.getUser().equals(msgInputDto.getMessage())
                    && worker.isWaiting == false) {
                worker.sendMessage(new MessageDto("", "", "", Constants.ESCAPE_CHAT));
                break;
            }
        }
    }

    public void sendMessage(MessageDto messdto) throws IOException {
        this.write.write(convertMessDto2JSon(messdto));
        this.write.newLine();
        this.write.flush();
    }

    public void Close() {
        try {
            Server.listUser.remove(this);
            this.setUser("");
            this.write.close();
            this.read.close();
            this.socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private MessageDto convertJSon2MessDto(String mess) {
        return gson.fromJson(mess, MessageDto.class);
    }

    public String convertMessDto2JSon(Object object) {
        return gson.toJson(object);
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public boolean isWaiting() {
        return isWaiting;
    }

    public void setWaiting(boolean isWaiting) {
        this.isWaiting = isWaiting;
    }

    public void refreshListSuggested() {
        this.listSuggested = new ArrayList<>();
    }
}
