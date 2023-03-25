# Assignment A2: Mesh Generator

-   Author #1 [walmsj1@mcmaster.ca]
-   Author #2 [gaudem5@mcmaster.ca]
-   Author #3 [morrig14@mcmaster.ca]

## How to run the product

_This section needs to be edited to reflect how the user can interact with thefeature released in your project_

### Installation instructions

This product is handled by Maven, as a multi-module project. We assume here that you have cloned the project in a directory named `A2`

To install the different tooling on your computer, simply run:

```
mosser@azrael A2 % mvn install
```

After installation, you'll find an application named `generator.jar` in the `generator` directory, and a file named `visualizer.jar` in the `visualizer` one.

### Generator

To run the generator, go to the `generator` directory, and use `java -jar` to run the product. The product takes up to 4 arguments. Whether it is a regular grid or an irregular mesh, the number of polygons in the mesh, the number of times the irregular mesh should be relaxed and the name of the file where the generated mesh will be stored as binary.

```
mosser@azrael A2 % cd generator
mosser@azrael generator % java -jar generator.jar sample.mesh
mosser@azrael generator % ls -lh sample.mesh
-rw-r--r--  1 mosser  staff    29K 29 Jan 10:52 sample.mesh
mosser@azrael generator %
```

```
usage: Generator [OPTIONS] grid/irregular
 -h,--help               Display help
 -n,--polygons <arg>     Number of Polygons
 -o,--fileName <arg>     Output File Name
 -r,--relaxation <arg>   Relaxation Level
```

### Visualizer

To visualize an existing mesh, go the the `visualizer` directory, and use `java -jar` to run the product. The product take three arguments: the file containing the mesh, the name of the file to store the visualization (as an SVG image) and whether it is in debug mode (-X for debug mode).

```
mosser@azrael A2 % cd visualizer
mosser@azrael visualizer % java -jar visualizer.jar ../generator/sample.mesh sample.svg
mosser@azrael visualizer % java -jar visualizer.jar ../island/island.mesh sample.svg

... (lots of debug information printed to stdout) ...

mosser@azrael visualizer % ls -lh sample.svg
-rw-r--r--  1 mosser  staff    56K 29 Jan 10:53 sample.svg
mosser@azrael visualizer %
```

To viualize the SVG file:

-   Open it with a web browser
-   Convert it into something else with tool slike `rsvg-convert`

### Island

## How to contribute to the project

When you develop features and enrich the product, remember that you have first to `package` (as in `mvn package`) it so that the `jar` file is re-generated by maven.

## Backlog

### Definition of Done

-- Insert here your definition of done for your features --

### Assignment 2 Backlog

| Id  | Feature title                                           | Who?            | Start      | End        | Status |
| :-: | ------------------------------------------------------- | --------------- | ---------- | ---------- | ------ |
| F01 | Draw segments between vertices to visualize the squares | Jack Walmsley   | 2023-02-01 | 2023-02-06 | D      |
| F02 | Mesh ADT                                                | All             | 2023-02-11 | 2023-02-11 | D      |
| F03 | Generating with Mesh ADT                                | Graham Morrison | 2023-02-11 | 2023-02-16 | D      |
| F03 | Visualizing with Mesh ADT                               | Jack Walmsley   | 2023-02-11 | 2023-02-16 | D      |
| F04 | Debug visualization mode                                | Mathieu Gaudet  | 2023-02-11 | 2023-02-22 | D      |
| F05 | Random points generation                                | Jack Walmsley   | 2023-02-19 | 2023-02-20 | D      |
| F06 | Voronoi diagram computation                             | Graham Morrison | 2023-02-26 | 2023-02-27 | D      |
| F07 | Lloyd relaxation                                        | Graham Morrison | 2023-02-26 | 2023-02-27 | D      |
| F08 | Mesh cropping to expected width and height              | Mathieu Gaudet  | 2023-02-27 | 2023-02-27 | D      |
| F09 | Delauney triangulation for neighbourhood relationships  | Jack Walmsley   | 2023-02-27 | 2023-02-27 | D      |
| F10 | Convex hull computation for consecutiveness of segments | Jack Walmsley   | 2023-02-27 | 2023-02-27 | D      |
| F11 | Reading options from command line                       | Mathieu Gaudet  | 2023-02-27 | 2023-02-27 | D      |
| F12 | Add colour to polygons in mesh                          | Mathieu Gaudet  | 2023-02-27 | 2023-02-27 | D      |

