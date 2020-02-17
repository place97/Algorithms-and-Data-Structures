/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Marco Placentino
 */
public class Edge<V,E> {
  
  private E weight;
  private final V origin;
  private final V destination;
  
   
  /**
   * Crea un nuovo "edge" non pesato
   * @author Marco Placentino
   * @param destination il vertice a cui punta "edge"
  */
  public Edge(V origin, V destination) {
    this.origin=origin;
    this.destination = destination;
    this.weight = null;
  }
  
  /**
   * Crea un nuovo "edge" pesato
   * @author Marco Placentino
   * @param origin il vertice da cui parte "edge"
   * @param destination il vertice a cui punta "edge"
   * @param weight il peso di "edge"
  */
  public Edge(V origin, V destination, E weight) {
    this.origin=origin;
    this.destination = destination;
    this.weight = weight;
  }
  
   /**
   * @author Marco Placentino
   * @return la destinazione del vertice 
   */
  public V getDestination() {
    return destination;
  }
  
  public V getOrigin(){
      return origin;
  }

  /**
   * @author Marco Placentino
   * @return il peso di edge , or null
   *        se edge è non pesato
  */
  public E getWeight() {
    return weight;
  }

  /**
   * Modifica il peso di  edge. Se
   * questo edge non è pesato sarà converito 
   * in un edge pesato
   * @author Marco Placentino
   * @param weight il peso da modifcare 
   */
  public void setWeight(E weight) {
    this.weight = weight;
  }
    
  @Override
  public String toString() {
        return  weight.toString()+" "+origin.toString()+" "+ destination.toString() ;
  } 
  
  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null)
      return false;
    if (getClass() != o.getClass())
      return false;
    Edge<V,E> other = (Edge<V,E>) o;
    if (destination == null) {
      if (other.destination != null)
        return false;
    } else if (!destination.equals(other.destination))
      return false;
    return true;
  }
  
}
