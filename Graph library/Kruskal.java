
import UnionfindSet.UnionFindSet;
import java.util.ArrayList;
import java.util.Comparator;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *

* @author Marco Placentino
 */
public class Kruskal {
  
    
    /**
    * Metodo per la restituzione di un mst
    * @author Marco Placentino
    * @param graph grafo di partenza
    * @param cmp  elemento di comparazione
    * @return UndirectedGraph<V,E>mst
    */
    public static <V,E> UndirectedGraph<V,E>mstKruskal(UndirectedGraph<V,E> graph,Comparator<E> cmp){
        if (!graph.isWeighted()) throw new IllegalArgumentException("The graph is not weighted");
        UndirectedGraph<V,E>mst=new UndirectedGraph<V,E>(true);
        ArrayList<V> vertex_list=graph.Vertex_list();
        ArrayList<Edge<V,E>> list=graph.getListEdge();
        UnionFindSet<V> ufs=new UnionFindSet<>();
        for(int i=0;i<vertex_list.size();i++){
           ufs.makeSet(vertex_list.get(i));
        }
        Mergesort.mergeSort(list, cmp);
        for(int i=0;i<list.size();i++){
            if(ufs.findSet(list.get(i).getOrigin())!=ufs.findSet(list.get(i).getDestination())){
                mst.addEdge(list.get(i).getOrigin(), list.get(i).getDestination(), list.get(i).getWeight());
                ufs.union(list.get(i).getOrigin(),list.get(i).getDestination());
            }
        }
        return mst;
    }
    

}
