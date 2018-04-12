///**
// * Your code goes in this file
// * fill in the empty methods to allow for the required
// * operations. You can add any fields or methods you want
// * to help in your implementations.
// *
// * @author Solomon Garber, Guillermo Narvaez
// */
//public class AVLPlayerNode extends AVLNode<Player>{
//    private int rank;
//    private String scoreBoard = "";
//    private int visina;
//    private Player data;
//    private double value;
//    private AVLPlayerNode parent;
//    private AVLPlayerNode leftChild;
//    private AVLPlayerNode rightChild;
//    private int rightWeight;
//    private int balanceFactor;
//
//
//    public AVLPlayerNode(Player data, double value) {
//        this.data = data;
//        this.value = value;
//        this.leftChild = null;
//        this.rightChild = null;
//        this.visina = 1;
//    }
//
//
//    //This should return the new root of the tree
//    //make sure to update the balance factor and right weight
//    //and use rotations to maintain AVL condition
//
//    public AVLPlayerNode insert(Player newGuy, double value){
//       return insert(newGuy,value,this);
//    }
//
//    private AVLPlayerNode insert(Player newGuy, double value, AVLPlayerNode root) {
//        //add if null
//        if (root == null) {
//            AVLPlayerNode novi = new AVLPlayerNode(newGuy, value);
//            return novi;
//        }
//        //choose which child to add
//        if (value < root.getValue()) {
//            root.setLeftChild(insert(root.getLeftChild(), newGuy, value));
//        } else if (value > root.getValue()) {
//            root.setRightChild(insert(root.getRightChild(), newGuy, value));
//        } else {
//            return root;
//        }
//        //set height
//        root.setVisina(Math.max(visina(root.getLeftChild()), visina(root.getRightChild())) + 1);
//        //calculate balance
//        int balansiranost = visina(root.getLeftChild()) - visina(root.getRightChild());
//        //balance the tree
//        // LL
//        if (balansiranost > 1 && value < root.getLeftChild().getValue())
//            return desnaRotacija(root);
//        // RR
//        if (balansiranost < -1 && value > root.getRightChild().getValue())
//            return levaRotacija(root);
//
//        // LR
//        if (balansiranost > 1 && value > root.getLeftChild().getValue()) {
//            root.setLeftChild(levaRotacija(root.getLeftChild()));
//            return desnaRotacija(root);
//        }
//
//        // RL
//        if (balansiranost < -1 && value < root.getRightChild().getValue()) {
//            root.setRightChild(desnaRotacija(root.getRightChild()));
//            return levaRotacija(root);
//        }
//        rank = computeRank(value)+1;
//        return root;
//
//    }
//    private int visina(AVLPlayerNode koren) {
//        if (koren == null) {
//            return 0;
//        } else {
//            return koren.getVisina();
//        }
//    }
//
//    @Override
//    public int getVisina() {
//        return visina;
//    }
//    //This should return the new root of the tree
//    //remember to update the right weight
//    public AVLPlayerNode delete(double value) {
//        return (AVLPlayerNode) super.delete(this, value);
//    }
//
//    //this should return the Player object stored in the node with this.value == value
//    public Player getPlayer(double value) {
//        return super.getData();
//    }
//
//    //this should return the rank of the node with this.value == value
//    public int computeRank(double value) {
//        int ranking=0;
//        if (value==this.getValue()){
//            return this.rank;
//        }else if (value>this.getValue()){
//            if (this.getRightChild()!=null){
//                ranking = computeRank(this.getRightChild().getValue());
//            }
//            return  ranking;
//        }else {
//            if (this.getRightChild()!=null) {
//                ranking = computeRank(this.getLeftChild().getValue());
//            }
//            return ranking;
//        }
//    }
//
//    public int getRank() {
//        return rank;
//    }
//
//    //this should return the tree of names with parentheses separating subtrees
//    //eg "((bob)alice(bill))"
//    public String treeString() {
//        return super.treeString(this);
//    }
//
//    private AVLPlayerNode desnaRotacija(AVLPlayerNode koren) {
//        System.out.println("desna rotacija (" + koren.getValue() + ")");
//        AVLPlayerNode y = koren;
//        AVLPlayerNode x = koren.getLeftChild();
//        AVLPlayerNode T2 = x.getRightChild();
//        //rotating
//        y.setLeftChild(T2);
//        x.setRightChild(y);
//        //update height
//        y.setVisina(Math.max(visina(y.getLeftChild()), visina(y.getRightChild())) + 1);
//        x.setVisina(Math.max(visina(x.getLeftChild()), visina(x.getRightChild())) + 1);
//        //new root
//        return x;
//    }
//
//    /**
//     * Smallest node in the tree O(height)
//     *
//     * @param koren
//     * @return
//     */
//    private double najmanji(AVLPlayerNode koren) {
//        if (koren.getLeftChild() == null) {
//            return koren.getValue();
//        }
//        return najmanji((koren.getLeftChild()));
//    }
//
//    //remember to maintain rightWeight
//    private AVLPlayerNode levaRotacija(AVLPlayerNode koren) {
//        System.out.println("leva rotacija (" + koren.getValue() + ")");
//        AVLPlayerNode x = koren;
//        AVLPlayerNode y = koren.getRightChild();
//        AVLPlayerNode T2 = y.getLeftChild();
//
//        x.setRightChild(T2);
//        y.setLeftChild(x);
//
//        x.setVisina(Math.max(visina(x.getLeftChild()), visina(x.getRightChild())) + 1);
//        y.setVisina(Math.max(visina(y.getLeftChild()), visina(y.getRightChild())) + 1);
//
//        return y;
//
//    }
//
//
//    //this should return a formatted scoreboard in descending order of value
//    //see example printout in the pdf for the command L
////    public String scoreboard() {
////
////        System.out.println("NAME\tID  SCORE");
////        StringBuilder builder = new StringBuilder(LKD(this));
////
////
////    }
////
////    String LKD(AVLNode koren) {
////        if (koren != null) {
////            LKD(koren.getRightChild());
////            Player player = (Player) koren.getData();
////            return (player.getName() + "\t" + player.getID() + "  "); //TODO: ADD SCORE
////
////            LKD(koren.getLeftChild());
////        }
////        return "x";
////    }
//
//
//
//    /**
//     * getters and setters
//     * @param rank
//     */
//    public void setRank(int rank) {
//        this.rank = rank;
//    }
//
//    public String getScoreBoard() {
//        return scoreBoard;
//    }
//
//    public void setScoreBoard(String scoreBoard) {
//        this.scoreBoard = scoreBoard;
//    }
//
//    @Override
//    public void setVisina(int visina) {
//        this.visina = visina;
//    }
//
//    @Override
//    public Player getData() {
//        return data;
//    }
//
//    @Override
//    public void setData(Player data) {
//        this.data = data;
//    }
//
//    @Override
//    public double getValue() {
//        return value;
//    }
//
//    @Override
//    public void setValue(double value) {
//        this.value = value;
//    }
//
//    @Override
//    public AVLPlayerNode getParent() {
//        return parent;
//    }
//
//    public void setParent(AVLPlayerNode parent) {
//        this.parent = parent;
//    }
//
//    @Override
//    public AVLPlayerNode getLeftChild() {
//        return leftChild;
//    }
//
//    public void setLeftChild(AVLPlayerNode leftChild) {
//        this.leftChild = leftChild;
//    }
//
//    @Override
//    public AVLPlayerNode getRightChild() {
//        return rightChild;
//    }
//
//    public void setRightChild(AVLPlayerNode rightChild) {
//        this.rightChild = rightChild;
//    }
//
//    @Override
//    public int getRightWeight() {
//        return rightWeight;
//    }
//
//    @Override
//    public void setRightWeight(int rightWeight) {
//        this.rightWeight = rightWeight;
//    }
//
//    @Override
//    public int getBalanceFactor() {
//        return balanceFactor;
//    }
//
//    @Override
//    public void setBalanceFactor(int balanceFactor) {
//        this.balanceFactor = balanceFactor;
//    }
//}
//
//
