package Event;

import ClientServer.MessageDto;

public interface IChat {
    public void recivedMessage(MessageDto messDto);
}
