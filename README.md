# knight-shortest-path-app

How does the web app work? 

The server-side part of the project is handled in the file, index.js (in routes foler), which listens on port 3000 and displays the view in index.ejs.
The file index.ejs (in views folder), which has the structure of an HTML file but allows for embedded JavaScript code, has the chess board and a button that the user can press to make the server call. index.ejs has another JavaScript file
functions.js (in public->javascripts folder) that has several helper functions responsible for modifying components of the view. 

Implementation of shortest path algorithm 

I adapted the algorithm that computes the minimum number of steps to reach a target cell using a knight to instead create a list of the coordinates that represent the shortest path. I implemented this algorithm in Java in a Java servlet (ShortestPath.java), but I wasn't quite able to get it to work with the server call made by my web application. 
