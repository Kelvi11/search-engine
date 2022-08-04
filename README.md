# search-engine

This application is simple search engine console application for storing and retriving documents based on the tokens associated with a document.


### Building the application

mvn package

### Running the application
java -jar target/search-engine-0.0.1-SNAPSHOT.jar

### Commands examples

<p>

**index:**

*Example 1*

**Input:** <br>
index 4 fish eggs bacon soup potato

**Output:**<br>
index ok 4

*Example 2*

**Input:** <br>
index K fish eggs bacon soup potato

**Output:**<br>
index error Doc id should be an integer!

</p>

<p>

**query:**

*Example 3*

**Input:** <br> 
query (soup & fish) | eggs

**Output:**<br>
query results 2 3

*Example 4*

**Input:** <br>
query (soup & fish) & airplane

**Output:**<br>
query error No results found.

</p>