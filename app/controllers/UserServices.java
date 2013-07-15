package controllers;

import java.io.IOException;
import java.io.StringWriter;

import play.*;
import play.mvc.*;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.JsonNode;

import play.mvc.BodyParser;
import views.html.*;

import com.easycode.pojo.User;

public class UserServices extends Controller {
  
    public static Result getUser(String userId) throws JsonGenerationException,
            JsonMappingException, IOException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        StringWriter sw = new StringWriter();
        User user = User.USERS.get(userId);
        mapper.writeValue(sw, user);
        String jj = sw.toString();
        sw.close();
        return ok(jj);
    }

    @BodyParser.Of(BodyParser.Json.class)
    public static Result saveUser(String userId) throws JsonGenerationException,
            JsonMappingException, IOException, InterruptedException {
        JsonNode json = request().body().asJson();
        String name = json.findPath("name").getTextValue();
        User user = User.USERS.get(userId);
        user.setName(name);

        ObjectMapper mapper = new ObjectMapper();
        StringWriter sw = new StringWriter();
        mapper.writeValue(sw, user);
        String jj = sw.toString();
        sw.close();
        return ok(jj);
    }
}
