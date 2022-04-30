package _HB_2.Backend.websockets.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;


    public List<Message> getMessagesForPairOfUser(int user1Id, int user2Id) {

//        List<Message> list = messageRepository.findMessagesForPairOfUsers(user1Id, user2Id);

        List<Message> list = new ArrayList<>();
        List<Message> allMessages = messageRepository.findAll();

        for (Message message : allMessages){
            if ((message.getUserReceived() != null) &&
                    (message.getUserSent() != null) &&
                    (message.getUserReceived().getId() == user1Id && message.getUserSent().getId() == user2Id ||
                    message.getUserReceived().getId() == user2Id && message.getUserSent().getId() == user1Id)) {
                list.add(message);
            }
        }
        return list;
    }
}
