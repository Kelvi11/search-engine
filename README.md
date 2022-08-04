# search-engine

This application is simple search engine console application for storing and retriving documents based on the tokens associated with a document.


# Building the application

mvn package

# Running the application
java -jar target/search-engine-0.0.1-SNAPSHOT.jar

# Commands examples

Input:
index 4 fish eggs bacon soup potato

Output:
index ok 4

Input:
query (soup & fish) | eggs

Output:
query results 2 3
