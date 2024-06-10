package org.example.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.example.model.Comment;
import org.example.model.Message;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    
    private final Map<Long, Message> messageMap = new HashMap<>();

    public MessageService() {
        Message message1 = new Message(1L, "Pierwsza wiadomosc", "Adam");
        List<Comment> comments = new ArrayList<>();
        comments.add(new Comment(1L, "Jan", "Fajna wiadomosc"));
        comments.add(new Comment(2L, "Krzysztof", "Bardzo fajna wiadomosc"));
        message1.setComments(comments);
        
        messageMap.put(1L, message1);
        messageMap.put(2L, new Message(2L, "Druga wiadomosc", "Tomek"));
        messageMap.put(3L, new Message(3L, "Trzecia wiadomosc", "Kasia"));
    }

    public List<Message> getMessages() {
        return new ArrayList<>(messageMap.values());
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
    
    // Metody do zarządzania komentarzami
    public List<Comment> getComments(long messageId) {
        Message message = messageMap.get(messageId);
        if (message != null) {
            return message.getComments();
        }
        return null;
    }
    
    public Comment getComment(long messageId, long commentId) {
        Message message = messageMap.get(messageId);
        if (message != null) {
            return message.getComments().stream()
                    .filter(comment -> comment.getId() == commentId)
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }
    
    public Comment createComment(long messageId, Comment comment) {
        Message message = messageMap.get(messageId);
        if (message != null) {
            if (message.getComments() == null) {
                message.setComments(new ArrayList<>());
            }
            comment.setId(message.getComments().size() + 1L);
            message.getComments().add(comment);
            return comment;
        }
        return null;
    }
    
    public Comment updateComment(long messageId, Comment comment) {
        Message message = messageMap.get(messageId);
        if (message != null && message.getComments() != null) {
            for (int i = 0; i < message.getComments().size(); i++) {
                if (message.getComments().get(i).getId() == comment.getId()) {
                    message.getComments().set(i, comment);
                    return comment;
                }
            }
        }
        return null;
    }
    
    public boolean deleteComment(long messageId, long commentId) {
        Message message = messageMap.get(messageId);
        if (message != null && message.getComments() != null) {
            return message.getComments().removeIf(comment -> comment.getId() == commentId);
        }
        return false;
    }
}
