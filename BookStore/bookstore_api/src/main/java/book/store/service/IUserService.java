package book.store.service;

import book.store.payload.request.AboutRq;
import book.store.payload.request.UserRq;

public interface IUserService {


    boolean createUser(UserRq userRq);

    boolean getUserByEmail(String email);

    boolean checkSignin(UserRq userRq);

}
