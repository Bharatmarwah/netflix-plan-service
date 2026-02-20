package in.bm.Netflix_plan_service.CONTROLLER;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/plans")
public class PlanController {


    @GetMapping
    public String getUserAgent(HttpServletRequest request){
       String UserAgent = request.getHeader("user-agent").toString();
       return UserAgent;

    }


}
