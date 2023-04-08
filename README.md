
# Assignment A2: Mesh Generator

-   Author #1 [walmsj1@mcmaster.ca] (A2 onward)
-   Author #2 [gaudem5@mcmaster.ca] (A2-A3)
-   Author #3 [morrig14@mcmaster.ca] (A2-A3)

## How to run the product
This program requires an internet connection to collect the training data for the city name generation(`MarkovNameGenerator.java`).
First, create the jarfiles (from the root directory)
`mvn install`

Here's the recommended settings for creating a mesh of appropriate size and an island on that mesh (recommended, feel free to change parameters as in `--help`).
`java -jar generator/generator.jar -o sample.mesh -h 2000 -k irregular -p 1200 -r 10 -w 2000`
This will create a mesh file called `sample.mesh` with enough polygons to show the island properly.

To turn this mesh into an island, use the following (recommended because I think it's pretty, feel free to change parameters as in `--help`).
`java -jar island/island.jar -i sample.mesh -o island.mesh -a linear -e volcano -l 3 -q 3 -r 8 -s circle -m normal -b tundra -c 6`

To visualize this island, use the following command.
`java -jar visualizer/visualizer.jar -i island.mesh -o island.svg`

Open `island.svg` in a picture viewer.

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
usage: java -jar generator.jar
 -d         activate DEMO mode
 -h <arg>   Heigth of the Mesh
 -help      print help message
 -k <arg>   Kind: grid or irregular
 -o <arg>   Output file name
 -p <arg>   Numbers of polygons (if irregular mesh
 -r <arg>   Relaxation coefficient
 -s <arg>   Size of squares (if grid mesh)
 -w <arg>   Width of the Mesh
```

### Visualizer

To visualize an existing mesh, go the the `visualizer` directory, and use `java -jar` to run the product. The product take three arguments: the file containing the mesh, the name of the file to store the visualization (as an SVG image) and whether it is in debug mode (-X for debug mode).

```
mosser@azrael A2 % cd visualizer
mosser@azrael visualizer % java -jar visualizer.jar -i ../generator/sample.mesh -o sample.svg
mosser@azrael visualizer % java -jar visualizer.jar -i ../island/island.mesh -o sample.svg

... (lots of debug information printed to stdout) ...

mosser@azrael visualizer % ls -lh sample.svg
-rw-r--r--  1 mosser  staff    56K 29 Jan 10:53 sample.svg
mosser@azrael visualizer %
```

To viualize the SVG file:

-   Open it with a web browser
-   Convert it into something else with tool slike `rsvg-convert`

### Island
You must have an existing generated mesh to create the island.
To turn an existing mesh into an island mesh, use the following command from the root directory
`java -jar island/island.jar -i [input.mesh file] -o [output.mesh file]`
Here's an example (these settings look pretty nice)
`java -jar island/isand.jar -i sample.mesh -o island.mesh -a linear -e volcano -l 3 -q 3 -r 8 -s circle -m normal -b tundra -c 6`
For help, see `java -jar island/island.jar --help`

```
usage: Island [OPTIONS]
 -a,--absorption <arg>   Soil absorption profile (linear, expoential),
                         default linear
 -b,--biome <arg>        Whittaker Biome (grassland, tundra, desert),
                         default grassland
 -c,--cities <arg>       Number of cities, default 3
 -d,--seed <arg>         Generation Seed, default system time
 -e,--elevation <arg>    Elevation Land Type, default volcano
 -h,--heatmap <arg>      Elevation Heatmap "e", Moisture Heatmap "m" or
                         normal island "i", default i
 -i,--input <arg>        Input mesh file
 -l,--lakes <arg>        Maximum number of Lakes, default 2
 -m,--mode <arg>         Generation mode (lagoon, normal), default normal
 -o,--output <arg>       Output mesh file
 -q,--aquifers <arg>     Maximum number of aquifers, default 3
 -r,--rivers <arg>       Maximum number of rivers, default 5
 -s,--shape <arg>        Island Shape (circle, square, triangle), default
 ```

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

| Id  | Feature title                                                               | Who?            | Start      | End        | Status |
| :-: | --------------------------------------------------------------------------- | --------------- | ---------- | ---------- | ------ |
| F21 | Create island sub-project with maven setup                                  | Jack Walmsley   | 2023-03-08 | 2023-03-08 | D      |
| F22 | Define land types (water, beach, lagoon and land)                           | Jack Walmsley   | 2023-03-08 | 2023-03-08 | D      |
| F23 | Determine land type of polygon from function.                               | Graham Morrsion | 2023-03-12 | 2023-03-13 | D      |
| F24 | Colour in land types.                                                       | Jack Walmsley   | 2023-03-08 | 2023-03-08 | D      |
| F25 | Implement shape generator interface and implement circle                    | Jack Walmsley   | 2023-03-08 | 2023-03-09 | D      |
| F26 | Implement other shapes such as square                                       | Jack Walmsley   | 2023-03-10 | 2023-03-11 | D      |
| F27 | Implement selection of the shapes from the command line arguments           | Graham Morrison | 2023-03-12 | 2023-03-24 | D      |
| F28 | Implement elevation generator interface and implement volcano               | Graham Morrison | 2023-03-24 | 2023-03-24 | D      |
| F29 | Implement heat map to verify elevation                                      | Mathieu Gaudet  | 2023-03-23 | 2023-03-23 | D      |
| F30 | Implement other land types such as mountain ridge                           | Graham Morrison | 2023-03-24 | 2023-03-24 | D      |
| F31 | Implement selection of the land types from the command line arguments       | Graham Morrison | 2023-03-24 | 2023-03-24 | D      |
| F32 | Place x random one tile lakes on land tiles                                 | Mathieu Gaudet  | 2023-03-22 | 2023-03-22 | D      |
| F33 | Implement lakes larger than one tile                                        | Mathieu Gaudet  | 2023-03-22 | 2023-03-23 | D      |
| F34 | Implement user inputted number of lakes from the command line arguments     | Mathieu Gaudet  | 2023-03-23 | 2023-03-23 | D      |
| F35 | Implement randomly placed rivers that flow downhill to water or new lake    | Jack Walmsley   | 2023-03-24 | 2023-03-25 | D      |
| F36 | Line segment width randomly generated                                       | Jack Walmsley   | 2023-03-25 | 2023-03-25 | D      |
| F37 | River flow: connecting ones add their widths                                | Jack Walmsley   | 2023-03-25 | 2023-03-25 | D      |
| F38 | Implement user inputted number of rivers from the command line arguments    | Jack Walmsley   | 2023-03-25 | 2023-03-25 | D      |
| F39 | Generate random aquifers that increase humidity to surrounding areas.       | Graham Morrison | 2023-03-24 | 2023-03-23 | D      |
| F41 | Implement moisture for lakes.                                               | Mathieu Gaudet  | 2023-03-23 | 2023-03-23 | D      |
| F42 | Implement moisture for rivers.                                              | Jack Walmsley   | 2023-03-25 | 2023-03-25 | D      |
| F43 | Assign biome based on humidity and elevation. Assign Colour based on biome. | Mathieu Gaudet  | 2023-03-25 | 2023-03-25 | D      |
| F44 | Decide what biomes are available (Whittaker diagram)                        | Mathieu Gaudet  | 2023-03-25 | 2023-03-25 | D      |
| F45 | Implement selection of climate (Whittaker diagram) from the CLI             | Mathieu Gaudet  | 2023-03-25 | 2023-03-25 | D      |
| F46 | Add a seed to all of the randomness to be able to reproduce the island      | Jack Walmsley   | 2023-03-24 | 2023-03-24 | D      |
| F47 | Implement ability to specify a seed from the command line arguments         | Graham Morrison | 2023-03-25 | 2023-03-25 | D      |
| F48 | Create an Island builder to do everything to create the board in one place  | Mathieu Gaudet  | 2023-03-23 | 2023-03-23 | D      |
| F49 | Implement user choice for output of map (heatmap or not)                    | Mathieu Gaudet  | 2023-03-23 | 2023-03-23 | D      |
| F50 | Soil absorption profile system                                              | Jack Walmsley   | 2023-03-25 | 2023-03-25 | D      |
| F51 | Soil absorption profile - linear                                            | Jack Walmsley   | 2023-03-25 | 2023-03-25 | D      |
| F52 | Soil absorption profile - exponential                                       | Jack Walmsley   | 2023-03-25 | 2023-03-25 | D      |
| F53 | CLI choice between lagoon demo and normal operation                         | Jack Walmsley   | 2023-03-25 | 2023-03-25 | D      |
