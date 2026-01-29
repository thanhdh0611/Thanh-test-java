package book.store.controller;

import book.store.entity.OrderDetail;
import book.store.entity.Users;
import book.store.payload.request.AboutRq;
import book.store.payload.request.UOrderRq;
import book.store.repository.UserRepository;
import book.store.service.IOrderService;
import book.store.utils.JWTHelperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {

    @Autowired
    private JWTHelperUtils jwtHelperUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    IOrderService iOrderService;

    @PostMapping("/addOrder")
    public ResponseEntity<?> addOrder(@RequestBody UOrderRq uOrderRq, HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        try {
            String token = header.substring(7);
            String data = jwtHelperUtils.validateToken(token);
            System.out.println(data);
            Users users = userRepository.findByEmail(data);
            iOrderService.addOrder(uOrderRq,users.getId());
        } catch (Exception e) {
            System.out.println("Token không hợp lệ + : " + e);
        }
        System.out.println(uOrderRq);

        return new ResponseEntity<>("", HttpStatus.OK);
    }

}
