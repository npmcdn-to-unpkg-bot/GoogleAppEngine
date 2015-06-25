<? 
include('config.php'); 
if (isset($_POST['submitted'])) { 
foreach($_POST AS $key => $value) { $_POST[$key] = mysql_real_escape_string($value); } 
$sql = "INSERT INTO `employee` ( `first_name` ,  `last_name` ,  `username` ,  `password` ,  `email` ,  `email_notify` ,  `phone` ,  `address` ,  `active` ,  `groupname` ,  `last4ssn` ,  `badge_num` ,  `hiredate` ,  `startdate` ,  `supervisor_flag`  ) VALUES(  '{$_POST['first_name']}' ,  '{$_POST['last_name']}' ,  '{$_POST['username']}' ,  '{$_POST['password']}' ,  '{$_POST['email']}' ,  '{$_POST['email_notify']}' ,  '{$_POST['phone']}' ,  '{$_POST['address']}' ,  '{$_POST['active']}' ,  '{$_POST['groupname']}' ,  '{$_POST['last4ssn']}' ,  '{$_POST['badge_num']}' ,  '{$_POST['hiredate']}' ,  '{$_POST['startdate']}' ,  '{$_POST['supervisor_flag']}'  ) "; 
mysql_query($sql) or die(mysql_error()); 
echo "Added row.<br />"; 
echo "<a href='list.php'>Back To Listing</a>"; 
} 
?>

<form action='' method='POST'> 
<p><b>First Name:</b><br /><input type='text' name='first_name'/> 
<p><b>Last Name:</b><br /><input type='text' name='last_name'/> 
<p><b>Username:</b><br /><input type='text' name='username'/> 
<p><b>Password:</b><br /><input type='text' name='password'/> 
<p><b>Email:</b><br /><input type='text' name='email'/> 
<p><b>Email Notify:</b><br /><input type='text' name='email_notify'/> 
<p><b>Phone:</b><br /><input type='text' name='phone'/> 
<p><b>Address:</b><br /><input type='text' name='address'/> 
<p><b>Active:</b><br /><input type='text' name='active'/> 
<p><b>Groupname:</b><br /><input type='text' name='groupname'/> 
<p><b>Last4ssn:</b><br /><input type='text' name='last4ssn'/> 
<p><b>Badge Num:</b><br /><input type='text' name='badge_num'/> 
<p><b>Hiredate:</b><br /><input type='text' name='hiredate'/> 
<p><b>Startdate:</b><br /><input type='text' name='startdate'/> 
<p><b>Supervisor Flag:</b><br /><input type='text' name='supervisor_flag'/> 
<p><input type='submit' value='Add Row' /><input type='hidden' value='1' name='submitted' /> 
</form> 
