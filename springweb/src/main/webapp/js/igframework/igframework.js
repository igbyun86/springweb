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
	$.igframework.filedownload = function(url, jsonData, addData){
		__tmpr__++;
		var target = 'exceliframe'+__tmpr__;
		var $iframe = $("<iframe name='" + target+ "' width='0' height='0' style='display: none;' />");
		$("body").append($iframe);
		var jsonString = JSON.stringify(jsonData);

		var dynamicForm = $("<form/>").attr({ method: 'post', action: url, target: target });
		dynamicForm.append($("<input/>").attr({ name: "__json__data", value: jsonString }));

		// csrf
		var csrf_token = $('meta[name="_csrf"]').attr('content');
		dynamicForm.append($("<input/>").attr({ name: "_csrf", value: csrf_token }));

		for (key in addData) {
			dynamicForm.append($("<input/>").attr({ name: key, value: addData[key] }));
		}

		$iframe.append(dynamicForm);

		dynamicForm.submit();
		dynamicForm.remove();
		//$iframe.remove();
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
		$.igframework.filedownload(url,parameter, {});
	};

})(jQuery);