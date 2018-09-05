$(function () {
    $('.table .eBtn').on('click', function (event) {

        event.preventDefault();
        var href = $(this).attr('href');

        $.get(href, function (category, status) {
            $('.edit-form #cat_id').val(category.id);
            $('.edit-form #cat_name').val(category.name);
            $('.edit-form #cat_desc').val(category.name);
        });

        $('.edit-form #editModal').modal();
    });

    $('.table .dBtn').on('click', function (event) {

        event.preventDefault();
        var href = $(this).attr('href');
        $('#delete_cat').attr('href', href);

        $('.delete-modal #deleteModal').modal();
    })
});