package com.blackiceinc.era.services;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ImportExportMessageProvider {

    private List<String> messages;

    public ImportExportMessageProvider() {
        messages = new ArrayList<>();
    }

    public void addMessage(String message){
        messages.add(message);
    }

    public List<String> getMessages() {
        return messages;
    }

}
