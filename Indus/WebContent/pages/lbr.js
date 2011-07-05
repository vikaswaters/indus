// Timestamp the page was last updated with
//var ajaxURL='http://localhost:8080/SprHibStrt';
var ajaxURL='http://eyaksh.com/SprHibStrt';
var lastUpdate = 0;
var zoomfactor=0.05 //Enter factor (0.05=5%)

function zoomhelper(){
if (parseInt(whatcache.style.width)>10&&parseInt(whatcache.style.height)>10){
whatcache.style.width=parseInt(whatcache.style.width)+parseInt(whatcache.style.width)*zoomfactor*prefix
whatcache.style.height=parseInt(whatcache.style.height)+parseInt(whatcache.style.height)*zoomfactor*prefix
}
}

function zoom(originalW, originalH, what, state){
if (!document.all&&!document.getElementById)
return
whatcache=eval("document.images."+what)
prefix=(state=="in")? 1 : -1
if (whatcache.style.width==""||state=="restore"){
whatcache.style.width=originalW+"px"
whatcache.style.height=originalH+"px"
if (state=="restore")
return
}
else{
zoomhelper()
}
beginzoom=setInterval("zoomhelper()",100)
}

function clearzoom(){
if (window.beginzoom)
clearInterval(beginzoom)
}
function mousePosition(e){
	var posx = 0;
	var posy = 0;
	if (!e) var e = window.event;
	if (e.pageX || e.pageY) 	{
		posx = e.pageX;
		posy = e.pageY;
	}
	else if (e.clientX || e.clientY) 	{
		posx = e.clientX + document.body.scrollLeft
			+ document.documentElement.scrollLeft;
		posy = e.clientY + document.body.scrollTop
			+ document.documentElement.scrollTop;
	}
	return [posx,posy];
}
function setZoom(img, dir, width, height, margin, zIndex, delay) {
	  setTimeout(function() {
	    if (img.dir==dir) {
	      img.style.width=width;
	      img.style.height=height;
	      img.style.margin=margin;
	      img.style.zIndex=zIndex;
	      img.parentNode.parentNode.style.zIndex=zIndex;
	    }
	  }, delay);
	}

	function larger(img, width, height) {
	  img.dir='rtl';
	  now=parseInt(img.style.zIndex);
	  for (i=now+1; i<=10; i++) {
	    w=(width*(10+i))/20+'px';
	    h=(height*(10+i))/20+'px';
	    m=(-i)+'px 0 0 '+(-width*i/40)+'px';
	    setZoom(img, 'rtl', w, h, m, i, 20*(i-now));
	  }
	}

	function smaller(img, width, height) {
	  img.dir='ltr';
	  now=parseInt(img.style.zIndex);
	  for (i=now-1; i>=0; i--) {
	    w=(width*(10+i))/20+'px';
	    h=(height*(10+i))/20+'px';
	    m=(-i)+'px 0 0 '+(-width*i/40)+'px';
	    setZoom(img, 'ltr', w, h, m, i, 20*(now-i));
	  }
	}

	function PreparePasswordField(value){
		//alert('PreparePasswordField  called..');
		if(value==1){ //new user
			document.getElementsByTagName("input")[5].style.visibility = 'hidden';
			document.getElementById('emailidrow').style.visibility = 'hidden';
			document.getElementById('signinbutton').style.visibility = 'hidden';
			document.getElementById('signupbutton').style.visibility = 'visible';
			document.getElementById('passwordrow').style.visibility = 'hidden';
			document.getElementById('forgetpasswordrow').style.visibility = 'hidden';
			
		}else{  // existing user
			document.getElementsByTagName("input")[5].style.visibility = 'visible';
			document.getElementById('emailidrow').style.visibility = 'visible';
			document.getElementById('signinbutton').style.visibility = 'visible';
			document.getElementById('signupbutton').style.visibility = 'hidden';	
			document.getElementById('passwordrow').style.visibility = 'visible';
			document.getElementById('forgetpasswordrow').style.visibility = 'visible';
		}
	}
	function checkEmail(emailStr) {
		if (emailStr.length == 0) {
			return true;
		}
		var emailPat=/^(.+)@(.+)$/;
		var specialChars="\\(\\)<>@,;:\\\\\\\"\\.\\[\\]";
		var validChars="\[^\\s" + specialChars + "\]";
		var quotedUser="(\"[^\"]*\")";
		var ipDomainPat=/^(\d{1,3})[.](\d{1,3})[.](\d{1,3})[.](\d{1,3})$/;
		var atom=validChars + '+';
		var word="(" + atom + "|" + quotedUser + ")";
		var userPat=new RegExp("^" + word + "(\\." + word + ")*$");
		var domainPat=new RegExp("^" + atom + "(\\." + atom + ")*$");
		var matchArray=emailStr.match(emailPat);
		if (matchArray == null) {
			return false;
		}
		var user=matchArray[1];
		var domain=matchArray[2];
		if (user.match(userPat) == null) {
			return false;
		}
		var IPArray = domain.match(ipDomainPat);
		if (IPArray != null) {
			for (var i = 1; i <= 4; i++) {
				if (IPArray[i] > 255) {
					return false;
				}
			}
			return true;
		}
		var domainArray=domain.match(domainPat);
		if (domainArray == null) {
			return false;
		}
		var atomPat=new RegExp(atom,"g");
		var domArr=domain.match(atomPat);
		var len=domArr.length;
		if ((domArr[domArr.length-1].length < 2) ||
				(domArr[domArr.length-1].length > 3)) {
			return false;
		}
		if (len < 2) {
			return false;
		}
		return true;
	}
	
	function ValidateSignInFormAndSubmit(formName, action){
/*		if(!document.getElementsByTagName("input")[2].checked && !document.getElementsByTagName("input")[3]){
			alert('Please select one of the ');
		}*/
		submitform = 1;
		if(document.getElementsByTagName("input")[3].checked){ //existing user
			//alert('Existing user');
			if(!checkEmail(document.getElementsByTagName("input")[4].value)){ //invalid email
				alert('Invalid email id');
				submitform =0;
			}
		}
		
		if(submitform==1){
			//alert('Sumbitting form');
			oForm = document.forms[formName];
			oForm.formAction.value = action;
			oForm.submit();
		}
	}	
	function DisplayItemDetails(formName, itemID){
		oForm = document.forms[formName];
		oForm.formAction.value = "SHOW_DETAILS";
		oForm.selectedItemID.value=itemID;
		oForm.submit();	
	}
	function loadTargetCatalogData(formName, action){
		oForm = document.forms[formName];
		oForm.formAction.value = action;
		oForm.submit();			
	}
	function SetActionSubmitFormWithStrutsValidation(formName, action, validateForm){
		oForm = document.forms[formName];
		oForm.formAction.value = action;
		//alert('SetActionSubmitFormWithStrutsValidation (lbr.js)called ......');
		ret = validateForm(oForm);
		//alert(ret);
		if(ret){
			//alert('Sumbitting form');
			oForm.submit();
		}
		//else 
			//alert('Ignoring form submit');
		return null;
	}	
	function calculateCost(quantity){
		//alert('OK1');
		document.forms['MainForm'].currcost.value=document.forms['MainForm'].rate.value*quantity;
	}
	function SetActionSubmitFormWithStrutsValidationX(){
		alert('OK1');
	}	
	
