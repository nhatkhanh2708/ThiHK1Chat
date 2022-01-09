package Event;

import ClientServer.MessageDto;

public interface ILobby {
    public void showSuggestChat(MessageDto mess);
    public void waiting();
    public void agreeSuggest(MessageDto mess);
    public void escapeChat();
}
