<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<Title> Movie Sentiment Analyzer</Title>
<style>
h1 {text-align:center;}
p {text-align:center;}
body {
font-family: 'Lucida Console', 'Courier New', monospace;
font-size: 26pt;
line-height: 1.2em;
}
#content {
width: 760px;
text-align: center;
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
</head>
<!-- Global site tag (gtag.js) - Google Analytics -->
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-109502326-1"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'UA-109502326-1');
</script>

<body>
<div id="content">

<form action = "Prediction" method = "POST">
<h3> SENTIMENT PREDICTOR APP</h3>
<%
	String str;
	String prediction = (String)request.getAttribute("pred");
	if (prediction!= null && prediction.equals("yes")){
		str = "Thanks for the feedback! ";
	}
	else if (prediction!= null && prediction.equals("no")){
		str = "Oops, thanks for letting us know.";
	}
	else{
		str = " "; 
	}
	out.println(str); 
%>
<h4> Type a Sentence </h4>
<textarea rows="10" cols="60" name="review"></textarea>
<br/>
<br/>
<input type = "submit" value = "Submit" />
</form>
<p></p>
<p> App built by: <a href="https://github.com/swethag04"> Swetha </a>  </p>
</div>
</body>
</html>