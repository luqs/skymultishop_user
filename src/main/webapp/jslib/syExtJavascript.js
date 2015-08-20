/**
 * 去字符串空格
 * 
 * @author zzpeng
 */
com.sirius.skymall.user.trim = function(str) {
	return str.replace(/(^\s*)|(\s*$)/g, '');
};
com.sirius.skymall.user.ltrim = function(str) {
	return str.replace(/(^\s*)/g, '');
};
com.sirius.skymall.user.rtrim = function(str) {
	return str.replace(/(\s*$)/g, '');
};

/**
 * 判断开始字符是否是XX
 * 
 * @author zzpeng
 */
com.sirius.skymall.user.startWith = function(source, str) {
	var reg = new RegExp("^" + str);
	return reg.test(source);
};
/**
 * 判断结束字符是否是XX
 * 
 * @author zzpeng
 */
com.sirius.skymall.user.endWith = function(source, str) {
	var reg = new RegExp(str + "$");
	return reg.test(source);
};

/**
 * iframe自适应高度
 * 
 * @author zzpeng
 * 
 * @param iframe
 */
com.sirius.skymall.user.autoIframeHeight = function(iframe) {
	iframe.style.height = iframe.contentWindow.document.body.scrollHeight + "px";
};

/**
 * 设置iframe高度
 * 
 * @author zzpeng
 * 
 * @param iframe
 */
com.sirius.skymall.user.setIframeHeight = function(iframe, height) {
	iframe.height = height;
};