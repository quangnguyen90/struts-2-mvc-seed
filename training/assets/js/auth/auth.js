//Constructor
function Auth() {

}
// function
Auth.prototype = {
	loadDistricts : function(value1) {
		$("#district").find('option').remove().end();
		$.ajax({
			type : 'GET',
			url : '/training/getDistrictList/' + value1,
			/* data: { city_ID: value1 }, */
			contentType : "application/json; charset=utf-8",
			dataType : 'json',
			success : function(data) {
				/*for ( var i = 0; i < data.district.length; i++) {
					var districtID = data.district[i].districtID;
					$("#district").append(
							"<option value=" + data.district[i].districtid
									+ ">" + data.district[i].districtName
									+ "</option>");
				}*/
				alert(data);
			},
			error : function(er) {
				alert("Something wrong while getting district");
			}
		});
	},
}

// Instance
authIs = new Auth();