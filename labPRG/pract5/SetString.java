package pract5;

import java.util.Scanner;
/**
 * SetString class. Implementation of a set of String by means of 
 * a lexicographically ordered linked sequence.
 *
 * @author (PRG. ETSINF. UPV)
 * @version (Academic Year 2025/26)
 */
public class SetString {
    
    private NodeString first;
    private int size;
    
    /** Constructor method that creates a new empty set. */
    public SetString() {
        // TO COMPLETE
    }

    /**
     * Method that adds s to the set.
     * If s belongs to the set, the set itself remains unchanged.
     * @param s String.
     */
    public void add(String s) {
        NodeString previous = null;
        NodeString current = first;
        
        while(current != null && current.data.compareTo(s) <= 0)
        {
            previous = current;
            current = current.next;
        }
        
        if(previous != null && s.equals(previous.data))
            return;
        
        var node = new NodeString(s, current);
        if(previous == null)
            first = node;
        else
            previous.next = node;
        
        size++;
    }
    
    /**
     * Checks if s belongs to the set.
     * @param s String.
     * @return boolean, true if and only if s belongs to the set..
     */
    public boolean contains(String s) {
        
        NodeString current = first;
        while(current != null)
        {
            var compare = current.data.compareTo(s);
            if(compare == 0)
                return true;
                
            if(compare > 0)
                return false;
                
            current = current.next;
        }
            
        return false;
    }

    /**
     * Removes s from the set.
     * If s does not belong to the set, the set itself remains unchanged.
     * @param s String.
     */
    public void remove(String s) {
        NodeString previous = null;
        NodeString current = first;
        
        while(current != null)
        {
            var compare = current.data.compareTo(s);
            if(compare == 0)
            {
                
                if(previous == null)
                    first = current.next;
                else
                    previous.next = current.next;
                    
                size--;
                return;
            }
                
            if(compare > 0)
                return;
                
            previous = current;
            current = current.next;
        }
    }
    
    /**
     * Returns the set size (or cardinal).
     * @return int, the set size.
     */
    public int size() {
        return size;
    }    
    
    /**
     * Returns the set resulting of the intersection of the current set 
     * and the argument set.
     * @param other SetString.
     * @return SetString, the resulting interseccion set.
     */
    public SetString intersection(SetString other) {
        SetString result = new SetString();
        NodeString n1 = this.first; // current node to check of this set
        NodeString n2 = other.first; // current node to check of the other set
        NodeString last = null; // last node of new set, initialised to null

        while(n1 != null && n2 != null)
        {
            var compare = n1.data.compareTo(n2.data);
            if(compare == 0)
            {
                var node = new NodeString(n1.data);
                if(last != null)
                    last.next = node;
                else
                    result.first = node;

                last = node;
                result.size++;
                
                n1 = n1.next;
                n2 = n2.next;
            }
            else if(compare < 0)
            {
                n1 = n1.next;
            }
            else
            {
                n2 = n2.next;
            }
        }
        
        
        return result;
    }
    
    /**
     * Returns the set resulting of the union of the current set and the 
     * argument set.
     * @param other SetString.
     * @return SetString, the resulting union set.
     */
    public SetString union(SetString other) {
        SetString result = new SetString();
        NodeString n1 = this.first; // current node to check of this set
        NodeString n2 = other.first; // current node to check of the other set
        NodeString last = null; // last node of new set, initialised to null

        while(n1 != null && n2 != null)
        {
            NodeString node;
            var compare = n1.data.compareTo(n2.data);
            if(compare == 0)
            {
                node = new NodeString(n1.data);
                n1 = n1.next;
                n2 = n2.next;
            }
            else if(compare < 0)
            {
                node = new NodeString(n1.data);
                n1 = n1.next;
            }
            else
            {
                node = new NodeString(n2.data);
                n2 = n2.next;
            }
            
            
            if(last != null)
                last.next = node;
            else
                result.first = node;

            last = node;
            
            result.size++;
        }
        
        var left = n1;
        if(left == null)
            left = n2;
        
        while(left != null)
        {
            var node = new NodeString(left.data);
            if(last != null)
                last.next = node;
            else
                result.first = node;

            last = node;
            
            result.size++;
            left = left.next;
        }

        return result;
    }   
    
    /**
     * Method that returns a String with the set list of words 
     * in ascending order.
     * @return String, the list of words of the set.
     */
    public String toString() {
        String result = "";
        NodeString aux = this.first;
        while (aux != null) {
            result += aux.data + "\n"; 
            aux = aux.next; 
        }
        return result;
    }
  
    /** 
     * Method that returns the SetString of the words read from 
     * Scanner s according to the separators by means of which
     * the Scanner has been configured.
     * @param s Scanner.
     * @return SetString, a new SetString of words read from Scanner s.
     */
    public static SetString setReading(Scanner s) {
        SetString setS = new SetString();
        while (s.hasNext()) {
            String word = s.next();
            setS.add(word);
        }
        return setS;
    }
    
    /** The method returns the SetString of the words read from s according to the
     * separators with which the scanner is configured. It assumes that the s tokens
     * are sorted. In the event that the tokens are not sorted, an
     * IllegalArgumentException is thrown with the message "Tokens not sorted".
     */
    public static SetString setReadingOrd(Scanner s) {
        SetString setS = new SetString();
        NodeString last = null;
        String word;
        
        if (s.hasNext()) {
            word = s.next();
            last = setS.first = new NodeString(word);
            setS.size = 1;
        }
        
        while (s.hasNext()) {
            word = s.next();
            int compare = word.compareTo(last.data);
            
            if(compare < 0)
                throw new IllegalArgumentException("Tokens not sorted.");
                
            if (compare > 0) {
                last = last.next = new NodeString(word);
                setS.size++;
            }
        }
    return setS;
    }
}
