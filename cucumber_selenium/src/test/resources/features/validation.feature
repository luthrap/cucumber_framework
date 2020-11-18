# Created by paras.luthra at 04/11/20
Feature: Sample Feature file
  A sample feature file for demo purpose

  Scenario: A sample login scenario

   Given I am on to STORE Page
   When I select Phones category
   Then I should see Samsung galaxy s6 product
   When I select Monitors category
   Then I should see ASUS Full HD product
   When I select Laptops category
   And I select Sony vaio i5 product
   And I add the product in the cart
   When I navigate to Home page
   And I select Laptops category
   And I select Dell i7 8gb product
   And I add the product in the cart
   When I navigate to Cart page
   And I delete Dell i7 8gb product
   Then the cart should not have Dell i7 8gb product
   When I get the total cart price
   And I click on Place Order
   And I purchase with following info
   |name|country|city |credit_card|month|year|
   |Test|USA    |Texas|123        |12   |2020|
   Then I verify the purchase price against cart price

