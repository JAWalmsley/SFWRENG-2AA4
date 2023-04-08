# Pathfinding
Author: Jack Walmsley (walmsj1@mcmaster.ca)

## Rationale
This library provides a set of services for representing and finding shortest paths in graphs. Currently, only Djikstra's algorithm is implemented, but more can eaily be added (see below). The graph ADT can be used for any purpose and is decoupled from the pathfinding algorithms.

## Extending the Library
To create a new pathfinding algorithm, make a class that implements the `Pathfinder` interface. This must implement the `findShortestPath` method and return a `List` of the shortest path from `end` at index 0 to `start` at the max index.

