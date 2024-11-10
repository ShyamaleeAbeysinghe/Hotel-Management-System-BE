package hotel.system.grand.service;

import hotel.system.grand.dto.LoginDTO;

import java.util.Map;

public interface LoginService {
    Map<String,String> staffLogin(LoginDTO loginDTO);
    Map<String,String> customerLogin(LoginDTO loginDTO);
}
