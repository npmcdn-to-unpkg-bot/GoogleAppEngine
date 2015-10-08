<? 
include('config.php'); 
if (isset($_GET['id']) ) { 
$id = (int) $_GET['id']; 
if (isset($_POST['submitted'])) { 
foreach($_POST AS $key => $value) { $_POST[$key] = mysql_real_escape_string($value); } 
$sql = "UPDATE `employee` SET  `first_name` =  '{$_POST['first_name']}' ,  `last_name` =  '{$_POST['last_name']}' ,  `username` =  '{$_POST['username']}' ,  `password` =  '{$_POST['password']}' ,  `email` =  '{$_POST['email']}' ,  `email_notify` =  '{$_POST['email_notify']}' ,  `phone` =  '{$_POST['phone']}' ,  `address` =  '{$_POST['address']}' ,  `active` =  '{$_POST['active']}' ,  `groupname` =  '{$_POST['groupname']}' ,  `last4ssn` =  '{$_POST['last4ssn']}' ,  `badge_num` =  '{$_POST['badge_num']}' ,  `hiredate` =  '{$_POST['hiredate']}' ,  `startdate` =  '{$_POST['startdate']}' ,  `supervisor_flag` =  '{$_POST['supervisor_flag']}'   WHERE `id` = '$id' "; 
mysql_query($sql) or die(mysql_error()); 
echo (mysql_affected_rows()) ? "Edited row.<br />" : "Nothing changed. <br />"; 
echo "<a href='list.php'>Back To Listing</a>"; 
} 
$row = mysql_fetch_array ( mysql_query("SELECT * FROM `employee` WHERE `id` = '$id' ")); 
?>

<form action='' method='POST'> 
<p><b>First Name:</b><br /><input type='text' name='first_name' value='<?= stripslashes($row['first_name']) ?>' /> 
<p><b>Last Name:</b><br /><input type='text' name='last_name' value='<?= stripslashes($row['last_name']) ?>' /> 
<p><b>Username:</b><br /><input type='text' name='username' value='<?= stripslashes($row['username']) ?>' /> 
<p><b>Password:</b><br /><input type='text' name='password' value='<?= stripslashes($row['password']) ?>' /> 
<p><b>Email:</b><br /><input type='text' name='email' value='<?= stripslashes($row['email']) ?>' /> 
<p><b>Email Notify:</b><br /><input type='text' name='email_notify' value='<?= stripslashes($row['email_notify']) ?>' /> 
<p><b>Phone:</b><br /><input type='text' name='phone' value='<?= stripslashes($row['phone']) ?>' /> 
<p><b>Address:</b><br /><input type='text' name='address' value='<?= stripslashes($row['address']) ?>' /> 
<p><b>Active:</b><br /><input type='text' name='active' value='<?= stripslashes($row['active']) ?>' /> 
<p><b>Groupname:</b><br /><input type='text' name='groupname' value='<?= stripslashes($row['groupname']) ?>' /> 
<p><b>Last4ssn:</b><br /><input type='text' name='last4ssn' value='<?= stripslashes($row['last4ssn']) ?>' /> 
<p><b>Badge Num:</b><br /><input type='text' name='badge_num' value='<?= stripslashes($row['badge_num']) ?>' /> 
<p><b>Hiredate:</b><br /><input type='text' name='hiredate' value='<?= stripslashes($row['hiredate']) ?>' /> 
<p><b>Startdate:</b><br /><input type='text' name='startdate' value='<?= stripslashes($row['startdate']) ?>' /> 
<p><b>Supervisor Flag:</b><br /><input type='text' name='supervisor_flag' value='<?= stripslashes($row['supervisor_flag']) ?>' /> 
<p><input type='submit' value='Edit Row' /><input type='hidden' value='1' name='submitted' /> 
</form> 
<? } ?> 
