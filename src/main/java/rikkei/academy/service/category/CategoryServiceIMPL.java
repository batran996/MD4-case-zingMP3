package rikkei.academy.service.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rikkei.academy.model.Category;
import rikkei.academy.repository.ICategoryRepository;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class CategoryServiceIMPL implements ICategoryService{
    @Autowired
    private ICategoryRepository categoryRepository;
    @Override
    public List findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Page findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }
}
