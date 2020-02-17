/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Map.Entry;
/**
 * Questa classe rappresenta Grafi diretti pesati e non pesati.
 * @author Marco Placentino
 * @param <V> Il tipo del vertice nel Grafo
 * @param <E> Il tipo del peso nel Grafo
 */
public class Graph<V,E> {
    
  protected HashMap<V, ArrayList<Edge<V,E>>> adjLists;
  protected final boolean isWeighted;
  
  /**
   * Creazione di un Grafo pesato vuoto
   * @author Marco Placentino
   * @param isWeighted true se il  Grafo sarà pesato false altrimenti
   */
  public Graph(boolean isWeighted) {
    this.isWeighted = isWeighted;
    this.adjLists = new HashMap<V, ArrayList<Edge<V,E>>>();
  }
  
  private static <K, V> void putIfAbsent(HashMap<K, V> map, K key, V value) {
    if (!map.containsKey(key)) map.put(key, value);
  }
  
  /**
   * Inserisce un vertice in questo Grafo se non è gia presente
   * @author Marco Placentino
   * @param new_vertex nuovo vertice da inserire
   * @throws NullPointerException if u == null
  */
  public void addVertex(V new_vertex) {
    if (new_vertex == null) throw new NullPointerException("The new vertex must not be null");
    putIfAbsent(adjLists, new_vertex, new ArrayList<Edge<V,E>>());
  }
  
  /**
   * Inserisce un nuovo Edge nel Grafo.
   * Se il vertice specificato non è nel Grafo, sarà creato.
   * Se il Grafo non è pesato il parametro weight sarà ignorato.
   * @author Marco Placentino
   * @param vertex_of_origin vertice di partenza di edge
   * @param destination_vertex vertice di destinazione di edge
   * @param weight il peso del nuovo edge
   * @throws IllegalArgumentException if vertex_of_origin.equals(destination_vertex) || edge è gia nel Grafo
   * @throws NullPointerException if vertex_of_origin == null || destination_vertex == null
    */
   public void addEdge(V vertex_of_origin, V destination_vertex, E weight) {
    if (vertex_of_origin == null || destination_vertex == null || (weight == null && isWeighted))
      throw new NullPointerException("The " + (isWeighted ? "weight and the " : "") + "vertices must not be null");
    if (vertex_of_origin.equals(destination_vertex)) throw new IllegalArgumentException("This graph does not support loops");
    putIfAbsent(adjLists, vertex_of_origin, new ArrayList<Edge<V,E>>());
    putIfAbsent(adjLists, destination_vertex, new ArrayList<Edge<V,E>>());
    ArrayList<Edge<V,E>> adj = adjLists.get(vertex_of_origin);
    Edge<V,E> newEdge = new Edge<V,E>(vertex_of_origin,destination_vertex, isWeighted ? weight : null);
    if (adj.contains(newEdge)) throw new IllegalArgumentException("The edge is already in this graph");
    adj.add(newEdge);
  }
   
   /**
    * Rimuove il vertice "delete_vertex" se esiste
    * @author Marco Placentino
    * @param delete_vertex  vertice da rimuovere
   */
   public void removeVertex(V delete_vertex) {
    if (!adjLists.containsKey(delete_vertex)) return;
    adjLists.remove(delete_vertex);
    for (Entry<V, ArrayList<Edge<V,E>>> entry : adjLists.entrySet()) {  
      entry.getValue().remove(new Edge<V,E>(entry.getKey(),delete_vertex));
    }
  }
   
   /**
   * Rimuove l'arco(u, v) se esiste 
   * @author Marco Placentino
   * @param vertex_of_origin vertice di partenza di edge
   * @param destination_vertex vertice di destinazione di edge
   */
    public void removeEdge(V vertex_of_origin, V destination_vertex) {
        if (!adjLists.containsKey(vertex_of_origin) || !adjLists.containsKey(destination_vertex)) return;
        adjLists.get(vertex_of_origin).remove(new Edge<V,E>(vertex_of_origin,destination_vertex));
    }
  
   /**
   * Questo metodo ritorna il set  dei vertici del Grafo.
   * @author Marco Placentino
   * @return il set dei vertici del Grafo
   */
    public Set<V> getVertexSet() {
        return new HashSet<V>(adjLists.keySet());
    }
   
