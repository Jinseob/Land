/*
 * 특정Div에 paging 태그 그리기
 */
function drawPagingDiv(currentPage, countPerPage, totalCount) {
	var page_html = '';		//페이징에 추가할 html 저장 변수
	var pageCnt = 5;		//표시될 페이지 넘버 갯수
	
	currentPage = Number(currentPage);		//현재 페이지
	countPerPage = Number(countPerPage);	//페이지당 출력할 데이터 갯수
	totalCount = Number(totalCount);		//전체 갯수
	
// 		alert(currentPage+ "," +countPerPage + "," + totalCount);
	
	var startPageNum = (Math.ceil(currentPage / pageCnt) * pageCnt) - (pageCnt - 1) ;
	var endPageNum = Math.ceil(totalCount / countPerPage);
	
	if(currentPage > 1) {
		page_html += '<span class="btn">';
		page_html += '	<a href="javascript:selectPage(\'' + Number(currentPage - 1) + '\');" class="prev">이전 페이지로 이동</a>';
		page_html += '</span>';
	} else {
		page_html += '<span class="btn" style="display:block;">';
		page_html += '	<a class="prev" style="display:none;"></a>';
		page_html += '</span>';
	}
	
	page_html += '<span class="num">';
	for(var i = 0; i < pageCnt; i++) {
		if(startPageNum <= endPageNum) {
			page_html += '	<a href="javascript:selectPage(\'' + startPageNum + '\');"';
			if(startPageNum == currentPage) {
				page_html += ' class="on">' + startPageNum + '</a>';
			} else {
				page_html += ' >' + startPageNum + '</a>';
			}
			startPageNum++;
		}
	}
	page_html += '</span>';
	
	if(currentPage < endPageNum) {
		page_html += '<span class="btn">';
		page_html += '	<a href="javascript:selectPage(\'' + Number(currentPage + 1) + '\');" class="next">다음 페이지로 이동</a>';
		page_html += '</span>';
	}
	
	$("#div_paging").children().remove();
	$("#div_paging").append(page_html);
}

function selectPage(page_num) {
	$("#currentPage").val(page_num);
	var countPerPage = $("#countPerPage").val();
	var totalCount = $("#totalCount").val();
	
	fncSearchList();
}