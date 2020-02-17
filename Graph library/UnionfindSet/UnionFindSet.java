/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UnionfindSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
/**
 *
 * @author Marco Placentino
 */
public class UnionFindSet<T> {
 /**
  * UnionFindSet<T> rapresenta una collezione di insiemi disgiunti 
  * (nessun oggetto esiste in più di un set).
  * Ho implementato l'insieme disgiunto come una foresta.
  * I nodi di ogni albero appartengono tutti allo stesso set.
  * Questa implementazione usa il path compression 
  * Viene mantenuto una profondità per ogni albero che viene utilizzato quando si eseguono operazioni di union
  * per garantire che l'albero rimanga bilanciato.
  * @param <T> 
  */
    
    
  /**
   * Mappa di Oggetti->T, Node<T> dove ogni chiave è un oggetto 
   * nel set disgiunto e i nodi rappresentano la posione e
   * il rango all'interno del set
   */
   private final HashMap<T, Node<T>> elementsToNodes = new HashMap<>();
   
   /**
    * Metodo che controlla se il set è vuoto
    * @param set_current
    * @return true se il set è vuoto false in caso contrario. 
    */
   public boolean isEmpty(Object set_current){
        Node<T> node = elementsToNodes.get(set_current);
        if (node == null){
            return true;
        }
        else{
            return false;       
        }
    }
   
    /**
     * 
     * @param obj_in_the_set, oggetto appartenente ad un determinato set
     * @return ritorna il set di appartenenza o null in caso contrario. 
    */
    public T findSet(Object obj_in_the_set){ 
        Node<T> node = elementsToNodes.get(obj_in_the_set);
        
        if(node == null){
            return null;
        }
        if(obj_in_the_set != node.parent){
            node.parent = findSet(node.parent);
        }
        return node.parent;
    }
    
    /**
     * Questo metodo aggiunge un nuovo set all'insieme dei set disgiunti.
     * Il nuovo oggetto verrà settato inizialmente con rank=0.
     * @param new_set, oggetto da aggiungere al set, si presume che tale oggetto non appartenga a nessun set
    */
    public void makeSet(T new_set){
        elementsToNodes.put(new_set, new Node<>(new_set, 0));
    }

    /**
     * Metodo che rimuove tutti gli elementi appartenenti ad un determinato set.
     * @param obj_current 
    */
    public boolean removeSet(Object obj_current){
        boolean flag = false;
        Object set = findSet(obj_current);
        if (set == null) {
            return flag;
        }
        for (Iterator<T> it = elementsToNodes.keySet().iterator(); it.hasNext();) {
            T next = it.next();
            if(next != set && findSet(next) == set) { 
                it.remove();
            }
        }
        elementsToNodes.remove(set);
        flag = true;
        return flag;
    }
    
    /**
     * Metodo che controlla se due elementi appartengono allo stesso set.
     * @param obj1
     * @param obj2
     * @return true se appartengono allo stesso set false in caso contrario
    */
    public boolean areInSameSet(Object obj1, Object obj2){
        if( findSet(obj1) == findSet(obj2) ){
            return true;
        }
        else { 
            return false;
        }
    }
    
    /**
     * Ritorna il numero di elementi nel set
     * @param o
     * @return i, numero id elementi nel set .
     */
    public int getNumberOfElements(T o){
        Object set = findSet(o);
        if (set == null){
            return 0;
        }
        
        int i = 0;
        for (Iterator<T> it = elementsToNodes.keySet().iterator(); it.hasNext();) {
            it.next();
            i = i + 1;
        }
        return i;
    }
    
    /**
     * Copia tutti gli oggetti nel set disgiunto nella lista fornita
     * @param list, into will be copied the object.
     */
    public void toList(List<? super T> list){
        list.addAll(elementsToNodes.keySet());
    }
    

    /**
     * Metodo che unisce il set x con il set y.
     * @param x set da unire.
     * @param y set da unire.
     * @return true se l'unione ha avuto successo false in caso contrario
    */
    public boolean union(T x, T y){
        boolean united = false;
        T setX = findSet(x);
        T setY = findSet(y);
        if (setX == null || setY == null) {
            return united;
        }
        if (setX == setY){
            return united;
        }
        Node<T> nodeX = elementsToNodes.get(setX);
        Node<T> nodeY = elementsToNodes.get(setY);

        if(nodeX.rank > nodeY.rank){
            nodeY.parent = x;
            united = true;
        } 
        else {
            nodeX.parent = y;
            united = true;
            if (nodeX.rank == nodeY.rank) {
                nodeY.rank++;
                united = true;
            }          
        }
        return united;
    } 
}
