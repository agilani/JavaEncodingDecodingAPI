package ie.gmit.Encoder.Huffman;

import java.util.Comparator;

/**
 * @author A Gilani
 * This class is to provide a comparator for the priority queue
 */
public class FrequencyComparator implements Comparator<Node>
{
    /* (non-Javadoc)
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    public int compare(Node a, Node b) {
        int freqA = a.getFreq();
        int freqB = b.getFreq();
 
        return freqA-freqB;
    }
}
