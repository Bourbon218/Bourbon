import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.event.*;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import java.awt.event.KeyEvent;

public class ClientTeacher extends JFrame implements ActionListener{

	//画面関連
	JTextArea textArea;//テキストエリア
	JTextPane linePane;

	private Container c;

	PrintWriter out;

	JButton bs=new JButton("送信");
	JButton bc=new JButton("クリア");


	GridBagLayout gbl = new GridBagLayout();

	public ClientTeacher(){

		//ウィンドウの作成c1
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Teacher");
		setSize(500,650);

		c = getContentPane();
		c.setLayout(new BorderLayout());


		//画面を作成
		textArea = new JTextArea();//入力画面
		JScrollPane scrollpane1 =new JScrollPane(textArea); //スクロールペインに"textArea"を設置
		scrollpane1.setRowHeaderView(new LineNumberView(textArea));
		textArea.setBorder(BorderFactory.createEmptyBorder(0,2,0,0));
		scrollpane1.setPreferredSize(new Dimension(300,300));
		textArea.setLineWrap(true); //折り返しの設定
		textArea.setWrapStyleWord(true);


		//プロンプト表示（未完成）
		//textArea.setText(NonEditableLineDocumentFilter.PROMPT);
		//((AbstractDocument) textArea.getDocument()).setDocumentFilter(new NonEditableLineDocumentFilter());

		//配置１
		c.add(scrollpane1,BorderLayout.CENTER);
		c.add(bs,BorderLayout.NORTH); //送信ボタン追加
		c.add(bc,BorderLayout.SOUTH); //消去ボタン追加


		//Gridの配置の設定
		//未完成
		//TextGrid(scrollpane1,0,0,c);
		//ButtonGrid(bs1,0,1,1,1,c);
		//ButtonGrid(bc1,1,1,1,1,c);

		//ActionListenerに追加
		bs.addActionListener(this);
		bc.addActionListener(this);

		//KeyListenerに追加
		textArea.addKeyListener(new KeyInput());

		//WindowListenerに追加
		addWindowListener(new WindowClosing());

		textArea.setEditable(true);
		requestFocusInWindow();

		//サーバ接続
		Socket socket = null;
		try {//別の端末で接続する場合、"localhost"をサーバーをたてた端末のIPアドレスに変更
			socket = new Socket("localhost", 10000);
		} catch (UnknownHostException e) {
			System.err.println("IP アドレス判定不可: " + e);
		} catch (IOException e) {
			System.err.println("エラー: " + e);
		}

		MesgRecvThread mrt = new MesgRecvThread(socket);
		mrt.start();
	}


	//メッセージ送受信スレッド
	public class MesgRecvThread extends Thread {

		Socket socket;

		public MesgRecvThread(Socket s){
			socket = s;
		}

		//通信管理
		public void run() {
			try{
				InputStreamReader sisr = new InputStreamReader(socket.getInputStream());
				BufferedReader br = new BufferedReader(sisr);
				out = new PrintWriter(socket.getOutputStream(), true);

				//ユーザー名を送る                                                                                                                            
				out.println("user:Teacher");

				while(true) {
					String inputLine = br.readLine();
					if (inputLine == null){
						break;
					}
				}
				socket.close();
			} catch (IOException e) {
				System.err.println("エラーが発生しました: " + e);
			}
		}
	}


	//コマンド実行メソッド
	public void process(String[] cmd) throws IOException{
		Process proc = Runtime.getRuntime().exec(cmd);
		InputStream isr = proc.getInputStream();
		InputStream esr = proc.getErrorStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(isr));
		BufferedReader ebr = new BufferedReader(new InputStreamReader(esr));
		String line,eline;

		textArea.append("\n");
		while(true){
			line=br.readLine();
			eline=ebr.readLine();
			if(line == null && eline == null){
				break;
			}
			//エラー結果の出力
			else if(eline!=null){
				textArea.append(System.getProperty("line.separator"));
				textArea.append(eline);
			}
			//結果の出力
			else if(line!=null){
				textArea.append(System.getProperty("line.separator"));
				textArea.append(line);
			}
		}
	}

	//テキストエリアでのキー処理
	//連続してうてない問題発生
	class KeyInput extends KeyAdapter{
		public void keyPressed(KeyEvent e){
			String text = textArea.getText();
			String[] cmd =text.split(" ");
			try{
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					process(cmd);
				}
			}
			catch(IOException ioe){
			}
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

			textArea.getDocument().addDocumentListener(new DocumentListener() {
				@Override public void insertUpdate(DocumentEvent e) {
					repaint();
				}
				@Override public void removeUpdate(DocumentEvent e) {
					repaint();
				}
				@Override public void changedUpdate(DocumentEvent e){
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

	/*    
    //プロンプト表示クラス
        class NonEditableLineDocumentFilter extends DocumentFilter{
	public static final String LB = "\n";
	public static final String PROMPT = "> ";

	    //入力した場合
	@Override public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
	    if (string == null) {
		return;
	    } else {
		replace(fb, offset, 0, string, attr);
	    }
	}
	    //削除した場合
	@Override public void remove(DocumentFilter.FilterBypass fb, int offset, int length) throws BadLocationException {
	    replace(fb, offset, length, "", null);
	}

	    //操作後の置換
	    @Override public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException{

	    Document doc = fb.getDocument();
	    Element root = doc.getDefaultRootElement();
	    int count = root.getElementCount();
	    int index = root.getElementIndex(offset);
	    Element cur = root.getElement(index);
	    int promptPosition = cur.getStartOffset() + PROMPT.length();

	    if (index == count - 1 && offset - promptPosition >= 0) {
		String str = text;
		if (LB.equals(str)) {
		    String line= doc.getText(promptPosition, offset - promptPosition);
		    String[] args = line.split(" ");

		     if (args==null) {
			 str = String.format("%n%s", PROMPT);
		     } else {
			 try{
			     process(args);
			 }
			 catch(IOException ie){}
		     }
		}
		fb.replace(offset, length, str, attrs);
	    }
	    }
	}
	 */



	//送信ボタンが押されたときの処理
	public void actionPerformed(ActionEvent a){
		if(a.getActionCommand()=="送信"){
			String msg=textArea.getText();
			// textArea.setText("");
			if(msg.length()>0){
				out.println(msg);
				out.println("-------------------------------------------------------");
				out.flush();
			}
		}

		else if(a.getActionCommand()=="クリア"){
			textArea.setText("");
		}

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

	//メイン関数
	public static void main(String[] args) {

		ClientTeacher ct = new ClientTeacher();

		ct.setVisible(true);

	}
}


