package _HB_2.Backend.websockets.chat;

import _HB_2.Backend.user.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "MessageController", description = "REST APIs related to Messages")
@RestController
@RequestMapping( "/message")
public class MessageController {

    @Autowired
    MessageService messageService;
    

}