function GetTotalOrdercost(formName, itemID){
	oForm = document.forms[formName];
	oForm.formAction.value = "DELETE_SELECTED_ITEM";
	oForm.selectedItemID.value=itemID;
	oForm.submit();	
}	
function DeleteItem(formName, itemID){
	//alert('Delete from lbr.js');
	oForm = document.forms[formName];
	oForm.formAction.value = "DELETE_SELECTED_ITEM";
	oForm.selectedDeleteItemID.value=itemID;
	oForm.submit();	
}

function DisplayItemDetails(formName, itemID){
	oForm = document.forms[formName];
	oForm.formAction.value = "SHOW_DETAILS";
	oForm.selectedItemID.value=itemID;
	oForm.submit();	
}
function loadTargetCatalogData(formName, action){
	oForm = document.forms[formName];
	oForm.formAction.value = action;
	oForm.submit();			
}
function ToggleCartDetails(divID){
	if(document.getElementById('shoppingDetailsHrefID').childNodes[0].nodeValue.indexOf("Hide"))
		showdiv(divID);	
	else
		hidediv(divID);
}
function hidediv(divID) {
	if (document.getElementById) { // DOM3 = IE5, NS6
		document.getElementById(divID).style.visibility = 'hidden';
		//alert(document.getElementById('shoppingDetailsHrefID').childNodes[0].nodeValue);
		document.getElementById('shoppingDetailsHrefID').childNodes[0].nodeValue='Show Details';
	}
	else {
		if (document.layers) { // Netscape 4
			document.hideshow.visibility = 'hidden';
		}
		else { // IE 4
			document.all.hideshow.style.visibility = 'hidden';
		}
	}
}

function showdiv(divID) {
	if (document.getElementById) { // DOM3 = IE5, NS6
		document.getElementById(divID).style.visibility = 'visible';
		//alert(document.getElementById('shoppingDetailsHrefID').childNodes[0].nodeValue);
		document.getElementById('shoppingDetailsHrefID').childNodes[0].nodeValue='Hide Details';
	}
	else {
		if (document.layers) { // Netscape 4
			alert('2');
			document.hideshow.visibility = 'visible';
		}
		else { // IE 4
			alert('3');
			document.all.hideshow.style.visibility = 'visible';
		}
	}
}

