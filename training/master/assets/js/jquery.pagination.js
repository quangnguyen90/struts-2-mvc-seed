/**
 * This jQuery plugin displays pagination links inside the selected elements.
 *
 * @author Gabriel Birke (birke *at* d-scribe *dot* de)
 * @version 1.2
 * @param {int} maxentries Number of entries to paginate
 * @param {Object} opts Several options (see README for documentation)
 * @return {Object} jQuery Object
 */
jQuery.fn.pagination = function(maxentries, opts){
	opts = jQuery.extend({
		items_per_page:2,
		num_display_entries:5,
		current_page:0,
		num_edge_entries:1,
		link_to:"#",
		total_text:"件数",
		page_number_text:"ページ数",
		first_img:"<img src='img/icons_first.gif' width='14' height='13' border='0'/>",
		prev_img:"<img src='img/icon_prev.gif' width='14' height='13' border='0'/>",
		next_img:"<img src='img/icons_next.gif' width='14' height='13' border='0'/>",
		last_img:"<img src='img/icons_last.gif' width='14' height='13' border='0'/>",
		ellipse_text:"...",
		prev_show_always:true,
		next_show_always:true,
		callback:function(){return false;}
	},opts||{});
	
	return this.each(function() {
		/**
		 * Calculate the maximum number of pages
		 */
		function numPages() {
			return Math.ceil(maxentries/opts.items_per_page);
		}
		
		/**
		 * Calculate start and end point of pagination links depending on 
		 * current_page and num_display_entries.
		 * @return {Array}
		 */
		function getInterval()  {
			var ne_half = Math.ceil(opts.num_display_entries/2);
			var np = numPages();
			var upper_limit = np-opts.num_display_entries;
			var start = current_page>ne_half?Math.max(Math.min(current_page-ne_half, upper_limit), 0):0;
			var end = current_page>ne_half?Math.min(current_page+ne_half, np):Math.min(opts.num_display_entries, np);
			return [start,end];
		}
		
		/**
		 * This is the event handling function for the pagination links. 
		 * @param {int} page_id The new page number
		 */
		function pageSelected(page_id, evt){
			current_page = page_id;
			drawLinks();
			var continuePropagation = opts.callback(page_id, panel);
			if (!continuePropagation) {
				if (evt.stopPropagation) {
					evt.stopPropagation();
				}
				else {
					evt.cancelBubble = true;
				}
			}
			return continuePropagation;
		}
		
		/**
		 * This function inserts the pagination links into the container element
		 */
		function drawLinks() {
			panel.empty();
			var interval = getInterval();
			var np = numPages();
			// This helper function returns a handler function that calls pageSelected with the right page_id
			var getClickHandler = function(page_id) {
				return function(evt){ return pageSelected(page_id,evt); }
			}
			// Helper function for generating a single link (or a span tag if it's the current page)
			var appendItem = function(page_id, appendopts){
				if(page_id==null){
					if(appendopts.classes=="total"){
						var lnk = jQuery("<span>"+(appendopts.text)+" : "+maxentries+"</span>");
					}else{
						var lnk = jQuery("<span>"+(appendopts.text)+" : "+(1+current_page)+"/"+np+"</span>");
					}
					
				}else{
					page_id = page_id<0?0:(page_id<np?page_id:np-1); // Normalize page id to sane value
					appendopts = jQuery.extend({text:page_id+1, classes:""}, appendopts||{});
					if(page_id == current_page){
						var lnk = jQuery("<span class='current'>"+(appendopts.text)+"</span>");
					}
					else
					{
						var lnk = jQuery("<a>"+(appendopts.text)+"</a>")
							.bind("click", getClickHandler(page_id))
							.attr('href', opts.link_to.replace(/__id__/,page_id));
							
							
					}
				}
				
				if(appendopts.classes){lnk.addClass(appendopts.classes);}
				panel.append(lnk);
			}
			
			if(opts.total_text){
				appendItem(null,{text:opts.total_text,classes:"total"});
			}
			// Generate "selected page number"
			if(opts.page_number_text){
				appendItem(null,{text:opts.page_number_text,classes:"sel_page_number"});
			}
			
			// Generate "first"-Link
			if(opts.first_img && (current_page > 0 || opts.prev_show_always)){
				appendItem(0,{text:opts.first_img, classes:"prev"});
			}
			// Generate "Previous"-Link
			if(opts.prev_img && (current_page > 0 || opts.prev_show_always)){
				appendItem(current_page-1,{text:opts.prev_img, classes:"prev"});
			}
			// Generate "Next"-Link
			if(opts.next_img && (current_page < np-1 || opts.next_show_always)){
				appendItem(current_page+1,{text:opts.next_img, classes:"next"});
			}
			// Generate "Last"-Link
			if(opts.last_img && (current_page < np-1 || opts.next_show_always)){
				appendItem(numPages(),{text:opts.last_img, classes:"next"});
			}
		}
		
		// Extract current_page from options
		var current_page = opts.current_page;
		// Create a sane value for maxentries and items_per_page
		maxentries = (!maxentries || maxentries < 0)?1:maxentries;
		opts.items_per_page = (!opts.items_per_page || opts.items_per_page < 0)?1:opts.items_per_page;
		// Store DOM element for easy access from all inner functions
		var panel = jQuery(this);
		// Attach control functions to the DOM element 
		this.selectPage = function(page_id){ pageSelected(page_id);}
		
		// When all initialisation is done, draw the links
		drawLinks();
        // call callback function
        opts.callback(current_page, this);
	});
}


