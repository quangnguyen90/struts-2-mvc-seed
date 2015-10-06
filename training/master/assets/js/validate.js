var filterEmail = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
var regEx = /^[a-zA-Z0-9\-]+$/;

/*
 * Function validate email
 * input: email
 * is email return true, else return false
 */
function isEmail(email){
	if(filterEmail.test(email) == false)
		return false;
	else
		return true;
}

/*
 * Function validate value
 * input: value, length
 * if > length return false, else return true
 */
function isValidLength(val, length){
	if(val.length > length)
		return false;
	else
		return true;
}

/*
 * Function validate value
 * input: value
 * true: not null, false: null
 */
function isValidNull(val){
	if(val.length > 0)
		return true;
	else
		return false;
}

/*
 * Function validate number
 * input: value
 * if is number return false, else return true
 */
function isNumber(val){
	return isNaN(val);
}


/*
 * Function set true or false listCheckbox
 * input: list, true or false
 */
function setCheckedListCheckbox(list, value){
	for(i=0;i<list.length;i++)
		list[i].checked = value;
}