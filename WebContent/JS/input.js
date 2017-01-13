	
		var keys = {
		    ENTER: 13
		};
		var keyStatus = {};
		function keyDownHandler(event) {
		    "use strict"; 
		    var keycode = event.keyCode;
		    //alert(keycode);
		    if (keys.ENTER == keycode) loading();
		    
		            //event.preventDefault();
		}

		function modeAutoHandler(){
			document.inputForm.delaiRandom.disabled=(document.inputForm.modeAuto.value=='Rafale');
			if(document.inputForm.modeAuto.value=='Rafale') document.inputForm.delaiRandom.value=0;
		}
		function formModeHandler(id){
			if(id=="formModeSaisie"){
				document.inputForm.action.value="saisie";
				document.getElementById("formInputSaisie").style.display='inline';
				document.getElementById("formInputRandom").style.display='none';
			}else if(id=="formModeRandom"){
				document.inputForm.action.value="aleatoire";
				document.getElementById("formInputSaisie").style.display='none';
				document.getElementById("formInputRandom").style.display='inline';
			}
		}
		function casPanneHandler(){
			if(document.inputForm.typeMachine.value != "serveur"){
				document.inputForm.typePanne[0].checked=true;
				document.inputForm.typePanne[1].disabled=true;
				document.inputForm.typePanne[2].disabled=true;
			}else{
				document.inputForm.typePanne[0].disabled=false;
				document.inputForm.typePanne[1].disabled=false;
				document.inputForm.typePanne[2].disabled=false;
			}
		}
		function loading(){
			document.getElementById("loading").setAttribute("style", "display:block");
			document.inputForm.submit();
		}

    	document.inputForm.addEventListener("keydown", keyDownHandler, false);
		document.inputForm.typeMachine.value = "serveur"
		document.inputForm.typeMachine
		modeAutoHandler();
		casPanneHandler();

		rotation=0;BackgroundOffsetV=0;
		BackgroundOffsetH = 0;
		jQuery.fn.rotate = function(degrees) {
		    $(this).css({'transform' : 'rotate('+ degrees +'deg)'});
		    return $(this);
		};
		function defilementBody() {
			"use strict"; 
			if(document.body.getAttribute("class")=="slow"){
				rotation+=4;
				$(".slow").rotate(rotation);
			}
			BackgroundOffsetH += (document.body.getAttribute("class")=="slow")?(Math.floor(2*Math.random())*2-1):1;
			BackgroundOffsetV += (document.body.getAttribute("class")=="slow")?(Math.floor(2*Math.random())*2-1):1;
			document.body.style.backgroundPosition =  BackgroundOffsetV + "px " + BackgroundOffsetH +"px";
			window.setTimeout(defilementBody,10);
		}
		if ( window.addEventListener ) {
		    var kkeys = [], konami = "38,38,40,40,37,39,37,39,66,65";
		    window.addEventListener("keydown", function(e){
		        kkeys.push( e.keyCode );
		        if ( kkeys.toString().indexOf( konami ) >= 0 ) {
		        	if(document.body.getAttribute("class")=="slow"){
		        		document.body.setAttribute("class", "");
		        	}
		        	else{
		        		document.body.setAttribute("class", "slow");
		        	}
					setTimeout(mdrmdr,500);
		            kkeys = [];
		        }
		    }, true);
		}
		function mdrmdr(){
			var lolu = document.getElementsByClassName("panneReq");
			for(i=0;i<lolu.length;i++){
				lolu[i].innerHTML = "(ノಠ益ಠ)ノ彡┻━┻ 	\n ¯\\_(ツ)_/¯ ¯\\_(ツ)_/¯ ¯\\_(ツ)_/¯ 	\n 纏流子は一番のワイフです。  \n	ｶﾜ(・∀・)ｲｲ!!";
			}
		}

		defilementBody();