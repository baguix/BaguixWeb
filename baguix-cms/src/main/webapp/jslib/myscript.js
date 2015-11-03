function menuto(m) {
	$('#main_lefttree').tree({
						url : basepath+'/manctrl/lefttree.do?pid=' + m,
						onClick : function(node) {
							if (node.attributes.url) {
								addTab({
									title : node.text,
									closable : true,
									href : node.attributes.url
								});
							}// if
						}// onClick
	});// tree
}

function linkto(url) {
	window.location.href = url;
}

function addTab(opts) {
	var t = $('#layout_center_tabs');
	if (t.tabs('exists', opts.title)) {
		t.tabs('select', opts.title);
	} else {
		t.tabs('add', opts);
	}
}
