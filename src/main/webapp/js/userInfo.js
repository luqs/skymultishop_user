$(function() {
	$('#resources').tree({
		parentField : 'pid',
		data : eval("(" + resourceTreeJson + ")")
	});
});