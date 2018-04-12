
/**
 * uros randelovic urosr
 * Your code goes in this file
 * fill in the empty methods to allow for the required
 * operations. You can add any fields or methods you want
 * to help in your implementations.
 *
 * @author Solomon Garber, Guillermo Narvaez
 */
public class AVLNode<T> {

    private T data;
    private double value;
    private AVLNode<T> parent;
    private AVLNode<T> leftChild;
    private AVLNode<T> rightChild;
    private int rightWeight;
    private int balanceFactor;
    private int visina;
    protected double rank;


    public AVLNode() {
    }

    public AVLNode(T data, double value) {
        this.data = data;
        this.value = value;
        this.leftChild = null;
        this.rightChild = null;
        this.visina = 1;
    }

    protected AVLNode<T> getParent() {
        return parent;
    }

    protected AVLNode<T> getLeftChild() {
        return leftChild;
    }

    protected AVLNode<T> getRightChild() {
        return rightChild;
    }

    private int visina(AVLNode koren) {
        if (koren == null) {
            return 0;
        } else {
            return koren.getVisina();
        }
    }


    /**
     * This should return the new root of the tree
     * make sure to update the balance factor and right weight
     * and use rotations to maintain AVL condition
     */
    public AVLNode<T> insert(AVLNode koren, T newData, double value) {
        //add if null
        if (koren == null) {
            AVLNode novi = new AVLNode(newData, value);
            return novi;
        }
        //choose which child to add
        if (value < koren.getValue()) {
            koren.setLeftChild(insert(koren.getLeftChild(), newData, value));
        } else if (value > koren.getValue()) {
            koren.setRightChild(insert(koren.getRightChild(), newData, value));
            koren.rightWeight++;
        } else {
            return koren;
        }
        //set height
        koren.setVisina(Math.max(visina(koren.getLeftChild()), visina(koren.getRightChild())) + 1);
        //calculate balance
        int balansiranost = visina(koren.getLeftChild()) - visina(koren.getRightChild());
        //balance the tree
        // LL
        if (balansiranost > 1 && value < koren.getLeftChild().getValue())
            return desnaRotacija(koren);
        // RR
        if (balansiranost < -1 && value > koren.getRightChild().getValue())
            return levaRotacija(koren);

        // LR
        if (balansiranost > 1 && value > koren.getLeftChild().getValue()) {
            koren.setLeftChild(levaRotacija(koren.getLeftChild()));
            return desnaRotacija(koren);
        }

        // RL
        if (balansiranost < -1 && value < koren.getRightChild().getValue()) {
            koren.setRightChild(desnaRotacija(koren.getRightChild()));
            return levaRotacija(koren);
        }
        return koren;
    }

    /**
     * This should return the new root of the tree
     * remember to update the right weight
     */
    public AVLNode<T> delete(AVLNode koren, double value) {
        //base case
        if (koren == null) {
            return koren;
        }
        //recursive case
        if (value < koren.getValue()) {
            koren.setLeftChild(delete(koren.getLeftChild(), value));
        } else if (value > koren.getValue()) {
            koren.rightWeight--;

            koren.setRightChild(delete(koren.getRightChild(), value));

        } else {
            if (koren.getLeftChild() == null &&
                    koren.getRightChild() == null) {
                koren = null;
            } else if (koren.getLeftChild() == null) {
                AVLNode tmp = koren.getRightChild();
                koren = tmp;
            } else if (koren.getRightChild() == null) {
                AVLNode tmp = koren.getLeftChild();
                koren = tmp;
            } else {
                AVLNode najmanji = najmanji(koren.getRightChild());
                double br = najmanji.getValue();
                Player player = (Player) najmanji.getData();
                koren.rightWeight--;
                koren.setValue(br);
                koren.setData(najmanji.getData());
                koren.setRightChild(delete(koren.getRightChild(), br));
            }
        }
        if (koren == null) {
            return koren;
        }
        //set new height
        koren.setVisina(Math.max(visina(koren.getLeftChild()), visina(koren.getRightChild())) + 1);
        //calculate balance factor
        int balansiranost = visina(koren.getLeftChild()) - visina(koren.getRightChild());

        //balance the tree
        // LL
        if (balansiranost > 1 && (visina(koren.getLeftChild().getLeftChild()) - visina(koren.getLeftChild().getRightChild()) >= 0)) //
            return desnaRotacija(koren);
        // RR
        if (balansiranost < -1 && (visina(koren.getRightChild().getLeftChild()) - visina(koren.getRightChild().getRightChild()) <= 0))
            return levaRotacija(koren);

        // LR
        if (balansiranost > 1 && (visina(koren.getLeftChild().getLeftChild()) - visina(koren.getLeftChild().getRightChild()) < 0)) {
            koren.setLeftChild(levaRotacija(koren.getLeftChild()));
            return desnaRotacija(koren);
        }

        // RL
        if (balansiranost < -1 && (visina(koren.getRightChild().getLeftChild()) - visina(koren.getRightChild().getRightChild()) > 0)) {
            koren.setRightChild(desnaRotacija(koren.getRightChild()));
            return levaRotacija(koren);
        }

        return koren;

    }
    private void getRightWeight(AVLNode koren){
        
    }



