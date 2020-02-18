import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
//frame.removeall()
//frame.remove()
public class ImageGUI{

	static String image;
	
	static Image iTest;
	
	static Graphics gc;

	public static void setGUI() throws Exception {

		JFrame frame = new JFrame("Image Viewer");

		JButton bt1 = new JButton("Open");
		JButton bt2 = new JButton("Save");
		JButton bt3 = new JButton("Flip X");
		JButton bt4 = new JButton("Flip Y");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     //setting close operation.
		frame.setLayout(new FlowLayout());		//setting layout using FlowLayout object
		frame.setSize(900, 900);				//setting size of Jframe

		JPanel panel = new JPanel(new GridBagLayout());//panel in frame

		GridBagConstraints con = new GridBagConstraints();

		frame.add(panel);

		con.gridx = 0;
		con.gridy = 0;

		panel.add(bt1, con);
		con.gridx++;

		panel.add(bt2,con);
		con.gridx++;

		panel.add(bt3,con);
		con.gridx++;

		panel.add(bt4,con);

		frame.setVisible(true);
		gc = frame.getGraphics();// needs to come after this line: frame.setVisible(true);

		bt1.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
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

		bt2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				saveFile();
			}
		});

		bt3.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{

				iTest.flipX();
				iTest.draw(gc, 100, 100);

			}
		});

		bt4.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{

				iTest.flipY();
				iTest.draw(gc, 100, 100);

			}
		});
		return;
	}
	public static String openFile(){
		//Only jpg png bmp wbmp gif files work due to limitations of javax.imageio.*;
		FileDialog dialog = new FileDialog((Frame)null, "Select File to Open");
		dialog.setMode(FileDialog.LOAD);

		dialog.setVisible(true);

		String file = dialog.getDirectory() + dialog.getFile();
		System.out.println("Opening: " + file);

		return file;
	}

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
	public static void main(String[] args) throws Exception {
		try{
			setGUI();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}


