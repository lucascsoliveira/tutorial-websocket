/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifes.sd.tutorial.websocket.client;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.ClientEndpoint;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

/**
 *
 * @author Lucas
 */
@ClientEndpoint
public class ClientDesktopEndpoint {

    @OnOpen
    public void onOpen(Session session) {
        try {
            System.out.println("Connected to endpoint: " + session.getBasicRemote());
            session.getBasicRemote().sendText("Hello");
        } catch (IOException ex) {
            Logger.getLogger(ClientDesktopEndpoint.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println(message);
    }

    @OnError
    public void onError(Throwable t) {
        Logger.getLogger(ClientDesktopEndpoint.class.getName()).log(Level.SEVERE, null, t);
    }
}
