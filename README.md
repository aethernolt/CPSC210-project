# Grocery Price Targeter
Particularly given the rising cost of living, one of the parts I find annoying about grocery shopping is having to determine if certain items are worth buying in the moment at one store or if I should wait and purchase them elsewhere. Having a centralized database of acceptable prices for each item to compare current shelf prices and deals to would make this process far simpler than trying to recall how much I typically pay. Such a system could be helpful to anyone trying to optimize spending on groceries.

## Functionality
The primary purpose of this project (built for CPSC 210 at UBC) is to allow users to add **grocery items** and their **target prices** for later reference. Users should be able to not only view prices for *specific* grocery items, but add existing grocery items to **categories** and view prices across the entire category. Users should also be able to provide the price of a grocery item and have the program show its relation to the target price.

## User Stories:
- As a user, I want to be able to add a grocery item with a name and target price
- As a user, I want to be able to edit the target price for a grocery item
- As a user, I want to be able to add a category of grocery items
- As a user, I want to be able to select a category and add a grocery item to the category
- As a user, I want to be able to view the target price for a specified grocery item
- As a user, I want to be able to view the target prices for a category of grocery items
- As a user, I want to be able to provide a price for a grocery item and be informed if it is higher than, lower than, or equal to my target price
- As a user, I want to be able to save my grocery and category data from a file
- As a user, I want to be able to load my grocery and category data to a file
- As a user, I want to be asked if I'd like to save my data when I quit the application

## Instructions for End User:

- You can generate the first required action related to the user story "adding multiple Xs to a Y" by clicking the "Add Grocery to Category" button to add a specified grocery to a specified category
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by clicking the "View Groceries in a Category" button to list all groceries and their prices in a specified category
- You can locate my visual component by adding either a grocery or category item successfully
- You can save the state of my application by clicking the save button or clicking yes when quitting and being prompted for having unsaved data
- You can reload the state of my application by clicking the load button