package com.example.service;

import com.example.dao.ProductDao;
import com.example.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gezilin on 4/05/17.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public void save(Product product) {
         productDao.save(product);

    }

    @Override
    public Product findOne(Long id) {
        return productDao.findOne(id);
    }

    @Override
    public void delete(Long id) {
        productDao.delete(id);

    }
}
