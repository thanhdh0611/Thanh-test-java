package book.store.controller;

import book.store.payload.request.AboutRq;
import book.store.payload.response.AboutRsp;
import book.store.service.IAboutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/about")
@CrossOrigin
public class AboutController {

    @Autowired
    private IAboutService iAboutService;

    @PostMapping("/addAbout")
    public ResponseEntity<?> addAbout(@RequestBody AboutRq aboutRq) {
        iAboutService.addAbout(aboutRq);
        return new ResponseEntity<>("", HttpStatus.OK);
    }


    @GetMapping("/getAllAbout")
    public ResponseEntity<?> getAllAbout() {

        List<AboutRsp> list = iAboutService.getAllAbout();
        return new ResponseEntity<>(iAboutService.getAllAbout(), HttpStatus.OK);
    }


}
