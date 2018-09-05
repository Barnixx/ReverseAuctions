$(function () {
    $("#category_select").on('change', function (event) {
        var catId = $(this).val();

        console.log("event");
        console.log(catId);
        $("#subcategory_select option").remove();

        $.getJSON("http://localhost:8080/api/category/subcategory/" + catId, function (data) {
            $("#subcategory_select").append('<option value="' + catId + '">Kategoria Główna</option>');
            $.each(data, function () {
                console.log($(this));
                $("#subcategory_select").append('<option value="' + this.id + '" >' + this.name + '</option>');
                $("#subcategory_select").addClass('selectpicker');
                $("#subcategory_select").attr('data-live-search', 'true');
                $("#subcategory_select").selectpicker('refresh');

            })
        });

        $("#subcategory_select").selectpicker('refresh');
    })
});