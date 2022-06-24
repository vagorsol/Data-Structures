NAME:
=====
	Audrey Yang

Programs Files:
===============
    - QuickSort.java
    - Selection.java
    - SortBase.java
    - Times.xlsx
    
	
How to Compile:
===============
    - javac QuickSort.java
    - javac Selection.java
    - javac SortBase.java
       
How to Run:
===========
    - java QuickSort
	
Reflection:
===========
    I had difficulty when translating the pseudocode to code, especially when trying to understand how the second for-loop in insertion
    sort works, and when I was debugging as I kept referencing the pseudocode code too heavily, but once I got over that translation 
    struggle, the assignment was much less daunting. Most of my bugs arise from indexing errors, which I still struggle at noticing 
    at first. I think I am getting better at identifying where the indexing issue is coming from, but identifying how to fix it still
    takes quite some time.

Quick Sort Times Table:
=======================
    Array Size          Average Time (microseconds)
    1000                18.2149
    4000                40.8597
    16000               120.55
    64000               336.199
    256000              347.53
    1024000             1037.03

Hybrid Quicksort Times Table (N=30):
=============================
    Array Size          Average Time (microseconds)
    1000                26.2156
    4000                80.6383
    16000               200.856
    64000               640.84
    256000              11174.46
    1024000             3962.83

Time Analysis/Quicksort vs Hybrid Quicksort Explaination:
========================================================
    For my data, the reason why hybrid quicksort wasn't significantly faster than quicksort is most likely
    because I kept N as 30, when it probably could be greater so that the hybrid use of insertion sort within 
    quicksort would be more effective. 
    Generally, hybrid quicksort is faster than quicksort because for smaller arrays, quicksort has to track multiple
    recursions which takes a lot of bookkeeping and in practice, for smaller arrays, insertion sort is much faster as 
    it requires less tracking (and by extension, resources). Because insertion sort is only utilitized for sorting a 
    small portion of an array (and is much faster at sorting it than quicksort), it does not cause hybrid sort to have
    an Order(N^2) time.

I Worked With:
==============
    N/A

Approximate Hours worked:
=========================
    ~8.5 Hours

Special Instructions to the grader:
===================================
    N/A

Known Bugs or Limitations:
==========================
    N/A

Other comments:
===============
    - Full table of all of the times can be found in Times.xlsx
    - I am not fully sure if (nanoseconds)/100000 gives the time in microseconds