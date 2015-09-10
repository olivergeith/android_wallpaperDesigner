<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
       "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
</head>
<body bgcolor="#222222">

<?php

if (!isset ($_FILES['image1'])) {
	$err_msg = 'The form was not sent in completely.';
} else {
	$image = $_FILES['image1'];
	if (!@move_uploaded_file($image['tmp_name'], $image['name']
	)) {
		$err_msg = "Error moving the file to its destination, "."please try again <a href='{$upload_page}'>here</a>.";
	}
}

?>  
</body>
</html>