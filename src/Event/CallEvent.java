package Event;

public class CallEvent {
    private static CallEvent instance;
    private ILogin iLogin;
    private ILobby iLobby;
    private IChat iChat;
    public static CallEvent getInstance() {
        if (instance == null) {
            instance = new CallEvent();
        }
        return instance;
    }
    
    public CallEvent(){
        
    }

    public ILogin getiLogin() {
        return iLogin;
    }

    public void setiLogin(ILogin iLogin) {
        this.iLogin = iLogin;
    }

    public ILobby getiLobby() {
        return iLobby;
    }

    public void setiLobby(ILobby iLobby) {
        this.iLobby = iLobby;
    }

    public IChat getiChat() {
        return iChat;
    }

    public void setiChat(IChat iChat) {
        this.iChat = iChat;
    }
    
}
