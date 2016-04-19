<H1>ƒy[ƒW‚Ìˆê——</H1>

<c:forEach var="wikiPage" items="${list}">
	<c:url value="/refer" var="url">
    	<c:param name="name" value="${wikiPage.name}" />
	</c:url>
	<LI><A HREF="${url}">${wikiPage.name}</A><BR>
</c:forEach>
