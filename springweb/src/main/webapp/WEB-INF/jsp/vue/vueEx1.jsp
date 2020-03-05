<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<%@ include file="/WEB-INF/jsp/include/meta.jsp"%>
	<%@ include file="/WEB-INF/jsp/include/scriptlib.jsp"%>


<style type="text/css">
	#contents {
		padding: 30px 15px;
		height: 100vh;
	}
	.left-vue-content {
		width: 250px;
		float: left;
	}
	.example-content {
		padding-left: 15px;
		float: left;
		width: 80%;
	}
	.space-line{
		height: 15px;
	}
</style>
</head>
<body onload="pageLoad();">
	<div id="contents">
		<h2>Vue Example</h2>
		<hr>
		<div class="left-vue-content">
			<a href="javascript:void(0);" data-menu="exam01">1. Html Binding</a>
		</div>
		<div class="example-content">
			<h3>1. Vue 인스턴스</h3>
			<hr>
			<h5 id="exam01">1-1. 원본 data값을 변경X</h5>
			<div id="app-1">
				<p>{{ foo }}</p>
				<!-- obj.foo는 더이상 변하지 않습니다! -->
				<button v-on:click="foo = 'baz'">Change it</button>
			</div>
			<h5>1-2. Vue 인스턴스 속성</h5>

			<h5>1-3. 인스턴스 라이프사이클 훅</h5>
			<div id="app-3">
				<p>{{ text }}</p>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
var _page = null;
function pageLoad() {
	_page = new fn_page();
	_page.intialize();
}

function fn_page() {
	this.intialize = function() {
		var obj = {
			foo: 'bar'
		}

		Object.freeze(obj)

		var vm = new Vue({
					el: '#app-1',
					data: obj
				});

		// 속성
		console.log(vm.$data);	// jsonData
		console.log(vm.$el);	// element

		var vm3 = new Vue({
					el: '#app-3',
					data: {
						text: "vue lifeCycle!!"
					},
					beforeCreate: () => console.log("beforeCreate"),	// 객체 생성 전
					created: () => console.log("created"),				// 객체 생성 후
					beforeMount: () => console.log("beforeMount"),		// data binding 전
					mounted: () => console.log("mounted"),				// data binding 후
					beforeUpdate: () => console.log("beforeUpdate"),	// data 변경 전
					updated: () => console.log("updated"),				// data 변경 후
					beforeDestroy: () => console.log("beforeDestroy"),
					destroyed: () => console.log("destroyed")
				});

		// data change
		vm3.$data.text = "vue data update!!";

		// vue instance destroy
		vm3.$destroy();
	};

	this.intializeEvent = function() {

	};
}
</script>
</html>