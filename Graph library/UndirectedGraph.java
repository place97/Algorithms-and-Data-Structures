/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * Questa classe rappresenta Grafi indiretti pesati e non pesati.
 * @author Marco Placentino
 * @param <V> Il tipo del vertice nel Grafo
 * @param <E> Il tipo del peso nel Grafo
 */
public class UndirectedGraph<V,E> extends Graph<V,E> {
  /**
   * Creazione di un Grafo indiretto vuoto
   * @author Marco Placentino
   * @param isWeighted true se il grafo sarà pesato false altrimenti
   */
  public UndirectedGraph(boolean isWeighted) {
    super(isWeighted);
  }
  
  @Override
  public void addEdge(V u, V v, E weight) {
    super.addEdge(u, v, weight);
    adjLists.get(v).add(new Edge<V,E>(v,u, isWeighted ? weight : null));
  }
  
  @Override
  public void removeVertex(V u){
      if(!adjLists.containsKey(u))return;
      for(Edge<V,E> edge : adjLists.get(u)){
         adjLists.get(edge.getDestination()).remove(new Edge<V,E>(edge.getOrigin(),u));
      }
      adjLists.remove(u);
  }
  
  @Override
  public void removeEdge(V u, V v) {
    super.removeEdge(u, v);
    adjLists.get(v).remove(new Edge<V,E>(v,u));
  }

  @Override
  public Double getWeightDouble() {
    return super.getWeightDouble()/2;
  }

  @Override
  public int getEdgeCount() {
    return super.getEdgeCount() / 2;
  }
} 

