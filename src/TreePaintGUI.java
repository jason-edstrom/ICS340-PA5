

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class TreePaintGUI extends JFrame  implements ActionListener
{
    
    //GUI Build
    JPanel untitled_5;
     Label lbl_Input;
     TextField tf_inpu;
     Button bt_Dra;
     Button bt_BuildBS;
    JScrollPane jsp;
    BinaryNode tree = new BinaryNode();
    BinaryNode bsTree = new BinaryNode();
   public TreePaintGUI()
   {
     getContentPane().setLayout(null);
     setupGUI();
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   void setupGUI()
   {
    untitled_5 = new JPanel();
	untitled_5.setLocation(13,84);
	untitled_5.setSize(538,451);
	//untitled_5.setBackground( new Color(-1) );
	//untitled_5.setText("");
	//untitled_5.setRows(5);
	//untitled_5.setColumns(5);
    jsp = new JScrollPane(untitled_5);
	getContentPane().add(jsp);



	lbl_Input = new Label();
	lbl_Input.setLocation(7,7);
	lbl_Input.setSize(40,32);
	lbl_Input.setText("Input:");
	getContentPane().add(lbl_Input);

	tf_inpu = new TextField();
	tf_inpu.setLocation(50,7);
	tf_inpu.setSize(501,34);
	tf_inpu.setBackground( new Color(-1) );
	tf_inpu.setText("");
	tf_inpu.setColumns(10);
	getContentPane().add(tf_inpu);

	bt_Dra = new Button();
	bt_Dra.setLocation(155,46);
	bt_Dra.setSize(90,32);
	bt_Dra.setLabel("Draw");
	getContentPane().add(bt_Dra);

	bt_BuildBS = new Button();
	bt_BuildBS.setLocation(280,47);
	bt_BuildBS.setSize(90,32);
	bt_BuildBS.setLabel("Build BST");
	getContentPane().add(bt_BuildBS);


   bt_BuildBS.addActionListener(this);
   bt_Dra.addActionListener(this);

	setTitle("TreePaintGUI");
	setSize(567,150);
	setVisible(true);
	setResizable(true);


	
   }
    public static void main( String args[] )
   {
     new TreePaintGUI();
   }



    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object obj = actionEvent.getSource();
        System.out.println(obj);
        
        //Empty check
        if (tf_inpu.getText().isEmpty() || tf_inpu.getText().length() == 0){
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame,
                    "Your input is empty.  Please add some text to the field.",
                    "No Input Found",
                    JOptionPane.ERROR_MESSAGE);

        } else{

            //Draw button logic
        if ( obj == bt_Dra){
            String input = new String (tf_inpu.getText());
            char[] inputArray = input.toCharArray();

            buildBinaryTree(inputArray);
            ColorSwingPrinter printer = new ColorSwingPrinter ();
            printer.nodeprint (tree, "Binary Tree");
            tree = null;
            tree = new BinaryNode();
        }
            //BST button logic
        if (obj == bt_BuildBS){
            String input = new String (tf_inpu.getText());
            String rebuilt = null;
            boolean isEven = false;
            if (input.length() % 2 == 0){
                isEven = true;
            }

            if (isEven){
               rebuilt =  input.charAt((input.length()/2)-1) + input.substring(0, (input.length()/2)-1) +input.substring((input.length()/2));
            } else{
                rebuilt =  input.charAt((input.length()/2)) + input.substring(0, (input.length()/2)) +input.substring((input.length()/2)+1);
            }
            char[] inputArray = rebuilt.toCharArray();
            buildBST(inputArray);
            ColorSwingPrinter printer = new ColorSwingPrinter ();
            printer.nodeprint (bsTree, "Binary Search Tree");
            bsTree = null;
            bsTree = new BinaryNode();

        }

        }

    }
        //Binary Tree Build
    public void buildBinaryTree(char[] input){
        long before = System.currentTimeMillis() ;
        int k = 0;
        for (char letter : input){

            //BinaryNode currentTree = new BinaryNode();
             BinaryNode binaryNode = new BinaryNode(letter, null, null, k );
            //currentTree = tree.addNode(binaryNode);
            //tree = currentTree;
            if (tree.getElement() == null){
                tree = binaryNode;
            } else{
                k++;
                binaryNode.setIndex(k);
            tree.insert(binaryNode);
            }
            //tree.setElement(currentTree.getElement());
           // tree.setLeft(currentTree.getLeft());
            //tree.setRight(currentTree.getRight());
        }
        System.out.println("Tree built");
        //tree.printInOrder();
        tree.treeFormatTraversal();
        long after = System.currentTimeMillis() ;
        System.out.println("Time to build and print: " +(after - before));

    }
        //Binary Search Tree build
    public void buildBST (char[] input){
        for (char letter : input){


            BinaryNode binaryNode = new BinaryNode(letter, null, null, CharToASCII(letter) );


            if (bsTree.getElement() == null){
                bsTree = binaryNode;
            } else{

                bsTree.bstAdd(binaryNode);
            }
    }
    }

    public static int CharToASCII(final char character){
        return (int)character;
    }

    static int countNodes( BinaryNode root) {
        // Count the nodes in the binary tree to which
        // root points, and return the answer.
        if ( root == null )
            return 0;  // The tree is empty.  It contains no nodes.
        else {
            int count = 1;   // Start by counting the root.
            //count += countNodes(root.left);  // Add the number of nodes
            //     in the left subtree.
            //count += countNodes(root.right); // Add the number of nodes
            //    in the right subtree.
            return count;  // Return the total.
        }
    } // end countNodes()
    
    //Tree printing
    
    abstract class NodePrinter {

        abstract void nodeprint (BinaryNode root, String title);
        int max (int a, int b) { return (a > b) ? a : b; }
        int depth (BinaryNode n)
        {
            if (n.getLeft() == null && n.getRight() == null) return 1;
            if (n.getLeft() == null) return 1 + depth (n.getRight());
            if (n.getRight() == null) return 1 + depth (n.getLeft());
            return 1 + max (depth (n.getLeft()), depth (n.getRight()));
        }
    }

    class ColorSwingPrinter extends NodePrinter {

        void nodeprint (BinaryNode root, String title) {
            JFrame jf = new JFrame (title);
            jf.setSize (650 , 520);
            jf.setLocationRelativeTo (null);
            JScrollPane scroll = new JScrollPane(new TreeCanvas (root, this));
            jf.add (scroll);
            jf.setDefaultCloseOperation (WindowConstants.DISPOSE_ON_CLOSE);
            jf.setVisible (true);
        }
    }

    class TreeCanvas extends JPanel {

        private BinaryNode root;
        private NodePrinter np;

        public TreeCanvas (BinaryNode root, NodePrinter np) {
            this.root = root;
            this.np = np;
            d = np.depth (root);
            rows = (2 * d); // - 1
            cols = 2 << d;
        }

        private int d;
        private int rows;
        private int cols;

        // @override
        public void paint (Graphics g) {
            Dimension dim = getSize ();
            int xf = dim.width / cols;
            int yf = dim.height / rows;
            int fontsize = (xf + yf) / 2;
            g.setFont (g.getFont().deriveFont (fontsize* 1.5f));
            xyPrint (root, dim.width/2, dim.width/2, 1, xf, yf, g);
        }


        /**
         ___50 60 70__________________
         10    |     x0    x0-x1:  (50,30) - (60, 10)
         20    |    /  \   x0-x2:  (60,10) - (70, 30)
         30    |  x1    x2
         */
        void xyPrint (BinaryNode n, int x, int dx, int y, int xf, int yf, Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setStroke (new BasicStroke (3.0f));

            g.drawString ("" + n.getElement(), x - xf, (y+1) * yf);
            g.setColor (Color.BLACK);
            if (n.getLeft() != null) {
                g.drawLine (x - (dx/2) + xf, (y+2) * yf, x, (y+1) * yf); // line:Up
                xyPrint (n.getLeft(), x - dx/2, dx/2, y + 2, xf, yf, g);
            }
            if (n.getRight() != null) {
                g.drawLine (x + xf, (y+1) * yf, x + (dx/2), (y+2) * yf); // line down
                xyPrint (n.getRight(), x + dx/2, dx/2, y + 2, xf, yf, g);
            }
        }
    }

}


