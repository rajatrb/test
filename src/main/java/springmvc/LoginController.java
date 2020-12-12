package springmvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
@SessionAttributes("name")
public class LoginController {

//    @RequestMapping(value = "/login")
//    @ResponseBody
//    public String sayHello() {
//        return "Hello World dummy";
//    }



   // private LoginService loginService = new LoginService();

    TodoService service = new TodoService();


    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginPage() {
      return "login";


    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String show() {
        return "login";


    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String handleUserLogin(ModelMap model, @RequestParam String name,
                                  @RequestParam String password) {

        if (!loginService.validateUser(name, password)) {
            model.put("errorMessage", "Invalid Credentials");
            return "login";
        }

        model.put("name", name);


        model.addAttribute("todos", service.retrieveTodos(name));
        return "list-todos";
    }






}
