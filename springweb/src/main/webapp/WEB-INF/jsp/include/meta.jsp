<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<title><spring:eval expression="@config['Globals.Page.Title']"/></title>
<!-- <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" /> -->

<meta name="_csrf_header" content="${_csrf.headerName}"/>
<meta name="_csrf" content="${_csrf.token}"/>