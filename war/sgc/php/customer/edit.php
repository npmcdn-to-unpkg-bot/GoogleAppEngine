<? 
include('config.php'); 
if (isset($_GET['id']) ) { 
$id = (int) $_GET['id']; 
if (isset($_POST['submitted'])) { 
foreach($_POST AS $key => $value) { $_POST[$key] = mysql_real_escape_string($value); } 
$sql = "UPDATE `customer` SET  `customerusername` =  '{$_POST['customerusername']}' ,  `customerpassword` =  '{$_POST['customerpassword']}' ,  `emailaddress` =  '{$_POST['emailaddress']}' ,  `firstname` =  '{$_POST['firstname']}' ,  `lastname` =  '{$_POST['lastname']}' ,  `city` =  '{$_POST['city']}' ,  `country` =  '{$_POST['country']}' ,  `website` =  '{$_POST['website']}' ,  `active` =  '{$_POST['active']}' ,  `suspended` =  '{$_POST['suspended']}' ,  `securityquestion` =  '{$_POST['securityquestion']}' ,  `securityanswer` =  '{$_POST['securityanswer']}' ,  `createdfromipaddress` =  '{$_POST['createdfromipaddress']}' ,  `adminid` =  '{$_POST['adminid']}' ,  `adminmemberid` =  '{$_POST['adminmemberid']}' ,  `maxexecutioncount` =  '{$_POST['maxexecutioncount']}' ,  `executioncount` =  '{$_POST['executioncount']}' ,  `authorisedapps` =  '{$_POST['authorisedapps']}' ,  `timezone` =  '{$_POST['timezone']}' ,  `emailaddressconfirmed` =  '{$_POST['emailaddressconfirmed']}' ,  `inserted` =  '{$_POST['inserted']}'   WHERE `id` = '$id' "; 
mysql_query($sql) or die(mysql_error()); 
echo (mysql_affected_rows()) ? "Edited row.<br />" : "Nothing changed. <br />"; 
echo "<a href='list.php'>Back To Listing</a>"; 
} 
$row = mysql_fetch_array ( mysql_query("SELECT * FROM `customer` WHERE `id` = '$id' ")); 
?>

<form action='' method='POST'> 
<p><b>Customerusername:</b><br /><input type='text' name='customerusername' value='<?= stripslashes($row['customerusername']) ?>' /> 
<p><b>Customerpassword:</b><br /><input type='text' name='customerpassword' value='<?= stripslashes($row['customerpassword']) ?>' /> 
<p><b>Emailaddress:</b><br /><input type='text' name='emailaddress' value='<?= stripslashes($row['emailaddress']) ?>' /> 
<p><b>Firstname:</b><br /><input type='text' name='firstname' value='<?= stripslashes($row['firstname']) ?>' /> 
<p><b>Lastname:</b><br /><input type='text' name='lastname' value='<?= stripslashes($row['lastname']) ?>' /> 
<p><b>City:</b><br /><input type='text' name='city' value='<?= stripslashes($row['city']) ?>' /> 
<p><b>Country:</b><br /><input type='text' name='country' value='<?= stripslashes($row['country']) ?>' /> 
<p><b>Website:</b><br /><input type='text' name='website' value='<?= stripslashes($row['website']) ?>' /> 
<p><b>Active:</b><br /><input type='text' name='active' value='<?= stripslashes($row['active']) ?>' /> 
<p><b>Suspended:</b><br /><input type='text' name='suspended' value='<?= stripslashes($row['suspended']) ?>' /> 
<p><b>Securityquestion:</b><br /><input type='text' name='securityquestion' value='<?= stripslashes($row['securityquestion']) ?>' /> 
<p><b>Securityanswer:</b><br /><input type='text' name='securityanswer' value='<?= stripslashes($row['securityanswer']) ?>' /> 
<p><b>Createdfromipaddress:</b><br /><input type='text' name='createdfromipaddress' value='<?= stripslashes($row['createdfromipaddress']) ?>' /> 
<p><b>Adminid:</b><br /><input type='text' name='adminid' value='<?= stripslashes($row['adminid']) ?>' /> 
<p><b>Adminmemberid:</b><br /><input type='text' name='adminmemberid' value='<?= stripslashes($row['adminmemberid']) ?>' /> 
<p><b>Maxexecutioncount:</b><br /><input type='text' name='maxexecutioncount' value='<?= stripslashes($row['maxexecutioncount']) ?>' /> 
<p><b>Executioncount:</b><br /><input type='text' name='executioncount' value='<?= stripslashes($row['executioncount']) ?>' /> 
<p><b>Authorisedapps:</b><br /><input type='text' name='authorisedapps' value='<?= stripslashes($row['authorisedapps']) ?>' /> 
<p><b>Timezone:</b><br /><input type='text' name='timezone' value='<?= stripslashes($row['timezone']) ?>' /> 
<p><b>Emailaddressconfirmed:</b><br /><input type='text' name='emailaddressconfirmed' value='<?= stripslashes($row['emailaddressconfirmed']) ?>' /> 
<p><b>Inserted:</b><br /><input type='text' name='inserted' value='<?= stripslashes($row['inserted']) ?>' /> 
<p><input type='submit' value='Edit Row' /><input type='hidden' value='1' name='submitted' /> 
</form> 
<? } ?> 
