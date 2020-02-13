(function ($){

	$.igframework.ajax = function (options) {
		var defaultOptions =
		{
			dataType: "json"
			,type: "POST"
			,contentType: "application/json"
			,methodName: null
			,url: null
			,data: {}
			,callback: null
			,async: true
			,cover : true
		};

		var mergeOptions = $.extend(true,{}, defaultOptions, options);

		var jsonString = JSON.stringify(mergeOptions.data);
		//mergeOptions.data += "__igframework__=" + encodeURIComponent(jsonString);

		var ajaxOptions = {
			dataType: "json"
			,type: "POST"
			,contentType: "application/json"
			,methodName: mergeOptions.methodName
			,url: mergeOptions.url
			,data: jsonString//mergeOptions.data
			,async: mergeOptions.async
			,beforeSend: function() {
				if(mergeOptions.cover) {
					$.igframework.showProgressBar();
				}
			}
			,success: function (responseData, responseStatus, jqXHR) {
				if (responseData.IsError) {
					alert(responseData.ErrorCode);
				}
				else {
					if (mergeOptions.callback != null) {
						try {
							mergeOptions.callback(responseData);
						} catch (e) {

							alert("$.igframework.ajax callback : " + (typeof(e) == "string" ? e : e.message));
						}
					}
				}
			}
			,error: function (jqXHR, textStatus, errorThrown) {
				if(errorThrown == "Unknown" || errorThrown == "" || errorThrown == undefined) {
					alert("[" + jqXHR.status + "] 시스템 장애가 발생했습니다.");
				}
				else{
					alert("$.igframework.ajax error : " + errorThrown);
				}
			}
			,complete : function(){
				if(mergeOptions.cover) {
					$.igframework.hideProgressBar();
				}
			}
		};

		$.ajax(ajaxOptions);
	};

})(jQuery);