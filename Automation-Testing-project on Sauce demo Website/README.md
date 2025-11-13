SauceDemo Automation Project
---
📌 Project Overview

This project is a full automation suite for the SauceDemo
 website using Java, Selenium WebDriver, and Maven.
It tests key functionalities like login, add to cart, checkout flows, sorting, footer links, and form submissions.
__________________________________________________________________________________________________________________________________
🗂 Project Structure
AutomationProject/
├── src/
│   ├── main/java/
│   │   └── org/example/
│   │       └── Main.java
│   └── test/java/
│       ├── AddtoCartModule.java
│       ├── AboutModule.java
│       ├── CheckoutModule.java
│       ├── FooterModule.java
│       ├── FullSiteTestModule.java
│       ├── LoginModule.java
│       ├── LogoutModule.java
│       ├── RemoveModule.java
│       ├── ResetAppStateModule.java
│       └── SortBoxModule.java
├── pom.xml
└── .gitignore
______________________________________________________________________________________________________________________________
🔹 Modules Description
1. LoginModule
Handles login to SauceDemo using standard user credentials.

2. AddtoCartModule

Adds multiple products to the cart, navigates between product pages, and verifies additions.

3. CheckoutModule

Covers checkout flows including:

Missing postal code

Missing first or last name

Successful checkout

4. SortBoxModule

Tests the product sorting feature (A-Z, Z-A, Price low-to-high, Price high-to-low).

5. FooterModule

Tests footer links to social media (Twitter, Facebook, LinkedIn) in new browser tabs.

6. AboutModule

Fills and submits the "Request Demo" form on SauceLabs site.


7. LogoutModule

Logs out the user from the site.

8. RemoveModule

Removes items from the shopping cart.

9. ResetAppStateModule

Resets the state of the app to ensure a clean test environment.

10. FullSiteTestModule

Runs end-to-end scenario covering login, add to cart, remove products, sort products, checkout, footer links, form submission, reset app state, and logout.


__________________________________________________________________________________________________________________________________________

📦 Dependencies

Selenium Java

WebDriverManager

TestNG (optional)

Dependencies are managed via pom.xml.
___________________________________________________________________________________________________________________________________________________
💡 Notes

Tests currently use Thread.sleep() for simplicity. Consider using explicit waits for production-level tests.

Ensure Edge version matches EdgeDriver.

Test data (user credentials, emails, names) can be updated as needed.
__________________________________________________________________________________________________________________________________________________________
👩‍💻 Author

Menna Allah Tarek | Jasmine Marey | Nermeen Mohammed | Asmaa Shahin
