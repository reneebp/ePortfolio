how can a user build and test your program (also called the user guide); 
    Compilation without file: 
        1. Enter rpalmer_eportfolio directory 
        2. Enter javac ePortfolio/*.java into the command line
        3. Enter java ePortfolio.Investment 
    Compilation with file: 
        1-2. Same process
        3. Enter java ePortfolio.Investment <filename.txt>
	- make sure file is in rpalmer_eportfolio folder and not ePortfolio folder
	- output file will appear in ePortfolio folder
    Using ePortfolio:
        - Upon first running the welcome window will appear. The menu bar will be at top of the page, which has all the commands in the program.
	    If you are running with no file added you will initially have no investments stored in the program and will first need to buy an
	    investment(s) in order to make use of the remaining commands.	   		  			   	       		
        - After adding in your desired amount of investments the user can buy, sell, update, see gains, and search as much as they like
        - To exit program, the user can either press the red button at the top left of the screen or select "Exit ePortfolio" from the menubar
The program was tested using the following examples:
    Buying Stock - testing Buy button:
    - Stock symbol = APPL
    - Stock name = Apple
    - Quantity = 500
    - Price = 110.08
    - Press "Buy" button
    - Buy window will then terminate and open new window with following message: "You now own <quantity> shares of <symbol> at $<price>"
        - should equal = $55,049.99

    Buying Stock - testing Reset button:
    - Stock symbol = APPL
    - Stock name = Apple
    - Quantity = 500
    - Price = 110.08
    - Press "Reset" button
    - All text fields will be cleared

    Selling Stock - testing Sell button:
    - Stock symbol = APPL
    - Stock name = Apple
    - Quantity = 200
    - Price = 142.23
    - Press "Sell" button
    - Sell window will then terminate and open new window with following message: "You now own <quantity> shares of <symbol> at $<price>"
        - Quantity will be now be 300 and price will now be 142.23

    Selling Stock - testing Reset button:
    - Stock symbol = APPL
    - Stock name = Apple
    - Quantity = 200
    - Price = 142.23
    - Press "Reset" button
    - All text fields will be cleared
    
    Updating stock - testing Update button:
    - Assume I have 3 investments stored in ePortfolio and APPL is the 2nd investment.  
    - Stock symbol = APPL, name = Apple, quantity = 300, price = 142.23
    - enter $152.23 
    - new price updated

    getGain - testing Update button:
    - Textfield on right side of window will display total gains from all investments and a new window will list all investments 

    Search - testing Update button:
    - Enter in fields (symbol, name/keywords, price range)
    - new window will appear with investment, if found
    

Note: Same testing process and inputs for mutual funds 
