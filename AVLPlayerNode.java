/**
 * uros randelovic urosr
 * Your code goes in this file
 * fill in the empty methods to allow for the required
 * operations. You can add any fields or methods you want
 * to help in your implementations.
 *
 * @author Solomon Garber, Guillermo Narvaez
 */
public class AVLPlayerNode extends AVLNode<Player> {
//    private static AVLPlayerNode root = this;

    public AVLPlayerNode(Player data, double value) {
        super(data, value);
    }

    //This should return the new root of the tree
    //make sure to update the balance factor and right weight
    //and use rotations to maintain AVL condition
    void whatsThis() {
        System.out.println(this.getPlayer(getValue()).getName());
    }

    public AVLPlayerNode insert(Player newGuy, double value) {
//        System.out.println(this.getPlayer(value).getName());
        AVLNode root = super.insert(this, newGuy, value);
        AVLPlayerNode newRoot = new AVLPlayerNode((Player) root.getData(), root.getValue());

        newRoot.setRightChild(root.getRightChild());
        newRoot.setLeftChild(root.getLeftChild());
        newRoot.setVisina(root.getVisina());
        newRoot.setBalanceFactor(root.getBalanceFactor());
        newRoot.setValue(root.getValue());
        newRoot.setParent(root.getParent());
        newRoot.setRightWeight(root.getRightWeight());
//        this.root = newRoot;
        System.out.println(newRoot.treeString());

        return newRoot;
    }


    //This should return the new root of the tree
    //remember to update the right weight
    public AVLPlayerNode delete(double value) {
//        System.out.println(this.getPlayer(value).getName());
        AVLNode root = super.delete(this, value);
        AVLPlayerNode newRoot = new AVLPlayerNode((Player) root.getData(), root.getValue());
        newRoot.setRightChild(root.getRightChild());
        newRoot.setLeftChild(root.getLeftChild());
        newRoot.setVisina(root.getVisina());
        newRoot.setBalanceFactor(root.getBalanceFactor());
        newRoot.setValue(root.getValue());
        newRoot.setParent(root.getParent());
        newRoot.setRightWeight(root.getRightWeight());
        System.out.println(newRoot.toString());
        return newRoot;
    }

    //this should return the Player object stored in the node with this.value == value
    public Player getPlayer(double value) {
        return super.getData(value);
    }

    //this should return the rank of the node with this.value == value
//    public int computeRank(double value) {
//        AVLNode node = findNode(this, value);
//
//    }

    //    private void calculateRankRight(AVLNode root){
//        if (root.getRightChild()==null){
//            root.getRightChild().setRank(1);
//            System.out.println("prvi");
//            return;
//        }else {
//            calculateRankRight(root.getRightChild());
//            System.out.println("trazim dalje u desnoj grani");
//        }
//        if (root.getLeftChild()==null && root.getRightChild()==null){
//            root.setRank(numberInRightSubtree(root.getParent())+1);
//            System.out.println("krajnji u levoj grani" + root.getRank());
//        }else {
//            calculateRankRight();
//        }
//    }
    private int calculateRankRight(AVLNode root, int number) {

        //found the rightmost child thus set the rank to be 1
        if (root.getRightChild() == null || (root.getRightChild() == null && root.getLeftChild() == null)) {
            root.setRank(1);
            return 1;
        } else {
            number += calculateRankRight(root.getRightChild(), number);
            root.setRank(number + 1);
        }

        if (root.getLeftChild() == null) {
            root.setRank(number + 1);
            return number + 1;
        } else {
            number++;
            calculateRankRight(root.getLeftChild(), number);
            return number;
        }


    }


    private int numberInRightSubtree(AVLNode root) {
        int number = 0;
        if (root.getRightChild() != null) {
            number += numberInRightSubtree(root.getRightChild());
            System.out.println(number);
        }
        if (root.getLeftChild() != null) {
            number += numberInRightSubtree(root.getLeftChild());
            System.out.println(number);
        }
        System.out.println("finalni broj" + number);
        return number;
    }

