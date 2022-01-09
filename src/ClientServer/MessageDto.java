package ClientServer;

public class MessageDto {
    private String fromUser;
    private String toUser;
    private String message;
    private String type;

    public MessageDto(String fromUser, String toUser, String message, String type) {
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.message = message;
        this.type = type;
    }
    
    public MessageDto(){
        
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
}
