<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

<title>부동산 시세 정보</title>

<jsp:directive.include file="/WEB-INF/views/common/taglib.jsp" />
<jsp:directive.include file="/WEB-INF/views/common/common_include.jsp" />
  
<script type="text/javascript">
	$(document).ready(function(){
		var fileTarget = $('.filebox .upload-hidden');
		
		fileTarget.on('change', function(){
			if(window.FileReader){
				var filename = $(this)[0].files[0].name;
			}else{
				var filename = $(this).val().split('/').pop().split('\\').pop();	// 파일명만 추출하는 부분.
			}
			
			$(this).siblings('.upload-name').val(filename);
		});
	});

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
<!-- 	<input type="button" value="상세이동" onclick="fncPageLoad('A0010.do')"/> -->
	<div class="wrap">
		<div class="sub_wrap_area">
			<div class="sub_wrap">
				<div class="sub_contents">
					<div class="local_nav_wrap">
						<h3 class="sub_tit">부동산 검색</h3>
						<div class="local_nav">
							<ul>
								<li style="background:none;"><img src="img/navi_home.gif" alt="HOME" title="HOME"></li>
<!-- 								<li>주문관리</li> -->
<!-- 								<li>제품검색</li> -->
							</ul>
	            		</div>
					</div>
					<div class="sub_cont">
<!-- 						<div class="search_check" id=""> -->
<!-- 							<div class="search_more_wrap search_more_on" id="opencheckBtn"> -->
<!-- 								<a href="#">더보기</a> -->
<!-- 							</div> -->
<!-- 							<input class="search_checkbox" id="parentChkBox_search" name="parentChkBox_search" type="checkbox" onclick="checkAll(this, 'uf_salegroup1');"/> -->
<!-- 							<label class="search_label" for="parentChkBox_search" style="font-weight: bold;">전체</label> -->
<!-- 						</div> -->
						<div class="search_area">
<!-- 								<input type="radio" name="radio_selectType" id="" checked/>  -->
<!-- 								<span>전체</span> -->
<!-- 								<input type="radio" name="radio_selectType" id=""/>  -->
<!-- 								<span>시판품</span> -->
<!-- 								<input type="radio" name="radio_selectType" id=""/>  -->
<!-- 								<span>주문품</span><br> -->
<!-- 							<select id="select_searchDiv" name="searchDiv"> -->
<%-- 								<option value="description" <c:if test="${searchVO.searchDiv eq 'description'}">selected</c:if>>제품명</option> --%>
<%-- 								<option value="item_code" <c:if test="${searchVO.searchDiv eq 'item_code'}">selected</c:if>>제품코드</option> --%>
<!-- 							</select> -->
							<h3 class="search_tit">시군구</h3>
							<input class="txt" id="input_searchText1" name="searchText1" type="text" value="${searchVO.searchText}" 
								onkeydown="if(event.keyCode==13){search(); return false;}" />
							<br/>
							<h3 class="search_tit">단지명</h3>
							<input class="txt" id="input_searchText2" name="searchText2" type="text" value="${searchVO.searchText}" 
								onkeydown="if(event.keyCode==13){search(); return false;}" />
							<br/>
							<h3 class="search_tit">전용면적(m2)</h3>
							<input class="txt" id="input_searchText3" name="searchText3" type="text" value="${searchVO.searchText}" 
								onkeydown="if(event.keyCode==13){search(); return false;}" />
							<br/>
							<h3 class="search_tit">기간</h3>
							<input class="txt" id="input_searchText4" name="searchText4" type="text" value="${searchVO.searchText}" 
								onkeydown="if(event.keyCode==13){search(); return false;}" />
							<br/>
							<h3 class="search_tit"></h3>
							<input class="btn_search1" type="button" onclick="fncPageLoad('A0010.do');" title="상세이동" />
							
							<br/>
							<br/>
							
							<div class="filebox">
								<input type="file" name="fileData" id="fileData" class="upload-hidden"/>
								<label for="fileData" class="btn_file">파일 검색</label>
								<input class="upload-name" value="파일선택" disabled="disabled"> 
								<input type="button" value="올리기" onclick="fncOnLoadPage('fileUpload.do')" class="btn_file"/>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</form>
</body>

</html>
