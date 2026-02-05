# Product CRUD Web & API App

A full-stack Spring Boot application that allows users to register and perform CRUD operations on a product table via both a web interface and a REST API.

## Features

- User registration and login
- Web-based product management (create, read, update, delete)
- REST API for product operations (no authentication required)
- Role-based access control
  - New accounts are assigned the **USER** role
  - **ADMIN** role required for delete operations

## Demo Credentials

- **User**  
  Email: `user@user`  
  Password: `user`

- **Admin**  
  Email: `admin@admin`  
  Password: `admin`


## Tech Stack

- **Backend**: Spring Boot, Spring MVC, Spring Security, JDBC
- **Frontend**: Thymeleaf
- **Database**: H2
- **Build Tool**: Maven

## Running the App

```bash
git clone https://github.com/yourusername/product-crud-app.git
cd product-crud-app
./mvnw spring-boot:run
```

Then visit: [http://localhost:8080](http://localhost:8080)

## API Endpoints (Public Access)

- `GET /api/v1/products`
- `POST /api/v1/products`
- `PUT /api/v1/products/{id}`
- `DELETE /api/v1/products/{id}` 

## Demo Keystore Notice

This repo includes a `keystore.p12` file for demo purposes only.  
**Do not reuse this keystore in production environments.**



---

## License

MIT License

Copyright (c) 2025 Tariq Sekhri

Permission is hereby granted, free of charge, to any person obtaining a copy  
of this software and associated documentation files (the "Software"), to deal  
in the Software without restriction, including without limitation the rights  
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell  
copies of the Software, and to permit persons to whom the Software is  
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all  
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR  
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,  
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE  
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER  
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,  
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE  
SOFTWARE.
