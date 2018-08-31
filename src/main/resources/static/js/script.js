$(function () {

    var slideUpSubcategory = function () {
        $(".subcategoryItem").hide();
    };

    slideUpSubcategory();

    function showSubcategory(subId) {
        $("#subcategory_" + subId).show();
    }

    $(".categoryNav").on("mouseenter", ".categoryItem", function () {
        slideUpSubcategory();
        console.log($(this));
        var idNumber = $(this).attr("id").split('_');
        console.log(idNumber[1]);
        showSubcategory(idNumber[1]);
    })
});