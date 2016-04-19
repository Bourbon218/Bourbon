import java.net.*;
import java.util.Properties;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.event.*;
import java.awt.Dimension;
import javax.swing.JScrollPane;

public class ClientStudent extends JFrame implements ActionListener{

	BufferedWriter out2 = null;
	BufferedReader in2 = null;

	//画面関連
	JTextArea textArea;//テキストエリア
	private Container c;
	PrintWriter out;
	JButton bc=new JButton("復元");
	MesgRecvThread mrt;

	public ClientStudent(){

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Student");
		setSize(500,450);
		c = getContentPane();
		c.setLayout(new BorderLayout());//レイアウトの設定

		//画面を作成
		textArea=new JTextArea(40,50);//出力画面
		JScrollPane scrollpane =new JScrollPane(textArea);
		scrollpane.setRowHeaderView(new LineNumberView(textArea));
		textArea.setBorder(BorderFactory.createEmptyBorder(0,2,0,0));
		scrollpane.setPreferredSize(new Dimension(650,600));
		textArea.setLineWrap(true); //折り返しの設定
		textArea.setWrapStyleWord(true);

		c.add(scrollpane,BorderLayout.CENTER);
		c.add(bc,BorderLayout.SOUTH);

		//ActionListenerの設定
		bc.addActionListener(this);
		//windowListenerの設定
		addWindowListener(new WindowClosing());

		textArea.setEditable(false);//編集不可

		//サーバ接続
		Socket socket = null;
		try { 
			//"localhost"をサーバーをたてた端末のIPアドレスに変更
			socket = new Socket("localhost", 10000);

		} catch (UnknownHostException e) {
			System.err.println("IP アドレス判定不可: " + e);
		} catch (IOException e) {
			System.err.println("エラー: " + e);
		}

		mrt = new MesgRecvThread(socket);
		mrt.start();
	}

	//メッセージ受信のスレッド
	public class MesgRecvThread extends Thread {

		Socket socket;

		public MesgRecvThread(Socket s){
			socket = s;
		}

		//通信管理
		public void run() {  /**  ここ重要  **/
			try{
				InputStreamReader sisr = new InputStreamReader(socket.getInputStream());
				BufferedReader br = new BufferedReader(sisr);
				out = new PrintWriter(socket.getOutputStream(), true);

				//ユーザー名を送る
				out.println("user:"+System.getProperty("user.name"));

				while(true) {

					String inputLine = br.readLine();
					if (inputLine != null){
						textArea.append(System.getProperty("line.separator"));
						textArea.append(inputLine);

					}
					else {
						break;
					}
				}
				socket.close(); //クローズ
			} catch (IOException e) {
				System.err.println("エラーが発生しました: " + e);
			}
		}

		//送信用
		public void export(){
			String inputLine = "load";
			out.println(inputLine);
		}
	}

	//Window制御                                                            
	class WindowClosing extends WindowAdapter{
		public void windowClosing(WindowEvent e){
			String inputLine="exit";
			out.println(inputLine);
		}
	}

	//行番号表示クラス                                                                                  
	class LineNumberView extends JComponent{
		private static final int MARGIN = 5;
		private final JTextArea textArea;
		private final FontMetrics fontMetrics;
		//private final int topInset;                                                                             
		private final int fontAscent;
		private final int fontHeight;
		private final int fontDescent;
		private final int fontLeading;

		public LineNumberView(JTextArea textArea) {
			super();
			this.textArea = textArea;
			Font font   = textArea.getFont();
			fontMetrics = getFontMetrics(font);
			fontHeight  = fontMetrics.getHeight();
			fontAscent  = fontMetrics.getAscent();
			fontDescent = fontMetrics.getDescent();
			fontLeading = fontMetrics.getLeading();
			//topInset    = textArea.getInsets().top;                                                             

			textArea.getDocument().addDocumentListener(new DocumentListener() {
				@Override public void insertUpdate(DocumentEvent e) {
					repaint();
				}
				@Override public void removeUpdate(DocumentEvent e) {
					repaint();
				}
				@Override public void changedUpdate(DocumentEvent e) {
				}
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
					BorderFactory.createEmptyBorder(i.top, MARGIN, i.bottom,MARGIN - 1)));
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
			for (int i = start-1; i <= end; i++) {
				String text = String.valueOf(i + 1);
				int x = getComponentWidth() - rmg - fontMetrics.stringWidth(text);
				y += fontAscent;
				g.drawString(text, x, y);
				y += fontDescent + fontLeading;
			}
		}
	}


	//ボタンが押されたときの処理
	public void actionPerformed(ActionEvent a){
		if(a.getActionCommand()=="復元"){
			mrt.export();
		}
	}



	//メイン関数
	public static void main(String[] args) {

		ClientStudent cs = new ClientStudent();  

		cs.setVisible(true);

		String userName = null;

		userName = System.getProperty("user.name");
	}
	public static String getHostName() {
		try {
			return System.getProperty("user.name");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "UnknownHost";
	}
}