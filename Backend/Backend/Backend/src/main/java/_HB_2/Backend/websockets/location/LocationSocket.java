package _HB_2.Backend.websockets.location;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import _HB_2.Backend.user.User;
import _HB_2.Backend.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller      // this is needed for this to be an endpoint to springboot
@ServerEndpoint(value = "/location/{username}")  // this is Websocket url
public class LocationSocket {

    private static UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;  // we are setting the static variable
    }

    // Store all socket session and their corresponding username.
    private static Map<Session, String> sessionUsernameMap = new Hashtable<>();
    private static Map<String, Session> usernameSessionMap = new Hashtable<>();

    private final Logger logger = LoggerFactory.getLogger(LocationSocket.class);

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username)
            throws IOException {

        logger.info("Entered into Open");

        // store connecting user information
        sessionUsernameMap.put(session, username);
        usernameSessionMap.put(username, session);

        User enter = userService.getUserByEmail(username);
        // broadcast that new user joined
        String message = "User: " + enter.getFirstName() + " " + enter.getLastName() + " has Joined the Chat";
        broadcastDisconnect(message);
    }


    @OnMessage
    public void onMessage(String location) throws IOException {

        // Handle new messages
        logger.info("Entered into Message: Got Message:" + location);

        String[] coords = location.split(":");
        String lon = coords[0];
        String lat = coords[1];

        broadcast(lon, lat);

    }


    @OnClose
    public void onClose(Session session) throws IOException {
        logger.info("Entered into Close");

        // remove the user connection information
        String username = sessionUsernameMap.get(session);
        sessionUsernameMap.remove(session);
        usernameSessionMap.remove(username);

        // broadcast that the user disconnected
        String message = username + " disconnected";
        broadcastDisconnect(message);
    }

    private void broadcastDisconnect(String message) {
        sessionUsernameMap.forEach((session, username) -> {
            try {
                session.getBasicRemote().sendText(message);
            }
            catch (IOException e) {
                logger.info("Exception: " + e.getMessage().toString());
                e.printStackTrace();
            }

        });

    }


    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
        logger.info("Entered into Error");
        throwable.printStackTrace();
    }

    private void broadcast(String lon, String lat) {
        sessionUsernameMap.forEach((session, username) -> {
            try {
                String message = lon + ":" + lat;
                session.getBasicRemote().sendText(message);
            }
            catch (IOException e) {
                logger.info("Exception: " + e.getMessage().toString());
                e.printStackTrace();
            }

        });

    }

}


