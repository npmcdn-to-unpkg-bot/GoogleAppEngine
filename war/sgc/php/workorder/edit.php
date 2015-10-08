<? 
include('config.php'); 
if (isset($_GET['id']) ) { 
$id = (int) $_GET['id']; 
if (isset($_POST['submitted'])) { 
foreach($_POST AS $key => $value) { $_POST[$key] = mysql_real_escape_string($value); } 
$sql = "UPDATE `workorder` SET  `datePerformed` =  '{$_POST['datePerformed']}' ,  `dateRequested` =  '{$_POST['dateRequested']}' ,  `address1` =  '{$_POST['address1']}' ,  `address2` =  '{$_POST['address2']}' ,  `addtionalService1` =  '{$_POST['addtionalService1']}' ,  `addtionalService2` =  '{$_POST['addtionalService2']}' ,  `addtionalService3` =  '{$_POST['addtionalService3']}' ,  `addtionalService4` =  '{$_POST['addtionalService4']}' ,  `addtionalService5` =  '{$_POST['addtionalService5']}' ,  `addtionalServiceComments` =  '{$_POST['addtionalServiceComments']}' ,  `allergicInfo` =  '{$_POST['allergicInfo']}' ,  `childrenInfo` =  '{$_POST['childrenInfo']}' ,  `city` =  '{$_POST['city']}' ,  `country` =  '{$_POST['country']}' ,  `email` =  '{$_POST['email']}' ,  `firstName` =  '{$_POST['firstName']}' ,  `lastName` =  '{$_POST['lastName']}' ,  `petComments` =  '{$_POST['petComments']}' ,  `petInfo` =  '{$_POST['petInfo']}' ,  `phone` =  '{$_POST['phone']}' ,  `postal` =  '{$_POST['postal']}' ,  `primaryCleaningComments` =  '{$_POST['primaryCleaningComments']}' ,  `primaryCleaningReason` =  '{$_POST['primaryCleaningReason']}' ,  `residenceType` =  '{$_POST['residenceType']}' ,  `room1Info` =  '{$_POST['room1Info']}' ,  `room2Info` =  '{$_POST['room2Info']}' ,  `room3Info` =  '{$_POST['room3Info']}' ,  `specialInstruction` =  '{$_POST['specialInstruction']}' ,  `state` =  '{$_POST['state']}' ,  `title` =  '{$_POST['title']}' ,  `allergic` =  '{$_POST['allergic']}' ,  `hasPet` =  '{$_POST['hasPet']}' ,  `alternatePhone` =  '{$_POST['alternatePhone']}' ,  `bathRoom` =  '{$_POST['bathRoom']}' ,  `bedRoom` =  '{$_POST['bedRoom']}' ,  `children` =  '{$_POST['children']}' ,  `familySize` =  '{$_POST['familySize']}' ,  `hoursSpent` =  '{$_POST['hoursSpent']}'   WHERE `id` = '$id' "; 
mysql_query($sql) or die(mysql_error()); 
echo (mysql_affected_rows()) ? "Edited row.<br />" : "Nothing changed. <br />"; 
echo "<a href='list.php'>Back To Listing</a>"; 
} 
$row = mysql_fetch_array ( mysql_query("SELECT * FROM `workorder` WHERE `id` = '$id' ")); 
?>

<form action='' method='POST'> 
<p><b>DatePerformed:</b><br /><input type='text' name='datePerformed' value='<?= stripslashes($row['datePerformed']) ?>' /> 
<p><b>DateRequested:</b><br /><input type='text' name='dateRequested' value='<?= stripslashes($row['dateRequested']) ?>' /> 
<p><b>Address1:</b><br /><input type='text' name='address1' value='<?= stripslashes($row['address1']) ?>' /> 
<p><b>Address2:</b><br /><input type='text' name='address2' value='<?= stripslashes($row['address2']) ?>' /> 
<p><b>AddtionalService1:</b><br /><input type='text' name='addtionalService1' value='<?= stripslashes($row['addtionalService1']) ?>' /> 
<p><b>AddtionalService2:</b><br /><input type='text' name='addtionalService2' value='<?= stripslashes($row['addtionalService2']) ?>' /> 
<p><b>AddtionalService3:</b><br /><input type='text' name='addtionalService3' value='<?= stripslashes($row['addtionalService3']) ?>' /> 
<p><b>AddtionalService4:</b><br /><input type='text' name='addtionalService4' value='<?= stripslashes($row['addtionalService4']) ?>' /> 
<p><b>AddtionalService5:</b><br /><input type='text' name='addtionalService5' value='<?= stripslashes($row['addtionalService5']) ?>' /> 
<p><b>AddtionalServiceComments:</b><br /><input type='text' name='addtionalServiceComments' value='<?= stripslashes($row['addtionalServiceComments']) ?>' /> 
<p><b>AllergicInfo:</b><br /><input type='text' name='allergicInfo' value='<?= stripslashes($row['allergicInfo']) ?>' /> 
<p><b>ChildrenInfo:</b><br /><input type='text' name='childrenInfo' value='<?= stripslashes($row['childrenInfo']) ?>' /> 
<p><b>City:</b><br /><input type='text' name='city' value='<?= stripslashes($row['city']) ?>' /> 
<p><b>Country:</b><br /><input type='text' name='country' value='<?= stripslashes($row['country']) ?>' /> 
<p><b>Email:</b><br /><input type='text' name='email' value='<?= stripslashes($row['email']) ?>' /> 
<p><b>FirstName:</b><br /><input type='text' name='firstName' value='<?= stripslashes($row['firstName']) ?>' /> 
<p><b>LastName:</b><br /><input type='text' name='lastName' value='<?= stripslashes($row['lastName']) ?>' /> 
<p><b>PetComments:</b><br /><input type='text' name='petComments' value='<?= stripslashes($row['petComments']) ?>' /> 
<p><b>PetInfo:</b><br /><input type='text' name='petInfo' value='<?= stripslashes($row['petInfo']) ?>' /> 
<p><b>Phone:</b><br /><input type='text' name='phone' value='<?= stripslashes($row['phone']) ?>' /> 
<p><b>Postal:</b><br /><input type='text' name='postal' value='<?= stripslashes($row['postal']) ?>' /> 
<p><b>PrimaryCleaningComments:</b><br /><input type='text' name='primaryCleaningComments' value='<?= stripslashes($row['primaryCleaningComments']) ?>' /> 
<p><b>PrimaryCleaningReason:</b><br /><input type='text' name='primaryCleaningReason' value='<?= stripslashes($row['primaryCleaningReason']) ?>' /> 
<p><b>ResidenceType:</b><br /><input type='text' name='residenceType' value='<?= stripslashes($row['residenceType']) ?>' /> 
<p><b>Room1Info:</b><br /><input type='text' name='room1Info' value='<?= stripslashes($row['room1Info']) ?>' /> 
<p><b>Room2Info:</b><br /><input type='text' name='room2Info' value='<?= stripslashes($row['room2Info']) ?>' /> 
<p><b>Room3Info:</b><br /><input type='text' name='room3Info' value='<?= stripslashes($row['room3Info']) ?>' /> 
<p><b>SpecialInstruction:</b><br /><input type='text' name='specialInstruction' value='<?= stripslashes($row['specialInstruction']) ?>' /> 
<p><b>State:</b><br /><input type='text' name='state' value='<?= stripslashes($row['state']) ?>' /> 
<p><b>Title:</b><br /><input type='text' name='title' value='<?= stripslashes($row['title']) ?>' /> 
<p><b>Allergic:</b><br /><input type='text' name='allergic' value='<?= stripslashes($row['allergic']) ?>' /> 
<p><b>HasPet:</b><br /><input type='text' name='hasPet' value='<?= stripslashes($row['hasPet']) ?>' /> 
<p><b>AlternatePhone:</b><br /><input type='text' name='alternatePhone' value='<?= stripslashes($row['alternatePhone']) ?>' /> 
<p><b>BathRoom:</b><br /><input type='text' name='bathRoom' value='<?= stripslashes($row['bathRoom']) ?>' /> 
<p><b>BedRoom:</b><br /><input type='text' name='bedRoom' value='<?= stripslashes($row['bedRoom']) ?>' /> 
<p><b>Children:</b><br /><input type='text' name='children' value='<?= stripslashes($row['children']) ?>' /> 
<p><b>FamilySize:</b><br /><input type='text' name='familySize' value='<?= stripslashes($row['familySize']) ?>' /> 
<p><b>HoursSpent:</b><br /><input type='text' name='hoursSpent' value='<?= stripslashes($row['hoursSpent']) ?>' /> 
<p><input type='submit' value='Edit Row' /><input type='hidden' value='1' name='submitted' /> 
</form> 
<? } ?> 
