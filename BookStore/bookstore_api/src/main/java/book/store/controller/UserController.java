package book.store.controller;


import book.store.payload.request.UserRq;
import book.store.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @PostMapping("/getUserByEmail")
    public ResponseEntity<?> getUserByEmail(@RequestBody UserRq userRq) {
        System.out.println(userRq.getEmail());
        return new ResponseEntity<>(iUserService.getUserByEmail(userRq.getEmail()), HttpStatus.OK);
    }


//    @PostMapping("/createUser")
//    public ResponseEntity<?> createUser(@RequestBody UserRq userRq) {
//        boolean success = iUserService.createUser(userRq);
//        return new ResponseEntity<>(success, HttpStatus.OK);
//    }

}
