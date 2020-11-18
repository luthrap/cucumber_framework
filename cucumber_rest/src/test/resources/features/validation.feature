# Created by paras.luthra at 04/11/20
Feature: Sample Feature file
  A sample feature file for demo purpose

  Scenario: A sample login scenario

   Given the application is running
   When I look for the pets with status as available
   Then I should be able to find all the Pets with following attributes
   |Id                    |Name    |
   |9222968140491062539   |doggie  |
   When I Post a new Pet from the src/test/resources/newpet.json file
   Then I should be able to find the Pet with the PetId 127
   When I update the pet from the src/test/resources/newpet.json file with the Status as sold
   Then I verify that the Pet with PetId 127 has the Status as sold
   When I delete the Pet with PetId 127
   Then I verify that the pet with PetId 127 does not exist
   And I clear the data
