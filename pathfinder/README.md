# Pathfinding
Author: Jack Walmsley (walmsj1@mcmaster.ca)

## Rationale
This library provides a set of services for representing and finding shortest paths in graphs.

## Extending the Library
To create a new pathfinding algorithm, make a class that implements the `Pathfinder` interface. This must implement the `findShortestPath` method and return a `List` of the shortest path from `end` at index 0 to `start` at the max index.

