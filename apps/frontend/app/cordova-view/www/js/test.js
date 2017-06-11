/**
 * Created by ksb on 2017. 6. 10..
 */
document.addEventListener("deviceready", onDeviceReady, false);

function onDeviceReady() {
    alert(FCMPlugin)
    FCMPlugin.getToken(
        function(token){
            console.log("token :"+ token)
            alert("token : "+ token);
        },
        function(err){
            console.log('error retrieving token: ' + err);
        }
    )

    FCMPlugin.onNotification(
        function(data){
            if(data.wasTapped){
                alert( JSON.stringify(data) );
            }else{
                alert( JSON.stringify(data) );
            }
        },
        function(msg){
            alert('onNotification callback successfully registered: ' + msg)
            console.log('onNotification callback successfully registered: ' + msg);
        },
        function(err){
            console.log('Error registering onNotification callback: ' + err);
        }
    );
}
