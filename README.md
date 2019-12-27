# hackerrank
Solutions of problems in Hackerrank that I consider special mosty due to a faster solution than proposed in the portal.

# Almost Sorted Intervals
https://www.hackerrank.com/challenges/almost-sorted-interval/problem

Short story: you got a permutation of 1..N. Find number of interval, where they start with the lowest and end with the highest value of its contents.

I use a dynamic programming approach and maintain a data structure to decide how a next number from input contributes to the result. My solution works in O(N) time.

Example input:

| 1 | 3 | 2 | 5 | 4 |
| --- | --- | --- | --- | --- |

Almost sorted intervals: 1; 3; 2; 5; 4; 1,3; 1,3,2,5; 2,5.

| Input | Data (*=new)   | New sequences  | Comments
| ----- | -------------- | -------------- | --------
| 1     | 1->1           | +1             | A new value creates a new sequence
| 3     | 3*->1,3        | +2             | Top value of all values so far lenghtens the existing sequence(s) and creates a new one. 3 can be an end of the sequences starting with 1 and 3.
| 2     | 3->1; 2*->2    | +1             | A value lower than the top erases all potential starting values which are above
| 5     | 5*->1,2,5      | +3             | Another top value merges all lower lists
| 4     | 5->1,2; 4*->4  | +1             | Another value is a new starting point, but 5 can't be a starting value anymore

Observations:
* We always have:
** a raising sequence of starting values 
** a descending sequence of ending values
* Each number arrives im the data structure once as a starting value, once as an ending value. It can get removed only once from each of those roles.
