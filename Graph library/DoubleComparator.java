
import java.util.Comparator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Marco Placentino  
 * 
 */
public class DoubleComparator implements Comparator<Double> {
    
    @Override
    public int compare(Double s1, Double s2) {
      return s1.compareTo(s2);
    }
}
