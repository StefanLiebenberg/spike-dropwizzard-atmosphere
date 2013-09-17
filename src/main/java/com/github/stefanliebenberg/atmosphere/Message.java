package com.github.stefanliebenberg.atmosphere;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Message {
    public String content = "";
    public String title = "";

    public Message() {}

    public Message(String content, String title) {
        this.content = content;
        this.title = title;
    }
}
