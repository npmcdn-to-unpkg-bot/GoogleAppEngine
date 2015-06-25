<? 
include('config.php'); 
echo "<table border=1 >"; 
echo "<tr>"; 
echo "<td><b>Id</b></td>"; 
echo "<td><b>First Name</b></td>"; 
echo "<td><b>Last Name</b></td>"; 
echo "<td><b>Username</b></td>"; 
echo "<td><b>Password</b></td>"; 
echo "<td><b>Email</b></td>"; 
echo "<td><b>Email Notify</b></td>"; 
echo "<td><b>Phone</b></td>"; 
echo "<td><b>Address</b></td>"; 
echo "<td><b>Active</b></td>"; 
echo "<td><b>Groupname</b></td>"; 
echo "<td><b>Last4ssn</b></td>"; 
echo "<td><b>Badge Num</b></td>"; 
echo "<td><b>Hiredate</b></td>"; 
echo "<td><b>Startdate</b></td>"; 
echo "<td><b>Supervisor Flag</b></td>"; 
echo "</tr>"; 
$result = mysql_query("SELECT * FROM `employee`") or trigger_error(mysql_error()); 
while($row = mysql_fetch_array($result)){ 
foreach($row AS $key => $value) { $row[$key] = stripslashes($value); } 
echo "<tr>";  
echo "<td valign='top'>" . nl2br( $row['id']) . "</td>";  
echo "<td valign='top'>" . nl2br( $row['first_name']) . "</td>";  
echo "<td valign='top'>" . nl2br( $row['last_name']) . "</td>";  
echo "<td valign='top'>" . nl2br( $row['username']) . "</td>";  
echo "<td valign='top'>" . nl2br( $row['password']) . "</td>";  
echo "<td valign='top'>" . nl2br( $row['email']) . "</td>";  
echo "<td valign='top'>" . nl2br( $row['email_notify']) . "</td>";  
echo "<td valign='top'>" . nl2br( $row['phone']) . "</td>";  
echo "<td valign='top'>" . nl2br( $row['address']) . "</td>";  
echo "<td valign='top'>" . nl2br( $row['active']) . "</td>";  
echo "<td valign='top'>" . nl2br( $row['groupname']) . "</td>";  
echo "<td valign='top'>" . nl2br( $row['last4ssn']) . "</td>";  
echo "<td valign='top'>" . nl2br( $row['badge_num']) . "</td>";  
echo "<td valign='top'>" . nl2br( $row['hiredate']) . "</td>";  
echo "<td valign='top'>" . nl2br( $row['startdate']) . "</td>";  
echo "<td valign='top'>" . nl2br( $row['supervisor_flag']) . "</td>";  
echo "<td valign='top'><a href=edit.php?id={$row['id']}>Edit</a></td><td><a href=delete.php?id={$row['id']}>Delete</a></td> "; 
echo "</tr>"; 
} 
echo "</table>"; 
echo "<a href=new.php>New Row</a>"; 
?>