<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
    <head>
    	<%@include file="globalIncludes.html" %>
    	<%@include file="commonTitle.html" %>
    	<script src="lib/highcharts/highcharts.js"></script>
        <script src="script/about.js"></script>
    </head>
    <body>
		<%@include file="navbar.html" %>
		<div class="wrapper">
			<div class="content container-full about-page">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<h1>About</h1>
							<p>${introText}</p>
						</div>
					</div>
					<div class="row stats">
						<h2 class="col-md-12">Stats</h2>
						<div class="col-md-4">
							<ul class="list-unstyled">
								<li><strong><c:out value="${stats.getNumFeatures()}"></c:out> associations</strong> between</li>
								<li><strong><c:out value="${stats.getNumAttributes()}"></c:out> attributes</strong> and</li>
								<li><strong><c:out value="${stats.getNumGenes()}"></c:out> genes</strong> from</li>
								<li><strong><c:out value="${stats.getNumDatasets()}"></c:out> datasets provided</strong> by</li>
								<li><strong><c:out value="${stats.getNumResources()}"></c:out> resources</strong></li>
							</ul>
						</div>
						<div class="col-md-4">
							<div id="dataset-pie-chart"></div>
						</div>
						<div class="col-md-4">
							<div id="attribute-pie-chart"></div>
						</div>
					</div>
					<div id="resources" class="row">
						<h2>Resources</h2>
						<p>To create the Harmonizome, we distilled information from original datasets into attribute tables that define significant associations between genes and attributes, where attributes could be genes, proteins, cell lines, tissues, experimental perturbations, diseases, phenotypes, or drugs, depending on the dataset. Gene and protein identifiers were mapped to NCBI Entrez Gene Symbols and attributes were mapped to appropriate ontologies. We also computed gene-gene and attribute-attribute similarity networks from the attribute tables. These attribute tables and similarity networks can be integrated to perform many types of computational analyses for knowledge discovery and hypothesis generation.</p>
						<div class="col-md-12">
							<div id="bar-chart"></div>
						</div>
					</div>
				</div>
			</div>
			<%@include file="footer.html" %>
        </div>
    </body>
</html>