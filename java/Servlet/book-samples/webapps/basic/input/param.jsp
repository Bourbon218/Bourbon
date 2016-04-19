<%@page contentType="text/html; charset=Windows-31J"%>

<HTML>
<BODY>

<H2>いろんなパラメータ</H2>


<FORM ACTION="../servlet/input.ParamListServlet">


	<P>テキストフィールド
	<INPUT TYPE="text" NAME="text1" VALUE="テキストフィールド">

	<P>テキストエリア
	<TEXTAREA NAME="textarea1" COLS="30" ROWS="5">テキストエリア</TEXTAREA>

	<P>ラジオボタン
	<INPUT TYPE="RADIO" NAME="radio1" VALUE="value1" CHECKED>左
	<INPUT TYPE="RADIO" NAME="radio1" VALUE="value2">右

	<P>チェックボックス
	<INPUT TYPE="CHECKBOX" NAME="check1">ゴルフ
	<INPUT TYPE="CHECKBOX" NAME="check2">テニス
	<INPUT TYPE="CHECKBOX" NAME="check3">サッカー

	<P>セレクト
	<SELECT NAME="lang" SIZE="3" MULTIPLE="true">
		<OPTION VALUE="sel1">VB
		<OPTION VALUE="sel2">C
		<OPTION VALUE="sel3">Java
	</SELECT>

	<P>
	<INPUT TYPE="SUBMIT">
	<INPUT TYPE="RESET">
	
</FORM>


</BODY>
</HTML>
