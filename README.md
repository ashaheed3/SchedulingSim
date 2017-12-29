
# SchedulingSim
## Process Scheduling Algorithm Simulator
Program simulates the following process scheduling algorithms:

* Round Robin
* First Come First Serve
* Shortest Job First
* Priority First
----
## Usage
From the command line, program takes in the input file name as one of the arguments
The input file will be a CSV file in which the columns contain information about
process priority, submision time, CPU burst time, and IO burst time. The number
of rows in the file is the number of process profiles.

## Installation
1. Download project folder
2. Open terminal
3. Change directory to folder that contains source file
4. Compile source file and class file
`javac OSProg3.java`
`javac Process.java`
5. Run
 `java OSprog3 example.csv` 
