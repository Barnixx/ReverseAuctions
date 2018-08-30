$(function () {

    var slideUpSubcategory = function () {
        $(".subcategoryList").hide();
    };

    slideUpSubcategory();

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

    $("select[name='category']").change(function () {

        $("select[name='subcategory'] :selected").removeAttr("selected");

        // using text value to match second select option groups could just as easily be based on value then label text
        var arr = $("select[name='category'] :selected").text();


        $("select[name='subcategory']").children("optgroup").hide();
        $("select[name='subcategory']").children("optgroup[label='" + arr + "']").show();
        $("select[name='subcategory']").children("optgroup[label='" + arr + "']").children().first().prop('selected', true);

    });
});