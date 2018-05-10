<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="java.sql.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Play Battle</title>
<%@include file="includes/header.jsp"%>
<script src="http://code.jquery.com/jquery.min.js"></script>
<script>

function selectAll(selectBox,selectAll) { 
    // have we been passed an ID 
   // alert("hi....")
   // alert(typeof selectBox);
    if (typeof selectBox == "string") { 
        selectBox = document.getElementById(selectBox);
    } 
    //alert("type:"+selectBox.type);
    // is the select box a multiple select box? 
    if (selectBox.type == "select-multiple") { 
        for (var i = 0; i < selectBox.options.length; i++) { 
             selectBox.options[i].selected = selectAll; 
        } 
       // alert("before form submission");
        document.getElementById("playBattleForm").submit();
       // alert("after form submission");
    }
}


function submit(){
	var obj = document.getElementById("select-to");
	 if (!hasOptions(obj)) { return; }
    for (var i=0; i<obj.options.length; i++) {
    	alert(obj.options[i]);
        obj.options[i].selected = true;
        }
	document.getElementById("playBattleForm").submit();
}

	$(document).ready(
			function() {

				$('#btn-add').click(
						function() {
							$('#select-from option:selected').each(
									function() {
										$('#select-to').append(
												"<option value='"
														+ $(this).val() + "'>"
														+ $(this).text()
														+ "</option>");
										$(this).remove();
									});
						});

				$('#btn-remove').click(
						function() {
							$('#select-to option:selected').each(
									function() {
										$('#select-from').append(
												"<option value='"
														+ $(this).val() + "'>"
														+ $(this).text()
														+ "</option>");
										$(this).remove();
									});
						});

			});

	function fnAdd_and_Remove(t) {
		var addId;
		var removeId;
		if (t.value == "Add") {
			addId = document.getElementById("idSelectToAddRobot");
			removeId = document.getElementById("idSelectToPlayRobot");
			var selectedRobot = addId.options[addId.selectedIndex];
			if (!isExists(selectedRobot.label)) {
				var option = document.createElement("option");
				option.value = selectedRobot.value;
				option.text = selectedRobot.label;
				removeId.add(option);
				addId.remove(selectedRobot.index);
			}
		} else if (t.value == "Remove") {
			removeId = document.getElementById("idSelectToPlayRobot");
			removeId.remove(removeId.selectedIndex);
		}
		if (document.getElementById("idSelectToPlayRobot").length < 2)
			document.getElementById("idSubmitRunBattle").style.visibility = "hidden";
		else
			document.getElementById("idSubmitRunBattle").style.visibility = "visible";
	}
</script>
</head>
<body>
	<div class="container">
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">
					<i class="fa fa-play"></i> Play Battle,
			<%=session.getAttribute("userx")%></h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
			<div class="row">
		<form action="playBattle" method="post" id="playBattleForm">
			<%
				try {
					Class.forName("com.mysql.jdbc.Driver").newInstance();
					Connection conn = null;
					Statement st = null;
					ResultSet rs = null;
					conn = DriverManager.getConnection(
							"jdbc:mysql://robocodedb.cloudapp.net:3306/form",
							"naren", "naren");
					st = conn.createStatement();
					rs = st.executeQuery("select Robot_Name from robot");
			%>
			

				<select name="selectfrom" id="select-from" multiple size="5">
					<%
						while (rs.next()) {
					%>
					<option value="<%=rs.getString("Robot_Name")%>"><%=rs.getString("Robot_Name")%></option>
					<%
						}
					%>
				</select>
				<button type="button" class="btn btn-default" id="btn-add"
					onclick="fnAdd_and_Remove(this)">ADD</button>
				<button type="button" class="btn btn-default"
					onclick="fnAdd_and_Remove(this)">REMOVE</button>
				<select name="selectto" id="select-to" multiple size="5">
				</select>

		<br />
		<br />
		<p>Number of Rounds</p>
		<input type="text" name="rounds">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;
		<button type="button" class="btn btn-success" id="create" onClick="selectAll('select-to',true)">Play
			Battle</button>
			
		</form>
		<%
			} catch (Exception e) {
			}
		%>
		
								
		
</div>
				<!-- /.col-lg-6 (nested) -->
			</div>
			<!-- /.row (nested) -->
		</div>
		<!-- /.panel-body -->
	</div>
</body>
<%@include file="includes/fotter.jsp"%>
</html>