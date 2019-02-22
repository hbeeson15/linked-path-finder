package linkedpathfinder;

import java.io.*;
import java.util.*;

/*
This program takes in an adjacency matrix, converts it to a graph representation,
and finds all possible paths between all possible pairs of vertices. The input and output
files are passed in as command line arguments. The input file contains the adjacency 
matrix, which is read in and stored in a 2-D array. From there, a graph is
built from the matrix. This uses an array of vertices and a linked list of edges.
An edge is created when a given point on the matrix equals 1. The paths are found 
recursively. A boolean array keeps track of the vertices that have been visited.
The output (all paths found) is written to the output file passed in as a command
line argument. 
*/

public class LinkedPathFinder {

    public static void main(String[] args) {
        //check if input and output files are provided as command line arguments
        if (args.length == 2) {
            //get files from arguments
            String inputFile = args[0];
            String outputFile = args[1];

            try {
                initialize(inputFile, outputFile);
                System.out.println("Output written to file: " + outputFile);
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + inputFile);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Please put input and output files as command line arguments.");
        }

    }

    /* This function reads in the adjacency matrix from the input file and displays all of the paths between all
    possible pairs of nodes */
    public static void initialize(String inputFile, String outputFile) throws IOException {
        //Scanner to read from file
        Scanner file = new Scanner(new File(inputFile));
        boolean isFirstMatrix = true;

        //read file
        int[][] matrix = new int[0][0];
        while (file.hasNextLine()) {
            //read size of the matrix
            int size = file.nextInt();
            //create matrix
            matrix = new int[size][size];
            //read matrix. If matrix is not square, error message is printed.
            try {
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        matrix[i][j] = file.nextInt();
                    }
                }
            } catch (NullPointerException e) {
                System.out.println("Input matrix is not in correct format.");
            }

            //Display paths
            Graph g = new Graph(matrix, size);
            g.buildGraph();
            String path = displayAllPaths(g);
            //write output to file
            if (isFirstMatrix) {
                writeFile(outputFile, matrix, path, false);
                isFirstMatrix = false;
            } else {
                writeFile(outputFile, matrix, path, true);
            }
        }
        //close scanner
        file.close();
    }

    // This function displays all of the paths from all of the vertices. 
    public static String displayAllPaths(Graph g) {
        int length = g.size;
        StringBuffer allPaths = new StringBuffer();
        //Start
        for (int i = 0; i < length; i++) {
            //get path for all possible vertices
            for (int j = 0; j < length; j++) {
                //create array for visited vertex
                boolean[] visited = new boolean[length];
                //create array to hold path
                int[] parent = new int[length];
                //set parent beginning vertex as -1
                Arrays.fill(parent, -1);
                //Add beginning vertex as visited if begin and end are different
                if (i != j) {
                    visited[i] = true;
                }
                //append beginning vertex and end vertex
                StringBuffer paths = new StringBuffer();
                if (i != j) {
                    allPaths.append(System.lineSeparator() + "Path from " + (i + 1) + " to " + (j + 1) + System.lineSeparator());
                    //get path from i to j
                    findPath(g, parent, visited, i, j, i, 0, false, paths);
                    //check if there is a path from i to j to allPaths
                    if (paths.toString().equals("")) {
                        paths.append("\tNo Path Found" + System.lineSeparator());
                    }
                    //append paths from i to j to allPaths
                    allPaths.append(paths);
                } else {
                    paths.append("\tNo Path Found" + System.lineSeparator());

                }
            }
        }
        //return all paths
        return allPaths.toString();
    }

    // This function returns a path between the beginning vertex to vertex i as a StringBuffer. 
    public static void getPath(int[] parent, int begin, int i, StringBuffer allPaths) {
        if (i == begin) {
            return;
        } else {
            getPath(parent, begin, parent[i], allPaths);
            allPaths.append("->" + (i + 1));
        }
    }

    //This function finds every path from the current vertex to all other vertices using recursion
    public static void findPath(Graph graph, int[] parent, boolean[] visited, int begin, int end, int vert1, int vert2, boolean endReached, StringBuffer paths) {
        //check if vert1 and vert2 are in range
        if ((vert1 >= graph.size) || (vert2 >= graph.size)) {
            return;
        }
        //if end is reached
        if (endReached) {
            paths.append("\t" + (begin + 1));
            getPath(parent, begin, parent[end], paths);
            paths.append("->" + (end + 1) + System.lineSeparator());
            return;
        }

        //check if there is a path from vert1 to vert2 and if vert2 is not visited
        if ((graph.hasEdge(vert1, vert2)) && (!visited[vert2])) {
            //set vert2 as visited
            visited[vert2] = true;
            parent[vert2] = vert1;
            //check if end will be reached in the next iteration
            if (vert2 == end) {
                endReached = true;
            } else {
                endReached = false;
            }
            //find path from beginning vertex
            findPath(graph, parent, visited, begin, end, vert2, 0, endReached, paths);
            //set vert2 unvisited
            visited[vert2] = false;
            parent[vert2] = -1;
            endReached = false;
        }
        //find path from beginning vertex
        findPath(graph, parent, visited, begin, end, vert1, vert2 + 1, endReached, paths);
    }

    /* This function writes all of the possible paths between all possible pairs of vertices
    to a text file that is passed in.*/
    public static void writeFile(String outputFile, int[][] matrix, String path, boolean append) throws IOException {
        //print writer to write to the file
        FileWriter writer = new FileWriter(new File(outputFile), append);
        //write matrix
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                writer.write(matrix[i][j] + " ");
                System.out.print(matrix[i][j] + " ");
            }

            writer.append(System.lineSeparator());
            System.out.println();
        }
        //write to all paths
        writer.write(path + System.lineSeparator());
        System.out.println(path);
        //add separator between matrices
        writer.write("************************" + System.lineSeparator());
        writer.close();
    }
}
