package com.github.stefanliebenberg.atmosphere;

import org.atmosphere.annotation.Broadcast;
import org.atmosphere.annotation.Suspend;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class EchoResource {

    @Suspend(contentType =  MediaType.APPLICATION_JSON)
    @GET
    public String suspend() {
        return "";
    }

    @Broadcast(writeEntity = false)
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response broadcast(Message message) {
        return new Response(message.title, message.content);
    }
}
