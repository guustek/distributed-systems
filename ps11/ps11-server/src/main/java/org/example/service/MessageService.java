package org.example.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.example.model.Message;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    
    private final Map<Long, Message> messageMap = new HashMap<>();

    public MessageService() {
        messageMap.put(1L, new Message(1L, "Pierwsza wiadomosc", "Adam"));
        messageMap.put(2L, new Message(2L, "Druga wiadomosc", "Tomek"));
        messageMap.put(3L, new Message(3L, "Trzecia wiadomosc", "Kasia"));
    }

    public List<Message> getAllMessages() {
        return new ArrayList<>(messageMap.values());
    }
    
    public List<Message> getAllMessagesStartWithPrefix(String prefix) {
        return new ArrayList<>(messageMap.values())
                .stream()
                .filter(message -> message.getMessage().startsWith(prefix))
                .toList();
    }
    
    public Message getMessage(long id) {
        return messageMap.get(id);
    }
    
    public Message createMessage(Message message) {
        message.setId(messageMap.size() + 1L);
        message.setCreated(new Date());
        messageMap.put(message.getId(), message);
        return message;
    }
    
    public Message updateMessage(Message message) {
        if (messageMap.containsKey(message.getId())) {
            messageMap.put(message.getId(), message);
            return message;
        }
        return null;
    }
    
    public void deleteMessage(long id) {
        messageMap.remove(id);
    }
}