    //remember to maintain rightWeight
    private AVLNode desnaRotacija(AVLNode koren) {
        System.out.println("desna rotacija (" + koren.getValue() + ")");
        AVLNode y = koren;
        AVLNode x = koren.getLeftChild();
        AVLNode T2 = x.getRightChild();
        //rotating
        y.setLeftChild(T2);
        x.setRightChild(y);
        //update height
        y.setVisina(Math.max(visina(y.getLeftChild()), visina(y.getRightChild())) + 1);
        x.setVisina(Math.max(visina(x.getLeftChild()), visina(x.getRightChild())) + 1);
        //new root
        x.rightWeight++;
        return x;
    }

    /**
     * Smallest node in the tree O(height)
     *
     * @param koren
     * @return
     */
    private AVLNode najmanji(AVLNode koren) {
        if (koren.getLeftChild() == null) {
            return  koren;
        }
        return najmanji((koren.getLeftChild()));
    }

    //remember to maintain rightWeight
    private AVLNode levaRotacija(AVLNode koren) {
        System.out.println("leva rotacija (" + koren.getValue() + ")");
        AVLNode x = koren;
        AVLNode y = koren.getRightChild();
        AVLNode T2 = y.getLeftChild();

        x.setRightChild(T2);
        y.setLeftChild(x);

        x.setVisina(Math.max(visina(x.getLeftChild()), visina(x.getRightChild())) + 1);
        y.setVisina(Math.max(visina(y.getLeftChild()), visina(y.getRightChild())) + 1);

        koren.rightWeight--;
        koren.rightWeight--;

        return y;

    }

    /**
     * this should return the data object stored in the node with this.value == value
     */

    public Player getData(double value) {
        Player found = (Player) findNode(value, this).getData();
        System.out.println("uros");
        return found;
    }

    private AVLNode findNode(double value, AVLNode node) {
        if (node != null) {
            if (node.getValue() == (value)) {
                return node;
            } else {
                AVLNode foundNode = findNode(value, node.getLeftChild());
                if (foundNode == null) {
                    foundNode = findNode(value, node.getRightChild());
                }
                return foundNode;
            }
        } else {
            return null;
        }
    }



    private int numberOfNodes(AVLNode root) {
        int count = 1;
        if (root.getLeftChild() != null) {
            count += numberOfNodes(root.getLeftChild());
        }
        if (root.getRightChild() != null) {
            count += numberOfNodes(root.getRightChild());
        }
        return count;
    }



    /**
     * Getters and setters
     *
     * @return
     */
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setParent(AVLNode<T> parent) {
        this.parent = parent;
    }

    public void setLeftChild(AVLNode<T> leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(AVLNode<T> rightChild) {
        this.rightChild = rightChild;
    }

    public int getRightWeight() {
        return rightWeight;
    }

    public void setRightWeight(int rightWeight) {
        this.rightWeight = rightWeight;
    }

    public int getBalanceFactor() {
        return balanceFactor;
    }

    public void setBalanceFactor(int balanceFactor) {
        this.balanceFactor = balanceFactor;
    }

    public int getVisina() {
        return visina;
    }

    public void setVisina(int visina) {
        this.visina = visina;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public double getRank() {
        return rank;
    }
}


