# 🌸 VogueNest
VogueNest is an editorial-grade, full-stack e-commerce web application built for premium wardrobe curation and luxury lifestyle shopping.

---

## 🚀 Live Demo
Experience the live application deployed on Render:
👉 **[Visit VogueNest Live](https://voguenest.onrender.com)**

---

## ✨ Key Features
- **Curated Editorial Aesthetics:** Crafted with a custom pastel-cream background, forest sage accents, and terracotta highlights.
- **Dual-Role Session Management:** Unified authentication routing that automatically directs regular shoppers to their active cart context and administrators to inventory dashboards.
- **Hybrid Asynchronous Cart Engine:** Seamlessly handles guest state via local storage and safely syncs and persists user cart records upon authentication.
- **Dynamic Admin Inventory Control:** A protected dashboard portal enabling authorized admins to add, manage, and catalog products dynamically.
- **Secure Checkout Pipeline:** Features a complimentary luxury shipping calculation engine and automated transactional data binding.

---

## 📸 Storefront Preview

### Minimalist Editorial Landing Page
![Model Showcase](https://raw.githubusercontent.com/osheenkhan1031/VogueNest/main/src/main/resources/static/images/model.jpg)

### Active Storefront Catalogue Page
![Accessories Preview](https://raw.githubusercontent.com/osheenkhan1031/VogueNest/main/src/main/resources/static/images/accessories.jpg)

---

## 🛠️ Tech Stack & Architecture
- **Backend:** Java, Spring Boot 3 (Spring MVC, Spring Data JPA, Spring Security)
- **Frontend:** Thymeleaf, HTML5, CSS3, Tailwind CSS
- **Database:** MySQL (powered by Aiven cloud)
- **Build Tool:** Apache Maven
- **Containerization & Deployment:** Docker (Multi-stage JDK/JRE build) hosted on Render

---

## 🚀 Future Plans & Roadmap
- **Spring Security & OAuth2:** Implementing JWT-based stateless API security and federated social logins.
- **Payment Gateway Integration:** Connecting Stripe or Razorpay webhooks for live payment processing.
- **Cloud Media Pipeline:** Moving local static image assets to AWS S3 or Cloudinary for fast CDN delivery.