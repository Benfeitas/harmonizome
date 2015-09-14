<%-- <div class="navbar navbar-static-top">
	<div class="container">
		<h1>
			<a href="" class="pull-left">
				<img src="image/logo_30x26.png">
				<span class="project-title">Harmonizome</span>
			</a>
		</h1>
		<%@include file="searchBar.html" %>
		<ul class="list-inline pull-right">
			<li>
				<a href="">Search</a>
			</li>
			<li>
				<a href="download">Download</a>
			</li>
			<li>
				<a href="visualize">Visualize</a>
			</li>
			<li>
				<a href="documentation">API</a>
			</li>
			<li>
				<a href="about">About</a>
			</li>
		</ul>
	</div>
</div> --%>

<div id="navbar-wrapper" class="navbar-static-top">
	<div class="container">
		<h1 class="pull-left">
			<a href="">
				<img src="image/logo_30x26.png">
				<span class="project-title">Harmonizome</span>
			</a>
		</h1>
	
		<!-- MENU -->
		
		<%@include file="searchBar.html" %>

		<!-- This is the dropdown button for the mobile version of the site -->
		<div id="menu-dropdown" class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#menu" aria-expanded="false" aria-controls="navbar">
			<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
		</div>

		<!-- Elements -->
		<div id="menu" class="navbar-collapse collapse pull-right">
	        <ul class="nav navbar-nav list-inline">
				<li>
					<a href="">Search</a>
				</li>
				<li>
					<a href="download">Download</a>
				</li>
				<li>
					<a href="visualize">Visualize</a>
				</li>
				<li>
					<a href="documentation">API</a>
				</li>
				<li>
					<a href="about">About</a>
				</li>
	        </ul>
		</div>
	</div>
</div>