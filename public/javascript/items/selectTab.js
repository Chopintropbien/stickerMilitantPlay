//selectTab.js


$( document ).ready(function() {

    var tabSelectClass = "tabSelect";
    var tabId = "tab-"
    var activeClass = "active";
    var inClass = "in";
    var tabPaneClass = "tab-pane";

    $("." + tabSelectClass).click(function(e) {
        e.preventDefault();

        $("." + tabSelectClass).removeClass(activeClass);
        $(this).addClass(activeClass);

        var id = $(this).attr("id").split("-")[1];

        $("." + tabPaneClass).removeClass(activeClass).removeClass(inClass);
        $("#" + tabId + id).addClass(activeClass).addClass(inClass);

        var url = window.location.href
        var urlArray = url.split("/");
        var oldCategory = urlArray[urlArray.length - 1].split("?")[0];
        var newCategory = $(this).attr("data-name");
        window.history.pushState(null, null, url.replace(oldCategory, newCategory));
    });
});