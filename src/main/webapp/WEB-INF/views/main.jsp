<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

<title>부동산 시세 정보</title>

<jsp:directive.include file="/WEB-INF/views/common/taglib.jsp" />
<jsp:directive.include file="/WEB-INF/views/common/common_include.jsp" />
  
<script type="text/javascript">
	function fncOnLoadPage(url){
		$("#frm").attr({"action" : url, "method" : "POST", "target" : "_self"}).submit();	
	} 
</script>
</head>

<body>
<form id="frm" name="frm" enctype="multipart/form-data">
	<input type="file" name="fileData" id="fileData" />
	<input type="button" value="올리기" onclick="fncOnLoadPage('fileUpload.do')"/>
</form>
	<table>
		<colgroup>
			<col style="width:5%;" />
			<col style="width:5%;" />
			<col style="width:5%;" />
			<col style="width:5%;" />
			<col style="width:5%;" />
			<col style="width:5%;" />
			<col style="width:5%;" />
			<col style="width:5%;" />
			<col style="width:5%;" />
			<col style="width:5%;" />
			<col style="width:5%;" />
			<col style="width:5%;" />
			<col style="width:5%;" />
			<col style="" />
		</colgroup>
		<thead>
			<tr>
				<th>시군구</th>
				<th>번지</th>
				<th>본번</th>
				<th>부번</th>
				<th>단지명</th>
			    <th>구분:매매/전세/월세</th>
			    <th>전용면적(㎡)</th>
			    <th>계약년월</th>
			    <th>계약일</th>
			    <th>거래금액(만원)</th>
			    <th>월세(만원)</th>
			    <th>층</th>
			    <th>건축년도</th>
			    <th>도로명</th>
			</tr>
		</thead>
		<tbody>
		<c:choose>
			<c:when test="${fn:length(list) > 0 }">
				<c:forEach var="list" items="${list }" varStatus="status" end="5">
					<tr>
						<td>${list.sgg }</td>
						<td>${list.bunji }</td>
						<td>${list.bonbun }</td>
						<td>${list.bubun }</td>
						<td>${list.bldnm }</td>
						<td>${list.type1 }</td>
						<td>${list.size1 }</td>
						<td>${list.contyy }</td>
						<td>${list.contdd }</td>
						<td>${list.amt1 }</td>
						<td>${list.amt2 }</td>
						<td>${list.floor }</td>
						<td>${list.bulidyy }</td>
						<td>${list.doronm }</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="14">검색 결과가 없습니다.</td>
				</tr>
			</c:otherwise>
		</c:choose>
		</tbody>
	</table>
</body>

</html>
