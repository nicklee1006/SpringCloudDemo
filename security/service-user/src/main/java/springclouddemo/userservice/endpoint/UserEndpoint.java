package springclouddemo.userservice.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserEndpoint {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserEndpoint.class);

    @Value("${server.port:2200}")
    private int serverPort = 2200;

    @RequestMapping(value = "/{loginName}", method = RequestMethod.GET)
    public User detail(@PathVariable String loginName) {
        String memos = "I come form " + this.serverPort;
        return new User(loginName, loginName, "/avatar/default.png", memos);
    }

    @RequestMapping(value = "/my", method = RequestMethod.GET)
    public User myDetail() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();

        return new User(userName, userName, "/avatar/default.png", "");
    }
}
