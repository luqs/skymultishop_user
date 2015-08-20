var cleanFun = function(id) {
	var url=com.sirius.skymall.user.dbmanagementServer + '/cleanuser.action';
	$.get(url, null, function(data) {
		if(data.status==1){
			alert("success");
		}else{
			alert("fail");
		}
	}, 'json');
};
