# RMI Java - Theater booking

## Description

This RMI (Remote Method Invocation) project simulates a theater booking application. A client can call the remote methods of the RMI server with the appropriate commands - actions shown below.

*This project was made during my Distributed Systems course in University.*

Available seats:

- 100 type ZA (Zone A) seats - 45 euro
- 200 type ZB (Zone B) seats - 35 euro
- 400 type ZC (Zone C) seats - 25 euro
- 225 type CE (Center) seats - 30 euro
- 75 type SI (Sides) seats - 20 euro

## How to run

1. ```javac *.java```
2. ```java THServer```
3. ```java THClient <arguments>```

**Client Actions:**

- ```java THClient list <hostname>``` ```//prints a list of available seats.```
- ```java THClient book <hostname> <type> <number> <name>``` ```//books a number of <type> seats, under the name <name>.```
- ```java THClient guests <hostname>``` ```//prints a list of clients that already booked seat(s).```
- ```java THClient cancel <hostname> <type> <number> <name>``` ```//cancels the respective seats made by book action.```

*```<hostname>``` can be localhost.*