### Assignment 3 Backlog

| Id  | Feature title                                                               | Who?          | Start      | End        | Status |
| :-: | --------------------------------------------------------------------------- | ------------- | ---------- | ---------- | ------ |
| F21 | Create island sub-project with maven setup                                  | Jack Walmsley | 2023-03-08 | 2023-03-08 | D      |
| F22 | Define land types (water, beach, lagoon and land)                           | Jack Walmsley | 2023-03-08 | 2023-03-08 | D      |
| F23 | Determine land type of polygon from function.                               |Graham Morrsion| 2023-03-12 | 2023-03-12 | D      |
| F24 | Colour in land types.                                                       | Jack Walmsley | 2023-03-08 | 2023-03-08 | D      |
| F25 | Implement shape generator interface and implement circle                    | Jack Walmsley | 2023-03-08 | 2023-03-09 | D      |
| F26 | Implement other shapes such as square                                       | Jack Walmsley | 2023-03-10 | 2023-03-11 | D      |
| F27 | Implement selection of the shapes from the command line arguments           |Graham Morrison| 2023-03-12 | 2023-03-24 | D      |
| F28 | Implement elevation generator interface and implement volcano               |Graham Morrison| 2023-03-24 | 2023-03-24 | D      |
| F29 | Implement heat map to verify elevation                                      | Mathieu Gaudet| 2023-03-23 | 2023-03-24 | D      |
| F30 | Implement other land types such as mountain ridge                           |Graham Morrison| 2023-03-24 | 2023-03-24 | D      |
| F31 | Implement selection of the land types from the command line arguments       |Graham Morrison| 2023-03-24 | 2023-03-24 | D      |
| F32 | Place x random one tile lakes on land tiles                                 | Mathieu Gaudet| 2023-03-22 | 2023-03-22 | D      |
| F33 | Implement lakes larger than one tile                                        | Mathieu Gaudet| 2023-03-22 | 2023-03-23 | D      |
| F34 | Implement user inputted number of lakes from the command line arguments     | Mathieu Gaudet| 2023-03-23 | 2023-03-23 | D      |
| F35 | Implement randomly placed rivers that flow downhill to water or new lake    | Jack Walmsley |            |            | P |
| F36 | Line segment width randomly generated                                       | Jack Walmsley |            |            | B(F35) |
| F37 | River flow: connecting ones add their widths                                | Jack Walmsley |            |            | B(F35) |
| F38 | Implement user inputted number of rivers from the command line arguments    | Jack Walmsley |            |            | B(F35) |
| F39 | Generate random aquifers that increase humidity to surrounding areas.       |Graham Morrison| 2023-03-24 | 2023-03-23 | D      |
| F41 | Implement moisture for lakes.                                               | Mathieu Gaudet| 2023-03-23 | 2023-03-23 | D      |
| F42 | Implement moisture for rivers.                                              | Jack Walmsley |            |            | B(F37) |
| F43 | Assign biome based on humidity and elevation. Assign Colour based on biome. | Mathieu Gaudet|            |            | B(F42) |
| F44 | Decide what biomes are available (Whittaker diagram)                        | Mathieu Gaudet|            |            | B(F43) |
| F45 | Implement selection of climate (Whittaker diagram) from the CLI             | Mathieu Gaudet|            |            | B(F44) |
| F46 | Add a seed to all of the randomness to be able to reproduce the island      | Jack Walmsley |            |            | P      |
| F47 | Implement ability to specify a seed from the command line arguments         |Graham Morrison|            |            | B(F46) |
| F48 | Create an Island builder to do everything to create the board in one place  | Mathieu Gaudet| 2023-03-23 | 2023-03-23 | D      |
| F49 | Implement user choice for output of map (heatmap or not)		    | Mathieu Gaudet| 2023-03-23 | 2023-03-23 | D      |
