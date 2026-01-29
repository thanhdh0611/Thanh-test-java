package book.store.service.impl;

import book.store.entity.Category;
import book.store.payload.response.CategoryRsp;
import book.store.repository.CategoryRepository;
import book.store.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public List<CategoryRsp> getAllCategory() {
        List<Category> list = categoryRepository.findAll();
        List<CategoryRsp> categoryRspList = new ArrayList<>();
        for (Category category : list) {
            CategoryRsp categoryRsp = new CategoryRsp();
            categoryRsp.setId(category.getId());
            categoryRsp.setName(category.getName());
            categoryRspList.add(categoryRsp);
        }
        return categoryRspList;
    }
}
