$(function () {
    var rootURL = "http://localhost:8080/Info5059Case2-war/webresources/vendors/"
    
    //event handler for vendor# click
    $('#chooseVendor').click(function() {
        $.getJSON(rootURL + "getAVendor/" + $('#vendorno').val(), null, function(data, status, jqXHR) {
            renderVendorInfo(data);
        }).error(function(jqXHR, textStatus, errorThrown) {
            console.log(textStatus + " - " +errorThrown);
        });


    });
    
});

function renderVendorInfo(data) {
    $('#vendorName').text(data.name);
    $('#vendorEmail').text(data.email);
    $('#vendorPhone').text(data.phone);
    $('#vendorType').text(data.type);
    $('#vendorAddress').text(data.address1);
    $('#vendorCity').text(data.city);
    $('#vendorPostal').text(data.postalCode);
    $('#vendorProvince').text(data.province);
}


