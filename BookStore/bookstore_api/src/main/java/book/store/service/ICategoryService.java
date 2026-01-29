package book.store.service;

import book.store.payload.response.CategoryRsp;

import java.util.List;

public interface ICategoryService {

    List<CategoryRsp> getAllCategory();

}
