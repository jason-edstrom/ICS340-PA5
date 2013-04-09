

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class TreePaintGUI extends JFrame  implements ActionListener
{
    TextArea untitled_5;
     Label lbl_Input;
     TextField tf_inpu;
     Button bt_Dra;
     Button bt_BuildBS;
     
   public TreePaintGUI()
   {
     getContentPane().setLayout(null);
     setupGUI();
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   void setupGUI()
   {
     	untitled_5 = new TextArea();
	untitled_5.setLocation(13,84);
	untitled_5.setSize(538,451);
	untitled_5.setBackground( new Color(-1) );
	untitled_5.setText("");
	untitled_5.setRows(5);
	untitled_5.setColumns(5);
	getContentPane().add(untitled_5);

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
	setSize(567,573);
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

        if ( obj == "Draw"){
            String input = new String (tf_inpu.getText());
            char[] inputArray = input.toCharArray();

        }

        if (obj == "Build BST"){

        }

    }
}
