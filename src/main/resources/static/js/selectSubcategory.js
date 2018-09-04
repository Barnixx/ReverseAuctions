$(function () {
    $("#category_select").change(function (event) {
        var catId = $(this).val();

        console.log("event");
        console.log(catId);


        $.getJSON("http://localhost:8080/categoryRest/" + catId, function (data) {
            $("#subcategory_select").html("");
            $.each(data, function () {
                console.log($(this));
                $("#subcategory_select").append('<option value="' + this.id + '" >' + this.name + '</option>');
                $("#subcategory_select").addClass('selectpicker');
                $("#subcategory_select").attr('data-live-search', 'true');
                $("#subcategory_select").selectpicker('refresh');

            })
        })
        // $.ajax({
        //     url: 'http://localhost:8080/categoryRest/',
        //     type: 'GET',
        //     dataType: 'json',
        //     success: function (json) {
        //         $.each(json, function () {
        //             $("#subcategory_select").append($('<option>').text($(this).name).attr('value', this.id));
        //         });
        //     }
        // });


    })
});