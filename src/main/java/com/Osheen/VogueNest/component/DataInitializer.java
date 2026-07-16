package com.Osheen.VogueNest.component;

import com.Osheen.VogueNest.model.Product;
import com.Osheen.VogueNest.model.User;
import com.Osheen.VogueNest.repository.ProductRepository;
import com.Osheen.VogueNest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value; // Import properly for environment injection
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * DataInitializer - VogueNest Secure Seeder
 * Automatically seeds the database with premium curated products on startup.
 * Admin credentials are secure and externalized using Spring's @Value architecture.
 */
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    // Pulls from app.admin.email properties placeholder dynamically
    @Value("${app.admin.email}")
    private String adminEmail;

    // Pulls secure admin password without keeping plaintext credentials committed to GitHub
    @Value("${app.admin.password}")
    private String adminPassword;

    @Override
    @Transactional
    public void run(String... args) {

        if (productRepository.count() == 0) {
            productRepository.saveAll(Arrays.asList(
                    // --- CATEGORY: DRESSES ---
                    new Product(
                            null,
                            "Minimalist Silk Slip Dress",
                            "dresses",
                            189.00,
                            "An elegant flowing dress with high-quality silk threads, featuring delicate spaghetti straps and a soft cowl neck.",
                            "https://images.unsplash.com/photo-1595777457583-95e059d581b8?q=80&w=600&auto=format&fit=crop",
                            new ArrayList<>()
                    ),
                    new Product(
                            null,
                            "Linen Wrap Midi Dress",
                            "dresses",
                            145.00,
                            "Breathable organic linen perfect for warm summer evenings. Features a flattering adjustable tie waist.",
                            "https://images.unsplash.com/photo-1572804013309-59a88b7e92f1?q=80&w=600&auto=format&fit=crop",
                            new ArrayList<>()
                    ),

                    // --- CATEGORY: FOOTWEAR ---
                    new Product(
                            null,
                            "Leather Heeled Mules",
                            "footwear",
                            220.00,
                            "Handcrafted soft Italian leather heels with a padded insole for long-lasting premium comfort.",
                            "https://images.unsplash.com/photo-1543163521-1bf539c55dd2?q=80&w=600&auto=format&fit=crop",
                            new ArrayList<>()
                    ),
                    new Product(
                            null,
                            "Minimalist Leather Sandals",
                            "footwear",
                            95.00,
                            "Genuine tan leather straps with a sturdy rubber sole, ideal for chic everyday resort wear.",
                            "https://images.unsplash.com/photo-1562273138-f46be4ebdf33?q=80&w=600&auto=format&fit=crop",
                            new ArrayList<>()
                    ),

                    // --- CATEGORY: ACCESSORIES ---
                    new Product(
                            null,
                            "18k Gold Twisted Hoop Earrings",
                            "accessories",
                            85.00,
                            "Waterproof 18k vacuum gold-plated brass hoops. Lightweight, hypoallergenic, and timelessly sleek.",
                            "https://images.unsplash.com/photo-1535632066927-ab7c9ab60908?q=80&w=600&auto=format&fit=crop",
                            new ArrayList<>()
                    ),
                    new Product(
                            null,
                            "Bespoke Silk Hair Ribbon",
                            "accessories",
                            35.00,
                            "A pure mulberry silk ribbon scarf to tie around your hair or wrap elegantly on your luxury handbag.",
                            "https://images.unsplash.com/photo-1576243345690-4e4b79b63288?q=80&w=600&auto=format&fit=crop",
                            new ArrayList<>()
                    ),

                    // --- CATEGORY: MAKEUP ---
                    new Product(
                            null,
                            "Hydrating Velvet Lip Tint",
                            "makeup",
                            32.00,
                            "A weightless liquid lip color with a soft-focus matte finish. Packed with nourishing Vitamin E.",
                            "https://images.unsplash.com/photo-1625093742435-6fa192b6fb10?q=80&w=600&auto=format&fit=crop",
                            new ArrayList<>()
                    ),
                    new Product(
                            null,
                            "Dewy Liquid Blush",
                            "makeup",
                            28.00,
                            "A weightless, long-lasting liquid formula that blends seamlessly for a healthy, second-skin flush.",
                            "https://i.pinimg.com/736x/56/38/36/563836c5eba58e41b38c4f3435eccdda.jpg",
                            new ArrayList<>()
                    ),

                    // --- CATEGORY: SKINCARE ---
                    new Product(
                            null,
                            "Ceramide Barrier Repair Cream",
                            "skincare",
                            54.00,
                            "Daily skin-defense whipped cream with multi-ceramides and hyaluronic acid to instantly calm and lock moisture.",
                            "https://images.unsplash.com/photo-1608248597279-f99d160bfcbc?q=80&w=600&auto=format&fit=crop",
                            new ArrayList<>()
                    ),
                    new Product(
                            null,
                            "Gentle Oat Cleansing Balm",
                            "skincare",
                            38.00,
                            "A nourishing balm-to-milk cleanser that effortlessly melts away heavy makeup and impurities while preserving the skin barrier.",
                            "https://images.unsplash.com/photo-1556229174-5e42a09e45af?q=80&w=600&auto=format&fit=crop",
                            new ArrayList<>()
                    )
            ));
            System.out.println(">> VogueNest Seed Data Successfully Injected! <<");
        }

        // Uses property placeholders instead of hardcoded values
        if (userRepository.findByEmail(adminEmail).isEmpty()) {
            User admin = new User();
            admin.setFullName("VogueNest Admin");
            admin.setEmail(adminEmail);
            admin.setPassword(adminPassword); // Protected and dynamic
            admin.setRole("ADMIN");
            userRepository.save(admin);
            System.out.println(">> Default Admin Account Created dynamically! <<");
        }
    }
}