    /**
   * Questo metodo ritorna la lista  dei vertici del Grafo.
   * @author Marco Placentino
   * @return ArrayList dei vertici del Grafo
   */
    public ArrayList<V> Vertex_list(){
        return new ArrayList<V>(adjLists.keySet());
    }
    /**
   * Questo metodo ritorna la lista di adiacenza di un determinato vertice.
   * @author Marco Placentino
   * @param current_vertex vertice attraverso il quale recuperiamo la sua lista di adiacenza
   * @return ritorna la lista di adicacenza di current_vertex o null in caso contrario
   */
    public ArrayList<V> getAdjacencyList(V current_vertex) {
        if (!adjLists.containsKey(current_vertex)) return null;
        ArrayList<V> adj = new ArrayList<V>();
        for (Edge<V,E> e : adjLists.get(current_vertex)) {
            adj.add(e.getDestination());
        }
        return adj;
    }

    /**
    * Ritorna il peso di un arco specifico
    * @author Marco Placentino
    * @param vertex_of_origin vertice di partenza di edge
    * @param destination_vertex vertice di destinazione di edge
    * @return the weight of the specified edge
    * @throws UnsupportedOperationException if this graph is not weighted
    * @throws NoSuchElementException if the edge does not exist in this graph
    */
    public E getEdgeWeight(V vertex_of_origin, V destination_vertex) {
        if (!isWeighted) throw new UnsupportedOperationException("Cannot get the weight of an edge in an unweighted graph");
        if (!adjLists.containsKey(vertex_of_origin)) throw new NoSuchElementException("This edge is not in the graph");
        ArrayList<Edge<V,E>> adjList = adjLists.get(vertex_of_origin);
        int index = adjList.indexOf(new Edge<V,E>(vertex_of_origin,destination_vertex));
        if (index == -1) throw new NoSuchElementException("This edge is not in the graph");
        return adjList.get(index).getWeight();
    }

    /**
    * @author Marco Placentino
    * @return il peso totale del Grafo
    * @throws UnsupportedOperationException se il Grafo non è pesato
    */
    public Double getWeightDouble() {
        if (!isWeighted) throw new UnsupportedOperationException("This graph is not weighted");
        Double weight = 0d;
        for (ArrayList<Edge<V,E>> adjList : adjLists.values()) {
            for (Edge<V,E> edge : adjList) {
                weight += (Double)edge.getWeight();
            }
        }
        return weight;
    }
    
    public Number getSumWeight(){
        ArrayList<Edge<V,E>> list=getListEdge();
        if(list.get(0).getWeight() instanceof Double)
            return getWeightDouble();
        return null;
    }
    
    
   
  /**
   * Controlla se nel Grafo è presente un determinato vertice
   * @author Marco Placentino
   * @param current_vertex  vertice corrente
   * @return true se il vertice esiste nel Grafo false altrimenti
   */
  public boolean containsVertex(V current_vertex) {
    return adjLists.containsKey(current_vertex);
  }

  /**
   * Controlla se eiste un determinato arco nel Grafo
   * @author Marco Placentino
   * @param vertex_of_origin vertice di partenza di edge
   * @param destination_vertex vertice di destinazione di edge
   * @return true se l'arco esiste nel grafo 
   */
  public boolean containsEdge(V vertex_of_origin, V destination_vertex) {
    if (!adjLists.containsKey(vertex_of_origin)) return false;
    return adjLists.get(vertex_of_origin).contains(new Edge<V,E>(vertex_of_origin,destination_vertex));
  }
  
  /**
   * @author Marco Placentino
   * @return il numero dei vertici nel Grafo
   */
  public int getVertexCount() {
    return adjLists.size();
  }

  /**
   * @author Marco Placentino
   * @return il numero di archi nel Grafo
   */
  public int getEdgeCount() {
    int count = 0;
    for (ArrayList<Edge<V,E>> adjList : adjLists.values()) {
      count += adjList.size();
    }
    return count;
  }
  
   /**
   * @author Marco Placentino
   * @return ArrayList di archi del grafo
   */
  public ArrayList<Edge<V,E>> getListEdge(){
      ArrayList<Edge<V,E>> list=new ArrayList<Edge<V,E>>();
      for (ArrayList<Edge<V,E>> adjList : adjLists.values()) {
         list.addAll(adjList);
      }
      return list;
  }

  /**
   * @author Marco Placentino
   * @return true se il Grafo non ha ne edge ne vertici
   */
  public boolean isEmpty() {
    return adjLists.isEmpty();
  }

  /**
   * @author Marco Placentino
   * @return true se il Grafo è pesato
   */
  public boolean isWeighted() {
    return isWeighted;
  }

  /**
   * Elimina ogni vertice e arco nel Grafo
   * @author Marco Placentino
   */
  public void clear() {
    adjLists.clear();
  }
  
  
}
