/**
 * 截取中间字符串
 * 示例: var value = "addTabHref(%www.baidu.com%)";
 * var u = SS_cutString(value, "addTabHref(%", "%)");
 * u=www.baidu.com
 */

function SS_cutString(str, begin, end){
	var value = str.substring(begin.length, str.lastIndexOf(end));
	return value;
}


/**
 * 数字判断
 * 
 * @param value
 */
function SS_isNumber(value) {
	return /(0|^[1-9]\d*$)/.test(value);
}

/**
 * 汉字判断
 * 
 * @param value
 */
function SS_isChinese(value) {
	return /^[\u4e00-\u9fa5]+$/.test(value);
}

/**
 * 根据iframe对象获取iframe的window对象
 */
function SS_GetFrameWindow(frame){
	return frame && typeof(frame)=='object' && frame.tagName == 'IFRAME' && frame.contentWindow;
}

/**
 * 根据iframe的id获取iframe的document对象
 */
function SS_getIFrameDOM(id){  
    return document.getElementById(id).contentDocument || document.frames[id].document;  
}

/**
 * 判断值是不是在数组里
 */
function SS_inarray(arr, value){
	//判断是不是在数组里
	for(i=0;i<arr.length;i++){
		if(value == $.trim( arr[i] )){
			return true;
		}
	}
	return false;
}

/**
 * 过滤数组重复元素(已经给Array增加了一个unique属性)
 * 用法：
 * var arr = new Array();
 * arr[0] = "George";
 * arr[1] = "John";
 * arr[2] = "John";
 * arr.unique();
 */
Array.prototype.unique = SS_array_unique;
function SS_array_unique() {
	var o = new Object();
	for ( var i = 0, j = 0; i < this.length; i++) {
		if (typeof o[this[i]] == 'undefined') {
			o[this[i]] = j++;
		}
	}
	this.length = 0;
	for ( var key in o) {
		this[o[key]] = key;
	}
	return this;
}

/**兼容IE6没有trim方法*/
String.prototype.trim = function() {  
    return this.replace(/^\s+|\s+$/g,"");  
}  
String.prototype.ltrim = function() {  
    return this.replace(/^\s+/,"");  
}  
String.prototype.rtrim = function() {  
    return this.replace(/\s+$/,"");  
}  

/**
 * gb2312编码
 * 
 * @param str
 */
function SS_encodeToGb2312(str) {
	var strOut = "";
	for ( var i = 0; i < str.length; i++) {
		var c = str.charAt(i), code = str.charCodeAt(i);
		if (c == " ")
			strOut += "+";
		else if (code >= 19968 && code <= 40869) {
			var index = code - 19968;
			strOut += "%" + z.substr(index * 4, 2) + "%"
					+ z.substr(index * 4 + 2, 2);
		} else {
			strOut += "%" + str.charCodeAt(i).toString(16);
		}
	}
	return strOut;
}
/**
 * 用JS解析URL
 * 实例：
 * var myURL = parseURL('http://abc.com:8080/dir/index.html?id=255&m=hello#top');
 * myURL.file;     // = 'index.html'
 * myURL.hash;     // = 'top'
 * myURL.host;     // = 'abc.com'
 * myURL.query;    // = '?id=255&m=hello'
 * myURL.params;   // = Object = { id: 255, m: hello }
 * myURL.path;     // = '/dir/index.html'
 * myURL.segments; // = Array = ['dir', 'index.html']
 * myURL.port;     // = '8080'
 * myURL.protocol; // = 'http'
 * myURL.source;   // = 'http://abc.com:8080/dir/index.html?id=255&m=hello#top'
 * 
 */
function parseURL(url) {
    var a =  document.createElement('a');
    a.href = url;
    return {
        source: url,
        protocol: a.protocol.replace(':',''),
        host: a.hostname,
        port: a.port,
        query: a.search,
        params: (function(){
            var ret = {},
                seg = a.search.replace(/^\?/,'').split('&'),
                len = seg.length, i = 0, s;
            for (;i<len;i++) {
                if (!seg[i]) { continue; }
                s = seg[i].split('=');
                ret[s[0]] = s[1];
            }
            return ret;
        })(),
        file: (a.pathname.match(/\/([^\/?#]+)$/i) || [,''])[1],
        hash: a.hash.replace('#',''),
        path: a.pathname.replace(/^([^\/])/,'/$1'),
        relative: (a.href.match(/tps?:\/\/[^\/]+(.+)/) || [,''])[1],
        segments: a.pathname.replace(/^\//,'').split('/')
    };
}