<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

<title>부동산 시세 정보</title>

<jsp:directive.include file="/WEB-INF/views/common/taglib.jsp" />
<jsp:directive.include file="/WEB-INF/views/common/common_include.jsp" />

<script type="text/javascript">
	$(document).ready(function() {
		
	});
</script>
<title>부동산 시세 정보</title>
</head>

<body>
	<form id="frm" name="frm">
<%-- 		<input type="hidden" id="jeonpyo_no" name="jeonpyo_no" value="${co.jeonpyo_no}" /> --%>
<%-- 		<input type="hidden" id="ilja" name="ilja" value="${co.ilja}" /> --%>
		
<%-- 		<input type="hidden" id="searchDate_from" name="searchDate_from" value="${coVO.searchDate_from}" /> --%>
<%-- 		<input type="hidden" id="searchDate_to" name="searchDate_to" value="${coVO.searchDate_to}" /> --%>
<%-- 		<input type="hidden" id="searchCheckBox_01" name="searchCheckBox_01" value="${coVO.searchCheckBox_01}" /> --%>
		
		<div class="wrap">
<%-- 			<jsp:directive.include file="/WEB-INF/views/templates/header.jsp" /> --%>

			<div class="sub_wrap_area">
				<div class="sub_wrap">
					<div class="sub_contents">
						<div class="local_nav_wrap">
							<h3 class="sub_tit">부동산 상세보기</h3>

							<div class="local_nav">
								<ul>
									<li class="home">홈</li>
									<li>상세</li>
