# **Online Bookstore**

### 1.SetUp

| **Framework**    | **Data Persistence** | Java    | API Doc       |
| :--------------- | -------------------- | ------- | ------------- |
| SpringBoot 3.3.4 | H2                   | 11.0.24 | OpenAPI 2.0.2 |

[GitHub repository]: https://github.com/Vihenne618/book_store.git
[API Documentation]: http://127.0.0.1:8080/swagger-ui/index.html#/

### 2.Brief

This online book shop is built on SpringBoot , using H2 database to store data , and integrated with OpenApI to generate interface documents . At the same time , while using Junit to test the implementation of the API interface .

Due to the relatively simple requirements, in the system only defined books, shopping carts, shopping cart items table, to achieve a simple data management operations and shopping cart to add the purchase operation. If further development, you can consider adding book types, customers, orders and other tables to achieve more complex business.

### 3.API Description

- ##### BooksManagementController

  - Add a book
    - url: /book/management/add
    - POST
  - Remove a book
    - url: /book/management/remove
    - GET
  - List the book
    - url: /book/management/list
    - POST

- ##### ShoppingCartController

  - Add a book to shoppingcart
    - url: /shopping/cart/add
    - POST
  - remove a book from shoppingcart
    - url: /shopping/cart/remove
    - POST
  - show the shoppingcart
    - url: /shopping/cart/show
    - GET

- ##### CheckoutController

  - check out the shoppingcart
    - url: /checkout/opera
    - GET

### 4.Test

I write 3 integration test classes to test the above 3 API endpoints:

- BooksManagementTest
- ShoppingCartTest
- CheckoutTest

The above interfaces can be tested step-by-step by running the test method in these test classes directly.