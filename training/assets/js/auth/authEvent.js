$("#dob").datepicker({dateFormat:'dd/mm/yy'});
$("#dob").datepicker("option", "dateFormat", "dd/mm/yy");

function loadDistricts(value){
	authIs.loadDistricts(value);
}

//======================================================================================================================
// Show user image after choose image
function no_found_image()
{
	$('#image_viewaccount')
		            	.attr('src', '/training/assets/img/users-img/nofound.jpg')
		             	.width(200)
				        .height(150);
	document.getElementById("image_viewaccount").value = "";
}

function readURL(input) 
{
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        var mimeType=input.files[0].type; // You can get the mime type
		var mimeSize=input.files[0].size;

		if (mimeType == "image/jpeg" || mimeType == "image/jpg" || mimeType == "image/png") {
			if(mimeSize <= 5242880)
			{
				reader.onload = function (e) {
					$('#image_viewaccount')
					            	.attr('src', e.target.result)
					             	.width(200)
				                    .height(150);
				}

    			reader.readAsDataURL(input.files[0]);
			}
			else {
				alert("Please choose image with size <= 5MB");
				no_found_image();
			}
		}
		else {
			alert("Only allow image type: jpg, jpeg, png");
			no_found_image();
		}
        
    }
}

$("#user-avatar").change(function(){
    readURL(this);
});