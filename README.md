VogueNest - Premium Women's Fashion Store

VogueNest is a modern, high-end editorial e-commerce platform designed for women's fashion and lifestyle. Built with Spring Boot 3, Thymeleaf, and MySQL/H2 Database, the application combines a luxury minimal aesthetic with a robust backend architecture tailored for a premium shopping experience.

🚀 Status: Work In Progress (WIP)

This project is actively under development. The current implementation roadmap and feature status are listed below.

🛠️ Features Implemented So Far

[x] Premium Landing Page: A minimalist, aesthetic landing page with custom pastel gradients and editorial grid layouts.

[x] User Authentication:

[x] Signup functionality with duplicate email checks in the database.

[x] Dynamic Login validation and session routing.

[x] Curated Categories Showcase: An interactive 5-column grid displaying curated items (Dresses, Footwear, Accessories, Makeup, and Skincare) with modern hover effects and zoom layouts.

📌 Roadmap (Next Milestones)

[ ] Product Listing Page: Dynamic database-driven product catalogue with category filters.

[ ] Interactive Shopping Cart: Real-time cart updates (Add, Remove, Quantity adjustments).

[ ] User Dashboard: Order history and profile management.

[ ] Checkout & Payment Integration: Mock checkout gate with secure validation.

📦 Tech Stack & Architecture

Backend: Java 21, Spring Boot (Spring MVC, Spring Data JPA)

Frontend: HTML5, CSS3, Thymeleaf Templates (Modularized components)

Database: MySQL / H2 In-Memory Database (configured for developmental testing)

Build Tool: Maven

📂 Project Folder Structure

The codebase follows standard enterprise packaging standards:

src/main/
├── java/com/Osheen/VogueNest/
│   ├── controller/
│   │   ├── AuthController.java
│   │   └── SiteController.java
│   ├── model/
│   │   └── User.java
│   ├── repository/
│   │   └── UserRepository.java
│   └── VogueNestApplication.java
└── resources/
├── static/
│   └── images/
│       ├── accessories.jpg
│       ├── cataccessories.jpg
│       ├── catdresses.jpg
│       ├── catfootwear.jpeg
│       ├── catmakeup.jpg
│       ├── catskincare.jpg
│       ├── dresses.jpg
│       ├── footwear.jpeg
│       ├── makeup.jpg
│       ├── model.jpg
│       └── skincare.jpg
├── templates/
│   ├── categories.html
│   ├── landingpage.html
│   ├── login.html
│   └── signup.html
└── application.properties


⚙️ How to Run the Project Locally

Prerequisites:

Java 21

Maven 3.x

IntelliJ IDEA or Eclipse

Clone the Repository:

git clone https://github.com/osheenkhan1031/VogueNest.git
cd VogueNest


Database Configuration:
Verify connection settings in src/main/resources/application.properties.

Build and Run:

mvn spring-boot:run


Open http://localhost:8080 in your web browser.

👤 Author

Osheen - Aspiring Software Engineer

Email: osheenkhan1031@gmail.com

LinkedIn: osheenkhan1031