<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>📚 Bookstore API</title>

</head>
<body>

  <header>
    <h1>📚 Bookstore API – RESTful Web Service</h1>
    <p>Java REST API for managing authors, books, and orders</p>
  </header>

  <div class="container">
    <h2>🧩 Tech Stack</h2>
    <ul>
      <li>⚙️ Java 17+</li>
      <li>🔧 JAX-RS (Jakarta RESTful Web Services)</li>
      <li>📦 Maven</li>
      <li>🧪 NetBeans IDE</li>
      <li>🌐 Apache Tomcat Server</li>
      <li>📄 JSON Data Format</li>
    </ul>

  <h2>✅ Key Features</h2>
    <ul>
      <li>📝 Author & Book Management – Full CRUD operations with validation</li>
      <li>🛒 Order System – Place, retrieve, and manage customer orders</li>
      <li>🚫 Robust Exception Handling – Custom error mappers with clear messages</li>
      <li>🔐 Input Validation – Ensure data integrity at every endpoint</li>
      <li>🧹 Clean Code Architecture – Layered design using services, resources, and models</li>
      <li>📦 WAR Deployment Ready – Easy to deploy on any servlet container</li>
      <li>🔁 RESTful Endpoint Design – Follows industry conventions for HTTP verbs and responses</li>
    </ul>

  <h2>🚀 Getting Started</h2>
    <div class="code-block">
<pre>
# Clone the repository
git clone https://github.com/your-username/bookstore-api.git

# Navigate into the project
cd bookstore-api

# Build the project
mvn clean package

# Deploy the generated WAR file to Apache Tomcat
</pre>
    </div>

  <p><strong>📍 Access API Endpoints:</strong></p>
    <ul>
      <li><a href="#">http://localhost:8080/testAPI/api/authors</a></li>
      <li><a href="#">http://localhost:8080/testAPI/api/books</a></li>
      <li><a href="#">http://localhost:8080/testAPI/api/orders</a></li>
    </ul>

  <h2>📁 Project Structure</h2>
    <div class="code-block">
<pre>
├── models          # Data classes (Book, Author, Order)
├── services        # Business logic
├── resources       # JAX-RS REST endpoints
├── mappers         # Custom exception handlers
└── webapp          # Deployment configuration (web.xml)
</pre>
    </div>

  <h2>💡 Ideal For</h2>
    <ul>
      <li>RESTful API development practice</li>
      <li>Java + Maven + Tomcat deployment workflows</li>
      <li>Clean, scalable backend design for web applications</li>
    </ul>
  </div>

  <footer>
    © 2025 Devin | Bookstore API Project
  </footer>

</body>
</html>