function detectBrowser(){  // 
	var browserName=navigator.appName; 
	if (browserName.indexOf("Microsoft")!= -1)
	{ 
		return 1; //alert("Hi Netscape User!");
	}
	else if (browserName.indexOf("Mozilla")!= -1)
	{
		return 2; //alert("Hi, Explorer User!");
	}	
	else if (browserName.indexOf("Netscape")!= -1)
	{
		return 2; //alert("Hi, Explorer User!");
	}
	else
	{
//		return -1; alert("What ARE you browsing with here?");
	}
}

function getElementValue(fieldName){
	   var contents = document.getElementsByName(fieldName)[0];
	   //alert(fieldName+ '-- :  '+contents.value);
	   return contents.value;
}

function setElementValue(fieldName, newValue){
	   var contents = document.getElementsByName(fieldName)[0];
	   //alert(contents+ ':  '+contents.value);
	  // if(detectBrowser()==2)
		   contents.value = newValue;
		   //contents.value=newValue;
	   //else if(detectBrowser()==1){ // IE
		//   contents.value = newValue;
	   
}

function InitiateCalendar(fieldName, anchor, fieldToBeChanged, changeEndDateAlso){
	CallCalendarMethod(fieldName, anchor, changeEndDateAlso);
}

function AddTimeStamp(formName, fieldName){
	//alert('AddTimeStamp called');
	//alert('AddTimeStamp completed');
	oForm = document.forms[formName];
	alert('FILED -- > '+oForm.fieldName.value);
	oForm.fieldName.value = oForm.fieldName.value+' 10:00:00';
}

function SetActionSubmitForm(formName, action){
	//alert('OK1');
	oForm = document.forms[formName];
	oForm.formAction.value = action;
	oForm.submit();
}

function SetActionSubmitForm2(formName, action){
	//alert('OK1');
	oForm = document.forms[formName];
	oForm.formUserAction.value = action;
	oForm.submit();
}

function loadSubCategoryData() {
	//alert('loadSubCategoryData  called....');
	var index = document.getElementsByName('category')[0].value;
	if(index>0){
		url = ajaxURL+"/UserPreference.do?action=add&ajax=yes&category="+index;
		sendAJAXGetRequest(url, updateSubcategory);
	}
}

function checkUserNameAvailability() {
	//alert(detectBrowser());
	//alert('checkUserNameAvailability  called....');
	//document.getElementsByName("unameavailability")[0].setAttribute("type", "hidden");
	var elem = document.getElementById("unameavailability");
	
   if(detectBrowser()==2)
	   elem.firstChild.nodeValue=" ";
   else if(detectBrowser()==1){
	   elem.value=" ";
   }	
	
	elem.removeAttribute("style");
	var potentialUserName = document.getElementsByName('userid')[0].value;
	if(potentialUserName!=""){
		url = ajaxURL+"/UserRegister.do?action=add&ajax=yes&userName="+potentialUserName;
		//alert('Ajax Request--> '+url);
		sendAJAXGetRequest(url, displayUserNameAvailability);
	}
}

function sendAJAXGetRequest(url, methodToBeInvokedAfterAjax){
	 var req = newXMLHttpRequest();
     //alert(req.toString());
	 req.onreadystatechange = getReadyStateHandler(req, methodToBeInvokedAfterAjax);
	 //req.open("GET", "lbr?action=add", true);  //  relative urls  do not work ??
	 req.open("GET", url, true);	 
	 req.send(null);
	 
	//for POST
	//url = ajaxURL+"/UserPreference.do";
    //postparams="action=add&item=6&category="+index;
}

function sendAJAXPostRequest(url, methodToBeInvokedAfterAjax, postparams){
	  var req = newXMLHttpRequest();
	  req.open("POST", url, true);
	  req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	  req.send(postparams);
}

/**
 * Update the page with the Ajax XML response
 * @param lbrXML
 */
function updateSubcategory(lbrXML) {
	//alert('updateCategory()  called...  XML==>!!'+lbrXML);
	var lbr = lbrXML.getElementsByTagName("lbr")[0];
	if(alert==null)
		alert('Seems like ajax getting served from cache... clear cache');
	var generated = lbr.getAttribute("generated");
	//alert('generated-> '+generated);
	if (generated > lastUpdate) {
		lastUpdate = generated;
		var contents = document.getElementsByName('subcategory')[0];
		contents.innerHTML = "";
		//alert('contents.innerHTML-> '+contents.innerHTML);
		var subCatNames = lbr.getElementsByTagName("subcategory")[0].getElementsByTagName("name");
		var subCatIDs = lbr.getElementsByTagName("subcategory")[0].getElementsByTagName("catid");
		// alert('numElems='+items.length);
		for (var I = 0 ; I < subCatNames.length ; I++) {
			var subCatName = subCatNames[I].firstChild.nodeValue;
			var subCatID = subCatIDs[I].firstChild.nodeValue;
			// alert(subcat);
			//var name = item.getElementsByTagName("name")[0].firstChild.nodeValue;
			//var quantity = item.getElementsByTagName("quantity")[0].firstChild.nodeValue;
			var attr = document.createAttribute('value');
			attr.nodeValue  = subCatID;
			AddChild(document, contents, "option", subCatName, attr);
		}
	}
}

