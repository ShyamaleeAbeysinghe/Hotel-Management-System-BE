package hotel.system.grand.controller;

import hotel.system.grand.dto.CustomerDTO;
import hotel.system.grand.dto.RoleDTO;
import hotel.system.grand.service.CustomerService;
import hotel.system.grand.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/role")
@CrossOrigin
public class RoleController {
    final RoleService roleService;
    @PostMapping("/add")
    public void addCustomer(@RequestBody RoleDTO roleDTO){
        roleService.addRole(roleDTO);
    }
    @PutMapping("/update")
    public  void updateCustomer(@RequestBody RoleDTO roleDTO){
        roleService.addRole(roleDTO);
    }

    @DeleteMapping("/delete")
    public Boolean deleteByCustomerId(@RequestParam Integer id){
        return roleService.deleteRole(id);

    }

    @GetMapping("/get-all")
    public List<RoleDTO> getAllCustomer(){
        return roleService.getAll();
    }
    @GetMapping("/search-by-roleName/{roleName}")
    public List<RoleDTO> getRoleDtoByRoleName(@PathVariable String roleName){
        return roleService.getRoleDtoByRoleName(roleName);
    }

}
