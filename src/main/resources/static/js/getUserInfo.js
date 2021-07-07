 function getUserInfo(){
    $.ajax({
        type:"GET",
        url:"user/getUserInfo",
        data:{
        },
    }).done(function (data){
        if(data.state == 1){
            $("#username").text(data.user.username);
        }
    })
}
