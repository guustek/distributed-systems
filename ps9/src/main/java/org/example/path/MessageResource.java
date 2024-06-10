package org.example.path;

import java.util.List;

import org.example.model.Message;
import org.example.service.MessageService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageResource {
    
    private final MessageService service = new MessageService();
    
    @GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public List<Message> getTextAsXML() {
        return service.getAllMessages();
    }
    
    @GetMapping(value = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Message> getTextAsJSON() {
        return service.getAllMessages();
    }
}
