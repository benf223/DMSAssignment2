# DMSAssignment2
Second assignment for Distributed Mobile Systems

# Developed by
Ben Fisher ID: 15906291

# Features
- Simple shop application
- Need user account
- User registration
- Shop has items stored in a database

# Beans
- Enterprise Beans
	- Database management
	- Users cart management
- Java Beans
	- Shop items

# JSPs
- Home page with different buttons depending on state
- Login page to facilitate logging users in with relevant prompts
- Register page to facilitate registering users with relevant prompts
- Shop page to display items
- Checkout page to allow the user to 'purchase' items

# Servlets
- CartInterpreter to manage changes to the users cart
- HomeToShop to setup the arrival in the shop JSP
- UserManagement to process registrations and login

# Database
- Apache Derby database
- Stores shop items in one table
- Stores plain text user data in another table
- Database builds itself on first attempt to access the database

# Setup
- Create .war file and launch with glassfish
- Database will set itself up with some data

# Usage
- Need to log in to use the system (database is initialised with user: admin password: admin)
- Can create user by selecting register and entering unused details to be stored into the database
- Once logged in can click on the shop button
- In the shop can type the quantity of each item to purchase and submit
- The program will add as many to your cart as possible and calculate a total
- The checkout will be shown and the user can either clear the cart or purchase the items
- Adding 0 or fewer items to the cart is invalid and will result in an empty cart
