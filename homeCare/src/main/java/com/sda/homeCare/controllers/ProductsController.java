package com.sda.homeCare.controllers;

import com.sda.homeCare.entities.Product;
import com.sda.homeCare.repositories.CategoryRepository;
import com.sda.homeCare.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/find-all")
    public ModelAndView getProducts(){
        ModelAndView modelAndView=new ModelAndView("products");
        modelAndView.addObject("productList",productRepository.findAll());
        return modelAndView;
    }

    @GetMapping("/admin")
    public ModelAndView getProductsAdmin(){
        ModelAndView modelAndView=new ModelAndView("admin");
        modelAndView.addObject("productAdminList",productRepository.findAll());
        return modelAndView;
    }

    @GetMapping("/find-all/by-category")
    public ModelAndView getCategory(@RequestParam("categoryName") String categoryName){
        ModelAndView modelAndView=new ModelAndView("products");
        modelAndView.addObject("productList",productRepository.findAllByCategory_CategoryName(categoryName));
        return modelAndView;
    }

    @GetMapping("/admin/addProductForm")
    public ModelAndView getProductForm(){
        ModelAndView modelAndView= new ModelAndView("addProductForm");
        modelAndView.addObject("product",new Product());
        modelAndView.addObject("categories", categoryRepository.findAll());
        return  modelAndView;
    }

    @PostMapping("/admin/saveProductForm")
    public String saveProduct(@ModelAttribute Product product){
        productRepository.save(product);
        return "redirect:/products/admin";
    }

    @GetMapping("/admin/delete")
    public ModelAndView deleteProduct(@RequestParam("id") Integer id){
        ModelAndView modelAndView= new ModelAndView("admin");
        Optional<Product> productOptional=productRepository.findById(id);
        Product product=productOptional.get();
        productRepository.delete(product);
        modelAndView.addObject("productAdminList",productRepository.findAll());
        return modelAndView;
    }

    @GetMapping("/admin/updateProductForm")
    public ModelAndView updateProductForm(@RequestParam("id") Integer id){
        ModelAndView modelAndView= new ModelAndView("updateProductForm");
        Optional<Product> productOptional=productRepository.findById(id);
        Product product=productOptional.get();
        modelAndView.addObject("product",product);
        modelAndView.addObject("categories", categoryRepository.findAll());
        return modelAndView;
    }

    @PostMapping("/admin/updateProduct")
    public String updateProduct( Product updatedProduct){
        productRepository.save(updatedProduct);
        return "redirect:/products/admin";
    }
}
