package book.store.controller;

import book.store.payload.request.UserRq;
import book.store.payload.response.BaseRsp;
import book.store.service.IUserService;
import book.store.utils.JWTHelperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class LoginController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private IUserService iUserService;
    @Autowired
    private JWTHelperUtils jwtHelperUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody UserRq userRq) {
        boolean success = iUserService.checkSignin(userRq);
        String token = jwtHelperUtils.generateToken(userRq.getEmail());
        BaseRsp response = new BaseRsp();
        response.setMessage(success ? "Login Success" : "Login Fail");
        response.setData(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@RequestBody UserRq userRq) {
        boolean success = iUserService.createUser(userRq);

        BaseRsp response = new BaseRsp();
        response.setData(success);
        response.setMessage(success ? "Đăng ký thành công" : "Đăng ký thất bại");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
