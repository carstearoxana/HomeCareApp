package com.sda.homeCare.controllers;
import com.sda.homeCare.entities.User;
import com.sda.homeCare.entities.Product;
import com.sda.homeCare.entities.ShoppingCart;
import com.sda.homeCare.entities.ShoppingCartItem;
import com.sda.homeCare.repositories.UserRepository;
import com.sda.homeCare.repositories.ProductRepository;
import com.sda.homeCare.repositories.ShoppingCartItemRepository;
import com.sda.homeCare.repositories.ShoppingCartRepository;
import com.sda.homeCare.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
//@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/shoppingCart")
    public ModelAndView getIndex(){
        ModelAndView modelAndView= new ModelAndView("shoppingCart");
        String userEmail= userService.getLogInUser();
        if (shoppingCartRepository.findByUser_UserEmail(userEmail)!=null) {
            modelAndView.addObject("cartItemList", shoppingCartRepository.findByUser_UserEmail(userEmail).getShoppingCartItemList());
        }
        return  modelAndView;
    }

    @Autowired
    UserService userService;
    @Autowired
    ShoppingCartRepository shoppingCartRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ShoppingCartItemRepository shoppingCartItemRepository;

    @GetMapping("/shoppingCart/add")
    public ModelAndView addProductToCart(@RequestParam("id") Integer id){
        ModelAndView modelAndView= new ModelAndView("shoppingCart");
        Optional<Product> productOptional=productRepository.findById(id);
        Product product=productOptional.get();
        String userEmail= userService.getLogInUser();
        ShoppingCartItem shoppingCartItem=new ShoppingCartItem();
        ShoppingCart shoppingCart=new ShoppingCart();
        User user = userRepository.findByUserEmail(userEmail);

        if(shoppingCartRepository.findByUser_UserId(user.getUserId())!=null){
            shoppingCart=shoppingCartRepository.findByUser_UserId(user.getUserId());
            shoppingCartItem.setProduct(product);
            shoppingCart.getShoppingCartItemList().add(shoppingCartItem);
            shoppingCartItem.setShoppingCart(shoppingCart);
            shoppingCartRepository.save(shoppingCart);
            shoppingCartItemRepository.save(shoppingCartItem);

        } else {
            shoppingCartItem.setProduct(product);
            List<ShoppingCartItem> shoppingCartItemList= new ArrayList<>();
            shoppingCartItemList.add(shoppingCartItem);
            shoppingCart.setShoppingCartItemList(shoppingCartItemList);
            shoppingCart.setUser(user);
            shoppingCartItem.setShoppingCart(shoppingCart);
            shoppingCartRepository.save(shoppingCart);
            shoppingCartItemRepository.save(shoppingCartItem);
        }
        modelAndView.addObject("cartItemList",shoppingCartRepository.findByUser_UserEmail(userEmail).getShoppingCartItemList());
        return modelAndView;
    }

    @GetMapping("/shoppingCart/delete")
    public ModelAndView deleteProductFromCart(@RequestParam("id") Integer id){
        ModelAndView modelAndView= new ModelAndView("shoppingCart");
        Optional<ShoppingCartItem> shoppingCartItemOptional=shoppingCartItemRepository.findById(id);
        ShoppingCartItem shoppingCartItem=shoppingCartItemOptional.get();
        shoppingCartItemRepository.delete(shoppingCartItem);
        String userEmail= userService.getLogInUser();
        modelAndView.addObject("cartItemList",shoppingCartRepository.findByUser_UserEmail(userEmail).getShoppingCartItemList());
        return modelAndView;
    }
}
