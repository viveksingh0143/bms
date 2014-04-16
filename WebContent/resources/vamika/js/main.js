$( document ).ready(function() {
    $( ".nav-sidebar > li.dropdown > a" ).click(function() {
			$( this ).parent().find("ul").slideToggle();
		});
		
		$('.sidebar [data-toggle=offcanvas]').click(function() {
				$(this).find('i').toggleClass('fa-chevron-right fa-chevron-left');
				$('.sidebar').find("a").toggleClass('previous');
				$('.sidebar').find("span.a-text").toggleClass('hidden-xs');
				$('.sidebar').toggleClass('col-xs-2 col-xs-12');
				$('.main-col').toggleClass('col-xs-10 col-xs-12');
		});
});