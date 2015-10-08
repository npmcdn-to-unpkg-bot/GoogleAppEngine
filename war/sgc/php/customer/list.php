<? 
include('config.php'); 
echo "<table border=1 >"; 
echo "<tr>"; 
echo "<td><b>Id</b></td>"; 
echo "<td><b>Customerusername</b></td>"; 
echo "<td><b>Customerpassword</b></td>"; 
echo "<td><b>Emailaddress</b></td>"; 
echo "<td><b>Firstname</b></td>"; 
echo "<td><b>Lastname</b></td>"; 
echo "<td><b>City</b></td>"; 
echo "<td><b>Country</b></td>"; 
echo "<td><b>Website</b></td>"; 
echo "<td><b>Active</b></td>"; 
echo "<td><b>Suspended</b></td>"; 
echo "<td><b>Securityquestion</b></td>"; 
echo "<td><b>Securityanswer</b></td>"; 
echo "<td><b>Createdfromipaddress</b></td>"; 
echo "<td><b>Adminid</b></td>"; 
echo "<td><b>Adminmemberid</b></td>"; 
echo "<td><b>Maxexecutioncount</b></td>"; 
echo "<td><b>Executioncount</b></td>"; 
echo "<td><b>Authorisedapps</b></td>"; 
echo "<td><b>Timezone</b></td>"; 
echo "<td><b>Emailaddressconfirmed</b></td>"; 
echo "<td><b>Inserted</b></td>"; 
echo "</tr>"; 
$result = mysql_query("SELECT * FROM `customer`") or trigger_error(mysql_error()); 
while($row = mysql_fetch_array($result)){ 
foreach($row AS $key => $value) { $row[$key] = stripslashes($value); } 
echo "<tr>";  
echo "<td valign='top'>" . nl2br( $row['id']) . "</td>";  
echo "<td valign='top'>" . nl2br( $row['customerusername']) . "</td>";  
echo "<td valign='top'>" . nl2br( $row['customerpassword']) . "</td>";  
echo "<td valign='top'>" . nl2br( $row['emailaddress']) . "</td>";  
echo "<td valign='top'>" . nl2br( $row['firstname']) . "</td>";  
echo "<td valign='top'>" . nl2br( $row['lastname']) . "</td>";  
echo "<td valign='top'>" . nl2br( $row['city']) . "</td>";  
echo "<td valign='top'>" . nl2br( $row['country']) . "</td>";  
echo "<td valign='top'>" . nl2br( $row['website']) . "</td>";  
echo "<td valign='top'>" . nl2br( $row['active']) . "</td>";  
echo "<td valign='top'>" . nl2br( $row['suspended']) . "</td>";  
echo "<td valign='top'>" . nl2br( $row['securityquestion']) . "</td>";  
echo "<td valign='top'>" . nl2br( $row['securityanswer']) . "</td>";  
echo "<td valign='top'>" . nl2br( $row['createdfromipaddress']) . "</td>";  
echo "<td valign='top'>" . nl2br( $row['adminid']) . "</td>";  
echo "<td valign='top'>" . nl2br( $row['adminmemberid']) . "</td>";  
echo "<td valign='top'>" . nl2br( $row['maxexecutioncount']) . "</td>";  
echo "<td valign='top'>" . nl2br( $row['executioncount']) . "</td>";  
echo "<td valign='top'>" . nl2br( $row['authorisedapps']) . "</td>";  
echo "<td valign='top'>" . nl2br( $row['timezone']) . "</td>";  
echo "<td valign='top'>" . nl2br( $row['emailaddressconfirmed']) . "</td>";  
echo "<td valign='top'>" . nl2br( $row['inserted']) . "</td>";  
echo "<td valign='top'><a href=edit.php?id={$row['id']}>Edit</a></td><td><a href=delete.php?id={$row['id']}>Delete</a></td> "; 
echo "</tr>"; 
} 
echo "</table>"; 
echo "<a href=new.php>New Row</a>"; 
?>