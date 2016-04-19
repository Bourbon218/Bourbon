<H1>${wikiPage.name} の編集</H1>

<FORM ACTION="update">
	<INPUT TYPE="HIDDEN" NAME="cmd" VALUE="update">
	<INPUT TYPE="HIDDEN" NAME="name" VALUE="${wikiPage.name}">


	<TEXTAREA ROWS="15" COLS="60" NAME="content">${wikiPage.content}</TEXTAREA>
	
	<BR>
	<INPUT TYPE="SUBMIT" VALUE="　更新　">
	<INPUT TYPE="SUBMIT" VALUE="　削除　" ONCLICK="cmd.value='delete'">
	<INPUT TYPE="BUTTON" VALUE="キャンセル"  ONCLICK="location.href='refer'">

</FORM>
