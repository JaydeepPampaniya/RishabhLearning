package com.PhoneX.Backend.Service;

import com.PhoneX.Backend.entity.Categories;
import com.PhoneX.Backend.globalException.BadRequestException;
import com.PhoneX.Backend.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesService {

    private final CategoriesRepository categoriesRepository;

    @Autowired
    public CategoriesService(CategoriesRepository categoriesRepository){
        this.categoriesRepository=categoriesRepository;
    }
    public String addCat(String categoriesName) {
        if(categoriesRepository.existsByCategoriesName(categoriesName)){
           throw new BadRequestException("Categories already exists!");
        }else{
            Categories cat=new Categories();
            cat.setCategoriesName(categoriesName);
            categoriesRepository.save(cat);
            return "Categories Saved";
        }
    }

    public List<Categories> getCategories() {
        return categoriesRepository.findAll();
    }
}
