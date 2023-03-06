package Service;

import Model.User;

import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonObject;
import javax.transaction.Transactional;

@ApplicationScoped
public class UserService {
    @Transactional
    public User postUser(JsonObject body) {
        User user = new User();
        user.username = body.getString("username");
        user.password = body.getString("password");
        user.alamat = body.getString("alamat");
        user.jk = body.getString("jk");
        user.email = body.getString("email");

        user.persist();
        return user;
    }

    @Transactional
    public static JsonObject deleteUser(JsonObject body) {
        User user = User.find("username = ?1", body.getString("username")).firstResult();
        User.delete("id =?1", user.id);
        User.delete("username", body.getString("username"));
        return body;
    }
}
