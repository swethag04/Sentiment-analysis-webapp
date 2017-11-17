<html>
<head>
<!-- Global site tag (gtag.js) - Google Analytics -->
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-109502326-1"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'UA-109502326-1');
</script>

<style>
body {
font-family: 'Lucida Console', 'Courier New', monospace;
font-size: 26pt;
line-height: 1.2em;
}
#content {
width: 760px;
text-align: left;
margin: 1em auto;
}
.permalink {
font-size: .5em;
color: #ddd;
line-height: 1em;
}
.permalink a {
text-decoration: none;
color: inherit;
}
.permalink a:hover {
text-decoration: underline;
}
</style>
<body>
<div id="content">
<%         
   String pred1 = (String)request.getAttribute("prediction");
   Cookie userreview = new Cookie("userreview",
 			  request.getParameter("review"));
   Cookie pred = new Cookie("pred",pred1);
  
   userreview.setMaxAge(60*60*10); 
   pred.setMaxAge(60*60*10); 
 
   // Add both the cookies in the response header.
   response.addCookie( userreview );
   response.addCookie( pred );
%>

<p> " <%=request.getAttribute("review") %> " </p>
<p> Sentiment Score: <%=request.getAttribute("score") %> </p>
<p> Sentiment: <%=request.getAttribute("prediction") %> </p>   
<p> Is the predicted sentiment correct ?</p>
<form  action = "Feedback" method = "POST">
  <input type="radio" name="userfeedback" value="yes"> Yes<br>
  <input type="radio" name="userfeedback" value="no"> No<br>
   <input type="submit" value="Submit">
  </form> 
<p></p>
<p> App built by: <a href="https://github.com/swethag04"> Swetha </a>  </p>
</div> 
</body>
</head></html>