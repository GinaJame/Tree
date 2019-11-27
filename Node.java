/**
 * Node
 */
public class Node<T> {

    private T value;
    private int height;
    private Node<T> LeftChild;
    private Node<T> RightChild;

    public Node(T value) {
        this.value = value;
        height = 1;
    }

    /**
     * @return the leftChild
     */
    public Node<T> getLeftChild() {
        return LeftChild;
    }

    /**
     * @return the rightChild
     */
    public Node<T> getRightChild() {
        return RightChild;
    }

    /**
     * @return the value
     */
    public T getValue() {
        return value;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param leftChild the leftChild to set
     */
    public void setLeftChild(Node<T> leftChild) {
        LeftChild = leftChild;
    }

    /**
     * @param rightChild the rightChild to set
     */
    public void setRightChild(Node<T> rightChild) {
        RightChild = rightChild;
    }

    /**
     * @param value the value to set
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }
}