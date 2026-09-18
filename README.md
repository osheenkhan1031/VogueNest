# 🌸 VogueNest
VogueNest is an editorial-grade, full-stack e-commerce web application built for premium wardrobe curation and luxury lifestyle shopping.

---

## 🚀 Live Demo
Experience the live application deployed on Render:
👉 **[Visit VogueNest Live](https://voguenest.onrender.com)**

---

## 📸 Storefront Preview
![Landing Page](https://raw.githubusercontent.com/osheenkhan1031/VogueNest/main/screenshots/landing.png)

---

## ✨ Key Features
- **Curated Editorial Aesthetics:** Crafted with a custom pastel-cream background, forest sage accents, and terracotta highlights.
- **Dual-Role Session Management:** Unified authentication routing that automatically directs regular shoppers to their active cart context and administrators to inventory dashboards.
- **Hybrid Asynchronous Cart Engine:** Seamlessly handles guest state via local storage and safely syncs and persists user cart records upon authentication.
- **Dynamic Admin Inventory Control:** A protected dashboard portal enabling authorized admins to add, manage, and catalog products dynamically.
- **Secure Checkout Pipeline:** Features a complimentary luxury shipping calculation engine and automated transactional data binding.

---

## 🛠️ Tech Stack & Architecture
- **Backend:** Java, Spring Boot 3 (Spring MVC, Spring Data JPA)
- **Security:** Custom session-based authentication with role-based access control; passwords hashed using Spring Security's `BCryptPasswordEncoder`
- **Frontend:** Thymeleaf, HTML5, CSS3, Tailwind CSS
- **Database:** MySQL (powered by Aiven cloud)
- **Build Tool:** Apache Maven
- **Containerization & Deployment:** Docker (Multi-stage JDK/JRE build) hosted on Render

---

## 🗺️ Future Scope
- Migrate to full Spring Security (filter chains, method-level `@PreAuthorize`, OAuth2 login) in place of manual session checks.
- Integrate a real payment gateway (Razorpay/Stripe) for live transactions.