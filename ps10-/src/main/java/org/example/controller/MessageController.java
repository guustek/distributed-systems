package org.example.controller;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import org.example.model.Message;
import org.example.service.MessageService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    
    private final MessageService messageService = new MessageService();
    
    @GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public List<Message> getTextAsXML() {
        return messageService.getAllMessages();
    }
    
    @GetMapping(value = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Message> getTextAsJSON() {
        return messageService.getAllMessages();
    }
    
    @GetMapping(value = "/message/{messageId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Message getMessage(@PathVariable Long messageId) {
        return messageService.getMessage(messageId);
    }
    
    @PostMapping(value = "/message")
    public Message createMessage(@RequestBody Message message) {
        return messageService.createMessage(message);
    }
    
    @PutMapping(value = "/message")
    public Message updateMessage(@RequestBody Message message) {
        return messageService.updateMessage(message);
    }
    
    @DeleteMapping(value = "/message/{messageId}")
    public void deleteMessage(@PathVariable Long messageId) {
        messageService.deleteMessage(messageId);
    }
    
    // oraz zad5
    @GetMapping(value = "/message-request-param", produces = MediaType.APPLICATION_JSON_VALUE)
    public Message getMessageWithRequestParam(@RequestParam Long messageId) {
        return messageService.getMessage(messageId);
    }
    
    @GetMapping(value = "/test-request-header", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getMessageWithRequestHeader(@RequestHeader("Accept") String headerValue) {
        return headerValue;
    }
    
    @GetMapping("/test-matrix-variable/{path}")
    public String getMessageWithMatrixVariable(@PathVariable String path, @MatrixVariable("age") String age) {
        return "Age: " + age;
    }
    
    @GetMapping(value = "/test-http-servlet-request", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getMessageWithHeaders(HttpServletRequest request) {
        return request.getRequestURI();
    }
}
