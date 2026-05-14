# Aid-Distribution-Management-System
A JavaFX desktop application for managing humanitarian aid distribution. Built using object-oriented design principles with a clean GUI that supports full CRUD operations, statistical reporting, and data persistence.

## Features

- Register and manage beneficiaries (Families and Individuals) across served cities  
- Add and categorize aid items (Food Package, Medical Kit, Winter Bag, Emergency Kit)  
- Record aid distribution events with date tracking  
- Generate statistical reports (most served city, items by category, beneficiaries between two dates, etc.)  
- Save and load data using text files and Java object serialization (.bin)  
- Interactive dashboard with live summary statistics  

## Tech Stack

- Language: Java  
- UI Framework: JavaFX  
- Data Persistence: Text files (custom format) + Java Object Serialization (.bin)  
- Design Principles: OOP (Inheritance, Abstraction, Interfaces, Custom Exceptions)  
- IDE: IntelliJ IDEA  
- UI Framework: JavaFX  
- UI Design: Java-based implementation 

## Architecture

The system follows a layered structure:

- Model layer: `AidItem`, `Beneficiary`, `DistributionEvent`  
- Controller/Manager: `AidManager` handles all business logic  
- UI layer: Each screen is a separate controller class  
- Main window connects everything via a menu-based navigation system  

## Screenshots
[HomeScreen](homeScreen.png)
[Beneficiary](Beneficiary.png)
[Report](report.png)
[file](file.png)
[aiditem](aiditem.png)
[aidDistribution](aidDistribution.png)
