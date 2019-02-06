<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="style.css">
</head>
<body>

	<!-- Begin Buttons -->
	<div class="center"
		<p align="center"><h2>Weather Monitoring Application</h2></p>
		<button onclick="document.getElementById('loginMenu').style.display='block'" align="center" style="width:auto;">Login</button>
		<button onclick="document.getElementById('registerMenu').style.display='block'" align="center" style="width:auto;">Register</button>
	</div>
 
 	<!-- Popup Menu voor Login -->
	<div id="loginMenu" class="popupmenu">
	  
		<form class="popupmenu-content animate" action="/action_page.php">
			<!-- nog toevoegen -> bedrijfslogo -->
			<div class="imgcontainer">
				<span onclick="document.getElementById('loginMenu').style.display='none'" class="close" title="Close">&times;</span>
				<img src="img_avatar2.png" alt="Logo" class="companylogo"> 
			</div>
		
			<div class="container">
				<label for="username"><b>Username</b></label>
				  <input type="text" placeholder="Enter Username" name="username" required>

				  <label for="password"><b>Password</b></label>
				  <input type="password" placeholder="Enter Password" name="password" required>
					
				  <button type="submit">Login Here</button>
				  <label>
					<input type="checkbox" checked="checked" name="remember"> Remember me
				</label>
			</div>

			<div class="container" style="background-color:#f1f1f1">
				  <button type="button" onclick="document.getElementById('loginMenu').style.display='none'" class="cancelbtn">Cancel</button>
				  <span class="psw">Forgot <a href="loser.php">password?</a></span>
			</div>	
		</form>
	</div>
	
	<!-- Popup Menu voor registratie -->
	<div id="registerMenu" class="popupmenu">
	  
		<form class="popupmenu-content animate" action="/action_page.php">
		
			<div class="imgcontainer">
				<span onclick="document.getElementById('registerMenu').style.display='none'" class="close" title="Close">&times;</span>
				<img src="img_avatar2.png" alt="Logo" class="companylogo"> 
			</div>
		
			<div class="container">
				<label for="uname"><b>Email Address</b></label>
				  <input type="text" placeholder="Enter Email" name="uname" required>

				  <label for="psw"><b>Password</b></label>
				  <input type="password" placeholder="Enter Password" name="psw" required>
				  
				  <label for="psw"><b>Re-enter Password</b></label>
				  <input type="password" placeholder="Enter Password" name="psw" required>
					
				  <button type="submit">Register</button>
			</div>

			<div class="container" style="background-color:#f1f1f1">
				  <button type="button" onclick="document.getElementById('registerMenu').style.display='none'" class="cancelbtn">Cancel</button>
			</div>
			
		</form>
		
	</div>

</body>
</html>