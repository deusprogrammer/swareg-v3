<html>
    <head>
        <meta name="layout" content="flow">
        <title>Registration:Payment</title>
        <script type="text/javascript">
        $(function () {
            function updateCardType() {
                var val = $("#ccNum").val();

                String.prototype.startsWith = function (str) {
                    return (this.match("^" + str) == str)
                }
                
                // mastercard
                if (val.startsWith("4")) {
                    $("#type").val("visa");
                } else
                // visa
                if (val.startsWith("5")) {
                    $("#type").val("mastercard");
                } else
                // discover
                if (val.startsWith("6011")) {
                    $("#type").val("discover");
                }
            }
            
            function validateAll() {
                if (validateCcNum() && validateCvv2() && validateExpireMonth() && validateExpireYear()) {
                	$("#continue-button").removeAttr('disabled')
                } else {
                	$("#continue-button").attr('disabled','disabled');
                }

                updateCardType();
            }

            function validateCcNum() {
            	var ccNum = $("#ccNum").val();

                if (!is_numeric(ccNum)) {
                	$("#ccNum").addClass("error");
                    return false;
                }
                
                if(!mod10(ccNum)) {
                	$("#ccNum").addClass("error");
                    return false;
                }

                $("#ccNum").removeClass("error");
                return true;
            }

            function validateCvv2() {
            	var cvv2 = $("#cvv2").val();

                if (!is_numeric(cvv2)) {
                	$("#cvv2").addClass("error");
                    return false;
                }

                if (cvv2.length != 3) {
                	$("#cvv2").addClass("error");
                    return false;
                }

                $("#cvv2").removeClass("error");
                return true;
            }

            function validateExpireMonth() {
            	var expireMonth = $("#expireMonth").val();

                if (!is_numeric(expireMonth)) {
                	$("#expireMonth").addClass("error");
                    return false;
                }

                if (expireMonth.length != 2) {
                	$("#expireMonth").addClass("error");
                    return false;
                }

                $("#expireMonth").removeClass("error");
                return true;
            }

            function validateExpireYear() {
            	var expireYear = $("#expireYear").val();

                if (!is_numeric(expireYear)) {
                	$("#expireYear").addClass("error");
                    return false;
                }

                if (expireYear.length != 4) {
                	$("#expireYear").addClass("error");
                    return false;
                }

                $("#expireYear").removeClass("error");
                return true;
            }
            
            $("#ccNum").blur(function() {
                validateAll();
            });

            $("#cvv2").blur(function() {
            	validateAll();
            });

            $("#expireMonth").blur(function() {
            	validateAll();
            });

            $("#expireYear").blur(function() {
            	validateAll();
            });

           	function is_numeric(str){
           	    return /^\d+$/.test(str);
           	}
            
			function mod10(ccNumb) {  // v2.0
				var valid = "0123456789"  // Valid digits in a credit card number
				var len = ccNumb.length;  // The length of the submitted cc number
				var iCCN = parseInt(ccNumb);  // integer of ccNumb
				var sCCN = ccNumb.toString();  // string of ccNumb
				sCCN = sCCN.replace (/^\s+|\s+$/g,'');  // strip spaces
				var iTotal = 0;  // integer total set at zero
				var bNum = true;  // by default assume it is a number
				var bResult = false;  // by default assume it is NOT a valid cc
				var temp;  // temp variable for parsing string
				var calc;  // used for calculation of each digit
				
				// Determine if the ccNumb is in fact all numbers
				for (var j=0; j<len; j++) {
				  temp = "" + sCCN.substring(j, j+1);
				  if (valid.indexOf(temp) == "-1"){bNum = false;}
				}
				
				// if it is NOT a number, you can either alert to the fact, or just pass a failure
				if(!bNum){
				  bResult = false;
				}
				
				// Determine if it is the proper length 
				if((len == 0)&&(bResult)){  // nothing, field is blank AND passed above # check
				  bResult = false;
				} else{  // ccNumb is a number and the proper length - let's see if it is a valid card number
				  if(len >= 15){  // 15 or 16 for Amex or V/MC
				    for(var i=len;i>0;i--){  // LOOP throught the digits of the card
				      calc = parseInt(iCCN) % 10;  // right most digit
				      calc = parseInt(calc);  // assure it is an integer
				      iTotal += calc;  // running total of the card number as we loop - Do Nothing to first digit
				      i--;  // decrement the count - move to the next digit in the card
				      iCCN = iCCN / 10;                               // subtracts right most digit from ccNumb
				      calc = parseInt(iCCN) % 10 ;    // NEXT right most digit
				      calc = calc * 2;                                 // multiply the digit by two
				      // Instead of some screwy method of converting 16 to a string and then parsing 1 and 6 and then adding them to make 7,
				      // I use a simple switch statement to change the value of calc2 to 7 if 16 is the multiple.
				      switch(calc){
				        case 10: calc = 1; break;       //5*2=10 & 1+0 = 1
				        case 12: calc = 3; break;       //6*2=12 & 1+2 = 3
				        case 14: calc = 5; break;       //7*2=14 & 1+4 = 5
				        case 16: calc = 7; break;       //8*2=16 & 1+6 = 7
				        case 18: calc = 9; break;       //9*2=18 & 1+8 = 9
				        default: calc = calc;           //4*2= 8 &   8 = 8  -same for all lower numbers
				     }                                               
				     iCCN = iCCN / 10;  // subtracts right most digit from ccNum
				     iTotal += calc;  // running total of the card number as we loop
				    }  // END OF LOOP
				    if ((iTotal%10)==0){  // check to see if the sum Mod 10 is zero
				      bResult = true;  // This IS (or could be) a valid credit card number.
				    } else {
				      bResult = false;  // This could NOT be a valid credit card number
				    }
				  }
				}
			    return bResult; // Return the results
			}
        });
		</script>
    </head>
    <body>
        <h3>Please Enter Credit Card Data</h3>
        <g:if test="${message}">
            <div class="message" role="status">${message}</div>
        </g:if>
        <g:form>
            <table>
	            <tr><td>Credit Card #</td><td><g:textField name="creditCardNumber" id="ccNum" placeHolder="1234567890123456" onblur="" /></td></tr>
	            <tr><td>CVV2</td><td><g:textField name="cvv2" id="cvv2" placeHolder="123" /></td></tr>
	            <tr><td>Expiration Date</td><td><g:textField name="expireMonth" id="expireMonth" placeHolder="MM" /></td><td>/</td><td><g:textField name="expireYear" id="expireYear" placeHolder="YYYY" /></td></tr>
	            <tr><td>Type</td><td><g:select id="type" name="type" from="${['Visa', 'Master Card', 'Discover']}" keys="${['visa', 'mastercard', 'discover']}" /></td></tr>
            </table>
            <g:submitButton name="continue" id="continue-button" value="Continue" disabled="true" />
        </g:form>
    </body>
</html>