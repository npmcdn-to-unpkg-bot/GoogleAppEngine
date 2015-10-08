<? 
include('config.php'); 
if (isset($_POST['submitted'])) { 
foreach($_POST AS $key => $value) { $_POST[$key] = mysql_real_escape_string($value); } 
$sql = "INSERT INTO `workorder` ( `datePerformed` ,  `dateRequested` ,  `address1` ,  `address2` ,  `addtionalService1` ,  `addtionalService2` ,  `addtionalService3` ,  `addtionalService4` ,  `addtionalService5` ,  `addtionalServiceComments` ,  `allergicInfo` ,  `childrenInfo` ,  `city` ,  `country` ,  `email` ,  `firstName` ,  `lastName` ,  `petComments` ,  `petInfo` ,  `phone` ,  `postal` ,  `primaryCleaningComments` ,  `primaryCleaningReason` ,  `residenceType` ,  `room1Info` ,  `room2Info` ,  `room3Info` ,  `specialInstruction` ,  `state` ,  `title` ,  `allergic` ,  `hasPet` ,  `alternatePhone` ,  `bathRoom` ,  `bedRoom` ,  `children` ,  `familySize` ,  `hoursSpent`  ) VALUES(  '{$_POST['datePerformed']}' ,  '{$_POST['dateRequested']}' ,  '{$_POST['address1']}' ,  '{$_POST['address2']}' ,  '{$_POST['addtionalService1']}' ,  '{$_POST['addtionalService2']}' ,  '{$_POST['addtionalService3']}' ,  '{$_POST['addtionalService4']}' ,  '{$_POST['addtionalService5']}' ,  '{$_POST['addtionalServiceComments']}' ,  '{$_POST['allergicInfo']}' ,  '{$_POST['childrenInfo']}' ,  '{$_POST['city']}' ,  '{$_POST['country']}' ,  '{$_POST['email']}' ,  '{$_POST['firstName']}' ,  '{$_POST['lastName']}' ,  '{$_POST['petComments']}' ,  '{$_POST['petInfo']}' ,  '{$_POST['phone']}' ,  '{$_POST['postal']}' ,  '{$_POST['primaryCleaningComments']}' ,  '{$_POST['primaryCleaningReason']}' ,  '{$_POST['residenceType']}' ,  '{$_POST['room1Info']}' ,  '{$_POST['room2Info']}' ,  '{$_POST['room3Info']}' ,  '{$_POST['specialInstruction']}' ,  '{$_POST['state']}' ,  '{$_POST['title']}' ,  '{$_POST['allergic']}' ,  '{$_POST['hasPet']}' ,  '{$_POST['alternatePhone']}' ,  '{$_POST['bathRoom']}' ,  '{$_POST['bedRoom']}' ,  '{$_POST['children']}' ,  '{$_POST['familySize']}' ,  '{$_POST['hoursSpent']}'  ) "; 
mysql_query($sql) or die(mysql_error()); 
echo "Added row.<br />"; 
echo "<a href='list.php'>Back To Listing</a>"; 
} 
?>

<form action='' method='POST'> 
<p><b>DatePerformed:</b><br /><input type='text' name='datePerformed'/> 
<p><b>DateRequested:</b><br /><input type='text' name='dateRequested'/> 
<p><b>Address1:</b><br /><input type='text' name='address1'/> 
<p><b>Address2:</b><br /><input type='text' name='address2'/> 
<p><b>AddtionalService1:</b><br /><input type='text' name='addtionalService1'/> 
<p><b>AddtionalService2:</b><br /><input type='text' name='addtionalService2'/> 
<p><b>AddtionalService3:</b><br /><input type='text' name='addtionalService3'/> 
<p><b>AddtionalService4:</b><br /><input type='text' name='addtionalService4'/> 
<p><b>AddtionalService5:</b><br /><input type='text' name='addtionalService5'/> 
<p><b>AddtionalServiceComments:</b><br /><input type='text' name='addtionalServiceComments'/> 
<p><b>AllergicInfo:</b><br /><input type='text' name='allergicInfo'/> 
<p><b>ChildrenInfo:</b><br /><input type='text' name='childrenInfo'/> 
<p><b>City:</b><br /><input type='text' name='city'/> 
<p><b>Country:</b><br /><input type='text' name='country'/> 
<p><b>Email:</b><br /><input type='text' name='email'/> 
<p><b>FirstName:</b><br /><input type='text' name='firstName'/> 
<p><b>LastName:</b><br /><input type='text' name='lastName'/> 
<p><b>PetComments:</b><br /><input type='text' name='petComments'/> 
<p><b>PetInfo:</b><br /><input type='text' name='petInfo'/> 
<p><b>Phone:</b><br /><input type='text' name='phone'/> 
<p><b>Postal:</b><br /><input type='text' name='postal'/> 
<p><b>PrimaryCleaningComments:</b><br /><input type='text' name='primaryCleaningComments'/> 
<p><b>PrimaryCleaningReason:</b><br /><input type='text' name='primaryCleaningReason'/> 
<p><b>ResidenceType:</b><br /><input type='text' name='residenceType'/> 
<p><b>Room1Info:</b><br /><input type='text' name='room1Info'/> 
<p><b>Room2Info:</b><br /><input type='text' name='room2Info'/> 
<p><b>Room3Info:</b><br /><input type='text' name='room3Info'/> 
<p><b>SpecialInstruction:</b><br /><input type='text' name='specialInstruction'/> 
<p><b>State:</b><br /><input type='text' name='state'/> 
<p><b>Title:</b><br /><input type='text' name='title'/> 
<p><b>Allergic:</b><br /><input type='text' name='allergic'/> 
<p><b>HasPet:</b><br /><input type='text' name='hasPet'/> 
<p><b>AlternatePhone:</b><br /><input type='text' name='alternatePhone'/> 
<p><b>BathRoom:</b><br /><input type='text' name='bathRoom'/> 
<p><b>BedRoom:</b><br /><input type='text' name='bedRoom'/> 
<p><b>Children:</b><br /><input type='text' name='children'/> 
<p><b>FamilySize:</b><br /><input type='text' name='familySize'/> 
<p><b>HoursSpent:</b><br /><input type='text' name='hoursSpent'/> 
<p><input type='submit' value='Add Row' /><input type='hidden' value='1' name='submitted' /> 
</form> 
