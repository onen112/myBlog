 function getUserInfo(){
    $.ajax({
        type:"GET",
        url:"user/getUserInfo",
        data:{
        },
    }).done(function (data){
        console.log(data);
        if(data.state == 1){
            $("#username").text(data.user.username);
            $("#rcount").text(data.user.rcount);
            $("#allart").text(data.user.allart);
        }
    })
}