    public static AVLNode findNode(AVLNode node, double value) {
        if (null == node) {
            return null;
        }
        //Condition 1. we found the value
        if (node.getValue() == value) {
            return node;
        }
        //Condition 2.
        //Value is less than node value. so go left sub tree
        else if (value < node.getValue())
            return findNode(node.getLeftChild(), value);
            //Condition 3.
            //Value is greater than node value. so go right sub tree
        else
            return findNode(node.getRightChild(), value);
    }

    public int computeRank(double value) {
        return getRank1(this, value);
    }


//    public int getRank1(double value, AVLNode v) {
//        if (value < v.getValue()) {
//            rank += v.getRightWeight() + 1;
//            return getRank1(value, v.getLeftChild());
//        }
//        if (value > v.getValue()) {
//            return getRank1(value, v.getRightChild());
//        }
//        if (value == v.getValue()) {
//            return (int) Math.round( 1 + v.getRightWeight());
//
//        }
//        return 0;
//    }


    private int getRank1(AVLNode root, double x) {
        int count = 0;
        if (root.getValue() > x) {
            count += root.getRightWeight()+1;
            count += getRank1(root.getLeftChild(), x);
        } else if (root.getValue() < x) {
            count += getRank1(root.getRightChild(), x);
        } else {
            count += root.getRightWeight()+1;
        }
        return count;
    }

    //this should return the tree of names with parentheses separating subtrees
    //eg "((bob)alice(bill))"


    protected AVLPlayerNode getLeftChild(AVLNode root) {
        AVLNode oldroot = root.getLeftChild();
        AVLPlayerNode newRoot = new AVLPlayerNode((Player) oldroot.getData(), oldroot.getValue());
        newRoot.setRightChild(oldroot.getRightChild());
        newRoot.setLeftChild(oldroot.getLeftChild());
        newRoot.setVisina(oldroot.getVisina());
        newRoot.setBalanceFactor(oldroot.getBalanceFactor());
        newRoot.setValue(oldroot.getValue());
        newRoot.setParent(oldroot.getParent());
        newRoot.setRightWeight(oldroot.getRightWeight());
        return newRoot;
    }

    protected AVLPlayerNode getRightChild(AVLNode root) {
        AVLNode oldroot = root.getRightChild();
        AVLPlayerNode newRoot = new AVLPlayerNode((Player) oldroot.getData(), oldroot.getValue());
        newRoot.setRightChild(oldroot.getRightChild());
        newRoot.setLeftChild(oldroot.getLeftChild());
        newRoot.setVisina(oldroot.getVisina());
        newRoot.setBalanceFactor(oldroot.getBalanceFactor());
        newRoot.setValue(oldroot.getValue());
        newRoot.setParent(oldroot.getParent());
        newRoot.setRightWeight(oldroot.getRightWeight());
        return newRoot;
    }

    //this should return a formatted scoreboard in descending order of value
    //see example printout in the pdf for the command L
//    public String scoreboard() {
//
//
//    }
//
//
    public String scoreboard() {
        System.out.println(this.treeString());
        return "NAME\tID  ELO\n" + scoreboard1();
    }

    public String scoreboard1() {
        AVLPlayerNode root = this;
        StringBuilder result = new StringBuilder();


        if (root.getRightChild() != null) {
            result.append(this.getRightChild(this).scoreboard1());
        }
        result.append(this.getData().getName() + "    \t"
                + this.getData().getID()
                + "   " + this.getData().getELO() + "weight: " + this.getRightWeight() + "\n");
//        result.append(this.getData().getName());

        if (root.getLeftChild() != null) {
            result.append(this.getLeftChild(this).scoreboard1());
        }

        return result.toString();
    }

    public String treeString() {
        AVLPlayerNode root = this;
        StringBuilder result = new StringBuilder();

        result.append("(");
        if (root.getLeftChild() != null) {
            result.append(this.getLeftChild(this).treeString());
        }
        result.append(this.getData().getName());

        if (root.getRightChild() != null) {
            result.append(this.getRightChild(this).treeString());
        }
        result.append(")");

        return result.toString();
    }

}


