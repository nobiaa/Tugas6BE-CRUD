package Controller;

import Model.User;
import Service.UserService;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import java.util.List;
import javax.persistence.EntityManager;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

@Path("/user")
public class UserResource {
    @Inject
    UserService userService;
    @GET
    public List<User> listUser(){
        return User.listAll();
    }

    @POST
    public User postUser(JsonObject body) {
        return userService.postUser(body);
    }
    @Inject
    EntityManager entityManager;
    @PUT
    @Path("/{username}")
    @Transactional
    @Produces("application/json")
    @Consumes("application/json")
    public JsonObject putUser(@PathParam("username") String username, JsonObject body){
        String newUsername = body.getString("username");
        String newPassword = body.getString("password");
        String newAlamat = body.getString("alamat");
        String newJk= body.getString("jk");
        String newEmail = body.getString("email");

        User user = User.find("username", username).firstResult();
        user.username = newUsername;
        user.password = newPassword;
        user.alamat = newAlamat;
        user.jk = newJk;
        user.email = newEmail;
        entityManager.persist(user);
        return body;
    }

    @DELETE
    public JsonObject deleteUser(JsonObject body){
        return UserService.deleteUser(body);
    }
}
