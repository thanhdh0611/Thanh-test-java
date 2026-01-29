package book.store.service.impl;

import book.store.entity.About;
import book.store.entity.Users;
import book.store.payload.request.AboutRq;
import book.store.payload.response.AboutRsp;
import book.store.repository.AboutRepository;
import book.store.repository.UserRepository;
import book.store.service.IAboutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AboutService implements IAboutService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AboutRepository aboutRepository;

    @Override
    public boolean addAbout(AboutRq aboutRq) {
        System.out.println(aboutRq);
        aboutRq.getUsersId();
        System.out.println(aboutRq.getUsersId());
        Optional<Users> usersOptional = userRepository.findById(aboutRq.getUsersId());
        Users user1 = usersOptional.get();

        About about = new About();
        about.setAge(aboutRq.getAge());
        about.setDegree(aboutRq.getDegree());
        about.setPhone(user1.getPhone());
        about.setEmail(user1.getEmail());
        about.setUsers(user1);


        aboutRepository.save(about);
        System.out.println("hi");
        return true;
    }

    @Override
    public List<AboutRsp> getAllAbout() {
        List<About> listAbout = aboutRepository.findAll();
        List<AboutRsp> aboutRspList = new ArrayList<>();

        for (About about : listAbout) {
            AboutRsp aboutRsp = new AboutRsp();

            Users users = userRepository.findByEmail(about.getEmail());
            aboutRsp.setAge(about.getAge());
            aboutRsp.setDesc(about.getDesc());
            aboutRsp.setEmail(about.getEmail());
            aboutRsp.setName(users.getLastName() + " " + users.getFirstName());
            aboutRsp.setPhone(users.getPhone());
            aboutRsp.setImage(about.getImage());
            aboutRspList.add(aboutRsp);
        }

        return aboutRspList;
    }
}
