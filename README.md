# anc-101

**This is an evaluator of spreadsheet formulas**


## Build🔨

Please use Apache Ant ([https://ant.apache.org/](https://ant.apache.org/)) to build this project. 
*Dependencies:* Oracle JDK 1.5+ ([https://www.oracle.com/](https://www.oracle.com/technetwork/pt/java/javase/downloads/index.html)) .


**How to build**

Using the following command inside the project root folder will compile all sorce code.
```
ant -f build.xml
```
**Generated files**

* 📁 build: contains the compiled code 
* 📁 dist: contains the compiled code in jar format
* 📁 dist/javadoc/: contains the generated documentation of the source code



## Run ⚙

After build this project, you can run the tool in the following ways:

1. **Using the compiled code**: Acessing the build/classes folder
   
   `$ cat spreadsheet.txt | java anc101.Spreadsheet`
2. **Using the jar file**: Acessing the dist folder
   
   `$ cat spreadsheet.txt | java -jar "anc101.jar"`

3. **Using the executable script**: In the root folder
   
    `$ ./run.sh spreadsheet.txt"`

**Important** 

Consider `spreadsheet.txt` the filename of the spreadsheet file that you want to process. 

The **cat** command extracts all data of a given file in *nix based systems. In Windows, use **type** command instead.

To run the executable script on Windows, use the **run.bat** file.




