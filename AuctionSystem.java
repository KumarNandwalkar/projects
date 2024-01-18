package com.kumar;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

class inneravl{

    String auctionid;
    int biddersid;
    int baseprice;
    public inneravl(String CON_auction_id, int CON_bidders_id, int CON_price)
    {
        auctionid = CON_auction_id;
        biddersid = CON_bidders_id;
        baseprice = CON_price;
    }

    public inneravl() {
    }

    public String getAttributesinneravl()
    {
        return auctionid;
    }



    class SmallNode extends inneravl
    {
        String auction_id; // for old constructor

        String getAuction_id;
        int bidders_id;
        int height;
        int price;
        String newww = getAttributesinneravl();

        public void mtdforgettingauctionid()
        {
            System.out.println(getAttributesinneravl());
            // this will give us auction id
            String newid = getAttributesinneravl();
        }
        inneravl.SmallNode left, right;

        //        public SmallNode(inneravl aid, int bidders_id, int price)
        public SmallNode(String aid, int biddersid, int price) // this was old constructor
        {
//            super(aid.getAttributesinneravl(), ); // using this we can access attribute of class inneravl
            // as a parameter for class smallnode, so that whenever we create a auction of specific auction id,
            // we also create a inneravl tree and changing the parameter of smallnode helps us making the connectivity
            // here whenever we want to create a smallnode(bid) for the specific node this will only allow us to bid with a specific
            // auction id
//            String aid = getAttributesinneravl();
            auction_id = aid;
            bidders_id = biddersid;
            height = 1;
            price = price;
        }
    }

    inneravl.SmallNode root;

    // Calculate height of a node
    int height(inneravl.SmallNode N) {
        return (N == null) ? 0 : N.height;
    }

    // Get balance factor of a node
    int getBalance(inneravl.SmallNode N) {
        return (N == null) ? 0 : height(N.left) - height(N.right);
    }

