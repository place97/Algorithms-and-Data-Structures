
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Marco Placentino
 */
public class KruskalMain {
    
    
  public static void main(String[] args) throws IOException { 
    UndirectedGraph<String,Double> g = buildGraphFromFile("path_to_test_the_graph");
    UndirectedGraph<String,Double> mst=Kruskal.mstKruskal(g,new DoubleComparator());
    printStats(mst);
  }  
    
  private static void printStats( UndirectedGraph<String,Double> graph) {
    double weight = graph.getWeightDouble();
    int edgeCount = graph.getEdgeCount();
    int nodeCount = graph.getVertexCount();
    System.out.println("MINIMUM SPANNING FOREST INFO:");
    System.out.println("Weight: " + weight);
    System.out.println("Edge count: " + edgeCount);
    System.out.println("Node count: " + nodeCount);
  }    
    
  private static UndirectedGraph<String,Double> buildGraphFromFile(String path) throws IOException {
    UndirectedGraph<String,Double> g = new UndirectedGraph<String,Double>(true);
    BufferedReader reader = new BufferedReader(new FileReader(path));
    while (reader.ready()) {
      String line = reader.readLine();
      String[] values = line.split(",");
      if (!g.containsEdge(values[0], values[1])) g.addEdge(values[0], values[1], Double.parseDouble(values[2]));
    }
    reader.close();
    return g;
  }
}
