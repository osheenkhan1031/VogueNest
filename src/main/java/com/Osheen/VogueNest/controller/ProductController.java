package com.Osheen.VogueNest.controller;

import com.Osheen.VogueNest.model.Product;
import com.Osheen.VogueNest.model.User;
import com.Osheen.VogueNest.model.Cart;
import com.Osheen.VogueNest.service.ProductService;
import com.Osheen.VogueNest.service.CartService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final CartService cartService;

    @GetMapping("/shop")
    public String showShopPage(@RequestParam(value = "category", required = false, defaultValue = "all") String category,
                               HttpSession session,
                               Model model) {
        List<Product> products = productService.getProducts(category);
        model.addAttribute("products", products);
        model.addAttribute("activeCategory", category != null ? category.toLowerCase() : "all");

        User loggedInUser = (User) session.getAttribute("loggedInUser");
        model.addAttribute("isLoggedIn", loggedInUser != null);
        model.addAttribute("user", loggedInUser);

        if (loggedInUser != null) {
            List<Cart> dbItems = cartService.getCartItems(loggedInUser.getId());
            model.addAttribute("dbCartItems", dbItems);
        }

        return "shop";
    }

    @GetMapping("/admin/add")
    public String showAddProductForm(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");

        //check to allow only authenticated ADMIN users
        if (user == null || !"ADMIN".equalsIgnoreCase(user.getRole())) {
            return "redirect:/login";
        }

        model.addAttribute("product", new Product());
        return "add-product";
    }

 // To show Admin Catalog List
 @GetMapping("/admin/list")
 public String listProductsForEdit(Model model, HttpSession session) {
     User user = (User) session.getAttribute("loggedInUser");
     if (user == null || !"ADMIN".equalsIgnoreCase(user.getRole())) {
         return "redirect:/login";
     }

     model.addAttribute("allProducts", productService.getProducts("all"));
     return "admin-list";
 }

    //  Edit mapping
    @GetMapping("/admin/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null || !"ADMIN".equalsIgnoreCase(user.getRole())) return "redirect:/login";

        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "edit-product";
    }

    //  Update mapping
    @PostMapping("/admin/update")
    public String updateProduct(@ModelAttribute("product") Product product, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null || !"ADMIN".equalsIgnoreCase(user.getRole())) return "redirect:/login";

        productService.updateProduct(product);
        return "redirect:/admin/list"; // Yahan se seedha wapas catalog list par bhejo
    }
    @GetMapping("/admin/delete/{id}")
    public String deleteProduct(@PathVariable Long id, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null || !"ADMIN".equalsIgnoreCase(user.getRole())) return "redirect:/login";

        productService.deleteProduct(id);
        return "redirect:/admin/list";
    }
}