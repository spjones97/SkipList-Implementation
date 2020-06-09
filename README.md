# Overview
You will write code that implements and exercises a skip list. The implementation will be similar to the approach we discussed in class, where a node in the list is an object with a data field and then an array of links (node object pointers) to connect it other nodes in the various levels.

To be a bit more space efficient, we will make the array of links on each cell not contain the max (like, say, 20) but rather be allocated to some number between 1 and the max by calling the constructor with an integer parameter. When we add a new cell (on insert) we will roll the dice and be told that the new node will have a specific level... say, a level 4 node. We then call the node constructor and pass 4 as a parameter; the constructor will allocate inside the node an array of links that has 4 elements. See the interfaces following for more details.

# Grading Notes
In the beginning, there is one initial node called root. This is a sentinel, with all the forward pointers of the root node next array null. The double data is Double.NaN, meaning unimportant because it is not used... this sentinel root node is not a data node in the list. Its function is to be a place for all the levels of the list can begin and the pointers to the first data node in each level can be easily found and accessed.

When a new node is inserted, it needs to be placed after the sentinel, and hooked into the necessary levels for the added node. A right place for a newly-created node should be searched with forward pointers starting from the root node. When creating a node for insertion, it should get a level along with double data. The level should be determined writing a flipping loop using the flip() method. The maximum level of a node in skiplist is pre-defined as 30 in class SkipList. The sentinel has this many levels in its link array.

For removing, the target node to be deleted should be first searched with forward pointers starting from the root node, same as insertion. Forward pointers of relevant nodes need to be adjusted correctly and the size of skiplist is decreased by one if remove succeed.

# Implementation Notes
You may wish to have two "temp node" allocated in your list so you can keep track of where you are at the various levels as you wander around in the skip list.
