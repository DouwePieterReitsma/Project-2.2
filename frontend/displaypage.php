<!DOCTYPE html>
<html>
  <head>
  
    <title>Data Sheet</title>
    <meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="style.css">
 

  </head>
  <body>
  
	<div  class="welcomebox">
	
	Welkom klootzakken, hier jullie grafwebsite. <3
	
	</div>
  
  
  	<div  class="databox">
		<header>shitload aan data, moet nog nice indeling komen</header>
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
    </div>
	
	<div  class="controlbox">
	
	controls en zoekfunctie etc?
	
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
	<div  class="downloadbox">
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
      }
    </script>
    <script src="https://maps.googleapis.com/maps/api/js?key=
AIzaSyABY3ccIgpUROaVrcQjp90IYjlXwTZhYdA&callback=initMap"
    async defer></script>
	

  </body>
</html>