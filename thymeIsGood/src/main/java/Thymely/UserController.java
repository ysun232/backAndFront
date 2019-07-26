package Thymely;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class UserController {

    @Autowired
    private  UserRepository userRepository;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));
    }

//    This goes with the pagination method that does not use html
//    private static final String LinkFormat="<a href='http://localhost:8080/allUsers?page=%s&size=%s'>%s</a><br/>";


    @GetMapping("/signup")
    public String showSignUpForm(User user, Model model){
        model.addAttribute("user", user);
        return "add-user";
    }

    @PostMapping("/adduser")
    public String addUser(@Valid User user, BindingResult result, Model model){
        if(result.hasErrors()){
            return "add-user";
        }
        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }

    @GetMapping("/edit/{id}")
    public String ShowUpdateForm(@PathVariable("id") Long id, Model model){
        User user = userRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid user Id:" +id));

        model.addAttribute("user", user);
        return "update-user";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id")Long id, @Valid User user, BindingResult result, Model model){
        if(result.hasErrors()){
            user.setId(id);
            return "update-user";
        }
        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id")Long id, Model model){
        User user= userRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid user Id: "+id));
        userRepository.delete(user);
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }
    @GetMapping("/AllUsers")
    public String gotUsers(@PageableDefault(page= 0, size = 10)Pageable pageable, Model model){
        Page<User> page = userRepository.findAll(pageable);
        model.addAttribute("page", page);
        return "allU";
    }


//    Working version of first page, previous page, next page, and last page
//    @ResponseBody
//    @GetMapping("/allUsers")
//    public String getUsers(@PageableDefault(page= 0, size = 10) Pageable pageable){
//        Page<User> page = userRepository.findAll(pageable);
//        List<User> users = page.getContent();
//
//        String response = users.stream().map(User::toString).collect(Collectors.joining("<br />"));
//        response += "<br/> <br/>";
//
////        Checks for first page button to appear
//        if(page.hasPrevious()){
//            response += String.format(LinkFormat, 0, page.getSize(), "First Page");
//        }
////        Checks for previous page button to appear
//        if(page.hasPrevious()){
//            response +=String.format(LinkFormat, page.getNumber() -1, page.getSize(), "Previous Page");
//        }
////        Checks for next page button to appear
//        if(page.hasNext()){
//            response += String.format(LinkFormat, page.getNumber() +1, page.getSize(), "Next Page");
//        }
////        Checks for last page button to appear
//        if(page.hasNext()){
//            response += String.format(LinkFormat, page.getTotalPages() -1,page.getSize(), "Last Page");
//        }
//
//        return response;
//    }



}
