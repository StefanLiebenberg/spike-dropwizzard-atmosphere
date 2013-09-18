package com.github.stefanliebenberg.atmosphere;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Response {

    public String title;
    public String content;

    public Response(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Response() {}
}
