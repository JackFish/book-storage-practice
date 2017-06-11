package kr.bookstorage.push;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by 권성봉 on 2016. 12. 5..
 */
@Controller
@RequestMapping("/web")
public class WebController {

    @RequestMapping(value = "/push", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("test", "test");
        return "push";
    }

}