<!-- 									<li>주문 상세보기</li> -->
								</ul>
							</div>
							<!--local_nav-->

						</div>
						<div class="sub_cont">

							<div class="search_btn_wrap" style="">
								<p class="result_num">주문상품 : ${fn:length(coItemList)} 건</p>
								<div class="search_btn_area">
									<input class="btn_newmake" id="" type="button" value="목록" onclick="javascript:goList();">
								</div>
							</div>

							<!-- board_list_wrap (게시물 리스트) -->
							<div class="orderdetail_wrap_tit">
								<table class="table_common" summary="전체 집계">
									<caption>주문서작성</caption>
									<colgroup>
										<col style="width: 100px;" />
										<col style="width: 100px;" />
										<col style="width: 100px;" />
										<col style="width: 100px;" />
										<col style="width: 100px;" />
										
										<col style="width: 100px;" />
										<col style="width: 100px;" />
										<col style="width: 100px;" />
										<col style="width: 100px;" />
										<col style="" />
									</colgroup>
									<thead>
										<tr>
											<th scope="col">전용면적</th>
											<th scope="col">매매평균가</th>
											<th scope="col">전세평균가</th>
											<th scope="col">매매 - 전세</th>
											<th scope="col">월세보증평균가</th>
											
											<th scope="col">월세평균</th>
											<th scope="col">월세환산가</th>
											<th scope="col">1년환산월세</th>
											<th scope="col">수익률(%)</th>
											<th scope="col">m2 당매매가</th>
										</tr>
									</thead>
								</table>
							</div>

							<div class="orderdetail_wrap">
								<table class="table_common" summary="전체 집계">
									<caption>전체 집계</caption>
									<colgroup>
										<col style="width: 100px;" />
										<col style="width: 100px;" />
										<col style="width: 100px;" />
										<col style="width: 100px;" />
										<col style="width: 100px;" />
										
										<col style="width: 100px;" />
										<col style="width: 100px;" />
										<col style="width: 100px;" />
										<col style="width: 100px;" />
										<col style="" />
									</colgroup>
									<tbody>
										<tr style="border-bottom: 0px;">
											<td class="txt_rig">${a0012.size1}</td>
											<td class="txt_rig">${a0012.amt1}</td>
											<td class="txt_rig">${a0012.amt2}</td>
											<td class="txt_rig">${a0012.diff1}</td>
											<td class="txt_rig">${a0012.amt3}</td>
											
											<td class="txt_rig blue_B">${a0012.amt4}</td>
											<td class="txt_rig blue_B">${a0012.mmcal}</td>
											<td class="txt_rig blue_B">${a0012.yycal}</td>
											<td class="txt_rig blue_B">${a0012.percal}</td>
											<td class="txt_rig blue_B last">${a0012.m2amt}</td>
										</tr>
									</tbody>
								</table>
							</div>

							<div class="order_subtit">월별 집계</div>

							<!-- board_list_wrap (게시물 리스트) -->
							<div class="orderdetail_wrap_tit">
								<table class="table_common" summary="상단">
									<caption>월별 집계</caption>
									<colgroup>
										<col style="width: 100px;" />
										<col style="width: 100px;" />
										<col style="width: 100px;" />
										<col style="width: 100px;" />
										<col style="width: 100px;" />
										
										<col style="" />
									</colgroup>
									<thead>
										<tr>
											<th scope="col">구분</th>
											<th scope="col">전용면적</th>
											<th scope="col">월별</th>
											<th scope="col">건수</th>
											<th scope="col">평균가격</th>
											
											<th scope="col">비고</th>
										</tr>
									</thead>
								</table>
							</div>

							<div class="orderdetail_wrap">
								<table class="table_common" summary="상단">
									<caption>상단</caption>
									<colgroup>
										<col style="width: 100px;" />
										<col style="width: 100px;" />
										<col style="width: 100px;" />
										<col style="width: 100px;" />
										<col style="width: 100px;" />
										
										<col style="" />
									</colgroup>
									<tbody>
										<c:choose>
											<c:when test="${fn:length(a0013List) > 0 }">
												<c:forEach items="${a0013List }" var="a0013" varStatus="status">
													<tr style="border-bottom: 0px;">
														<td class="txt_center">${a0013.type1}</td>
														<td class="txt_rig">${a0013.size1}</td>
														<td class="txt_center">${a0013.yymm}</td>
														<td class="txt_rig">${a0013.cnt}</td>
														<td class="txt_rig">${a0013.amt1}</td>
														
														<td class="txt_left blue_B last">${a0013.amt2}</td>
													</tr>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<tr id="tr_empty"><td class="pro_code txt_center" colspan="6">검색된 자료가 없습니다</td></tr>
											</c:otherwise>
										</c:choose>
									</tbody>
								</table>
							</div>

							<!-- board_list_wrap (게시물 리스트) -->
							<div class="orderdetail_wrap_tit">
								<table class="table_common" summary="상단">
									<caption>월별 집계</caption>
									<colgroup>
										<col style="width: 100px;" />
										<col style="width: 100px;" />
										<col style="width: 100px;" />
										<col style="width: 100px;" />
										<col style="width: 100px;" />
										
										<col style="width: 100px;" />
										<col style="width: 100px;" />
										<col style="width: 100px;" />
										<col style="width: 100px;" />
										<col style="" />
									</colgroup>
									<thead>
										<tr>
											<th scope="col">전용면적</th>
											<th scope="col">월별</th>
											<th scope="col">매매평균가</th>
											<th scope="col">전세평균가</th>
											<th scope="col">매매 - 전세</th>
											
											<th scope="col">월세보증평균가</th>
											<th scope="col">월세평균</th>
											<th scope="col">월세환산가</th>
											<th scope="col">1년환산월세</th>
											<th scope="col">수익률(%)</th>
										</tr>
									</thead>
								</table>
							</div>

							<div class="orderdetail_wrap">
								<table class="table_common" summary="상단">
									<caption>상단</caption>
									<colgroup>
										<col style="width: 100px;" />
										<col style="width: 100px;" />
										<col style="width: 100px;" />
										<col style="width: 100px;" />
										<col style="width: 100px;" />
										
										<col style="width: 100px;" />
										<col style="width: 100px;" />
										<col style="width: 100px;" />
										<col style="width: 100px;" />
										<col style="" />
									</colgroup>
									<tbody>
										<c:choose>
											<c:when test="${fn:length(a0014List) > 0 }">
												<c:forEach items="${a0014List }" var="a0014" varStatus="status">
													<tr style="border-bottom: 0px;">
														<td class="txt_rig">${a0014.size1}</td>
														<td class="txt_rig">${a0014.contyy}</td>
														<td class="txt_rig">${a0014.amt1}</td>
														<td class="txt_rig">${a0014.amt2}</td>
														<td class="txt_rig">${a0014.diff1}</td>
														
														<td class="txt_rig">${a0014.amt3}</td>
														<td class="txt_rig">${a0014.amt4}</td>
														<td class="txt_rig">${a0014.mmcal}</td>
														<td class="txt_rig">${a0014.yycal}</td>
														<td class="txt_rig blue_B last">${a0014.percal}</td>
													</tr>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<tr id="tr_empty"><td class="pro_code txt_center" colspan="10">검색된 자료가 없습니다</td></tr>
											</c:otherwise>
										</c:choose>
									</tbody>
								</table>
							</div>

							<div class="bottom_btn_wrap">
								<div class="right_btn_area">
									<c:choose>
										<c:when test="${co.jindo_code == 'S'}">
											<input class="btn_modify" type="button" id="" value="수정" onclick="javascript:alterCo();" />
											<input class="btn_ord_cancel" type="button" id="" value="주문 취소" onclick="javascript:cancelCo();" />
										</c:when>
										<c:otherwise>
											<input class="btn_modify" type="button" id="" value="수정" onclick="javascript:ignoreAlert();" />
											<input class="btn_ord_cancel" type="button" id="" value="주문 취소" onclick="javascript:ignoreAlert();" />
										</c:otherwise>
									</c:choose>
								</div>
							</div>
							<!--bottom_btn_wrap-->
						</div>
						<!--sub_cont-->

					</div>
					<!--sub_contents-->
				</div>
				<!--sub_wrap-->
			</div>
			<!--sub_wrap_area-->
<%-- 			<jsp:directive.include file="/WEB-INF/views/templates/footer.jsp" /> --%>
			
		</div>
		<!--wrap end-->
	</form>
</body>
</html>
