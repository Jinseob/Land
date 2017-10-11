<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

<title>부동산 시세 정보</title>

<jsp:directive.include file="/WEB-INF/views/common/taglib.jsp" />
<jsp:directive.include file="/WEB-INF/views/common/common_include.jsp" />
  
<script type="text/javascript">
	function fncOnLoadPage(url){
		var fileData = $("#fileData").get(0).files[0];
		if(fileData){
			$("#frm").attr({"action" : url, "method" : "POST", "target" : "_self"}).submit();	
		}else{
			alert("파일을 첨부해주세요.");
		}
	}
	
	function fncPageLoad(page){
		$("#frm").attr({"action" : page, "method" : "POST", "target" : "_self"}).submit();
	}
</script>
</head>

<body>
<form id="frm" name="frm" enctype="multipart/form-data">
	<input type="file" name="fileData" id="fileData" />
	<input type="button" value="올리기" onclick="fncOnLoadPage('fileUpload.do')"/>
	<input type="button" value="상세이동" onclick="fncPageLoad('A0010.do')"/>
</form>
	<div class="wrap">
		<div class="sub_wrap_area">
			<div class="sub_wrap">
				<div class="sub_contents">
					<div class="local_nav_wrap">
						<h3 class="sub_tit">제품검색</h3>
						<div class="local_nav">
							<ul>
								<li style="background:none;"><img src="img/navi_home.gif" alt="HOME" title="HOME"></li>
								<li>주문관리</li>
								<li>제품검색</li>
							</ul>
	            		</div>
					</div>
					<div class="sub_cont">
						<div class="search_check" id="">
<!-- 							<div class="search_more_wrap search_more_on" id="opencheckBtn"> -->
<!-- 								<a href="#">더보기</a> -->
<!-- 							</div> -->
<!-- 							<input class="search_checkbox" id="parentChkBox_search" name="parentChkBox_search" type="checkbox" onclick="checkAll(this, 'uf_salegroup1');"/> -->
<!-- 							<label class="search_label" for="parentChkBox_search" style="font-weight: bold;">전체</label> -->
						</div>
						<div class="search_area">
<!-- 								<input type="radio" name="radio_selectType" id="" checked/>  -->
<!-- 								<span>전체</span> -->
<!-- 								<input type="radio" name="radio_selectType" id=""/>  -->
<!-- 								<span>시판품</span> -->
<!-- 								<input type="radio" name="radio_selectType" id=""/>  -->
<!-- 								<span>주문품</span><br> -->
							<select id="select_searchDiv" name="searchDiv">
								<option value="description" <c:if test="${searchVO.searchDiv eq 'description'}">selected</c:if>>제품명</option>
								<option value="item_code" <c:if test="${searchVO.searchDiv eq 'item_code'}">selected</c:if>>제품코드</option>
							</select>
							<input class="txt" id="input_searchText" name="searchText" type="text" value="${searchVO.searchText}" 
								onkeydown="if(event.keyCode==13){search(); return false;}">
							<input class="btn_search" type="button" onclick="search();">
						</div>
						
<!-- 						<div class="common_tab_wrap"> -->
<!-- 							<ul> -->
<%-- 								<li <c:if test="${searchVO.sales_type == ''}">class="on"</c:if>><a href="javascript:changeSalesType('');">일반 조회</a></li> --%>
<%-- 								<li <c:if test="${searchVO.sales_type == 'detail'}">class="on"</c:if>><a href="javascript:changeSalesType('detail');">상세 조회</a></li> --%>
<!-- 							</ul> -->
<!-- 						</div> -->
						
						<div class="common_tab_contents">
							<div class="search_btn_wrap">
								<p class="result_num">검색결과 : ${fn:length(list)}건</p>
								<div class="search_btn_area">
<!-- 									<span>페이지당 표시 : </span> -->
<!-- 									<select id="select_countPerPage" name="select_countPerPage" style="margin-right:20px;"> -->
<%-- 										<option value="10" <c:if test="${searchVO.page_countPer == '10'}">selected</c:if>>10</option> --%>
<%-- 										<option value="25" <c:if test="${searchVO.page_countPer == '25'}">selected</c:if>>25</option> --%>
<%-- 										<option value="50" <c:if test="${searchVO.page_countPer == '50'}">selected</c:if>>50</option> --%>
<!-- 									</select> -->
<!-- 									<input class="btn_reset" id="" type="button" value="전체 재고조회" onclick="ajaxRefreshQty_all();"> -->
								</div>
							</div>
			
							<div class="searchlist_wrap_tit">
								<table summary="검색">
									<caption>검색</caption>
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
								</table>
							</div>
							
							<div class="searchlist_wrap">
								<table class="table_common focus_tr" id="table_item" summary="제품검색" >
									<caption>제품검색</caption>
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
									<tbody>
									<c:choose>
										<c:when test="${fn:length(list) > 0 }">
											<c:forEach var="list" items="${list }" varStatus="status" end="10">
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
													<td>${list.vulidyy }</td>
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
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</body>

</html>
