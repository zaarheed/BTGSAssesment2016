# BTGSAssesment2016
###### BT Graduate Scheme Coding Test 2015/16

**Description:** For my BT Graduate scheme application, I was asked to solve a coding exercise. More details on the question (for future readers and browsers) will be posted after I have completed my application and gained permission from BT.

I have submitted my application through GitHub so the assessor can see the progress I have made in my code, and so that they can see how I may have initially approached the problem. In addition, the commits prior to submission contain debugging code which the assessor may want to use to test my code, or really get down-n-dirty with how my brains works!

A ReadMe, as requested, follows…


### README
This code has been written in Java and uses libraries which are all bundled within JDK 1.8.

#####The program can be compiled and run from Command Line as follows:
1. Download this repo as a .ZIP file and extract it to somewhere convenient
2. Navigate to your preferred Command Line application.
3. Navigate to the src folder (BTGSAssesment2016/src) from your Command Line application and then compile the ‘btassesment’ package by:
	```
	javac btassesment/*.java
	```
4. You can then run the program by entering the following to your Command Line:
	```
	java btassesment.BTAssesment packages.txt swingui awtui unknown
	```
Where ‘btassesment.BTAssesment’ is the name of the program (package name is required), ‘packages.txt’ is the package-dependencies file placed in the ‘src’ directory and ’swingui awtui unknown’ are arguments.


### Improvements
This code has lots of room for improvements, as outlined below:
- This task was set 4 days before I had back-to-back exams, so in order to deliver as quickly as possible, I approached it from a procedural perspective. I would have preferred something more modular and OOP
- When scanning Strings, the code should use RegEx to ensure it’s more universal and not hard-coded
- Haven’t handled the FileNotFound exception
- I feel there is a better way to print the output alphabetically
- The code is not very efficient. Many loops can be combined
- I have redeclared the same counter variable ‘i’ in nested functions. This may make it hard for others to follow the procedure of my program