    // Right rotate subtree rooted with y
    inneravl.SmallNode rightRotate(inneravl.SmallNode y) {
        inneravl.SmallNode x = y.left;
        inneravl.SmallNode T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    // Left rotate subtree rooted with x
    inneravl.SmallNode leftRotate(inneravl.SmallNode x) {
        inneravl.SmallNode y = x.right;
        inneravl.SmallNode T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    // Insert a node
    inneravl.SmallNode insert(inneravl.SmallNode node, String auction_id, int bidders_id, int price) {
        if (node == null) {
            return new inneravl.SmallNode(auction_id, bidders_id, price);
        }

        // Perform normal BST insertion
        if (auction_id.compareTo(node.auction_id) < 0) {
            node.left = insert(node.left, auction_id, bidders_id, price);
        } else if (auction_id.compareTo(node.auction_id) > 0) {
            node.right = insert(node.right, auction_id, bidders_id, price);
        } else {
            // Duplicate auction_id is not allowed in AVL tree
            return node;
        }

        // Update height of this ancestor node
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // Get the balance factor
        int balance = getBalance(node);

        // Left Left Case
        if (balance > 1 && auction_id.compareTo(node.left.auction_id) < 0) {
            return rightRotate(node);
        }

        // Right Right Case
        if (balance < -1 && auction_id.compareTo(node.right.auction_id) > 0) {
            return leftRotate(node);
        }

        // Left Right Case
        if (balance > 1 && auction_id.compareTo(node.left.auction_id) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && auction_id.compareTo(node.right.auction_id) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // Print in-order traversal of the AVL tree
    void inOrderTraversal(inneravl.SmallNode node)
    {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.println("Auction ID: " + node.auction_id + ", Bidder ID: " + node.bidders_id + ", Price: " + node.price);
            inOrderTraversal(node.right);
        }
    }

    // Search for a node with a given auction_id
    SmallNode search(String targetAuctionId) {
        return search(root, targetAuctionId);
    }

    private SmallNode search(SmallNode node, String targetAuctionId) {
        // Base cases: root is null or target auction_id is found
        if (node == null || node.auction_id.equals(targetAuctionId)) {
            return node;
        }

        // If the target auction_id is smaller than the node's auction_id,
        // lies in the left subtree
        if (targetAuctionId.compareTo(node.auction_id) < 0) {
            return search(node.left, targetAuctionId);
        }

        // If the target auction_id is larger than the node's auction_id,
        // in the right subtree
        return search(node.right, targetAuctionId);
    }


}


class OuterAVLTree      // outer AVLTree
{
    public class Node extends inneravl {   // outer node

        int key;                // key = user id
        int height;
        String auction_id;
        String item_name;
        int base_price;

        inneravl InnerAVL;
        Node left, right;

        public Node(int Key, String a_id, String itemname, int baseprice) {
            super(a_id, Key, baseprice);
            // super methode
//                InnerAVL = new inneravl(a_id, Key, baseprice);

            key = Key;   // taking input for user id
            height = 1;
            auction_id = a_id;
            item_name = itemname;
            base_price = baseprice;
//            inneravl newinnertree = new inneravl();
//            newinnertree.SmallNode root = newinnertree.new SmallNode(a_id,key,base_price);
////            newinnertree.root = inneravl.SmallNode(a_id,key,baseprice);
//            newinnertree.root = newinnertree.insert(newinnertree.root,a_id,key,baseprice);

        }

        public inneravl getInnerAVL() {
            return InnerAVL;
        }

//        int id;            // this id is user id
//        int auction_id;    // auction id is the name of root node of inner avl tree and also the name of outer avl tree node
//        String item_name;  // item name:- item on which bid is going to happen
//        int base_price;    // base price of bid

    }
    Node root;
//        smallnode Root;

    int height(Node N) {                            // height of node
        return (N == null) ? 0 : N.height;
    }

    int max(int a, int b) {                         // for finding height difference of node
        return (a > b) ? a : b;
    }

    Node rightRotate(Node y) {                      // right rotation of node
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        return x;
    }

    Node leftRotate(Node x) {                       // left rotation of node
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        return y;
    }

    int getBalance(Node N) {                        // getting balance factor
        return (N == null) ? 0 : height(N.left) - height(N.right);
    }

    Node insert(Node node, int key, String auction_id, String itemname, int baseprice) {
//            inneravl newnodeinserted = new inneravl();

        if (node == null)
            return (new Node(key, auction_id, itemname, baseprice));

        if (baseprice < node.base_price)
            node.left = insert(node.left, key, auction_id, itemname, baseprice);
        else if (baseprice > node.base_price)
            node.right = insert(node.right, key, auction_id, itemname, baseprice);
        else
            return node;

        node.height = 1 + max(height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1 && baseprice < node.left.base_price)
            return rightRotate(node);

        if (balance < -1 && baseprice > node.right.base_price)
            return leftRotate(node);

        if (balance > 1 && baseprice > node.left.base_price) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && baseprice < node.right.base_price) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

//            AVLTree acid = new AVLTree();

        return node;
    }

    Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }

    Node deleteNode(Node root, int key) {               // key = user id
        if (root == null) return root;

        if (key < root.key)
            root.left = deleteNode(root.left, key);
        else if (key > root.key)
            root.right = deleteNode(root.right, key);
        else {
            if ((root.left == null) || (root.right == null)) {
                Node temp = null;
                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;

                if (temp == null) {
                    temp = root;
                    root = null;
                } else
                    root = temp;
            } else {
                Node temp = minValueNode(root.right);
                root.key = temp.key;
                root.right = deleteNode(root.right, temp.key);
            }
        }

        if (root == null) return root;

        root.height = 1 + max(height(root.left), height(root.right));

        int balance = getBalance(root);

        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);

        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
//                System.out.println("node.left");
            System.out.print("Auctioner's ID = " + node.key + "\n Auction ID = " + node.auction_id + "\n Item Name = " + node.item_name + "\n Base Price = " + node.base_price + " \n");
            inOrder(node.right);
//                System.out.println("node.right");
            System.out.println(" ");

        } // we removed else condition cuz, whenever inorder use to traverse to
        // the leaf nodes in left and right side it use to show tree is empty
    }

    boolean isEmpty() {
        return root == null;
    }

    void check() {

    }

    public Node searchAuctionID(Node node, String auctionID) {
        while (true)
        {   // Base cases: if the tree is empty or the auction ID is found
            if (node == null || node.auction_id.equals(auctionID)) {

                return node;
            }

            // If the auction ID is greater than the current node's auction ID,
            // then it lies in the right subtree
            if (auctionID.compareTo(node.auction_id) > 0) {
                return searchAuctionID(node.right, auctionID);
            }

            // If the auction ID is smaller than the current node's auction ID,
            // then it lies in the left subtree
            return searchAuctionID(node.left, auctionID);
        }
    }
    Node searchByBasePrice(Node node, int basePrice) {
        if (node == null) {
            return node;
        }

        // If the current node's base price matches the search criteria, print it
        if (node.base_price == basePrice) {
            System.out.println("Auction ID: " + node.auction_id + ", Item Name: " + node.item_name + ", Base Price: " + node.base_price);
        }

        // If the base price is less than the current node's base price,
        // search in the left subtree
        if (basePrice < node.base_price) {
            searchByBasePrice(node.left, basePrice);
        }

        // If the base price is greater than the current node's base price,
        // search in the right subtree
        if (basePrice > node.base_price) {
            searchByBasePrice(node.right, basePrice);
        }
        return node;
    }



}

