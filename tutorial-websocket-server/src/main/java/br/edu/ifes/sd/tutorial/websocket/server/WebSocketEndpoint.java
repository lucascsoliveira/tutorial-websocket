/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifes.sd.tutorial.websocket.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author Lucas
 */
@ServerEndpoint("/websocket/{client-id}")
public class WebSocketEndpoint {

    private static final List<Session> clients = new ArrayList<>();

    @OnOpen
    public void onOpen(Session session) {
        clients.add(session);
    }

    @OnMessage
    public void onMessage(String message, @PathParam("client-id") String clientId) {
        try {
            for (Session client : clients) {
                client.getBasicRemote().sendObject(clientId + ": " + message);
            }
        } catch (IOException | EncodeException ex) {
            Logger.getLogger(WebSocketEndpoint.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @OnClose
    public void onClose(Session peer) {
        clients.remove(peer);
    }

}
