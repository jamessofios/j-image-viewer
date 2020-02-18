import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.imageio.stream.*;

public class Image
{
	private int width, height;

	private int[][] pixels;

	public Image(String filename) throws Exception
	{
		this.read(filename);
	}

	public int getWidth()
	{
		return this.width;
	}

	public int getHeight()
	{
		return this.height;
	}

	public void read(String filename) throws Exception
	{
		File fileImage = new File(filename);

		BufferedImage bufImage = ImageIO.read(fileImage);

		this.width = bufImage.getWidth();

		this.height = bufImage.getHeight();

		pixels = new int[this.height][this.width];

		for (int row = 0; row < this.height; row++)
		{
			for (int col = 0; col < this.width; col++)
				pixels[row][col] = bufImage.getRGB(col, row);
		}

	} //end read()

	private BufferedImage createBufferedImage()
	{

		BufferedImage bufImage = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);

		for (int row = 0; row < this.height; row++)
		{
			for (int col = 0; col < this.width; col++)
			{
				bufImage.setRGB(col, row, this.pixels[row][col]);
			}
		}

		return bufImage;
	} //end createBufferedImage()

	public void flipY() {
		
		int[][] p = this.pixels;
		
		int[][] mirrored = new int[p.length][p[0].length];

		for(int i=0; i < p.length ; i++) {
			for(int j=(p[0].length - 1); j >= 0; j--)
				mirrored[i][p[0].length - (1 + j)] = (p[i][j]);
		}

		pixels = mirrored;
	} //end flipY()

	public void flipX() {

		int[][] p = this.pixels;

		int[][] flipped = new int[p.length][p[0].length];

		for(int r = 0; r < p.length; r++) {
			for(int c = 0; c < p[0].length; c++)
				flipped[flipped.length - (1+r)][flipped[0].length - (1+c)] = p[r][c];
		}

		pixels = flipped;
	}//end flipX()


	public void write(String filename) throws Exception
	{
		File fileImage = new File(filename);

		String ext = filename.substring(filename.indexOf('.') + 1);

		BufferedImage bufImage = createBufferedImage();

		ImageIO.write(bufImage, ext, fileImage);
	} //end write()

	public void draw(Graphics gc, int x, int y)
	{
		BufferedImage bufImage = createBufferedImage();

		gc.drawImage(bufImage, x, y, null);
	} //end draw()

} //end Class Image
