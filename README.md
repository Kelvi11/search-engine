# search-engine

This application is simple search engine console application for storing and retriving documents based on the tokens associated with a document.


### Building the application

mvn package

### Running the application
java -jar target/search-engine-0.0.1-SNAPSHOT.jar

### Commands examples

<p>

**index:**

**Input:** <br>
index 4 fish eggs bacon soup potato

**Output:**<br>
index ok 4

</p>

<p>

**query:**

**Input:** <br> 
query (soup & fish) | eggs

**Output:**<br>
query results 2 3

</p>