package ie.gmit.Encoder.Huffman;

/**
 * @author A Gilani
 * This is a node class to be used with huffman binary tree
 */
public final class Node
{
    private char alpha; //stores character
    private int freq; // stores character's frequency
    private Node left, right; // to store left or right node
 
    /** Constructor of Node class
     * @param a
     * @param f
     */
    public Node(char a, int f) {
        setAlpha(a);
        setFreq(f);
    }
 
    /**
     * Default Constructor of Node Class
     */
    public Node() {
 
    }
 
	/** To get the left node of this node
	 * @return Node
	 */
	public Node getLeft() {
		return left;
	}

	/** sets the left node of this node
	 * @param left left is a Node
	 */
	public void setLeft(Node left) {
		this.left = left;
	}

	/** get the right node of this node
	 * @return Node
	 */
	public Node getRight() {
		return right;
	}

	/** sets the right node of this node
	 * @param right right is a node
	 */
	public void setRight(Node right) {
		this.right = right;
	}

	/** get the character property of this node
	 * @return char
	 */
	public char getAlpha() {
		return alpha;
	}

	/** sets the character property of this node
	 * @param alpha alpha is a char
	 */
	public void setAlpha(char alpha) {
		this.alpha = alpha;
	}

	/** get the frequency associated to the alpha of this node
	 * @return int
	 */
	public int getFreq() {
		return freq;
	}

	/** sets the frequency associated to this alpha of this node
	 * @param freq
	 */
	public void setFreq(int freq) {
		this.freq = freq;
	}

}
