/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UnionfindSet;

/**
 *
 * @author Marco Placentino
 */
public class Node<T>{
    
    /**
    * @param rank profondità albero
    * @param parent genitore del nodo corrente. 
    */
    int rank;
    T parent;
    
    Node(T parent, int rank){
       this.parent = parent;
       this.rank = rank;
    }
    
}
