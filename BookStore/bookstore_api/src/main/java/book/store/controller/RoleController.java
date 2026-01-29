package book.store.controller;

import book.store.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService iRoleService;


    @GetMapping("/getAllRole")
    public ResponseEntity<?> getAllRole() {
        return new ResponseEntity<>(iRoleService.getAllRole(), HttpStatus.OK);

    }
}
