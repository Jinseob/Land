/*
 * 첨부파일 확장자 검사 type = 검사할 파일명의 타입 ex: A 공지사항, G 상품권 등록 등.. file_nm = 검사할 대상 파일명
 * (String)
 */
function fileExtensionCheck(type, file_nm) {
	var result = true;
	var a_extList = [];		//공지사항
	var g_extList = [];		//상품권신청
	var l_extList = ["jpg", "jpeg", "gif", "psd"];		//대리점 소식
	var p_extList = ["jpg", "gif", "jpg", "jpg"];		//신제품 소개

	var file_ex = file_nm.substring(file_nm.lastIndexOf(".") + 1, file_nm.length);
	
	if(file_ex == null || file_ex == "" || file_nm.lastIndexOf(".") < 0) {
		c_alert("확장자가 없는 파일은 등록할 수 없습니다");
		result = false;
	}
	
//	console.log("file_ex :: " + file_ex + "//" + "type: " + type);
	if(type == "A") {			//공지사항
//		if(a_extList.indexOf(file_ex) < 0) {
//			c_alert("공지사항은 다음 확장자를 가진 파일만 등록 가능 합니다.<br>" + a_extList);
//			result = false;
//		}
	
	} else if(type == "G") {	//상품권 신청
		if(g_extList.indexOf(file_ex) < 0) {
			c_alert("상품권 신청 행사는 다음 확장자를 가진 파일만 등록 가능 합니다.<br>" + g_extList);
			result = false;
		}
	
	} else if(type == "L") {	//대리점 소식
		if(l_extList.indexOf(file_ex) < 0) {
			c_alert("대리점 소식은 다음 확장자를 가진 파일만 등록 가능 합니다.<br>" + l_extList);
			result  = false;
		}

	} else if(type == "P") {	//신제품 소개
		if(p_extList.indexOf(file_ex) < 0) {
			c_alert("신제품 소개는 다음 확장자를 가진 파일만 등록 가능 합니다.<br>" + p_extList);
			result = false;
		}
	}
	
	return result;
}


/*
 * 검색조건 날짜 셋팅 (3일)
 */
function setDefaultSearchDate() {
	//현재 모델에서 날짜 데이터 처리중
//	var date = new Date();
//	$("#searchDate_to").val(dateToString(date, "-"));
//	date.setDate(date.getDate() - 2);
//	$("#searchDate_from").val(dateToString(date, "-"));
}


/*
 * 파일 다운로드
 */
function fileDownload(file_nm) {
	$("#file_nm").val(file_nm);
	
	var frm = document.forms["frm"];
	frm.target = "_self";
	frm.method = "POST";
	frm.action = "fileDownload.do";
	frm.submit();
}

/*
 * 로그아웃
 */
function logout() {
	c_confirm("로그아웃 하시겠습니까?").then(function (result) {		
        if(result){		//yes Click
        	c_href("sdpz000901u_logout.do");
        } else {		//no Click
        	return;
        }
    });
}

/*
 * 천단위 마다 콤마 (,)붙이기
 */
function commaSplit(srcNumber) {
	var txtNumber = '' + srcNumber;

	var rxSplit = new RegExp('([0-9])([0-9][0-9][0-9][,.])');
	var arrNumber = txtNumber.split('.');
	arrNumber[0] += '.';
	do {
		arrNumber[0] = arrNumber[0].replace(rxSplit, '$1,$2');
	} while (rxSplit.test(arrNumber[0]));

	if (arrNumber.length > 1) {
		return arrNumber.join('');
	} else {
		return arrNumber[0].split('.')[0];
	}
}

/*
 * 문자, 기호등을 제거한 숫자만 반환
 */
function returnNum(str) {
	var len = str.length;
	var comp = "0123456789";
	var temp = "";
	for (i = 0; i < len; i++) {
		if (comp.indexOf(str.substring(i, i + 1)) >= 0) {
			temp = temp + str.substring(i, i + 1);
		}
	}
	return temp;
}


/*
 * 선택 아이템을 관심품목에 추가하는 dialog
 */
