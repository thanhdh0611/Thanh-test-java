package book.store.service.impl;

import book.store.entity.About;
import book.store.entity.Role;
import book.store.entity.Users;
import book.store.payload.request.AboutRq;
import book.store.payload.request.UserRq;
import book.store.payload.response.UserRsp;
import book.store.repository.AboutRepository;
import book.store.repository.RoleRepository;
import book.store.repository.UserRepository;
import book.store.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AboutRepository aboutRepository;

    @Override
    public boolean createUser(UserRq userRq) {
        if (getUserByEmail(userRq.getEmail())) {
            Users user = new Users();
            user.setEmail(userRq.getEmail());
            user.setPassword(passwordEncoder.encode(userRq.getPassword()));
            user.setPhone(userRq.getPhone());
            user.setFirstName(userRq.getFirstName());
            user.setLastName(userRq.getLastName());
            Set<Role> roleList = new HashSet<>();
            Role role = roleRepository.findById(2).orElse(null);
            if (role != null) {
                roleList.add(role);
            }
            user.setRoles(roleList);
            user.setStatus("active");
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean getUserByEmail(String email) {
        List<Users> list = userRepository.findAll();
        if (list.isEmpty()) {
            return true;
        }
        List<UserRsp> userRspList = new ArrayList<>();
        for (Users user : list) {
            UserRsp userRsp = new UserRsp();
            userRsp.setEmail(user.getEmail());
            userRsp.setPassword(user.getPassword());
            userRspList.add(userRsp);
        }
        boolean success = true;
        for (UserRsp userRsp : userRspList) {
            if (Objects.equals(email, userRsp.getEmail())) {
                success = false;
                break;
            }
        }
        return success;
    }

    @Override
    public boolean checkSignin(UserRq userRq) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userRq.getEmail(), userRq.getPassword());
        // Thực thi chứng thực sử dụng CustomAuthenProvider nếu không thành công thì dừng code ở đây luôn.
        authenticationManager.authenticate(authenticationToken);
        return authenticationManager.authenticate(authenticationToken).isAuthenticated();
    }

}
