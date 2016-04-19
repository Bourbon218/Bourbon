<H1>${param.name}の作成</H1>


<FORM ACTION="create">

	<INPUT TYPE="HIDDEN" NAME="cmd" VALUE="create">
	<INPUT TYPE="HIDDEN" NAME="name" VALUE="${param.name}">
	
	<TEXTAREA ROWS="15" COLS="60" NAME="content"></TEXTAREA>
	
	<BR>
	<INPUT TYPE="SUBMIT" VALUE="　作成　">
	<INPUT TYPE="BUTTON" VALUE="キャンセル" ONCLICK="location.href='refer'">

</FORM>
