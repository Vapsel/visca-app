$(function () {
    $('.visca-position-up').on('click', function () {
        $.ajax({
            type: 'POST',
            url: '/api/position',
            data: {
                'direction': 'UP'
            },
            success: function(msg){
                alert('wow ' + msg);
            }
        });
    });
});

