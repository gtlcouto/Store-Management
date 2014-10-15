$(function () {
    var rootURL = "http://localhost:8080/Info5059Case2-war/webresources/vendorlist/";
    //var rootURL = "http://ec2-54-200-109-228.us-west-2.compute.amazonaws.com:8080/Info5059Case2-war/webresources/vendorlist/"
    //var rootURL = "webresources/vendorlist/";
    $("#poGrid").hide();
    var grid, blocka, blockb, blockc, blockd, blocke;
    getAllVendors(rootURL);
    
    //event handler for vendor click
    $('#chooseVendor').click(function() {
        getAllPOsForVendor($('#vendornos').val(), rootURL);
    });
    
    $('#choosePO').click(function() {
        renderPO($('#ponos').val());
    });
    
    
    
});

function getAllPOsForVendor(vendorno, rootURL) {
    $.getJSON(rootURL + "POList/" + vendorno, null, function(data, status, jqXHR) {
            renderPOList(data);
        }).error(function(jqXHR, textStatus, errorThrown) {
            console.log(textStatus + " - " +errorThrown);
        });
}

function renderVendorList(data) {
    $(data).each(function() {
        $('#vendornos').append("<option>" + this + "</option>");
        
    });
}
    
function renderPOList(data){
    if (data.length > 0) {
        $('#ponos').empty();
        $(data).each(function() {
            $('#ponos').append("<option>" + this.ponumber + "</option>");
        });
        $("#poGrid").show();
        poData = data;
    }else{
        $("#poGrid").hide();
        $("#container").hide();
        alert("No purchase orders for this vendor!");
    }
        
}

function renderPO(poId) {
    $("container").remove();
    collapse = "";
    collapse = $("<div>").attr({
        'data-role': "collapsible",
        'id': "container"
    });
    
    tot = 0.0;
    tax = 0.0;
    
    var h3 = $("<h3>");
    $(collapse).append(h3);
    
    grid = getGrid(3);
    grid.attr({'style': "text-align:center;font-size:12px;font-family:verdana;"});
    blockb.text("Products");
    grid = getGrid(5);
    grid.attr({'style': "text-align:center;font-size:12px;font-family:verdana;"});
    blocka.text("Code");
    blockb.text("Name");
    blockc.text("Price");
    blockd.text("Qty");
    blocke.text("Ext.");
    grid = getGrid(3);
    grid.attr({'style':"padding-top:10px;"});
    blocka.text("");
    blockb.text("");
    blockc.text("");


    
    $(poData).each(function() {
        if(this.ponumber == poId) {
            h3.text("PO# " + poId + " - " + this.podate);
            $(this.items).each(function() {
                if(this.qty > 0) {
                    grid = getGrid(5);
                    grid.attr({'style': "text-align:center;font-size:12px;font-family:verdana;"});
                    blocka.text(this.prodcd);
                    blockb.text(this.prodnam.substring(0,8));
                    blockc.text(cur(this.price));
                    grid.attr({'style': "text-align:right;"});
                    blockd.text(this.qty);
                    grid.attr({'style': "text-align:right;"});
                    var ext = (this.price * this.qty);
                    blocke.text(cur(ext));
                    tot = tot + ext;      
                }
                    
            });
            grid = getGrid(5);
            grid.attr({'style': "text-align:right;font-size:12px;font-family:verdana;"});
            blocka.text("");
            blockb.text("");
            blockc.text("");
            blockd.text("");
            blocke.text("-------------");
            grid = getGrid(5);
            grid.attr({'style': "text-align:right;font-size:12px;font-family:verdana;"});
            blocka.text("");
            blockb.text("");
            blockc.text("");
            blockd.text("Sub:");
            blocke.text(cur(tot));

            tax = (tot / 100)*13;
            grid = getGrid(5);
            grid.attr({'style': "text-align:right;font-size:12px;font-family:verdana;"});
            blocka.text("");
            blockb.text("");
            blockc.text("");
            blockd.text("Tax:");
            blocke.text(cur(tax));

            tot = tot + tax;
            grid = getGrid(5);
            grid.attr({'style': "text-align:right;font-size:12px;font-family:verdana;"});
            blocka.text("");
            blockb.text("");
            blockc.text("");
            blockd.text("Total:");
            blocke.text(cur(tot));
        }
    });
    
    $(collapse).appendTo($("#poset"));
    $(collapse).collapsible({refresh: true});
}

//obtained from the internet
function cur(num) {
    num = num.toString().replace(/\$|\,/g, '');
    if(isNaN(num))
        num = "0";
    sign = (num == (num = Math.abs(num)));
    num = Math.floor(num * 100 + 0.5000000001);
    cents = num % 100;
    num = Math.floor(num / 100).toString();
    if(cents < 10)
        cents = "0" + cents;
    for (var i = 0; i < Math.floor((num.length - (1 + i)) /3); i++)
        num = num.substring(0, num.length - (4*i+3)) + ',' +
        num.substring(num.length - (4*i+3));
    return (((sign) ? '' : '-') + '$' + num + '.' + cents);
}

function getBlock(num){
    var block;
    switch(num){
        case 1:
        block = $("<div>").attr({
            'class': "ui-block-a"
        });
        break;
        case 2:
        block = $("<div>").attr({
            'class': "ui-block-b"
        });
        break;
        case 3:
        block = $("<div>").attr({
            'class': "ui-block-c"
        });
        break;
        case 4:
        block = $("<div>").attr({
            'class': "ui-block-d"
        });
        break;
        case 5:
        block = $("<div>").attr({
            'class': "ui-block-e"
        });
        break;
    }
    return block;
}

function getGrid(num) {
    blocka = getBlock(1);
    blockb = getBlock(2);
    blockc = getBlock(3);
    blockd = getBlock(4);
    blocke = getBlock(5);
    switch(num){
        case 2:
            grid = $("<div>").attr({
            'class': "ui-grid-a"
            });
            blocka.appendTo(grid);
            blockb.appendTo(grid);
            break;
        case 3:
            grid = $("<div>").attr({
            'class': "ui-grid-b"
            });
            blocka.appendTo(grid);
            blockb.appendTo(grid);
            blockc.appendTo(grid);
            break;
        case 4:
            grid = $("<div>").attr({
            'class': "ui-grid-c"
            });
            blocka.appendTo(grid);
            blockb.appendTo(grid);
            blockc.appendTo(grid);
            blockd.appendTo(grid);
            break;
        case 5:
            grid = $("<div>").attr({
            'class': "ui-grid-d"
            });
            blocka.appendTo(grid);
            blockb.appendTo(grid);
            blockc.appendTo(grid);
            blockd.appendTo(grid);
            blocke.appendTo(grid);
            break;
    }
    $(collapse).append(grid);
    return grid;
    
}

function getAllVendors(rootURL) {
    $.getJSON(rootURL + "vendorlist", null, function(data, status, jqXHR) {
        renderVendorList(data);
    }).error(function(jqXHR, textStatus, errorThrown) {
        console.log(textStatus + " - " + errorThrown);
    });
}



