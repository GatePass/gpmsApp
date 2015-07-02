<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="resources/gpmsStyle.css" />
<title>Gate Pass Management System</title>
</head>
<body>
	<div id="wrapper">

		<div id="header" align="center">
			<table>
				<tr>
					<td><%@include file="gpmsHeader.jsp"%></td>
				</tr>
			</table>
		</div>
		<!-- #header -->
		
		<div id="content" align="center">
		<table style="margin-top: 30px">
			<tr>
				<td>
					<div align="center">
						<fieldset id="fieldSet"
							style="border-width: 2px; background-color: lightblue;  width: 800px;">
							<sf:form action="navigation">
								<table cellspacing="10px">
									<tr>
										<td class="boxHeading" align="left" colspan="2" ><b> <label>User Access Management</label>
										</b></td>
									</tr>
									<tr>
										<td align="left" colspan="2" ><label>Create/Modify an Employee/ISIT Member</label>
										</td>
										<td><input type="submit" name="createUser" value="Create User" /></td>
										<td><input type="submit" name="modifyUser" value="Modify User" /></td>
									</tr>
									<tr>
										<td align="left" colspan="2" ><label>Delete an Employee/ISIT Member</label>
										</td>
										<td><input type="submit" name="deleteUser" value="Delete User" /></td>
									</tr>
									<br>
									<tr>
										<td class="boxHeading" align="left" colspan="2" ><b> <label>Assets Management</label>
										</b></td>
									</tr>
									<tr>
										<td align="left" colspan="2" ><label>Create/Modify a bonded asset</label>
										</td>
										<td><input type="submit" name="createAsset" value="Create Asset" /></td>
										<td><input type="submit" name="modifyAsset" value="Modify Asset" /></td>
									</tr>
									<tr>
										<td align="left" colspan="2" ><label>Delete a bonded asset</label>
										</td>
										<td><input type="submit" name="deleteAsset" value="Delete Asset" /></td>
									</tr>
									<tr>
										<td align="left" colspan="2" ><label>Assign a bonded item to Employee</label>
										</td>
										<td><input type="submit" name="bondedItemAssign" value="Assign Asset" /></td>
									</tr>
									<tr>
										<td align="left" colspan="2" ><label>Return of the bonded item</label>
										</td>
										<td><input type="submit" name="bondedItemReturn" value="Return Asset" /></td>
									</tr>
									<br>
									<tr>
										<td class="boxHeading" align="left" colspan="2" ><b> <label>Reporting</label>
										</b></td>
									</tr>
								</table>
							</sf:form>
							
						</fieldset>
					</div>
				</td>
			</tr>
		</table>
		</div>
		<!-- #content -->

		<div id="footer" align="center">
			<table>

				<tr>
					<td><%@include file="gpmsFooter.jsp"%></td>
				</tr>
			</table>
		</div>
		<!-- #footer -->




	</div>
</body>
</html>