------------------------------------------------------------------------
This is the project README file
------------------------------------------------------------------------

PROJECT TITLE:	Document Sharing Network
VERSION:	Milestone #3 2015/11/23
AUTHORS:	Hamza Arab	100888409,
		Karim Hersi	100865348,
		Abdallah Saket	100896072,
		Atif Siddiqui	100770386

USER INSTRUCTIONS:
1.Run  the  main in the GUI Class.
2.Enter the number of Consumers.
3.Enter the number of Producers.
4.Enter the number of Search Results to return.
5.Select the Like/Follow Strategy for the Producer.

The GUI displays some relevant information/statistics about the network. The Activity Log breaks down each 
individual Execution Cycle. The Network Statistics display shows all the users in the network and displays a list 
of their followers. It pauses after EACH cycle and allows for the user to either perform another cycle or to do
nothing / quit.

The user only enters the number of the Cosumers and Producers and not their taste. The taste is randomly 
generated. This was done to make testing/simulating the network less time consuming and tedious. Once the GUI is 
further developed, users will manually enter the attributes of consumers/producers.

After performing many cycles, the evolution of the network can be seen over time via the displayed Graph. The 
Documents increase and they gain more Likes but the Average Number of Likes per Document does not increase. The 
users begin to interact/follow each other over time. The payoff of each cycle represents how effective the search 
results were for the cycle.

Since Documents only have tags instead of unique titles, Documents that share the same tag are in differentiable. 
This is likely to occur in the Simulation because a Producer produces a Document with its tag during the act 
method. Documents have a unique identifier integer variable but was not used on the GUI.

Note: Getters and Setters were not commented because they are self explanatory. Also, Javadoc styled comments 
were avoided in methods that had parameters/returns that were self explanatory. Instead, the method's function 
was explained.


Team Contribuions:
Design Decisions:		Hamza Arab	100888409
				Atif Siddiqui	100770386
User Manual:			Hamza Arab	100888409
				Atif Siddiqui	100770386
README:				Karim Hersi	100865348
				Abdallah Saket	100896072
UML Design:			Karim Hersi	100865348
				Abdallah Saket	100896072
SimulationData Class:		Hamza Arab	100888409
				Abdallah Saket	100896072
Ranking_Strategies Class:	Karim Hersi	100865348
				Atif Siddiqui	100770386
Producer_Strategy_A Class:	Karim Hersi	100865348
				Abdallah Saket	100896072
Producer_Strategy_B Class:	Karim Hersi	100865348
				Abdallah Saket	100896072
JUnit Testing:			Hamza Arab	100888409
				Abdallah Saket	100896072


All group members were present and contributed to the design and UML diagram which included classes, variables, 
and method signatures. Only the implementation was delegated between the group members.