function displayUserNameAvailability(lbrXML) {
	//alert(navigator.appName);
	 //alert('displayUserNameAvailability()  called...  XML==>!!'+lbrXML);
	// alert('lbrXML.getElementsByTagName("usernameAvail")[0]-->' +lbrXML.getElementsByTagName("usernameAvail")[0]);
	 var answer = lbrXML.getElementsByTagName("usernameAvail")[0].getElementsByTagName("avail")[0];
	 //alert('Answer= '+answer.firstChild.nodeValue);
	  // var contents = document.getElementsByName("unameavailability")[0];
	 var attr = document.createAttribute('src'); // for success failure ICON image (e.g <img height="15" width="15" src="/images/success.png">)
	 // getElementById works in IE only if the name and id attribute of the element is the same. strange but thats  the way it works
	 var contents = document.getElementById("unameavailability");
	 //alert(document.getElementsByTagName("IMG").length);
	if(document.getElementsByTagName("IMG")!=null && document.getElementsByTagName("IMG").length > 0)
		 contents.removeChild(document.getElementsByTagName("IMG")[0]);
	 //alert('index of NOT->'+answer.firstChild.nodeValue.indexOf("NOT"));
	 if(answer.firstChild.nodeValue.indexOf("NOT") == -1){ // success
		 //alert('make it red->'+ answer.firstChild.nodeValue.indexOf("NOT"));
		 attr.nodeValue  = "/images/success.png";
		 if(detectBrowser()==1) // IE special
			 contents.style.setAttribute('cssText', "color:  #347C2C"); 
		 else
			 contents.setAttribute("style", "color:  #347C2C");
	 }
	 else{ 
		 //alert('make it green->'+ answer.firstChild.nodeValue.indexOf("NOT"));
		 attr.nodeValue  = "/images/error.png";
		 if(detectBrowser()==1)
			 contents.style.setAttribute('cssText', "color:  #FF0000");
		 else		 
			 contents.setAttribute("style", "color:  #FF0000");
	 }
	   //alert('contents= '+contents.nodeValue);
	   //contents.innerHTML = "";
		
	  AddChild(document, contents, "IMG", null, attr);
	   if(detectBrowser()==2)
		   contents.firstChild.nodeValue=answer.firstChild.nodeValue;
		   //contents.firstChild.nodeValue=" <img height=/"15/" width=/"15/" src=/"/images/success.png/">/" + answer.firstChild.nodeValue;
	   else if(detectBrowser()==1){ // IE
		   contents.innerHTML = answer.firstChild.nodeValue;
	   }
	}

/*function updateSubcategoryOrig(lbrXML) {
	alert('updateSubcategoryOrig()  called !!');
 var lbr = lbrXML.getElementsByTagName("lbr")[0];
 var generated = lbr.getAttribute("generated");
 if (generated > lastUpdate) {
   lastUpdate = generated;
   var contents = document.getElementById("contents");
   contents.innerHTML = "";

   var items = lbr.getElementsByTagName("item");
   for (var I = 0 ; I < items.length ; I++) {

     var item = items[I];
     var name = item.getElementsByTagName("name")[0].firstChild.nodeValue;
     var quantity = item.getElementsByTagName("quantity")[0].firstChild.nodeValue;

     var listItem = document.createElement("li");
     listItem.appendChild(document.createTextNode(name+" x "+quantity));
     contents.appendChild(listItem);
   }

 }
 document.getElementById("total").innerHTML = lbr.getAttribute("total");
}*/

function AddChild(DOC, PARENT, CHILD, CHILDTEXT, attribute){
	var ChildElement;
	if (typeof(CHILD) == "string"){
		ChildElement = DOC.createElement(CHILD);
	}else{
		ChildElement = CHILD;
	}
	if (typeof CHILDTEXT != "undefined" && CHILDTEXT != null){
		var ChildText = DOC.createTextNode(CHILDTEXT);
		ChildElement.appendChild(ChildText);
	}
	if (typeof attribute != "undefined"){
			ChildElement.setAttributeNode(attribute); 
	}
	//alert('step6');
	PARENT.appendChild(ChildElement);
	return ChildElement;
}
