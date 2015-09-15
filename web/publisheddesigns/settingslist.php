<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
       "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
</head>
<body bgcolor="#222222">
<table border="0" cellpadding="6">

<?php
$files = glob("*.jpg");
  
// Sort files by modified time, latest to earliest
// Use SORT_ASC in place of SORT_DESC for earliest to latest
array_multisort(
  array_map( 'filemtime', $files ),
  SORT_NUMERIC,
  SORT_DESC,
  $files
);  
  
foreach($files as $jpg){    
	$zipname = str_replace (".jpg" , ".zip" , $jpg);
	print "<tr><a href=\"".$zipname."\"> <img border=\"0\" src=\"".$jpg."\" width=\"400\"></a></tr>";
}
?>  
</table>
</body>
</html>