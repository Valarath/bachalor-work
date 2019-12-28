package cz.honestcity.endpoints.user;

import cz.honestcity.service.user.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user-suggestions")
    public GetUserSuggestionsResponse getUserSuggestions(GetUserSuggestionsRequest request) {
        return new GetUserSuggestionsResponse()
                .setUserSuggestions(userService.getUserSuggestions(request.getUserId()));
    }
}
