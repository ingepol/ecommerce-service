# 📌 Project: "E-Commerce Microservices System"

📢 Objective: Implement an order system with microservices applying the studied design patterns.

## 🛠️ Technologies

- Java 17+
- Spring Boot
- Spring Cloud
- Kafka/RabbitMQ
- Resilience4J
- Docker

## 📌 Architecture and Applied Patterns

### 1️⃣ Creational Patterns

🔹 Singleton → Management of database connections.  
🔹 Factory Method → Creation of Payment, Order, Product objects in the services.  
🔹 Abstract Factory → Creation of database commands (DatabaseCommand).  
🔹 Builder → Creation of DTOs in the microservices.  

### 2️⃣ Structural Patterns  

🔹 Adapter → Integration with external payment gateways (e.g., Stripe, PayPal).  
🔹 Decorator → Data enrichment in the notification service.  
🔹 Facade → Aggregation of customer, order, and payment services into a single API Gateway.  
🔹 Proxy → Security in order endpoints with Spring Security.  

### 3️⃣ Behavioral Patterns

🔹 Observer → Real-time notifications with Kafka/RabbitMQ.  
🔹 Strategy → Validation of payment methods (card, PayPal, cryptocurrencies).  
🔹 Command → Implementation of CQRS in the order service.  
🔹 Chain of Responsibility → Order validations at different steps.  

### 4️⃣ Microservices Architecture

🔹 API Gateway → Spring Cloud Gateway.  
🔹 Service Discovery → Eureka/Consul.  
🔹 Circuit Breaker & Retry → Resilience4J.  
🔹 CQRS → Separation of commands and queries in orders. 
🔹 Event Sourcing → Event-based persistence.  
🔹 Saga Pattern → Distributed transactions in orders and payments.  

## 📌 ⚙️ Microservices
  
✅ User Service → User management and authentication.  
✅ Order Service → Creation and management of orders.  
✅ Payment Service → Processing payments with various methods.  
✅ Product Service → Management of inventory and product catalog.  
✅ Notification Service → Sending notifications via email/SMS.  
✅ Shipping Service → Handling shipments and logistics.  
✅ Service Registry → Eureka Server  
✅ API Gateway → Spring Cloud Gateway  
✅ Common Library → Common library for DTOs and exceptions  
✅ Config Server → Cloud configuration  
  
## 📌 🏗️ Structure of the Projects
  
```
ecommerce-microservices/
│
├── common-library/com
│   ├── src/main/java/
│   │   └── com/ecommerce/common/
│   ├── src/main/resources/
│   └── pom.xml
│
├── config-server/com
│   ├── src/main/java/
│   │   └── com/ecommerce/configserver/
│   ├── src/main/resources/
│   └── pom.xml
│
├── discovery-service/
│   ├── src/main/java/
│   │   └── com/ecommerce/discoveryservice/
│   ├── src/main/resources/
│   └── pom.xml
│
├── api-gateway/com   
│   ├── src/main/java/
│   │   └── com/ecommerce/apigateway/
│   ├── src/main/resources/
│   └── pom.xml
│
├── user-service/com  
│   ├── src/main/java/
│   │   └── com/ecommerce/userservice/
│   ├── src/main/resources/
│   └── pom.xml
│
├── order-service/com
│   ├── src/main/java/
│   │   └── com/ecommerce/orderservice/
│   ├── src/main/resources/
│   └── pom.xml
│
├── payment-service/
│   ├── src/main/java/
│   │   └── com/ecommerce/paymentservice/
│   ├── src/main/resources/
│   └── pom.xml
│
├── product-service/
│   ├── src/main/java/
│   │   └── com/ecommerce/productservice/
│   ├── src/main/resources/
│   └── pom.xml
│
├── notification-service/
│   ├── src/main/java/
│   │   └── com/ecommerce/notificationservice/
│   ├── src/main/resources/
│   └── pom.xml
│
└── shipping-service/
│   ├── src/main/java/
│   │   └── com/ecommerce/shippingservice/
│   ├── src/main/resources/
│   └── pom.xml

└── readme.md
```