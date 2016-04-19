import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;

public class LifePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private boolean data[][];
	private int dataWidth;
	private int dataHeight;
	private Image offScreen;
	private Graphics offg;

	public LifePanel(){
		this(25, 25);
	}

	public LifePanel(int i, int j){
		dataWidth = i;
		dataHeight = j;
		setPreferredSize(new Dimension(dataWidth * 5,
				dataHeight * 5));
		initialData();
		addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent mouseevent){
				doMouseDown(mouseevent);
			}
		});
	}

	public void initialData(){
		data = new boolean[dataWidth][dataHeight];
		for(int i = 0; i < dataWidth; i++){
			for(int j = 0; j < dataHeight; j++)
				data[i][j] = false;

		}
	}

	public void doMouseDown(MouseEvent ev){
		int i = ev.getX() / 5;
		int j = ev.getY() / 5;
		if(i >= dataWidth || j >= dataHeight){
			return;
		} else {
			data[i][j] = !data[i][j];
			repaint();
			return;
		}
	}

	public void update(Graphics g){
		paint(g);
	}

	public void paint(Graphics g){
		if(offScreen == null){
			offScreen = createImage(dataWidth * 5, dataHeight * 5);
			offg = offScreen.getGraphics();
		}
		offg.setColor(Color.BLACK);
		offg.fillRect(0, 0, dataWidth * 5, dataHeight * 5);
		offg.setColor(Color.GREEN);
		for(int i = 0; i < dataWidth; i++){
			for(int j = 0; j < dataHeight; j++)
				if(data[i][j])
					offg.fillRect(i * 5, j * 5, 4, 4);

		}

		g.clearRect(0, 0, getSize().width, getSize().height);
		g.drawImage(offScreen, 0, 0, this);
	}

	public void checkAllLife(){
		boolean aflag[][] = new boolean[dataWidth][dataHeight];
		for(int j = 0; j < dataWidth; j++){
			for(int k = 0; k < dataHeight; k++){
				int i = checkLife(j, k);
				aflag[j][k] = false;
				if(i == 2 && data[j][k])
					aflag[j][k] = true;
				if(i == 3)
					aflag[j][k] = true;
				if(i < 2 || i > 3)
					aflag[j][k] = false;
			}

		}
		for(int l = 0; l < dataWidth; l++){
			for(int i1 = 0; i1 < dataHeight; i1++)
				data[l][i1] = aflag[l][i1];

		}
		repaint();
	}

	public int checkLife(int i, int j){
		int k = 0;
		int l = dataWidth;
		int i1 = dataHeight;
		return k = (k = (k = (k = (k = (k = (k =
				(k += data[(i + l + -1) % l][(j + i1 + -1) % i1] ? 1 : 0)
				+ (data[(i + l) % l][(j + i1 + -1) % i1] ? 1 : 0))
				+ (data[(i + l + 1) % l][(j + i1 + -1) % i1] ? 1 : 0))
				+ (data[(i + l + -1) % l][(j + i1) % i1] ? 1 : 0))
				+ (data[(i + l + 1) % l][(j + i1) % i1] ? 1 : 0))
				+ (data[(i + l + -1) % l][(j + i1 + 1) % i1] ? 1 : 0))
				+ (data[(i + l) % l][(j + i1 + 1) % i1] ? 1 : 0))
				+ (data[(i + l + 1) % l][(j + i1 + 1) % i1] ? 1 : 0);
	}
}