# Program:      Car-voting App 
- Descrption:   A Java desktop application used to vote the best car of the year
- Started:      08.10.24
- Updated:      20.10.24
- Status:       *COMPLETE*
- Version:      beta
- Developers
    + Ammaar 
    + Siyambuka
    + Joshua
---


### Setting up the application (using Apache Netbeans and Apache Derby)
1. Download the complete package 
    - download the zipped folder OR
    - use one of the following commands in the command-line
    ```
    git clone <repo URL>
    git clone --branch <branch name> <repo URL>
    ```
2. Set up the database connection in Netbeans

3. Run the server project

4. Run the client project
---

### About the application
- This is a desktop application
- It was created using Java, Maven, Java Swing and Apache Derby
- It is a client-server application, and uses sockets to communicate between the client and the local server

- The app has 2 basic functionalities
1. Adding a vote for a specific car (updating a record)
2. Adding a new car to vote for (adding a record)
---

### Using the application
#### Adding a vote
- Select a car from the dropdown (comboxbox)
- Click 'Vote' to add a new vote for that selected car
- _notice the table will update once you added your vote_

#### Adding a new car to vote for
+ Click 'Add car'. A small window will appear
+ Add the name of the car in the inputfield, and click 'Add Car' (An error will occur if the car already exists)
+ _If successful, the new car will be added to the combobox and the table_	
---

### Extra
Use the following git command to use the GUI for git
```
gitk
```
---

