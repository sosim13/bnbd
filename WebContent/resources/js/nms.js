//공통 모달 팝업
function modal_popup(el){

    var temp = $('#' + el);
    var bg = temp.prev().hasClass('bg');    //dimmed 레이어를 감지하기 위한 boolean 변수

    if(bg){
            $('.modal-popup').fadeIn();   //'bg' 클래스가 존재하면 레이어가 나타나고 배경은 dimmed 된다. 
    }else{
            temp.fadeIn();
    }

    // 화면의 중앙에 레이어를 띄운다.
    if (temp.outerHeight() < $(document).height() ) temp.css('margin-top', '-'+temp.outerHeight()/2+'px');
    else temp.css('top', '0px');
    if (temp.outerWidth() < $(document).width() ) temp.css('margin-left', '-'+temp.outerWidth()/2+'px');
    else temp.css('left', '0px');

    temp.find('a.cbtn').click(function(e){
        if(bg){
            $('.modal-popup').fadeOut(); //'bg' 클래스가 존재하면 레이어를 사라지게 한다. 
        }else{
            temp.fadeOut();
        }
        e.preventDefault();
    });

    $('.modal-popup .bg').click(function(e){  //배경을 클릭하면 레이어를 사라지게 하는 이벤트 핸들러
        $('.modal-popup').fadeOut();
        e.preventDefault();
    });

};

//알림 리스트 아코디언
$(function(){
    $('#accordian ul li h2').click(function(){
        $('#accordian ul li div').slideUp();
        if(!$(this).next().is(':visible'))
        {
            $(this).next().slideDown();
        }
    });
});   

//비밀번호변경
$(function() {
    $('#btnPwchange').click(function() {
        $('#pwChange').show('fast');
        $(this).hide('fast');
    });
});
