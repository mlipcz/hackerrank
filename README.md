# hackerrank
Solutions of problems in Hackerrank that I consider special mosty due to a faster solution than proposed in the portal.

# Almost Sorted Intervals
https://www.hackerrank.com/challenges/almost-sorted-interval/problem

I use a dynamic programming approach and maintain a data structure to decide how a next number from input contributes to the result.

Example input:

|1|5|2|4|3|

1: 1->1; +1
5: 5->1,5; +2
2: 5->1; 2->2; +1


