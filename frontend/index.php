<?php

session_start();

if(!isset($_SESSION["loggedin"]) || $_SESSION["loggedin"] == false)
{
    header("Location: loginpage.php");
    exit;
}

?>

<!DOCTYPE html>
<html>
  <head>
  
    <title>Data Sheet</title>
    <meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="style.css">
 

  </head>
  <body>
  
  	<div class="logobox">
	
	<img src="logo.png" alt="Logo" class="companylogo"> 
	
	</div>
  
	<form align="right" name="form1" method="post" action="logout.php">
		<label class="logoutLblPos">
			<input name="submit2" type="submit" id="submit2" value="log out">
		</label>
	</form>
  
	<div class="welcomebox">
	
	Welkom blablabla<3
	
	</div>
  
  
  	<div  class="databox">
		<header>datadump, moet nog nice indeling komen</header>
		<nav>
            <ul>
                <li>data</li>
                <li>data</li>
                <li>data</li>
                <li>data</li>
                <li>data</li>
				<li>data</li>
                <li>data</li>
                <li>data</li>
                <li>data</li>
                <li>data</li>


            </ul>
        </nav>
		
		<div align="center">
			<b>Data Download</b> <br> <br>
			 Please select the date that you want the weather data from. <br>
			 When you have selected a date hit download. 
			 <form>
				<!-- folder selectie nog implementeren -->
				<b>Select a day</b>
				 <input type="date" name="folderdate" 
			</form> 
			<!-- Download optie nog implementeren -->
			<button onclick=? align="right" style="width:auto;">Download</button>
		</div>
		
    </div>
	
	
	<div style="height: 400px; overflow: auto;">
    <!-- Html Elements -->
	</div>
	
	
	<!-- nog toevoegen actie die de geselecteerde map met data selecteerd voor jaar maand dag etc -->
	<!-- misschien later, eerst simpelere form met date input
	<div class="dropdown">
		<button class="dropbtn">Month</button>
			<div class="dropdown-content">
			<a >January</a>
			<a >February</a>
			<a >March</a>
			<a >April</a>
			<a> May</a>
			<a >June</a>
			<a >July</a>
			<a >August</a>
			<a >September</a>
			<a >October</a>
			<a >November</a>
			<a >December</a>			
			</div>
	</div> 
	-->


	
	<div  class="mapdata"</div>
		
		<table style="width:10%" padding: 5px;>
			<tr padding: 5px;>
				<th>Date</th>
				<td>variable</td>
			</tr>	
			<tr>
				<th>Time</th>
				<td>variable</td>
			</tr>	
			<tr>
				<th>Country</th>
				<td>variable</td>
			</tr>
			<tr>
				<th>City</th>
				<td>variable</td>
			</tr>		
			<tr>
				<th>Longtitude</th>
				<td>variable</td>
			</tr>			
			<tr>
				<th>Latitude</th>
				<td>variable</td>
			</tr>
			<tr>
				<th>Temperature</th>
				<td>variable</td>
			</tr>					
			<tr>
				<th>Humidity</th>
				<td>variable</td>
			</tr>					
			<tr>
				<th>Air Pressure</th>
				<td>variable</td>
			</tr>
			<tr>
				<th>iets</th>
				<td>variable</td>
			</tr>	
			<tr>
				<th>nog iets</th>
				<td>variable</td>
			</tr>			
			<tr>
				<th>etc</th>
				<td>variable</td>
			</tr>	
			<tr>
				<th>etc</th>
				<td>variable</td>
			</tr>				
			<tr>
				<th>etc</th>
				<td>variable</td>
			</tr>				
			<tr>
				<th>etc</th>
				<td>variable</td>
			</tr>				
			<tr>
				<th>etc</th>
				<td>variable</td>
			</tr>				
			
		</table> 
	
	</div>

   <div id="map" </div>
    <script>
      var map;
      function initMap() {
        map = new google.maps.Map(document.getElementById('map'), {
          center: {lat: 42.45, lng: 59.617},
          zoom: 3
        });

				map.data.loadGeoJson("weatherdata.php");
      }


    </script>
    <script src="https://maps.googleapis.com/maps/api/js?key=
AIzaSyABY3ccIgpUROaVrcQjp90IYjlXwTZhYdA&callback=initMap"
    async defer></script>
	

  </body>
</html>
