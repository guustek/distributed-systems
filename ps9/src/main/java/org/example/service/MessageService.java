package org.example.service;

import java.util.ArrayList;
import java.util.List;

import org.example.model.Message;

public class MessageService {
    
    private final List<Message> list = new ArrayList<>();

    public MessageService() {
        Message m1 = new Message(1L, "Pierwsza wiadomosc", "Adam");
        Message m2 = new Message(2L, "Druga wiadomosc", "Tomek");
        Message m3 = new Message(3L, "Trzecia wiadomosc", "Kasia");

        list.add(m1);
        list.add(m2);
        list.add(m3);
    }

    public List<Message> getAllMessages() {
        return list;
    }
}
