(
	function ($) {
		jQuery.expr[':'].Contains = function(a,i,m){
			return (a.textContent || a.innerText || "").toUpperCase().indexOf(m[3].toUpperCase())>=0;
		};

		function listFilter(container, header, list) { // header is any element, list is an unordered list
			$(container).each(function( index ) {
				var filter_header = $(this).find(header);
				var filter_list = $(this).find(list);
				if (!$(filter_list).hasClass( "no-filter-box" )) {
					var input = $("<input>").attr({"type":"text", "class":"form-control input-sm filter-search", "placeholder":"Search Keyword"});
					$(input).prependTo(filter_list);
					$(input)
						.change( function () {
							var filter = $(this).val();
							if(filter) {
								// this finds all links in a list that contain the input,
								// and hide the ones not containing the input while showing the ones that do
								$(filter_list).find("li:not(:Contains(" + filter + "))").slideUp();
								$(filter_list).find("li:Contains(" + filter + ")").slideDown();
							} else {
								$(filter_list).find("li").slideDown();
							}
							return false;
						})
					.keyup( function () {
							// fire the above change event after every letter
							$(this).change();
					});
				}
				
				var slide_btn = $("<i>").attr({"class":"fa toggle-btn"});
				if ($(filter_list).is(':visible')) {
					slide_btn.removeClass("fa-toggle-up").addClass("fa-toggle-down");
				}
				else {
					slide_btn.removeClass("fa-toggle-up").addClass("fa-toggle-up");
				}
					 
				$(slide_btn).prependTo(filter_header);
				$(slide_btn).click(function() {
					if ($(filter_list).is(':visible')) {
						slide_btn.removeClass("fa-toggle-up").addClass("fa-toggle-up");
						$(filter_list).slideUp();
					}
					else {
						slide_btn.removeClass("fa-toggle-up").addClass("fa-toggle-down");
						$(filter_list).slideDown();
					}
				});
			});
		}
		//ondomready
		$(function () {
			listFilter(".filter-box", ".filter-header", ".filter-list");
			$('.filter-box .filter-list input[type="checkbox"]').click(function() {
					$(this).closest("form").submit();
			});
		});
}(jQuery));