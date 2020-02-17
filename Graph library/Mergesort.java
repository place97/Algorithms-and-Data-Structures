
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
public class Mergesort {
  
   /**
   * Implementation of the merge sort algorithm
   * @author Marco Placentino
   * @param <V,E> The type of the elements in the ArrayList
   * @param arr The ArrayList to be sorted
   * @param comp The Comparator to determine the order of the ArrayList
   * @throws IllegalArgumentException if comp == null
   */
  public static <V,E> void mergeSort(ArrayList<Edge<V,E>> arr, Comparator<? super E> comp) {
    if (comp == null) throw new IllegalArgumentException();
    if (arr == null) return;
    mergeSort(arr, comp, 0, arr.size() - 1);
  }

  private static <V,E> void mergeSort(ArrayList<Edge<V,E>> arr, Comparator<? super E> comp, int left, int right) {
    if (left < right) {
      int middle = (left + right) / 2;
      mergeSort(arr, comp, left, middle);
      mergeSort(arr, comp, middle + 1, right);
      merge(arr, comp, left, middle, right);
    }
  }

  private static <V,E> void merge(ArrayList<Edge<V,E>> arr, Comparator<? super E> comp, int left, int middle, int right) {
    int i = left;
    int j = middle + 1;

    ArrayList<Edge<V,E>> temp = new ArrayList<Edge<V,E>>();

    while (i <= middle && j <= right) {
      if (comp.compare(arr.get(i).getWeight(), arr.get(j).getWeight()) < 0) {
        temp.add(arr.get(i));
        i++;
      } else {
        temp.add(arr.get(j));
        j++;
      }
    }

    for ( ; i <= middle; i++) {
      temp.add(arr.get(i));
    }

    for ( ; j <= right; j++) {
      temp.add(arr.get(j));
    }

    for (int k = left; k <= right; k++) {
      arr.set(k, temp.get(k - left));
    }
  }    
}
