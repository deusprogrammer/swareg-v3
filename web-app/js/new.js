(function($) {
    $.fn.setError = function(critical, message) {
        console.log("ERROR MESSAGE: " + message);
        $(this).addClass("error");
        $(this).tooltip({content : message});
        $(this).tooltip({ show: { effect: "bounce", duration: 800 }});
        $(this).tooltip({ position: {at: "right+105"}});
        $(this).attr("title", message);
        $(this).tooltip("open");
    }
    
    $.fn.clearError = function() {
        $(this).removeClass("error");
        $(this).tooltip({content : ""});
        $(this).removeAttr("title");
        $(this).tooltip("close");
    }
})(jQuery);

function validateForm() {
	console.log("MODEL: " + $(".validate-me").attr("model"));
	
	$.ajaxSetup({
		async: false
	})
	
	var fields = {};
	var jsonObject = {};
	var result = false;

	$(".validate-me input")
	.not(".validate-me input[type=submit]")
    .not(".validate-me input[type=button]")
    .not(".ignore")
    .each(function () {
		if ( $(this).attr("name") && $(this).val()) {
			console.log($(this).attr("name") + " => " + $(this).val());
			fields[$(this).attr("name")] = $(this).val();
		}
	});
	
	jsonObject.fields = fields;
	jsonObject.domain = $(".validate-me").attr("model");
	
	console.log("CALLING VALIDATE(n)!");
	$.ajax({
		   url: "/swareg/validation/validate",
		   type: "PUT",
		   data: JSON.stringify(jsonObject),
		   dataType: "json",
	       contentType: "application/json; charset=utf-8",
		   success: function(data) {
			   result = data.success
		  		for (field in data.fields) {
		  			var selector = "input[name=" + field + "]";
		  			if (!data.fields[field].success) {
		  				$(selector).setError(true, data.fields[field].message);
		  			} else {
		  				$(selector).clearError();
		  			}
		  		}
				if (data.success) {
					console.log("VALIDATE SUCCESSFUL!");
				}
		  	}
	});
	
	console.log("SUCCESS? " + result);
	
	return result;
}

$(function() {
    $("#date").datepicker();
    $("#startDate").datepicker();
    $("#endDate").datepicker();
    
    $(".validate-me").attr("onsubmit", "return validateForm();")
    
	$(".validate-me input")
	.not(".validate-me input[type=submit]")
	.not(".validate-me input[type=button]")
	.not(".ignore")
	.blur(function () {
		console.log("MODEL: " + $(".validate-me").attr("model"));
		console.log($(this).attr("name") + " => " + $(this).val());
		
		var jsonObject = {};

		jsonObject.fields = {};
		jsonObject.fields[$(this).attr("name")] = $(this).val();
		jsonObject.domain = $(".validate-me").attr("model");
		
		console.log("CALLED VALIDATE(1)!");
		$.ajax({
			   url: "/swareg/validation/validate",
			   type: "PUT",
			   data: JSON.stringify(jsonObject),
			   dataType: "json",
		       contentType: "application/json; charset=utf-8",
			   success: function(data) {
				   result = data.success
			  		for (field in data.fields) {
			  			var selector = "input[name=" + field + "]";
			  			if (!data.fields[field].success) {
			  				$(selector).setError(true, data.fields[field].message);
			  			} else {
			  				$(selector).clearError();
			  			}
			  		}
					if (data.success) {
						console.log("VALIDATE SUCCESSFUL!");
					}
			  	}
		});
	});
    
	/*
    $("input[type=text]").blur(function() {
        console.log("CLEARING");
        
        // Clear everything, and then perform checks.
    });
    
    $(".taxRate").blur(function() {
        console.log("CHECKING TAXRATE");
        var value = parseFloat($(this).val());
        if (!value) {
            $(this).setError(true, "Value must be a number!");
        } else if (value > 1.0) {
            $(this).setError(true, "Value must be less than 1!");
        } else {
            $(this).clearError();
        }
    });
    
    $(".numeric").blur(function() {
        console.log("CHECKING NUMERIC");
        var filter = /^[0-9]+$/;
        if (!filter.test($(this).val())) {
            $(this).setError(true, "Value must be a number!");
        } else {
            $(this).clearError();
        }
    });

    $(".password-confirm").blur(function() {
        console.log("CHECKING PASSWORD");
        if ($(this).val() != $(".password").val()) {
            $(this).setError(true, "Passwords must match!");
        } else {
            $(this).clearError();
        }
    });

    $(".email").blur(function() {
        console.log("CHECKING EMAIL");
        var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        if (!filter.test($(this).val())) {
            $(this).setError(true, "Value must be a valid email address!");
        } else {
            $(this).clearError();
        }
    });

    $(".url").blur(function() {
        console.log("CHECKING URL");
        var filter = /[-a-zA-Z0-9@:%_\+.~#?&//=]{2,256}\.[a-z]{2,4}\b(\/[-a-zA-Z0-9@:%_\+.~#?&//=]*)?/;
        if (!filter.test($(this).val())) {
            $(this).setError(true, "Value must be a url!");
        } else {
            $(this).clearError();
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
            $(this).setError(true, "Value must be a price!");
        } else {
            $(this).clearError();
        }
    });

    $(".validFor").blur(function() {
        console.log("CHECKING VALIDFOR");
        var filter = /^[0-9]+ (year|month|day|hour|minute)+s*$/;
        if (!filter.test($(this).val())) {
            $(this).setError(true, "Value must be a period of time!");
        } else {
            $(this).clearError();
        }
    });
    */
    
    $('.disableoncheck').on('click', function () {
        $(this).closest('fieldset').find('input[type=text]').prop('disabled', this.checked);
    });

    $(function() {
        $(document).tooltip();
    });
});