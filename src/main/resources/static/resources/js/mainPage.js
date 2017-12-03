$(function () {
    $('.visca-position-up').on('click', function () {
        var data = {
            'direction': 'UP'
        };
       postAPI('/position', data);
    });

    $('.visca-position-down').on('click', function () {
        var data = {
            'direction': 'DOWN'
        };
        postAPI('/position', data);
    });

});

function postAPI(endpoint, data) {
    $.ajax({
        type: 'POST',
        url: '/api' + endpoint,
        data: data,
        success: function(msg){
            console.log('Response: ' + msg);
        }
    });
}

