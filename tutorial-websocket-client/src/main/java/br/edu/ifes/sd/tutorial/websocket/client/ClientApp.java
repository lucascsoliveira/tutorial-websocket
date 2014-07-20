/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifes.sd.tutorial.websocket.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

/**
 *
 * @author Lucas
 */
public class ClientApp {

    public Session session;

    protected void start() {

        try {
            WebSocketContainer webSocketContainer = ContainerProvider.getWebSocketContainer();

            String uri = "ws://localhost:8080/tutorial-websocket-server/websocket/desktop-client";

            System.out.println("Connecting to " + uri);

            session = webSocketContainer.connectToServer(ClientDesktopEndpoint.class, URI.create(uri));
        } catch (DeploymentException | IOException ex) {
            Logger.getLogger(ClientApp.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String args[]) {
        ClientApp clientApp = new ClientApp();
        clientApp.start();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input = "";

        try {
            do {
                input = bufferedReader.readLine();
                if (!input.equals("exit")) {
                    clientApp.session.getBasicRemote().sendText(input);
                }

            } while (!input.equals("exit"));
        } catch (IOException ex) {
            Logger.getLogger(ClientApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
