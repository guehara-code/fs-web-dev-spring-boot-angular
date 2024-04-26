package org.example;

public class UserService {

    private MessageService messageService;

    public UserService(MessageService messageService) {
        this.messageService = messageService;
    }

    public void notifyUsers() {
        boolean result = this.messageService.sendMessage();
        System.out.println("result is: " + result);
    }
}
