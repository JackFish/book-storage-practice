package kr.bookstorage.email;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by ksb on 2017. 6. 10..
 */
@Controller
@RequestMapping("/web")
public class WebController {

    @RequestMapping(value = "/email", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("test", "test");
        return "email";
    }

}
