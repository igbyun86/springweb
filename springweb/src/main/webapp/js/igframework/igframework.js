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

	$.igframework.parseTime = function(arrvTime, arrvPreStationCnt) {
		var result = "";

		if (arrvTime == 0 && arrvPreStationCnt == 0) {
			result = "도착정보없음";

			return result;
		}

		if (Number(arrvTime) > 0) {
			var minute = parseInt(arrvTime / 60);
			var second = arrvTime % 60;
			result = minute + "분" + second + "초";
			result += "[" + arrvPreStationCnt + "번째전]";
		}

		/*
		var mIndex = strArriveInfo.indexOf("분");
		if(mIndex > -1){
			var sIndex = strArriveInfo.indexOf("초후");
			result.time = strArriveInfo.substring(0,mIndex) + "분" + strArriveInfo.substring(mIndex+1,sIndex) + "초";

			var tIndex = strArriveInfo.indexOf("[");
			result.info = strArriveInfo.substring(tIndex+1, strArriveInfo.length-1);
		}
		else{
			result.time = strArriveInfo;
			result.info = "";
		}
		 */
		return result;
	};

})(jQuery);