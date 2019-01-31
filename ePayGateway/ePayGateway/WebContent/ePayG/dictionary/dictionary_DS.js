var appContextRoot = "csp-kmb";
var dct_id = "DS";
var dct_uid = "3469";
var dct_name = "Discover";

Dictionary.brand = {
	"id": dct_id,
	"uid": dct_uid,
	"name": dct_name,
	"publisher": "KMB"
};


Dictionary.coverImageUrl = {
	"top_cover_100": "",
	"top_cover_125": "",
	"top_cover_150": "",
	"digital_cover_img": ""
};

Dictionary.contactNumbers = { // also update servlet_controller.jsp
	"service_number": "800-829-9132", 
	"digital_service_number": "", // only populate if different
	"intl_service_number": "" // only populate if different
};

Dictionary.legal = {
	"frequency": "",
	"subject_to_approval": "",
	"tax" : "",
	"delivery": ""
};

Dictionary.continuousService = {
		"title": "Continuous Service Language",
		"terms_paypal": "",
		"terms": "Your subscription will continue without interruption for as long as you wish, unless you instruct us otherwise. We will notify and bill or charge you at the discounted renewal rate then in effect prior to each subscription term. You may cancel at any time and receive a full refund for any unmailed issues.",
		"service_link_title": "",
		"service_link_href": "",
		"acct_info_description": "As part of this program, as you agreed, your subscription will be renewed automatically at the discounted renewal rate then in effect prior to each subscription term. It will continue without interruption so you never miss an issue for as long as you wish, unless you instruct us otherwise. One month before each renewal period starts, we will notify you by mail of your new subscription period, its price and your savings. You will be billed or charged as your next subscription period begins. We’ll never bill or charge your credit/debit card without informing you first. You may cancel your subscription at any time by contacting customer service and receive a full refund for any unmailed issues."
	};

// A master-map of links.
// Use this for direct lookups.
Dictionary.links = {
	"new_sub": { 
		"title": "New Subscriptions",
		"href": "#",
		"target": "_blank"
	},
	"gift_sub": {
		"title": "Gift Subscriptions",
		"href": "#",
		"target": "_blank"
	},
	"newsletter": {
		"title": dct_name+"Free Newsletter",
		"href": "http://discovermagazine.com/magazine/newsletter",
		"target": "_blank"			
	},
	"cs_form": {
		"title": "E-mail Customer Service",
		"href": "/servlet/Show?WESPAGE="+appContextRoot+"/contact_us.jsp&MSRSMAG="+dct_id,
		"target": ""
	},
	"contact_mag": {
		"title": "Contact the Magazine",
		"href": "http://discovermagazine.com/magazine/contact",
		"target": "_blank"
	},
	"faq": {
		"title": "Frequently Asked Questions",
		"href": "/servlet/Show?WESPAGE="+appContextRoot+"/faq.jsp&MSRSMAG="+dct_id,
		"target": ""
	},
	"digital_forgot_password": {
		"title": "Forgot Digital Password",
		"href": "https://subscription.timeinc.com/storefront/universalForgotPassword.ep?magcode="+dct_id,
		"target": "_blank"
	},
	"manage_subscriptions": { // SCV
		"title": "Manage Your Subscriptions",
		"href": "#",
		"target": ""
	},
	"facebook": {
		"title": "Facebook",
		"href": "http://www.facebook.com/discovermag",
		"target": "_blank"
	},
	"twitter": {
		"title": "Twitter",
		"href": "http://www.twitter.com/discovermag",
		"target": "_blank"
	},
	"gplus": {
		"title": "Google+",
		"href": "https://plus.google.com/113563966860765122485/",
		"target": "_blank"
	},
	"youtube": {
		"title": "YouTube",
		"href": "http://www.youtube.com/user/discovermagazine",
		"target": "_blank"
	},
	"rss": {
		"title": "RSS",
		"href": "http://discovermagazine.com/rss",
		"target": "_blank"
	},
	"site_about": {
		"title": "About",
		"href": "http://discovermagazine.com/about",
		"target": "_blank"
	},
	"site_terms": {
		"title": "Terms of Use",
		"href": "http://discovermagazine.com/termsofuse",
		"target": "_blank"
	},
	"site_privacy": {
		"title": "Privacy Policy",
		"href": "http://www.kalmbach.com/privacy",
		"target": "_blank"
	},
	"site_copyright": {
		"title": "Copyright Policy",
		"href": "http://www.kalmbach.com/copyrightpolicy",
		"target": "_blank"
	}
};	
	
// Map of arbitrary mini view models
// to support small chunks of pages.
Dictionary.views = {
		"exclusives": {
			"title": dct_name+" Exclusives",
			"links": ["new_sub", "gift_sub", "newsletter"]
		},
		"customer_service": {
			"title": "Customer Service",
			"links": ["faq", "cs_form", "contact_mag"]
		},
		"other": {
			"title": "Discover Magazine",
			"links": ["site_about", "site_terms", "site_privacy", "site_copyright"]
		}
	};	