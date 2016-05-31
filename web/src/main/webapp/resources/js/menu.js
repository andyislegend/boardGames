$(document).ready(function(){
    var menu = 1;
    
    $('#menu_btn').click(function(){
        $('#menu_btn').toggleClass('open');
        if(menu == 0 ){
            $('#main_menu').animate({ left: '0'}, 1000);
            menu=1;
        }
        else{
            $('#main_menu').animate({ left: '-105%'}, 1000);
            menu=0;
        }

    });
    
    $('#main_menu ul li').click(function() {
        $('li').removeClass('isSelected');
        $(this).addClass('isSelected');
    });
   
});