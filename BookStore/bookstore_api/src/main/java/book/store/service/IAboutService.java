package book.store.service;

import book.store.entity.About;
import book.store.payload.request.AboutRq;
import book.store.payload.response.AboutRsp;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IAboutService {
    boolean addAbout (AboutRq aboutRq);

    List<AboutRsp> getAllAbout();
}
