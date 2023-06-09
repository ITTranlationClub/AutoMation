package github.ittranslation.automation.chatgpt.automation.api;


import github.ittranslation.automation.chatgpt.automation.api.model.UserModel;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
public interface UserService {

    String getUserName(Long id);

    UserModel addUser(UserModel user);
}
