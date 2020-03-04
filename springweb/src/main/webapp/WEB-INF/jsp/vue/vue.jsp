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
			<h5 id="exam01">1. Html Binding</h5>
			<div id="app">
				{{ message }}
			</div>
			<div class="space-line"></div>
			<h5>2. Attribute Binding</h5>
			<div id="app-2">
				<span v-bind:title="message">
					내 위에 잠시 마우스를 올리면 동적으로 바인딩 된 title을 볼 수 있습니다!
				</span>
			</div>
			<div class="space-line"></div>
			<h5>4. if</h5>
			<div id="app-3">
				<p v-if="seen">이제 나를 볼 수 있어요</p>
			</div>
			<div class="space-line"></div>
			<h5>5. for</h5>
			<div id="app-4">
				<ol>
					<!-- <li v-for="(obj, index) in resultList" v-if="index > 0"> -->
					<li v-for="(obj, index) in resultList">
						{{ obj.text }}
					</li>
				</ol>
				<button v-on:click="addData">data 추가</button>
			</div>
			<div class="space-line"></div>
			<h5>5. click event</h5>
			<div id="app-5">
				<p>{{ message }}</p>
				<button v-on:click="alertMessage">click 이벤트</button>
			</div>
			<div class="space-line"></div>
			<h5>6. form input binding</h5>
			<div id="app-6">
				<p>{{ message }}</p>
				<input v-model="message"><br>
				<select v-model="selValue">
					<option value="10">10</option>
					<option value="20">20</option>
					<option value="30">30</option>
					<option value="40">40</option>
				</select><br>
				<input type="checkbox" v-model="chkValue" /><br>
				apple : <input type="radio" name="rdo" value="apple" v-model="rdoValue" />
				banana : <input type="radio" name="rdo" value="banana" v-model="rdoValue" />
				grape : <input type="radio" name="rdo" value="grape" v-model="rdoValue" />
			</div>
			<div class="space-line"></div>
			<h5>7. Component</h5>
			<div id="app-7">
				<ol>
				<!--
					이제 각 todo-item 에 obj 객체를 제공합니다.
					화면에 나오므로, 각 항목의 컨텐츠는 동적으로 바뀔 수 있습니다.
					또한 각 구성 요소에 "키"를 제공해야합니다
				-->
				<todo-item
					v-for="item in groceryList"
					v-bind:obj="item"
					v-bind:key="item.id"
				></todo-item>
			</ol>
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
		var jsonData = {};
		jsonData.message = '안녕하세요 Vue!';

		// 1. Html Binding
		var app = new Vue({
			el: '#app',
			data: jsonData
		});

		var jsonData2 = {};
		jsonData2.message = '이 페이지는 ' + new Date() + ' 에 로드 되었습니다';

		// 2. Attribute Binding
		var app2 = new Vue({
			el: '#app-2',
			data: jsonData2
		});

		// 3. if문
		var jsonData3 = {};
		jsonData3.isShow = true;

		var app3 = new Vue({
			el: '#app-3',
			data: {
				seen: jsonData3.isShow
			}
		});

		// 4. for문
		var jsonData4 = {};
		var list = [
			{ text: 'JavaScript 배우기' },
			{ text: 'Vue 배우기' },
			{ text: '무언가 멋진 것을 만들기' }
		];
		jsonData4.resultList = list;

		var app4 = new Vue({
			el: '#app-4',
			data: jsonData4,
			methods: {
				addData: function (event) {
					this.resultList.push({text: 'data를 추가합니다.'});
				}
			}
		});

		// 5. click event
		var jsonData5 = {};
		jsonData5.message = '안녕하세요! Vue.js!';
		var app5 = new Vue({
			el: '#app-5',
			data: jsonData5,
			methods: {
				reverseMessage: function (event) {
					//this.message = this.message.split('').reverse().join('');
					_reverseMessage(this);
				},
				addMessage: function (event) {
					this.message = this.message + "추가 메시지!!";
				},
				alertMessage: function (event) {
					alert(this.message);
				}
			}
		});

		function _reverseMessage(obj) {
			obj.message = obj.message.split('').reverse().join('');
		}

		// 6. form data binding
		var app6 = new Vue({
			el: '#app-6',
			data: {
				message: '안녕하세요 Vue!',
				selValue : "20",
				chkValue : true,
				rdoValue : "banana"
			}
		});

		// 7. Component
		/*
		Vue.component('todo-item', {
			template: '<li>할일 항목 하나입니다.</li>'
		});
 */
		Vue.component('todo-item', {
			// 이제 todo-item 컴포넌트는 "prop" 이라고 하는
			// 사용자 정의 속성 같은 것을 입력받을 수 있습니다.
			// 이 prop은 obj라는 이름으로 정의했습니다.
			props: ['obj'],
			template: '<li>{{ obj.text }}</li>'
		});

		var app7 = new Vue({
			el: '#app-7',
			data: {
				groceryList: [
					{ id: 0, text: 'Vegetables' },
					{ id: 1, text: 'Cheese' },
					{ id: 2, text: 'Whatever else humans are supposed to eat' }
				]
			}
			});


	};

	this.intializeEvent = function() {

	};
}
</script>
</html>