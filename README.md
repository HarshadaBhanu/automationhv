# automationhv
Automation practice on mac for login
Sogeti Automation Task ReadMe
The purpose of this doc is to give an overview of solution to the automation task assigned.
Description
This code is written to achieve maximum testing automation as possible for the test cases provided as Automation Task in seven days span.
Automation Task - https://drive.google.com/drive/folders/1r-Pzw2kFf3VXpDpzhNMl0Yh0N88zpWdx?usp=sharing
It comprised of most of all the concepts used in selenium.
Unfinished parts - 
	1. While automating a Test Case 2, encounter a test step which cannot be 				Automated.  Automation the earlier Test Step is done and the rest of the part where 		test script had to pass a reCAPTCHA is remaining as it cannot pass through without 	human intervention as far as tried.
	A possible workaround for it would be we bypass this step when system is under 			test.
	2.Last Test Case’s (Test Case2) for API last point, where content type is JSON and 		Verify in Response - "Place Name" for each input "Country" and "Postal 				Code”.Was to be done. The response code is html and unable to parse the 			data.Possibly more study will lead to a solution which could be existing.
Few common points like 
Working on few points like creating a testNG.xml
Working on adding a man file so execution can be done via command prompt
Creating Jeinkins job

Getting Started
Dependencies
Describe any prerequisites, libraries, OS version, etc., needed before installing program.
Selenium (with TestNG, Maven)
Eclipse
Github
Chrome Driver in user.dir (to be added and path updated in base class)

Installing
How/where to download your program

	From GitHub Public repository https://github.com/HarshadaBhanu/automationhv/	


Executing program
How to run the program? >> Execute the test cases as TestNG.xml from the program

Authors
Contributors names and contact info
Harshada Bhanu harshadabhanu@gmail.com

Version History
0.1
Initial Release

License
NA
Acknowledgments Inspiration, Help code snippets, etc.
Google
Stackoverflow
GitHub
Selenium
Selenium for sure classes notes
Test Projects
