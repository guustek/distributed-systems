package org.example.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.example.model.Comment;
import org.example.model.Message;
import org.example.service.MessageService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class MessageController {
    
    private final MessageService messageService;
    
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }
    
    @GetMapping(value = "/messages", produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<EntityModel<Message>> getMessages() {
        List<EntityModel<Message>> messages = messageService.getMessages().stream()
                .map(message -> EntityModel.of(message,
                                               WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MessageController.class).getMessage(message.getId())).withSelfRel(),
                                               WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MessageController.class).getMessages()).withRel("messages")))
                .collect(Collectors.toList());
        return CollectionModel.of(messages, WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MessageController.class).getMessages()).withSelfRel());
    }
    
    @GetMapping(value = "/message/{messageId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EntityModel<Message> getMessage(@PathVariable Long messageId) {
        Message message = messageService.getMessage(messageId);
        return EntityModel.of(message,
                              WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MessageController.class).getMessage(messageId)).withSelfRel(),
                              WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MessageController.class).getMessages()).withRel("messages"),
                              WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MessageController.class).getComments(messageId)).withRel("comments"));
    }
    
    @PostMapping(value = "/message")
    public ResponseEntity<EntityModel<Message>> createMessage(@RequestBody Message message, UriComponentsBuilder uriBuilder) {
        Message createdMessage = messageService.createMessage(message);
        URI location = uriBuilder.path("/message/{id}")
                .buildAndExpand(createdMessage.getId())
                .toUri();
        EntityModel<Message> resource = EntityModel.of(createdMessage,
                                                       WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MessageController.class).getMessage(createdMessage.getId())).withSelfRel(),
                                                       WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MessageController.class).getMessages()).withRel("messages"));
        return ResponseEntity.created(location).body(resource);
    }
    
    @PutMapping(value = "/message")
    public EntityModel<Message> updateMessage(@RequestBody Message message) {
        Message updatedMessage = messageService.updateMessage(message);
        return EntityModel.of(updatedMessage,
                              WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MessageController.class).getMessage(updatedMessage.getId())).withSelfRel(),
                              WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MessageController.class).getMessages()).withRel("messages"));
    }
    
    @DeleteMapping(value = "/message/{messageId}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long messageId) {
        messageService.deleteMessage(messageId);
        return ResponseEntity.noContent().build();
    }
    
    // Endpointy do zarządzania komentarzami
    @GetMapping(value = "/message/{messageId}/comments", produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<EntityModel<Comment>> getComments(@PathVariable Long messageId) {
        List<EntityModel<Comment>> comments = messageService.getComments(messageId).stream()
                .map(comment -> EntityModel.of(comment,
                                               WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MessageController.class).getComment(messageId, comment.getId())).withSelfRel(),
                                               WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MessageController.class).getComments(messageId)).withRel("comments")))
                .collect(Collectors.toList());
        return CollectionModel.of(comments, WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MessageController.class).getComments(messageId)).withSelfRel());
    }
    
    @GetMapping(value = "/message/{messageId}/comment/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EntityModel<Comment> getComment(@PathVariable Long messageId, @PathVariable Long commentId) {
        Comment comment = messageService.getComment(messageId, commentId);
        return EntityModel.of(comment,
                              WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MessageController.class).getComment(messageId, commentId)).withSelfRel(),
                              WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MessageController.class).getComments(messageId)).withRel("comments"),
                              WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MessageController.class).getMessage(messageId)).withRel("message"));
    }
    
    @PostMapping(value = "/message/{messageId}/comment")
    public EntityModel<Comment> createComment(@PathVariable Long messageId, @RequestBody Comment comment) {
        Comment createdComment = messageService.createComment(messageId, comment);
        return EntityModel.of(createdComment,
                              WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MessageController.class).getComment(messageId, createdComment.getId())).withSelfRel(),
                              WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MessageController.class).getComments(messageId)).withRel("comments"),
                              WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MessageController.class).getMessage(messageId)).withRel("message"));
    }
    
    @PutMapping(value = "/message/{messageId}/comment")
    public EntityModel<Comment> updateComment(@PathVariable Long messageId, @RequestBody Comment comment) {
        Comment updatedComment = messageService.updateComment(messageId, comment);
        return EntityModel.of(updatedComment,
                              WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MessageController.class).getComment(messageId, updatedComment.getId())).withSelfRel(),
                              WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MessageController.class).getComments(messageId)).withRel("comments"),
                              WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MessageController.class).getMessage(messageId)).withRel("message"));
    }
    
    @DeleteMapping(value = "/message/{messageId}/comment/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long messageId, @PathVariable Long commentId) {
        messageService.deleteComment(messageId, commentId);
        return ResponseEntity.noContent().build();
    }
}