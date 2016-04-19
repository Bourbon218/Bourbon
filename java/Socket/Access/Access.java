import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.PrintWriter;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import javax.swing.text.Element;

public class Access extends JFrame implements ActionListener{

	JTextArea textArea;//テキストエリア
	JTextPane linePane;

	private Container c;

	PrintWriter out;

	JButton bs=new JButton("ServerExit");


	GridBagLayout gbl = new GridBagLayout();

	public Access(){

		//ウィンドウの作成c
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Access");
		setSize(500,500);

		c = getContentPane();
		//c.setLayout(new GridBagLayout());//レイアウトの設定
		c.setLayout(new BorderLayout());


		//画面を作成
		textArea = new JTextArea();//表示画面
		JScrollPane scrollpane1 =new JScrollPane(textArea); //スクロールペインに"textArea"を設置
		textArea.setBorder(BorderFactory.createEmptyBorder(0,2,0,0));
		scrollpane1.setPreferredSize(new Dimension(300,300));
		textArea.setLineWrap(true); //折り返しの設定
		textArea.setWrapStyleWord(true);
		scrollpane1.setRowHeaderView(new LineNumberView(textArea)); //LineNumber

		//配置１
		c.add(scrollpane1,BorderLayout.CENTER);
		c.add(bs,BorderLayout.SOUTH); //更新ボタン

		//ActionListenerに追加
		bs.addActionListener(this);

		textArea.setEditable(true);
		requestFocusInWindow();

		textArea.setEditable(false);//編集不可にする

	}

	//レイアウトの設定
	public void TextGrid(JScrollPane s,int x,int y,Container c){

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill=GridBagConstraints.BOTH;
		gbc.gridx=x;
		gbc.gridy=y;
		//gbc.gridwidth=w;
		//gbc.gridheight=h;
		gbl.setConstraints(s,gbc);
		c.add(s);

	}

	public void ButtonGrid(JButton b,int x,int y,int w,int h,Container c){

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill=GridBagConstraints.BOTH;
		gbc.gridx=x;
		gbc.gridy=y;
		gbc.gridwidth=w;
		gbc.gridheight=h;
		gbl.setConstraints(b,gbc);
		c.add(b);

	}


	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == bs){
			System.out.println("Server Exit!");
			System.exit(0);
		}      

	}

	public void setUserName(String userName){
		textArea.append(userName);		
		textArea.append("\n");
	}

}

//行番号表示
class LineNumberView extends JComponent {

	private static final int MARGIN = 5;
	private final JTextArea textArea;
	private final FontMetrics fontMetrics;
	//private final int topInset;
	private final int fontAscent;
	private final int fontHeight;
	private final int fontDescent;
	private final int fontLeading;

	public LineNumberView(JTextArea textArea) {
		this.textArea = textArea;
		Font font   = textArea.getFont();
		fontMetrics = getFontMetrics(font);
		fontHeight  = fontMetrics.getHeight();
		fontAscent  = fontMetrics.getAscent();
		fontDescent = fontMetrics.getDescent();
		fontLeading = fontMetrics.getLeading();
		//topInset  = textArea.getInsets().top;

		textArea.getDocument().addDocumentListener(new DocumentListener() {
			@Override public void insertUpdate(DocumentEvent e) {
				repaint();
			}
			@Override public void removeUpdate(DocumentEvent e) {
				repaint();
			}
			@Override public void changedUpdate(DocumentEvent e) {}
		});
		textArea.addComponentListener(new ComponentAdapter() {
			@Override public void componentResized(ComponentEvent e) {
				revalidate();
				repaint();
			}
		});
		Insets i = textArea.getInsets();
		setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(0, 0, 0, 1, Color.GRAY),
				BorderFactory.createEmptyBorder(i.top, MARGIN, i.bottom, MARGIN - 1)));
		setOpaque(true);
		setBackground(Color.WHITE);
		setFont(font);
	}
	private int getComponentWidth() {
		Document doc  = textArea.getDocument();
		Element root  = doc.getDefaultRootElement();
		int lineCount = root.getElementIndex(doc.getLength());
		int maxDigits = Math.max(3, String.valueOf(lineCount).length());
		Insets i = getBorder().getBorderInsets(this);
		return maxDigits * fontMetrics.stringWidth("0") + i.left + i.right;
		//return 48;
	}
	private int getLineAtPoint(int y) {
		Element root = textArea.getDocument().getDefaultRootElement();
		int pos = textArea.viewToModel(new Point(0, y));
		return root.getElementIndex(pos);
	}
	@Override public Dimension getPreferredSize() {
		return new Dimension(getComponentWidth(), textArea.getHeight());
	}
	@Override public void paintComponent(Graphics g) {
		g.setColor(getBackground());
		Rectangle clip = g.getClipBounds();
		g.fillRect(clip.x, clip.y, clip.width, clip.height);

		g.setColor(getForeground());
		int base  = clip.y;
		int start = getLineAtPoint(base);
		int end   = getLineAtPoint(base + clip.height);
		int y     = start * fontHeight;
		int rmg   = getBorder().getBorderInsets(this).right;
		for (int i = start; i <= end; i++) {
			String text = String.valueOf(i + 1);
			int x = getComponentWidth() - rmg - fontMetrics.stringWidth(text);
			y += fontAscent;
			g.drawString(text, x, y);
			y += fontDescent + fontLeading;
		}
	}		
}


