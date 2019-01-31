<footer class="footer">
      <div class="container">
        <div class="col-lg-3 col-lg-push-15 col-md-4 col-md-push-14 col-sm-18 col-xs-18 footer-links">
			<div class="footer-links-title">Follow Discover:</div>
			<div class="footer-social">
				<span data-bind="if: links.pinterest"></span>			
				<span data-bind="if: links.facebook">
					<a data-bind="attr: {href: links.facebook.href, target:links.facebook.target}" target="_blank" href="http://www.facebook.com/discovermag"><img alt="Follow Discover on Facebook" src="/csp-kmb/images/icon_facebook.png"></a>
				</span>
				<span data-bind="if: links.twitter">
					<a data-bind="attr: {href: links.twitter.href, target:links.twitter.target}" target="_blank" href="http://www.twitter.com/discovermag"><img alt="Follow Discover on Twitter" src="/csp-kmb/images/icon_twitter.png"></a>
				</span>
				<span data-bind="if: links.gplus">
					<a data-bind="attr: {href: links.gplus.href, target:links.gplus.target}" target="_blank" href="https://plus.google.com/113563966860765122485/"><img alt="Follow Discover on Google+" src="/csp-kmb/images/icon_googleplus.png"></a>
				</span>
				<span data-bind="if: links.youtube">
					<a data-bind="attr: {href: links.youtube.href, target:links.youtube.target}" target="_blank" href="http://www.youtube.com/user/discovermagazine"><img alt="Follow Discover on YouTube" src="/csp-kmb/images/icon_youtube.png"></a>
				</span>
				<span data-bind="if: links.rss">
					<a data-bind="attr: {href: links.rss.href, target:links.rss.target}" target="_blank" href="http://discovermagazine.com/rss"><img alt="Follow Discover " src="/csp-kmb/images/icon_rss.png"></a>
				</span>								
			</div>	  	        
		</div>
        <div class="col-lg-11 col-md-9 col-sm-18 col-xs-18 footer-links">
			<div data-bind="text: globalViews.site_com.title" class="footer-links-title">More great sites from Kalmbach Publishing Co.:</div>
			<span data-bind="foreach: globalViews.site_com.links">
				<span data-bind="visible : $index()!=0" style="display: none;"> | </span>  
				<a data-bind="text: $parent.globalLinks[$data].title, attr: {href:$parent.globalLinks[$data].href}" target="_blank" href="http://www.amsnow.com/">American Snowmobiler</a>
		  	
				<span data-bind="visible : $index()!=0"> | </span>  
				<a data-bind="text: $parent.globalLinks[$data].title, attr: {href:$parent.globalLinks[$data].href}" target="_blank" href="http://www.astronomy.com/">Astronomy</a>
		  	
				<span data-bind="visible : $index()!=0"> | </span>  
				<a data-bind="text: $parent.globalLinks[$data].title, attr: {href:$parent.globalLinks[$data].href}" target="_blank" href="http://www.beadandbutton.com/">Bead &amp; Button</a>
		  	
				<span data-bind="visible : $index()!=0"> | </span>  
				<a data-bind="text: $parent.globalLinks[$data].title, attr: {href:$parent.globalLinks[$data].href}" target="_blank" href="http://www.beadandbuttonshow.com/">Bead &amp; Button Show</a>
		  	
				<span data-bind="visible : $index()!=0"> | </span>  
				<a data-bind="text: $parent.globalLinks[$data].title, attr: {href:$parent.globalLinks[$data].href}" target="_blank" href="http://www.classictoytrains.com/">Classic Toy Trains</a>
		  	
				<span data-bind="visible : $index()!=0"> | </span>  
				<a data-bind="text: $parent.globalLinks[$data].title, attr: {href:$parent.globalLinks[$data].href}" target="_blank" href="http://www.classictrainsmag.com/">Classic Trains</a>
		  	
				<span data-bind="visible : $index()!=0"> | </span>  
				<a data-bind="text: $parent.globalLinks[$data].title, attr: {href:$parent.globalLinks[$data].href}" target="_blank" href="http://www.discovermagazine.com/">Discover</a>
		  	
				<span data-bind="visible : $index()!=0"> | </span>  
				<a data-bind="text: $parent.globalLinks[$data].title, attr: {href:$parent.globalLinks[$data].href}" target="_blank" href="http://www.drone360mag.com/">Drone 360</a>
		  	
				<span data-bind="visible : $index()!=0"> | </span>  
				<a data-bind="text: $parent.globalLinks[$data].title, attr: {href:$parent.globalLinks[$data].href}" target="_blank" href="http://www.finescale.com/">FineScale Modeler</a>
		  	
				<span data-bind="visible : $index()!=0"> | </span>  
				<a data-bind="text: $parent.globalLinks[$data].title, attr: {href:$parent.globalLinks[$data].href}" target="_blank" href="http://www.gardenrailways.com/">Garden Railways</a>
		  	
				<span data-bind="visible : $index()!=0"> | </span>  
				<a data-bind="text: $parent.globalLinks[$data].title, attr: {href:$parent.globalLinks[$data].href}" target="_blank" href="https://store.jewelrymakingmagazines.com/">Jewelry and Beading Store</a>
		  	
				<span data-bind="visible : $index()!=0"> | </span>  
				<a data-bind="text: $parent.globalLinks[$data].title, attr: {href:$parent.globalLinks[$data].href}" target="_blank" href="https://kalmbachhobbystore.com/">Kalmbach Hobby Store</a>
		  	
				<span data-bind="visible : $index()!=0"> | </span>  
				<a data-bind="text: $parent.globalLinks[$data].title, attr: {href:$parent.globalLinks[$data].href}" target="_blank" href="http://www.modelrailroader.com/">Model Railroader</a>
		  	
				<span data-bind="visible : $index()!=0"> | </span>  
				<a data-bind="text: $parent.globalLinks[$data].title, attr: {href:$parent.globalLinks[$data].href}" target="_blank" href="http://www.mrvideoplus.com/">Model Railroader Video Plus</a>
		  	
				<span data-bind="visible : $index()!=0"> | </span>  
				<a data-bind="text: $parent.globalLinks[$data].title, attr: {href:$parent.globalLinks[$data].href}" target="_blank" href="https://www.myscienceshop.com/">My Science Shop</a>
		  	
				<span data-bind="visible : $index()!=0"> | </span>  
				<a data-bind="text: $parent.globalLinks[$data].title, attr: {href:$parent.globalLinks[$data].href}" target="_blank" href="http://www.scaleautomag.com/">Scale Auto</a>
		  	
				<span data-bind="visible : $index()!=0"> | </span>  
				<a data-bind="text: $parent.globalLinks[$data].title, attr: {href:$parent.globalLinks[$data].href}" target="_blank" href="http://www.trainsmag.com/">Trains</a>
		  	
				<span data-bind="visible : $index()!=0"> | </span>  
				<a data-bind="text: $parent.globalLinks[$data].title, attr: {href:$parent.globalLinks[$data].href}" target="_blank" href="http://www.trains.com/">Trains.com</a>
		  	</span>
		</div>
        <div class="col-lg-3 col-lg-pull-14 col-md-4 col-md-pull-13 col-sm-18 col-xs-18 footer-logo">
        	<img alt="Kalmbach Publishing Co." class="img-responsive" src="/csp-kmb/images/logo_kpc.png">
        	<div style="padding-top: 7px;">
        		Copyright &copy; 2016
			</div>
        </div>		
      </div>
    </footer>