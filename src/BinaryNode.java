// BinaryNode class; stores a node in a tree.
//
// CONSTRUCTION: with (a) no parameters, or (b) an Object,
//     or (c) an Object, left child, and right child.
//
// *******************PUBLIC OPERATIONS**********************
// int size( )            --> Return size of subtree at node
// int height( )          --> Return height of subtree at node
// void printPostOrder( ) --> Print a postorder tree traversal
// void printInOrder( )   --> Print an inorder tree traversal
// void printPreOrder( )  --> Print a preorder tree traversal
// BinaryNode duplicate( )--> Return a duplicate tree

/**
 * Binary node class with recursive routines to
 * compute size and height.
 */
final class BinaryNode<AnyType>
{
    public BinaryNode( )
    {
        this( null, null, null, 0);
    }
    
    public BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt, int levelIn )
    {
        element = theElement;
        left    = lt;
        right   = rt;
        index   = levelIn;
        //asciiValue = ascii;
    }

    public BinaryNode( AnyType theElement)
{
    this(theElement, null, null, 0);
}
    public BinaryNode( AnyType theElement, int ascii)
    {
        this(theElement, null, null, ascii);
    }

    /**
     * Return the size of the binary tree rooted at t.
     */
    public static <AnyType> int size( BinaryNode<AnyType> t )
    {
        if( t == null )
            return 0;
        else
            return 1 + size( t.left ) + size( t.right );
    }

    /**
     * Return the height of the binary tree rooted at t.
     */
    public static <AnyType> int height( BinaryNode<AnyType> t )
    {
        if( t == null )
            return -1;
        else
            return 1 + Math.max( height( t.left ), height( t.right ) );
    }

    // Print tree rooted at current node using preorder traversal.
    public void printPreOrder( )
    {
        System.out.println( element );       // Node
        if( left != null )
            left.printPreOrder( );           // Left
        if( right != null )
            right.printPreOrder( );          // Right
    }


    // Print tree rooted at current node using postorder traversal.
    public void printPostOrder( )
    {
        if( left != null )
            left.printPostOrder( );          // Left
        if( right != null )
            right.printPostOrder( );         // Right
        System.out.println( element );       // Node
    }

    // Print tree rooted at current node using inorder traversal.
    public void printInOrder( )
    {
        if( left != null )
            left.printInOrder( );            // Left
        System.out.println( element + " : " + index );       // Node
        if( right != null )
            right.printInOrder( );           // Right
    }


    /**
     * Return a reference to a node that is the root of a
     * duplicate of the binary tree rooted at the current node.
     */
    public BinaryNode<AnyType> duplicate( )
    {
        BinaryNode<AnyType> root = new BinaryNode<AnyType>( element, null, null, 0 );

        if( left != null )            // If there's a left subtree
            root.left = left.duplicate( );    // Duplicate; attach
        if( right != null )          // If there's a right subtree
            root.right = right.duplicate( );  // Duplicate; attach
        return root;                      // Return resulting tree
    }

        //Binary Tree Node logic
    public void insert(BinaryNode node) {
        boolean goLeft = false;
        boolean goRight = false;

        if(node.getIndex() == 2*this.getIndex()+1){
                goLeft = true;
        }
        if(node.getIndex() == 2*this.getIndex()+2){
                goRight = true;
        }

        if (goLeft){
            if (left != null){
                left.insert(node);
            }   else {
                System.out.println("  Inserted " + node.element + " to left of node " + this.element);
                this.left = new BinaryNode(node.getElement(), null, null, node.getIndex());
                return;

            }
        }  else if (goRight){
            if (right != null){
                right.insert(node);
            }   else {
                System.out.println("  Inserted " + node.element + " to right of node " + this.element);
                this.right = new BinaryNode(node.getElement(), null, null, node.getIndex());
                return;
            }

            }  //else if ( index == 0 ) {

            //}

            else{
            if (left != null){
            left.insert(node);
            }
            if (right != null){
            right.insert(node);
            }
        }

    }
    
    //BST node add logic
    public void bstAdd (BinaryNode node){
        if (node.getIndex() <  this.index){
            if (left != null){
                left.bstAdd(node);
            }   else {
                System.out.println("  Inserted " + node.element + " to left of node " + this.element);
                this.left = new BinaryNode(node.getElement(), null, null, node.getIndex());
                return;

            }
        }  else if (node.getIndex() > this.index){
            if (right != null){
                right.bstAdd(node);
            }   else {
                System.out.println("  Inserted " + node.element + " to right of node " + this.element);
                this.right = new BinaryNode(node.getElement(), null, null, node.getIndex());
                 return;
            }

        }
    }

    public void setIndex(int i){
     index = i;
    }
    public int getIndex(){
        return index;
    }
    public AnyType getElement( )
    {
        return element;
    }
    
    public BinaryNode<AnyType> getLeft( )
    {
        return left;
    }
    
    public BinaryNode<AnyType> getRight( )
    {
        return right;
    }
    
    public void setElement( AnyType x )
    {
        element = x;
    }
    
    public void setLeft( BinaryNode<AnyType> t )
    {
        left = t;
    }
    
    public void setRight( BinaryNode<AnyType> t )
    {
        right = t;
    }

    public void treeFormatTraversal(){//begin
        int level = 3;
        if(this !=null)
            treeFormatHelper(this, level);
        else
            System.out.println("\nTree is Empty\n");
    }
    private void treeFormatHelper(BinaryNode node, Integer indentSpaces){

        if(node==null)
            return;
        treeFormatHelper(node.right,indentSpaces+1);
        for(int x=1;x<indentSpaces;x++)
            System.out.print("\t");
        System.out.println(node.getElement());
        treeFormatHelper(node.left,indentSpaces+1);
    }

    private AnyType             element;
    private BinaryNode<AnyType> left;
    private BinaryNode<AnyType> right;
    private int index;



    //private int asciiValue;
}
