<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
       "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
</head>
<body bgcolor="#222222">
<table border="0" cellpadding="6">

<?php
  $files = glob("*.jpg");
foreach($files as $jpg){    
	$zipname = str_replace (".jpg" , ".zip" , $jpg);
	print "<tr><a href=\"".$zipname."\"> <img border=\"0\" src=\"".$jpg."\" width=\"400\"></a></tr>";
}
?>  
</table>
</body>
</html>