function dialog_addFavItem(obj) {
	var item = $(obj).parent().parent().find("[name='hid_item']").val();
	var qty_allocjob = $(obj).parent().parent().find("[name='hid_qty_allocjob']").val();
	var u_m = $(obj).parent().parent().find("[name='hid_u_m']").val();
	$("#dialog_addFavorite").dialog({
		title : "알림"
		,resizable : false
		,height : "auto"
		,width : "auto"
		,modal : true
	});
	$("#dialog_addFavorite #btn_confirm").attr("onclick", "javascript:addFavItem('" + item + "','" + qty_allocjob + "','" + u_m + "');");
}

/*
 * 실제로 db에서 관심품목에 추가
 */
function addFavItem(item, qty_allocjob, u_m) {
	$.ajaxSettings.traditional = true;
	jQuery.ajax({
		type : "POST",
		url : "ajaxAddFavItem.do",
		data : {
			group_cd : $("#target_group_cd").val()
			, item : item
			, qty_allocjob : qty_allocjob
			, u_m : u_m
		},
		datatype : "JSON",
		success : function(data) {
			if(data.resultStr == true) {
				$("input[id='"+item+qty_allocjob+u_m+"']").parent().parent().find("#btn_addFavorite").removeClass();
				$("input[id='"+item+qty_allocjob+u_m+"']").parent().parent().find("#btn_addFavorite").addClass("ico_wish_fixed");
				closeDialog("dialog_addFavorite");
			} else {
				c_alert("이미 해당 그룹에 제품이 추가 되어 있습니다");
			}
		},
		error : function(xhr, status, error) {
			alert("error");	
			closeDialog("dialog_addFavorite");
		}
	});
}

/* 
 * 제품 검색 팝업창 호출
 */
function popup_itemList(selectType){
	var pop_itemList = window.open("sdpz000101p." + selectType + ".do", "pop_itemList", "width=760,height=805, scrollbars=yes, resizable=no");
	pop_itemList.focus();
}


/* 
 * 주소록 호출 팝업
 */
function popup_manageAddr(){
	var pop = window.open("sdpz000301p.do", "배송지관리", "width=720,height=620, scrollbars=yes, resizable=yes");
	pop.focus();
}


/*
 * 검색조건의 날짜 변경 fnc
 * type : 검색조건 날짜칸에 입력될 날짜 유형
 */
function setDate(type, url){
	var date = new Date();
	
	if(type == 'today') {
		$("#searchDate_to").val(dateToString(date, "."));
		$("#searchDate_from").val(dateToString(date, "."));

	} else if(type == 'yesterday') {
		$("#searchDate_to").val(dateToString(date, "."));
		date.setDate(date.getDate() - 1);
		$("#searchDate_from").val(dateToString(date, "."));
		
	} else if(type == 'week') {
		$("#searchDate_to").val(dateToString(date, "."));
		date.setDate(date.getDate() - 6);
		$("#searchDate_from").val(dateToString(date, "."));
		
	} else if(type == 'month') {
		$("#searchDate_to").val(dateToString(date, "."));
		date.setMonth(date.getMonth() - 1);
		$("#searchDate_from").val(dateToString(date, "."));
	}
	
	c_submit('frm', url);
}


/*
 * 날짜타입 dt를 스트링 형태로 리턴
 * dt : 대상 날짜 (javascript date타입 변수)
 * divChar : 년, 월, 일 사이 입력될 구분자
 */
function dateToString(dt, divChar) {
	var date = new Date(dt);
	year = date.getFullYear();
	month = String(date.getMonth()+1).length == 1? "0"+String(date.getMonth()+1) : String(date.getMonth()+1);
	day = String(date.getDate()).length == 1 ? "0"+date.getDate() : date.getDate();
	
	return year + divChar + month + divChar + day;
}


/*
 * submit
 * formName : submit 할 폼 이름
 * targetUrl : 액션이 이루어질 URL
 */
function c_submit(formName, targetUrl) {
	showLoadingDiv();
	
	var frm = document.forms[formName];
	frm.target = "_self";
	frm.method = "POST";
	frm.action = targetUrl;
	frm.submit();
}

