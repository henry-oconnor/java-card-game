Initializing SQL Database
-------------------------
1. Log into MySQL as root user with command prompt
2. Use command "source RUN-ME-FIRST.sql;" to run script

Finding JDBC Driver
-------------------
mysql-connector-java-5.1.45-bin.jar is included in the main project folder.
To link to it, right click the main project folder "TexasHoldem" in Netbeans, 
click "Resolve project problems", click "Resolve...", and provide the correct path.

Running game
--------------
1. Run server file alone. A dialog should pop up with the following text: 

 Running
 Connected to schema 'holdemdb'
 Server started at port 8000
 Waiting for players

If it doesn't connect, change port number by editing PORT_NUMBER in the interface HoldemConstants.

2. Run client file alone. You will have to use "Register" button to log in first, and will be able to log in 
with that username and password in future sessions. 

3. Click "Continue" button to progress through the game. Cards will be dealt and your score will be displayed.
Pressing "Continue" again will start a new game. Wheeeee!