JAVA Coding Challenge
Your task is to create a Spring Boot applica3on with APIs and tables for the products screen in a retail applica3on. You need to implement the business logic as described in the challenge. You are free to define the necessary tables and proper3es for the Product en3ty. Use java version 8 or above. Below are the API requirements:
1. API to List Ac3ve Products:
   • Endpoint: GET /api/products
   • Descrip3on: Get the list of ac3ve products sorted by the latest first.
2. API to Search Products:
   • Endpoint: GET /api/products/search
   • Parameters: productName (op3onal), minPrice (op3onal), maxPrice (op3onal),
   minPostedDate (op3onal), maxPostedDate (op3onal)
   • Descrip3on: Search for products based on the given criteria (product name, price range,
   and posted date range).
3. API to Create a Product:
   • Endpoint: POST /api/products
   • Request Body: Product object (name, price, status)
   • Descrip3on: Create a new product, but the price must not exceed $10,000. If the price is
   more than $5,000, the product should be pushed to the approval queue.
4. API to Update a Product:
   • Endpoint: PUT /api/products/{productId}
   • Request Body: Product object (name, price, status)
   • Descrip3on: Update an exis3ng product, but if the price is more than 50% of its previous
   price, the product should be pushed to the approval queue.
5. API to Delete a Product:
   • Endpoint: DELETE /api/products/{productId}
   • Descrip3on: Delete a product, and it should be pushed to the approval queue.
6. API to Get Products in Approval Queue:
   • Endpoint: GET /api/products/approval-queue
   • Descrip3on: Get all the products in the approval queue, sorted by request date (earliest
   first).
7. API to Approve a Product:
   • Endpoint: PUT /api/products/approval-queue/{approvalId}/approve

• Descrip3on: Approve a product from the approval queue. The product state should be updated, and it should no longer appear in the approval queue.
8. API to Reject a Product:
   • Endpoint: PUT /api/products/approval-queue/{approvalId}/reject
   • Descrip3on: Reject a product from the approval queue. The product state should remain
   the same, and it should no longer appear in the approval queue.
   Note: You need to design the database tables for the Product en3ty and the Approval Queue en3ty to support the above func3onali3es. You can use your preferred database (e.g., MySQL, PostgreSQL) and define appropriate data types and lengths for the proper3es.
   Please implement the Spring Boot applica3on with the above APIs and database tables according to the provided requirements. Remember to handle appropriate error responses and valida3ons.
