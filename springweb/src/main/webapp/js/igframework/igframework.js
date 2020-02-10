(function ($){

	$.igframework = {};

	$.igframework.showProgressBar = function() {

		var $ = window.top.$;

		if ($("#loading-frame").length > 0) {
			$("#loading-frame").show();
		} else {
			$("<div/>").attr("id", "loading-frame")
			.append($("<div/>").addClass("window")
			.append($("<img/>").attr("src", "/images/com/progress4.gif")))
			.appendTo($("body"));
		}
	};

	$.igframework.hideProgressBar = function() {
		var $ = window.top.$;
		$("#loading-frame").hide();
	};

	var __tmpr__ = 0;
	$.igframework.filedownload = function(url, jsonData){
		__tmpr__++;
		var target = 'exceliframe'+__tmpr__;
		var $iframe = $("<iframe name='" + target+ "' border='0' width='0' height='0' />");
		$("body").append($iframe);
		var jsonString = JSON.stringify(jsonData);

		var dynamicForm = $("<form/>").attr({ method: 'post', action: url, target: target });
		dynamicForm.append($("<input/>").attr({ name: "__json__data", value: jsonString }));

		$("body").append(dynamicForm);

		dynamicForm.submit();
		dynamicForm.remove();
	};

	$.igframework.EXCELDOWN = function(options){
		var defaultOption = {
				header : "",
				title : "",
				url : "",
				fileName : "",
				sheetName : ""
		};

		options = $.extend(true,{}, defaultOption, options);

		var headData = {
				"title" : options.title,			//title
				"header" : options.header,			//header
				"fileName" : options.fileName,
				"sheetName" : options.sheetName
		};

		var parameter = {};
		parameter["headData"] = headData;

		var url = options.url;
		$.igframework.filedownload(url,parameter);
	};

})(jQuery);