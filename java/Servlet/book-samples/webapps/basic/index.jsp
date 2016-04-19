<%@page contentType="text/html; charset=Windows-31J"%>

<HTML>

<HEAD>
<TITLE>basic サンプル一覧</TITLE>
</HEAD>


<BODY>

<H1>basic サンプル一覧</H1>

<H2>入力パラメータ</H2>

<P><A HREF="input/echo.jsp">echo.jsp</A> →EchoServlet

<P><A HREF="input/echo2.jsp">echo2.jsp</A> →EchoServlet2(文字化け対策)

<P><A HREF="input/method.jsp">method.jsp</A> →MethodServlet

<P><A HREF="input/param.jsp">param.jsp</A> →ParamListServlet

<P><A HREF="input/guess.jsp">guess.jsp</A> →GuessServlet(練習問題)

<p>注：日本語の入力パラメータの文字化け回避にはTomcatの設定が必要です

<H2>入力チェックと値の変換</H2>

<P><A HREF="validate/eto.jsp">eto.jsp</A> →EtoServlet(入力チェックなし)

<P><A HREF="validate/eto2.jsp">eto2.jsp</A> →EtoServlet2(入力チェックあり)

<P><A HREF="validate/guess.jsp">guess.jsp</A> →GuessServlet(練習問題)


<H2>フィルタの利用</H2>

<P>ここから呼び出すサンプルはありません



<H2>画面遷移を行う</H2>

<P><A HREF="servlet/dispatch.ForwardServletA">ForwardServletA</A>(サーブレット→サーブレット)

<P><A HREF="servlet/dispatch.ForwardServlet2">ForwardServlet2</A>(サーブレット→ＪＳＰ)

<P><A HREF="servlet/dispatch.RandomWalkServlet">RandomWalkServlet</A>



<H2>オブジェクトのスコープとリクエスト属性</H2>

<P><A HREF="servlet/reqattr.ServletA">ServletA</A>(サーブレット→サーブレット)

<P><A HREF="servlet/reqattr.Servlet2A">Servlet2A</A>(自作クラスのインスタンス)

<P><A HREF="servlet/reqattr.Servlet3A">Servlet3A</A>(サーブレット→ＪＳＰ)

<P><A HREF="reqattr/guess.jsp">guess.jsp</A> →GuessServlet(練習問題)


<H2>セッションを使う</H2>

<P><A HREF="servlet/session.CounterServlet">CounterServlet</A>

<P><A HREF="servlet/session.SessionListServlet">SessionListServlet</A>

<P><A HREF="session/guess.jsp">guess.jsp</A> →GuessServlet(練習問題)


</BODY>
</HTML>