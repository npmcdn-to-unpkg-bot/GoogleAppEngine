<? 
include('config.php'); 
if (isset($_POST['submitted'])) { 
foreach($_POST AS $key => $value) { $_POST[$key] = mysql_real_escape_string($value); } 
$sql = "INSERT INTO `customer` ( `customerusername` ,  `customerpassword` ,  `emailaddress` ,  `firstname` ,  `lastname` ,  `city` ,  `country` ,  `website` ,  `active` ,  `suspended` ,  `securityquestion` ,  `securityanswer` ,  `createdfromipaddress` ,  `adminid` ,  `adminmemberid` ,  `maxexecutioncount` ,  `executioncount` ,  `authorisedapps` ,  `timezone` ,  `emailaddressconfirmed` ,  `inserted`  ) VALUES(  '{$_POST['customerusername']}' ,  '{$_POST['customerpassword']}' ,  '{$_POST['emailaddress']}' ,  '{$_POST['firstname']}' ,  '{$_POST['lastname']}' ,  '{$_POST['city']}' ,  '{$_POST['country']}' ,  '{$_POST['website']}' ,  '{$_POST['active']}' ,  '{$_POST['suspended']}' ,  '{$_POST['securityquestion']}' ,  '{$_POST['securityanswer']}' ,  '{$_POST['createdfromipaddress']}' ,  '{$_POST['adminid']}' ,  '{$_POST['adminmemberid']}' ,  '{$_POST['maxexecutioncount']}' ,  '{$_POST['executioncount']}' ,  '{$_POST['authorisedapps']}' ,  '{$_POST['timezone']}' ,  '{$_POST['emailaddressconfirmed']}' ,  '{$_POST['inserted']}'  ) "; 
mysql_query($sql) or die(mysql_error()); 
echo "Added row.<br />"; 
echo "<a href='list.php'>Back To Listing</a>"; 
} 
?>

<form action='' method='POST'> 
<p><b>Customerusername:</b><br /><input type='text' name='customerusername'/> 
<p><b>Customerpassword:</b><br /><input type='text' name='customerpassword'/> 
<p><b>Emailaddress:</b><br /><input type='text' name='emailaddress'/> 
<p><b>Firstname:</b><br /><input type='text' name='firstname'/> 
<p><b>Lastname:</b><br /><input type='text' name='lastname'/> 
<p><b>City:</b><br /><input type='text' name='city'/> 
<p><b>Country:</b><br /><input type='text' name='country'/> 
<p><b>Website:</b><br /><input type='text' name='website'/> 
<p><b>Active:</b><br /><input type='text' name='active'/> 
<p><b>Suspended:</b><br /><input type='text' name='suspended'/> 
<p><b>Securityquestion:</b><br /><input type='text' name='securityquestion'/> 
<p><b>Securityanswer:</b><br /><input type='text' name='securityanswer'/> 
<p><b>Createdfromipaddress:</b><br /><input type='text' name='createdfromipaddress'/> 
<p><b>Adminid:</b><br /><input type='text' name='adminid'/> 
<p><b>Adminmemberid:</b><br /><input type='text' name='adminmemberid'/> 
<p><b>Maxexecutioncount:</b><br /><input type='text' name='maxexecutioncount'/> 
<p><b>Executioncount:</b><br /><input type='text' name='executioncount'/> 
<p><b>Authorisedapps:</b><br /><input type='text' name='authorisedapps'/> 
<p><b>Timezone:</b><br /><input type='text' name='timezone'/> 
<p><b>Emailaddressconfirmed:</b><br /><input type='text' name='emailaddressconfirmed'/> 
<p><b>Inserted:</b><br /><input type='text' name='inserted'/> 
<p><input type='submit' value='Add Row' /><input type='hidden' value='1' name='submitted' /> 
</form> 
