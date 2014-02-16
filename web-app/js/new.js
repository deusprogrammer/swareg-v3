function validate(selector, filter, message) {
    if (!filter.test($(selector).val())) {
        $(selector).addClass("error");
        $(selector).tooltip({ show: { effect: "bounce", duration: 800 }});
        $(selector).tooltip({ position: {at: "right+105"}});
        $(selector).attr("title", message);
        $(selector).tooltip("open");
    } else {
        $(selector).removeClass("error");
        $(selector).tooltip({content : ""});
        $(selector).tooltip("close");
    }
}

$(function() {
    $("#date").datepicker();
    $("#startDate").datepicker();
    $("#endDate").datepicker();
    
    $("input[type=text]").blur(function() {
    	console.log("CLEARING");
    	
    	// Clear everything, and then perform checks.
    });
    
    $(".taxRate").blur(function() {
    	console.log("CHECKING TAXRATE");
    	var value = parseFloat($(this).val());
    	if (!value) {
    		$(this).addClass("error");
            $(this).tooltip({ show: { effect: "bounce", duration: 800 }});
            $(this).tooltip({ position: {at: "right+105"}});
            $(this).attr("title", "Must enter a decimal value.");
            $(this).tooltip("open");
    	} else if (value > 1.0) {
    		$(this).addClass("error");
            $(this).tooltip({ show: { effect: "bounce", duration: 800 }});
            $(this).tooltip({ position: {at: "right+105"}});
            $(this).attr("title", "Value must be less than 1");
            $(this).tooltip("open");
    	} else {
            $(this).removeClass("error");
            $(this).tooltip({content : ""});
            $(this).tooltip("close");
        }
    });
    
    $(".numeric").blur(function() {
    	console.log("CHECKING NUMERIC");
    	var filter = /^[0-9]+$/;
        if (!filter.test($(this).val())) {
            $(this).addClass("error");
            $(this).tooltip({ show: { effect: "bounce", duration: 800 }});
            $(this).tooltip({ position: {at: "right+105"}});
            $(this).attr("title", "Must be a number!");
            $(this).tooltip("open");
        } else {
            $(this).removeClass("error");
            $(this).tooltip({content : ""});
            $(this).tooltip("close");
        }
    });

    $(".password-confirm").blur(function() {
    	console.log("CHECKING PASSWORD");
        if ($(this).val() != $(".password").val()) {
            $(this).addClass("error");
            $(this).tooltip({ show: { effect: "bounce", duration: 800 }});
            $(this).tooltip({ position: {at: "right+105"}});
            $(this).attr("title", "Passwords must match");
            $(this).tooltip("open");
        } else {
            $(this).removeClass("error");
            $(this).tooltip({content : ""});
            $(this).tooltip("close");
        }
    });

    $(".email").blur(function() {
    	console.log("CHECKING EMAIL");
    	var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        if (!filter.test($(this).val())) {
            $(this).addClass("error");
            $(this).tooltip({ show: { effect: "bounce", duration: 800 }});
            $(this).tooltip({ position: {at: "right+105"}});
            $(this).attr("title", "Invalid email address");
            $(this).tooltip("open");
        } else {
            $(this).removeClass("error");
            $(this).tooltip({content : ""});
            $(this).tooltip("close");
        }
    });

    $(".url").blur(function() {
    	console.log("CHECKING URL");
        var filter = /[-a-zA-Z0-9@:%_\+.~#?&//=]{2,256}\.[a-z]{2,4}\b(\/[-a-zA-Z0-9@:%_\+.~#?&//=]*)?/;
        if (!filter.test($(this).val())) {
            $(this).addClass("error");
            $(this).tooltip({ show: { effect: "bounce", duration: 800 }});
            $(this).tooltip({ position: {at: "right+105"}});
            $(this).attr("title", "Malformed url");
            $(this).tooltip("open");
        } else {
            $(this).removeClass("error");
            $(this).tooltip({content : ""});
            $(this).tooltip("close");
        }
    });

    $(".price").blur(function() {
    	console.log("CHECKING PRICE");
    	// If decimal left off, add it.
        var prefilter = /^[0-9]+$/;
        if (prefilter.test($(this).val())) {
        	$(this).val($(this).val() + ".00");
        }
    	
        var filter = /^[0-9]+.[0-9]+$/;
        if (!filter.test($(this).val())) {
            $(this).addClass("error");
            $(this).tooltip({ show: { effect: "bounce", duration: 800 }});
            $(this).tooltip({ position: {at: "right+105"}});
            $(this).attr("title", "Malformed price");
            $(this).tooltip("open");
        } else {
            $(this).removeClass("error");
            $(this).tooltip({content : ""});
            $(this).tooltip("close");
        }
    });

    $(".validFor").blur(function() {
    	console.log("CHECKING VALIDFOR");
        var filter = /^[0-9]+ (year|month|day|hour|minute)+s*$/;
        if (!filter.test($(this).val())) {
            $(this).addClass("error");
            $(this).tooltip({ show: { effect: "bounce", duration: 800 }});
            $(this).tooltip({ position: {at: "right+105"}});
            $(this).attr("title", "Invalid time period");
            $(this).tooltip("open");
        } else {
            $(this).removeClass("error");
            $(this).tooltip({content : ""});
            $(this).tooltip("close");
        }
    });

    $("#sameAsShipping").click(function() {
        if ($(this).is(':checked')) {
        	$(".disablable").each(function() {
            	$(this).prop('disabled', true);
        	});
        } else {
            $(".disablable").each(function() {
                $(this).prop('disabled', false);
            });
        }
    });

    $(function() {
        $(document).tooltip();
    });
});