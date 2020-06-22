import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

//frame.removeall()
//frame.remove()

public class ImageGUI{
	static String image;
	static Image iTest;
	static Graphics gc;
	public static void setGUI() throws Exception{
		//declare main frame and window label
		JFrame frame = new JFrame("J Image Viewer");
		//declare buttons and button text
		JButton bt1 = new JButton("Open");
		JButton bt2 = new JButton("Save");
		JButton bt3 = new JButton("Flip X");
		JButton bt4 = new JButton("Flip Y");

		//setting close operation.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//setting layout using FlowLayout object
		frame.setLayout(new FlowLayout());

		//setting size of Jframe
		frame.setSize(900, 900);

		//declare a panel in the Jframe with a GBLayout
		JPanel panel = new JPanel(new GridBagLayout());

		//declare a variable to hold the grid constraints
		GridBagConstraints con = new GridBagConstraints();

		//add the panel, which has grid constraints, to the main Jframe
		frame.add(panel);

		//initialize the constraint grid coordinates
		con.gridx = 0;
		con.gridy = 0;

		//add btn1 to the panel at the constraint grid coordinates
		panel.add(bt1, con);

		//increment the x coordinates of the constraint grid
		con.gridx++;

		//add btn2 to the panel at the constraint grid coordinates
		panel.add(bt2,con);

		//increment the x coordinates of the constraint grid
		con.gridx++;

		//add btn3 to the panel at the constraint grid coordinates
		panel.add(bt3,con);

		//increment the x coordinates of the constraint grid
		con.gridx++;

		//add btn4 to the panel at the constraint grid coordinates
		panel.add(bt4,con);

		//make everything visible and display graphics
		frame.setVisible(true);
		gc = frame.getGraphics();// needs to come after this line: frame.setVisible(true);

		//This section sets the button actions
		bt1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				try{
					image = openFile();
					iTest = new Image(image);
					iTest.draw(gc, 100, 100);
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
			}
		});

		bt2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				saveFile();
			}
		});

		bt3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				iTest.flipX();
				iTest.draw(gc, 100, 100);
			}
		});

		bt4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				iTest.flipY();
				iTest.draw(gc, 100, 100);
			}
		});
		return;
	}

	//This defines the action of opening a file
	public static String openFile(){
		//Only jpg png bmp wbmp gif files work due to limitations of javax.imageio.*;
		FileDialog dialog = new FileDialog((Frame)null, "Select File to Open");
		dialog.setMode(FileDialog.LOAD);
		dialog.setVisible(true);
		String file = dialog.getDirectory() + dialog.getFile();
		System.out.println("Opening: " + file);
		return file;
	}

	//This defines the action of opening a file
	public static void saveFile(){
		//Only jpg png bmp wbmp gif files work due to limitations of javax.imageio.*;
		FileDialog dialog = new FileDialog((Frame)null, "Save your file");
		dialog.setMode(FileDialog.SAVE);
		dialog.setVisible(true);
		String file = dialog.getDirectory() + dialog.getFile();
		System.out.println("Saving to: " + file);
		try{
			iTest.write(file);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return;
	}

	public static void main(String[] args) throws Exception{
		try{
			setGUI();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
