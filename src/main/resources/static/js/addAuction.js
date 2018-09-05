$(function () {

    $("#category_select").on('change', function (event) {
        var catId = $(this).val();

        console.log("event");
        console.log(catId);
        $("#subcategory_select option").remove();
        $("#subsubcategory_select option").remove();

        $.getJSON("http://localhost:8080/api/category/subcategory/" + catId, function (data) {
            // $("#subcategory_select").append('<option value="'+catId+'">Kategoria Główna</option>');
            $("#subcategory_select").append('<option>Wybierz Podkategorie</option>');

            $.each(data, function () {
                console.log($(this));
                $("#subcategory_select").append('<option value="' + this.id + '" >' + this.name + '</option>');
                $("#subcategory_select").addClass('selectpicker');
                $("#subcategory_select").attr('data-live-search', 'true');
                $("#subcategory_select").selectpicker('refresh');

            })
        });

        $("#subcategory_select").selectpicker('refresh');
    });

    $("#subcategory_select").on('change', function (event) {
        var catId = $(this).val();

        console.log("event");
        console.log(catId);
        $("#subsubcategory_select option").remove();

        $.getJSON("http://localhost:8080/api/category/subcategory/" + catId, function (data) {
            $("#subsubcategory_select").append('<option disabled>Wybierz Podkategorie</option>');
            $.each(data, function () {
                console.log($(this));
                $("#subsubcategory_select").append('<option value="' + this.id + '" >' + this.name + '</option>');
                $("#subsubcategory_select").addClass('selectpicker');
                $("#subsubcategory_select").attr('data-live-search', 'true');
                $("#subsubcategory_select").selectpicker('refresh');

            })
        });

        $("#subcategory_select").selectpicker('refresh');
    });


    //
    // var slideUpSubcategory = function () {
    //     $(".subcategoryList").hide();
    // };
    //
    // slideUpSubcategory();

    // function showSubcategory(subId) {
    //     console.log("#subcategory_"+subId);
    //     $("#subcategory_" + subId).show();
    //     $("#subcategory_2").show();
    // }
    //
    // $("#category").on("change", function () {
    //     slideUpSubcategory();
    //     console.log($(this));
    //     var idNumber = $(this).val();
    //     console.log(idNumber);
    //     showSubcategory(idNumber);
    // })

    // $("select[name='category']").change(function () {
    //
    //     $("select[name='subcategory'] :selected").removeAttr("selected");
    //
    //     // using text value to match second select option groups could just as easily be based on value then label text
    //     var arr = $("select[name='category'] :selected").text();
    //
    //
    //     $("select[name='subcategory']").children("optgroup").hide();
    //     $("select[name='subcategory']").children("optgroup[label='" + arr + "']").show();
    //     $("select[name='subcategory']").children("optgroup[label='" + arr + "']").children().first().prop('selected', true);
    //
    // });
});