/*
 * c_href (로딩중 이미지를 띄우기 위해 href를 공통 펑션 으로 처리)
 */
function c_href(url) {
	showLoadingDiv();
	location.href = url;
}

/**
 * confirm dialog 호출 2
 * msg : 출력될 메시지
 */	
function c_confirm(msg) {
	var def = $.Deferred();
	
	$("#dialog_confirm").find("#div_msg").html(msg);
	$("#dialog_confirm").dialog({
		title : "알림"
		,resizable : false
		,height : "auto"
		,width : "auto"
		,modal : true
		,buttons : {
			"확인" : function() {
				def.resolve(true);
				closeDialog("dialog_confirm");
	        },
	        "취소" : function() {
	        	def.resolve(false);
	        	closeDialog("dialog_confirm");
	        }
		}
	});
	return def.promise();
}


/**
 * confirm dialog 호출 2 (장바구니 용.. 예 아니오로 넣기 위해 하나 추가함)
 * msg : 출력될 메시지
 */
function c_confirmBasket(msg) {
	var def = $.Deferred();
	
	$("#dialog_confirm").find("#div_msg").html(msg);
	$("#dialog_confirm").dialog({
		title : "알림"
		,resizable : false
		,height : "auto"
		,width : "auto"
		,modal : true
		,buttons : {
			"예" : function() {
				def.resolve(true);
				closeDialog("dialog_confirm");
	        },
	        "아니오" : function() {
	        	def.resolve(false);
	        	closeDialog("dialog_confirm");
	        }
		}
	});
	return def.promise();
}


/**
 * custom alert 호출
 */
function c_alert(msg) {
	$("#dialog_alert").find("#div_msg").html(msg);
	
	$("#dialog_alert").dialog({
		title : "알림"
		,resizable : false
		,height : "auto"
		,width : "auto"
		,modal : true
	});
	return false;
}


/**
 * 다이얼로그 닫기
 * objId : 다이얼로그 ID
 */
function closeDialog(objId) {
	$("#"+objId).dialog("close");
}


/**
 * 재고수량 갱신
 *  - 테이블과 각 필드ID를 동일하게 맞춰줘야 함
 *  - 참조 페이지 : sdpa001001l.jsp
 */
function ajaxRefreshQty_all() {
	var cbItemList = $("input[name=cbItem]");
	var itemList = [];
	for(var i = 0; i < cbItemList.length; i++){
		var temp = cbItemList[i].value.split(";");
		itemList.push(temp[0]+temp[1]+temp[2]);
		cbItemList[i].setAttribute("title", temp[0]+temp[1]+temp[2]);
	}
		
	if(cbItemList.length > 0){
		$.ajaxSettings.traditional = true;
		jQuery.ajax({
			type : "POST",
			url : "ajaxRefreshQty.do",
			data : {
				itemList : itemList
			},
			datatype : "JSON",
			success : function(data) {
				console.log(data);
				var qtyList = data.qtyList;
				var targetObj;
				for(var i = 0 ; i < qtyList.length; i++){
					targetObj = $("input[title='" + qtyList[i].item.trim() + qtyList[i].qty_allocjob.trim() + qtyList[i].um.trim() + "']");
					$(targetObj).parent().parent().find(".inventory").html(Number(qtyList[i].qty_on_hand01).toLocaleString() + "/" + Number(qtyList[i].keep_qty_onhand).toLocaleString());
					$(targetObj).parent().find("[name=qty_onhand]").val(qtyList[i].qty_on_hand01);
					$(targetObj).parent().find("[name=keep_qty_onhand]").val(qtyList[i].keep_qty_onhand);
				}
			},
			error : function(xhr, status, error) {
				alert(error);
			}
		});
	}
}


/*
 * 도로명검색API 팝업 호출
 */
function popup_searchAddress() {
	// 주소검색을 수행할 팝업 페이지를 호출합니다.
	// 호출된 페이지(jusopopup.jsp)에서 실제
	// 주소검색URL(http://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
	var pop = window.open("popup.searchAddress.do", "popupSearchAddress"
		, "width=570,height=420, scrollbars=yes, resizable=no");

	pop.focus();
}


