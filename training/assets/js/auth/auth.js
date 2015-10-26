//Constructor
function Auth() {

}
// function
Auth.prototype = {
	loadDistricts : function(cityId) {
		$("#district").find('option').remove().end();
		$.ajax({
			type : 'GET',
			headers:{
				Accept: "application/json; charset=utf-8","Content-type":"application/json; charset=utf-8",
			},
			url : 'getDistrictList.html',
			data: { cityId: cityId },
			success : function(data, status) {
				for ( var i = 0; i < data.records.length; i++) {
					var districtID = data.records[i].districtId;
					$("#district").append("<option value=" + data.records[i].districtId + ">" + data.records[i].districtName + "</option>");
				}
			},
			error : function(er) {
				alert("Something wrong while getting district");
			}
		});
	},
}

// Instance
authIs = new Auth();