class smallnodeAVL // a seperate avl tree created using collections avl tree to store all rootnodes of inneravltrees
{

}

public class git
{


    public static void main(String[] args)
    {
//        OuterAVLTree tree = new OuterAVLTree();       // this wont work cuz we dont have any static instance here
//        ADS ADS = new ADS();                        // creating an instance of latestADS.ADS class
//        ADS ads = new ADS(); // creating a new object ads so that we can use the inner class
// so that we can access the non static class inside latestADS.ADS class
//        inneravl inneravl = new inneravl();
        OuterAVLTree tree = new OuterAVLTree();  // creating an instace of OuterAVLTree using the latestADS.ADS instance
// here this latestADS.ADS. is public class latestADS.ADS its not the name of object
        Scanner sc = new Scanner(System.in);
        while (true) {
//            tree.inOrder(tree.root);
            System.out.println("This is an Auction Platform made using AVL tree");
            System.out.println("1. Place a Bid or Create a new Auction");
            System.out.println("2. View old Bids and Auctions");
            int input = sc.nextInt();
            switch (input) {
                case 1:
                    System.out.println("1. Place Bid in existing Auction");
                    System.out.println("2. Auction your Item");
                    int input1 = sc.nextInt();
                    switch (input1) {
                        case 1:

                            if (tree.isEmpty()) {
                                System.out.println("theres no ongoing auction -> create new auction");
//                            System.out.println("you need to make a bid reason being you are the first customer");
                                System.out.println("As theres no auction you need to create one");
                                System.out.println("ending this program, re-run again \nand select option 2 at your second choice");
                                System.out.println("Thank You!");
                            } else {
                                System.out.println("These are ongoing Auctions with their\nAuctioners id, Auction ID, Item Name, Base Price and the Price of most recent Bid \n");
                                // printing all nodes with their auctioner id (user id), auction_id, item_name, base_price and mostrecentbid.price
//                        performing inorder traversal
                                tree.inOrder(tree.root);

                                System.out.println("Enter the Auction ID and base price of any particular auction you want to bid on");
                                System.out.println("First enter auction ID then enter baseprice");
                                String auctionID = sc.nextLine();
                                sc.nextLine();
                                int baseprice = sc.nextInt();

                                OuterAVLTree.Node result = tree.searchByBasePrice(tree.root, baseprice);
                                // Check if the auction ID was found
                                if (result != null) {
                                    System.out.println("Auction found with:- \n" + "Auction ID = " + result.auction_id + " \nItem Name = " + result.item_name + " \nBase Price: " + result.base_price + "\n");
//                                System.out.println("This is the most recent bid in this auction :" + inneravl.SmallNode.);
                                    System.out.println("enter the auction ID for bid \n");
                                    // new bid is created
                                    // for that a new innerAVLTree should be created
//                            meth 1 if any smallnode exists in the latestADS.inneravl tree of that specific auction
//                              then add a new smallnode to that inneravltree, else create a new tree and add smallnode to it
//                            meth 2 if no such file with auctionID.json found in the package then create one
//                                    if found then see if that file contains any innveavltree and its smallnode
//                                inneravl innerAVLTree = new inneravl();
                                    String aid = sc.nextLine();  // auction id for th eongoing auction
                                    sc.nextLine();
                                    System.out.println("Enter the Bidders ID for Bid \n");
                                    int biddersid = sc.nextInt(); // users id

                                    System.out.println("Enter the Price which you want to bid on");
                                    int price = sc.nextInt();

//                                inneravl.SmallNode smallNode = innerAVLTree.new SmallNode(aid, biddersid, price);

                                    inneravl innerTree = result.getInnerAVL(); // for connecting the outer avl tree's node
                                    // inner avl tree for bids
                                    inneravl.SmallNode newsmallnode = innerTree.new SmallNode(aid, biddersid, price);
                                    innerTree.insert(newsmallnode, aid, biddersid, price);
                                    // this should insert a smallnode in inneravltree
//                                InnerAVL.SmallNode newsmallnode = InnerAVL.new SmallNode(aid, biddersid, price);

                                    // to crosscheck if the smallnode is created inside the auction
//                                    innerTree.inOrderTraversal(.root);
                                    System.out.println("Congrats!!, You have placed a new bid successfully");
                                } else {
                                    System.out.println("Auction ID " + auctionID + " not found.");

                                    System.out.println("would you like to re-enter the auction ID or exit");

                                    String reply = sc.nextLine();
                                    switch (reply) {
                                        case "re-enter": {
                                            auctionID = null;
                                            String AuctionID = sc.nextLine();

                                            OuterAVLTree.Node Result = tree.searchAuctionID(tree.root, auctionID);
                                            // Check if the auction ID was found
                                            if (result != null) {
                                                System.out.println("Auction ID " + AuctionID + " found. Item Name: " + result.item_name + ", Base Price: " + result.base_price);
                                            } else {
                                                System.out.println("Auction ID " + AuctionID + " not found." + "try again later");
                                            }
                                            break;
                                        }

                                        case "exit": {
                                            System.out.println("Try later");
                                            break;
                                        }

                                        default:
                                            System.out.println("input- either (re-enter) or (exit) ");
                                            break;
                                    }
                                }

                            }

                            break;

                        // removing the break statement from this case so that user can

                        case 2:
                            System.out.println("To Auction your item, you need to fill the details below");
                            // taking user input for id, auction_id, item_name, base_price,
                            System.out.println("Enter your custom User Id\n");
                            int key = sc.nextInt();
                            sc.nextLine();
                            System.out.println("Enter your custom Auction ID\n");
                            String auction_id = sc.nextLine();

//                        while (auction_id.isEmpty()) {
//                            System.out.println("Input cannot be empty. Please enter a valid value:");
//                            auction_id= sc.nextLine();
//                        }

                            // reason for adding sc.nextline() after every userinput
                            // after reading an integer input using nextInt(),
                            // there is still a newline character left in the input buffer.
                            // When we try to read the auction ID using nextLine() immediately after nextInt(), it consumes the leftover newline character and does not wait for user input, causing the program to skip the auction ID input.

                            System.out.println("Enter the name of Item you want the people to bid on\n");
                            String item_name = sc.nextLine();

                            System.out.println("Enter the base price above which you want people to bid");
                            int base_price = sc.nextInt();
                            sc.nextLine();
//                        OuterAVLTree newoutertree = new OuterAVLTree();
//                        ADS.OuterAVLTree tree = ads.new OuterAVLTree();
                            OuterAVLTree.Node newauction = tree.new Node(key, auction_id, item_name, base_price);

                            tree.root = tree.insert(tree.root, key, auction_id, item_name, base_price);
//                            tree.inOrder(newauction);
                            List<OuterAVLTree.Node> nodeList = new ArrayList<>();
                            //  newacution = ads.new Node(key,auction_id,item_name, base_price);
                            nodeList.add(newauction);  // this will add the created node to a arraylist
                            // and this will stop JVM from considering nodes as a garbage after execution
                            // of program

//                        a new auction is created sucesfully and the node is inserted in tree
                            System.out.println("Check the details please (true/false)");
                            System.out.println("User ID = " + newauction.key + "\nAuction ID = " + newauction.auction_id + "\nItem Name = " + newauction.item_name + "\nBase price = " + newauction.base_price);
                            Boolean check = sc.nextBoolean();
                            if (check) {
                                System.out.println("Auction for item" + " " + newauction.item_name + " " + "created successfully");
                            } else {
                                System.out.println("Enter details below");
                                newauction = null;            // making the value null so that JVM will make it eligible for garbage collection and it will be removed from memory
                                int user_id = sc.nextInt();       //key
                                String auctionid = sc.nextLine(); // auction_id
                                String itemname = sc.nextLine();  // itemname
                                int baseprice = sc.nextInt();     // baseprice
                                OuterAVLTree.Node newauction1 = tree.new Node(user_id, auctionid, itemname, baseprice);
                                tree.insert(newauction1, user_id, auctionid, itemname, baseprice);
                                System.out.println("New Auction Created");

                            }
                            break;

                        default:
                            System.out.println("invalid input");
                            break;

                    }
                    break;// break of case 1

                case 2:
                    System.out.println("Currently you are looking at ongoing auctions and auctions which are closed ");
                    tree.inOrder(tree.root);

                    break;

                default:
                    System.out.println("invalid input");
                    break;
            }
        }

    }
}