/*
 * 도로명검색API 팝업에서 부모창으로 값을 넘겨줄 때 호출
 * roadFullAddr		:전체주소명(도로명주소)
 * roadAddrPart1	:도로명 주소1
 * addrDetail		:상세주소
 * roadAddrPart2	:도로명 주소2
 * engAddr			:영문 주소
 * jibunAddr		:지번 주소
 * zipNo			:우편번호
 * admCd			:주소 코드 (불확실)
 * rnMgtSn			:
 * bdMgtSn			:
 */
function callBackAddress(roadFullAddr, roadAddrPart1, addrDetail, roadAddrPart2,
		engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn) {
	// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
	$("#addr1").val(roadAddrPart1 + " " + roadAddrPart2);
	$("#addr2").val(addrDetail);
	$("#zip").val(zipNo);
}


/*
 * ajax 통신 중 로딩처리
 */
$(document).ajaxStart(function() {
//	showLoadingDiv();
});
$(document).ajaxStop(function() {
//	hideLoadingDiv();
});

/*
 * 로딩이미지 시작
 */
var loading_chk = false;
function showLoadingDiv() {
	if (!loading_chk) {
//		$("input:text").attr("readonly", true);
//		$("input:button").attr("disabled", true);
//		$("input:checkbox").attr("disabled", true);
//		$("input:submit").attr("disabled", true);
//		$("a").bind("click", false);
//		$("p").bind("click", false);
//		$("textarea").attr("readonly", true);

		var loadingGif = '<div id="loadingDiv" style=""><img alt="" src="img/loading.gif"></div>';
		$("body").append(loadingGif);

		loading_chk = true;
	}
}

/*
 * 로딩이미지 종료
 */
function hideLoadingDiv() {
	if (loading_chk) {
//		$("input:text").attr("readonly", false);
//		$("input:button").attr("disabled", false);
//		$("input:checkbox").attr("disabled", false);
//		$("input:submit").attr("disabled", false);
//		$("a").bind("click", true);
//		$("p").bind("click", true);
//		$("textarea").attr("readonly", false);

		$("#loadingDiv").remove();

		loading_chk = false;
	}
}


/*
 * input 핸드폰전화번호 데이터 검증
 * obj : 전화번호가 기입된 input 객체
 */
function validate_phone(obj) {
	var formatText = /^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/;
	
	if(obj.value != null && obj.value != "") {
		if (!formatText.test(obj.value)) {
			obj.value = "";
			c_alert("잘못된 휴대전화 번호 형식입니다.<br>숫자와 '-'를 이용해 기입해 주십시오");
			return false;
			
		} else {
			return true;
		}
	}
}


/*
 * input 숫자 데이터 검증
 * obj : 숫자가 기입된 input 객체
 * 숫자를 제외한 나머지를 모두 지우고 return 시킴
 */
function validate_number(obj) {
	var formatText = /^[0-9]+$/;
	var org_val = obj.value;
	if(obj.value.length != 0) {
		if (!formatText.test(org_val)) {
			obj.value = (org_val).replace(/[^0-9]/gi, "");
			obj.focus();
			c_alert("숫자만 입력 가능합니다");
			return false;
			
		} else {
			return true;
		}
	}
}

/*
 * 날짜 유효성 검증
 */
function validate_date(param) {
    try {
        param = param.replace(/[.]/g,'');

        // 자리수가 맞지않을때
        if( isNaN(param) || param.length!=8 ) {
            return false;
        }
        var year = Number(param.substring(0, 4));
        var month = Number(param.substring(4, 6));
        var day = Number(param.substring(6, 8));

        var dd = day / 0;
         
        if( month<1 || month>12 ) {
            return false;
        }
         
        var maxDaysInMonth = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
        var maxDay = maxDaysInMonth[month-1];
         
        // 윤년 체크
        if( month==2 && ( year%4==0 && year%100!=0 || year%400==0 ) ) {
            maxDay = 29;
        }
         
        if( day<=0 || day>maxDay ) {
            return false;
        }
        return true;

    } catch (err) {
        return false;
    }                       
}
