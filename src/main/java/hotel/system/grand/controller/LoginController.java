package hotel.system.grand.controller;

import hotel.system.grand.dto.LoginDTO;
import hotel.system.grand.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
@CrossOrigin
public class LoginController {
    final LoginService loginService;

    @PostMapping("/staff")
    public Map<String,String> staffLogin(@RequestBody LoginDTO loginDTO){
        return loginService.staffLogin(loginDTO);
    }

    @PostMapping("/customer")
    public Map<String,String> customerLogin(@RequestBody LoginDTO loginDTO){
        return loginService.customerLogin(loginDTO);
    }
}
