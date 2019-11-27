
/**
 * Tree
 */
public class Tree<T extends Comparable<T>> {

    Node root;

    public void insert(T element) {
        root = insert(root, element);
    }

    private Node<T> insert(Node<T> parentNode, T element) {
        if (parentNode == null) {
            return new Node<>(element);
        }

        if (element.compareTo(parentNode.getValue()) > 0) {
            parentNode.setLeftChild(insert(parentNode.getLeftChild(), element));
        }

        else if (element.compareTo(parentNode.getValue()) < 0) {
            parentNode.setRightChild(insert(parentNode.getRightChild(), element));
        }

        else {
            return parentNode;
        }

        parentNode.setHeight(1 + max(height(parentNode.getLeftChild()), height(parentNode.getRightChild())));

        int balance = getBalance(parentNode);

        if (balance > 1 && element.compareTo(parentNode.getLeftChild().getValue()) > 0) {
            return SimpleRightRotation(parentNode);
        }

        if (balance < -1 && element.compareTo(parentNode.getRightChild().getValue()) < 0) {
            return SimpleLeftRotation(parentNode);
        }

        if (balance > 1 && element.compareTo(parentNode.getLeftChild().getValue()) < 0) {
            return DoubleRightRotation(parentNode);
        }

        if (balance < -1 && element.compareTo(parentNode.getRightChild().getValue()) > 0) {
            return DoubleLeftRotation(parentNode);
        }

        return parentNode;

    }

    public Node<T> SimpleRightRotation(Node<T> node) {
        Node<T> tempNode = node.getLeftChild();
        node.setLeftChild(tempNode.getRightChild());
        tempNode.setRightChild(node);
        node.setHeight(max(height(node.getLeftChild()), height(node.getRightChild())) + 1);
        tempNode.setHeight(max(height(tempNode.getLeftChild()), height(node.getRightChild())) + 1);
        return tempNode;
    }

    public Node<T> SimpleLeftRotation(Node<T> node) {
        Node<T> tempNode = node.getRightChild();
        node.setRightChild(tempNode.getLeftChild());
        tempNode.setLeftChild(node);
        node.setHeight(max(height(node.getRightChild()), height(node.getLeftChild())) + 1);
        tempNode.setHeight(max(height(tempNode.getRightChild()), height(node.getLeftChild())) + 1);
        return tempNode;
    }

    public Node<T> DoubleRightRotation(Node<T> node) {
        node.setLeftChild(SimpleLeftRotation(node.getLeftChild()));
        return SimpleRightRotation(node);
    }

    public Node<T> DoubleLeftRotation(Node<T> node) {
        node.setRightChild(SimpleRightRotation(node.getRightChild()));
        return SimpleLeftRotation(node);
    }

    public int max(int a, int b) {
        if (a > b) {
            return a;
        } else {
            return b;
        }
    }

    public int height(Node<T> node) {
        if (node == null) {
            return 0;
        } else {
            return node.getHeight();
        }
    }

    public int getBalance(Node<T> node) {
        if (node == null)
            return 0;

        return height(node.getLeftChild()) - height(node.getRightChild());
    }

    void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.getValue() + " ");
            preOrder(node.getRightChild());
            preOrder(node.getLeftChild());
        }
    }

    public void clear() {
        root = null;
    }

    public void delete(T element) {
        if (root == null) {
            System.out.println("None");
        }
        // Locate the node to be deleted and also locate its parent node
        Node<T> parent = null;
        Node<T> current = root;
        while (current != null) {
            if (element.compareTo(current.getValue()) < 0) {
                parent = current;
                current = current.getRightChild();
            } else if (element.compareTo(current.getValue()) > 0) {
                parent = current;
                current = current.getLeftChild();
            } else {
                break;
            }
        }

        if (current == null) {
            System.out.println("NO ESTA EN EL ARBOL");
        }
        if (current.getRightChild() == null) {
            if (parent == null) {
                root = current.getLeftChild();
            } else {
                if (element.compareTo(parent.getValue()) < 0) {
                    parent.setRightChild(current.getLeftChild());
                } else {
                    parent.setLeftChild(current.getLeftChild());
                }
            }
            Balance(parent, parent.getValue());
        } else {
            /**
             * Case 2: The current node has a left child. Locate the rightmost node in the
             * left subtree of the current node and also its parent
             */
            Node<T> rightParent = current;
            Node<T> mostRight = current.getRightChild();

            while (mostRight.getLeftChild() != null) {
                rightParent = mostRight;
                mostRight = mostRight.getLeftChild(); // Keep going to the right
            }

            // Replace the element in current by the element in rightMost
            current.setValue(mostRight.getValue());

            // Eliminate rightmost node
            if (rightParent.getLeftChild() == mostRight) {
                rightParent.setLeftChild(mostRight.getRightChild());
            } else // Special case: parentOfRightMost is current
            {
                rightParent.setRightChild(mostRight.getRightChild());
            }

            Balance(rightParent, rightParent.getValue());
        }
    }

    public void Balance(Node<T> root, T element) {
        if (height(root.getLeftChild()) - height(root.getRightChild()) == -2) {
            if (element.compareTo(root.getRightChild().getValue()) > 0) {
                root = SimpleLeftRotation(root);
            } else {
                root = DoubleLeftRotation(root);
            }
        }
        if (height(root.getLeftChild()) - height(root.getRightChild()) == 2) {
            if (element.compareTo(root.getLeftChild().getValue()) < 0) {
                root = SimpleRightRotation(root);
            } else {
                root = DoubleRightRotation(root);
            }
        }
    }

    /**
     * @return the root
     */
    public Node getRoot() {
        return root;
    }

    /**
     * @param root the root to set
     */
    public void setRoot(Node root) {
        this.root = root;
    }
}