(function ($){

	function fn_ajax(options){
		var attachDefaultOptions =
        {
            dataType: 'text',
            type: "POST" ,
            Binder: {
                useBinder: false,
                useCover : false,
                assemblyInfo : "",
                methodName: null,
                classFullName: null,
                debug : false,
                data: {}
            }
            , success: null
        };

        var parameterOptions = $.extend({}, attachDefaultOptions, options);

        var jsonString = '';
        if (parameterOptions.Binder.useBinder == true) {
            parameterOptions.dataType = "text";
            jsonString = JSON.stringify(parameterOptions.Binder);
            if($.staframework.environment('Data.Encryption.YN') =='Y'){
            	jsonString = $.staframework.base64enc(jsonString);
            }
        }

        if (jsonString != '') {
            try {
                if (parameterOptions.data && typeof parameterOptions.data === 'string') {
                    if (parameterOptions.data.toString().indexOf('&') > 0) {
                        parameterOptions.data += "&__staframework__=" + encodeURIComponent(jsonString);
                    }
                    else {
                        parameterOptions.data += "__staframework__=" + encodeURIComponent(jsonString);
                    }
                }
                else {
                    // use plainObject
                    if (!parameterOptions.data) {
                        parameterOptions.data = {};
                    }
                    var attachOptions = { '__staframework__': jsonString };
                    parameterOptions.data = $.extend({}, parameterOptions.data, attachOptions);
                }
            }
            catch (e) {
                alert('error :' + e.description);
            }
        }


        var originalCallbackFunction = parameterOptions.success;

        var successCallbackFunction = function (responseData, responseStatus, jqXHR) {

        	if (parameterOptions.dataType != "jsonp"){
        		// check session
				var result = $.staframework.result(responseData);

				if (!result.IsAuthenticated) {
					$.staframework.getValidLayoutWIndow().$.staframework.logout('세션이 종료되었습니다.\n다시 로그인 해주세요');
					return false;
				}
				 // bind success callback function
	            if (originalCallbackFunction != null) {
	                originalCallbackFunction(responseData, responseStatus, jqXHR);
	            }
			}


        };
        parameterOptions.success = successCallbackFunction;
        return $.ajax(parameterOptions);
	}



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
                    $.staframework.message.alert(responseData.ErrorCode);
                }
                else {
                	if (mergeOptions.callback != null) {
                		try {
                			mergeOptions.callback(responseData);
                		} catch (e) {

                			alert("$.staframework.ajax callback : " + (typeof(e) == "string" ? e : e.message));
                		}
                    }
                }
            }
            ,error: function (jqXHR, textStatus, errorThrown) {
            	if(errorThrown == "Unknown" || errorThrown == "" || errorThrown == undefined) {
            		alert("[" + jqXHR.status + "] 시스템 장애가 발생했습니다.");
            	}
            	else{
            		alert("$.staframework.ajax error : " + errorThrown);
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