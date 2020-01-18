# DS_project_election_us
Election in the USA – Distributed Systems Project

Our system guarantees the following:

Atomicity – If a client made a request it will either fail or succeed, there is no in-between.

Fault tolerance – For each service we provide a fault tolerance environment, and each cluster can determine the amount of failures it can recover from based on the scale the service we need. 

Linearizability – Each state shard will have the same view of the vote count in his state.

Reliability – Once a client has vote and it succeeded it will persist from that time forward, or until he overwrites his vote.
This is guaranteed only if the fault tolerance limit hasn’t exceeded.

Timeliness – The client view on the system is guaranteed to be up to date each time he checks for the election results.

High consistency – We create a replica of each user vote on each of the state nodes, and we don’t return a reply to the client until all the state cluster have the most recent vote.
	
The following is a high level diagram of our system:

![alt text](https://github.com/Duckilicious/DS_project_election_us/blob/master/DS_project_documentation/HighLevenDesign.png)

Further documentation can be found in the repo.
