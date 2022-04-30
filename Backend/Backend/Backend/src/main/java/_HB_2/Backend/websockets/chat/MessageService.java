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

        return messageRepository.findMessagesForPairOfUsers(user1Id, user2Id);
    }
}
