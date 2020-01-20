jQuery( document ).ready(function( $ ) {
	"use strict";
		$('.owl-carousel').owlCarousel({
		    items:4,
		    lazyLoad:true,
		    loop:true,
		    dots:true,
		    margin:20,
		    responsiveClass:true,
			    responsive:{
			        0:{items:1,},
			        600:{items:2,},
			        1000:{items:4,}
			    }
		});
});

$(document).ready(function () {
    var links = $('.navbar-nav a');
    $.each(links, function (key, va) {
        if (va.href == document.URL) {
            $(this).addClass('active');
        }
    });
});
// $("#filtersForm").ajaxForm({url: '/sklep', type: 'post'});

// function submitFilter(){
// 	// document.getElementById("filtersForm").submit();
// 	var form = document.getElementById("filtersForm");
// 	var url = $(form).attr('action');
//
// 	$.ajax({
// 		type: "POST",
// 		url: url,
// 		data: $(form).serialize(), // serializes the form's elements.
// 		success: function(data)
// 		{
// 			alert(data); // show response from the php script.
// 		}
// 	});


function sendAjax() {
	var searchByProductName = $('#searchByProductName').val();
	var minPrice = $('#minPrice').val();
	var maxPrice = $('#maxPrice').val();
	var chosenCategory = $('#chosenCategory').val();
	var listOrder = $('#ListOrder').val();
	var chosenLayout = $('#ChosenLayout').val();
	var isInStock;
	if (document.getElementById('isInStock').checked) {
		isInStock = "true";
	} else {
		isInStock = "false";
	}

	$.ajax({
		url: "tester",
		type: 'POST',
		data: {
			searchByProductName : searchByProductName,
			minPrice : minPrice,
			maxPrice : maxPrice,
			isInStock : isInStock,
			chosenCategory : chosenCategory,
			listOrder : listOrder
		},

		success: function (data) {
			var div = document.getElementById('ListOfProducts');
			var product = "";
			var layoutClassNamePart;
			switch(chosenLayout){
				case '1': layoutClassNamePart = 12; break;
				case '2': layoutClassNamePart = 4; break;
				case '3': layoutClassNamePart = 3; break;
			}

			if(layoutClassNamePart==12){
				for(var i = 0; i < data.list.length; i++) {
					product += '<div id="product-' + data.list[i].id + '" class="item col-md-' + layoutClassNamePart + '">';
					product += '<div class="featured-item row simple-style-product-image">';
					product += '<div class="col-md-4">';
					product += '<a href="/sklep/produkt?id=' + data.list[i].id + '">';
					if(data.list[i].imageOne) {
						product += '<img class="" src="' + data.list[i].imageOne + '" alt="' + data.list[i].product_name + ' - ' + data.list[i].category + '">';
					} else {
						product += '<img class="" src="/assets/images/products/product-placeholder.jpg" alt="' + data.list[i].product_name + ' - ' + data.list[i].category + '">';
					}
					product += '</a>';
					product += '</div>';
					product += '<div class="col-md-8">';
					product += '<h3>' + data.list[i].product_name + ' - ' + data.list[i].category + '</h3>';
					if(data.list[i].sale_price>0) {
						product += '<h6><span class="greyed-price">' + parseFloat(data.list[i].price).toFixed(2) + ' zł</span> ' + parseFloat(data.list[i].sale_price).toFixed(2) + ' zł</h6>';
					} else {
						product += '<h6>' + parseFloat(data.list[i].price).toFixed(2) + ' zł</h6>';
					}
					product += '<p><b>Na stanie: ' + data.list[i].quantity + ' szt.</b></p>';
					var description = data.list[i].description;
					var words = description.split(" ");
					if(words.length>50) {
						description = words.splice(0, 50).join(" ") + " ... <a href=\"/sklep/produkt?id=" + data.list[i].id + "\">Zobacz więcej</a>.";
					}

					product += '<p>' + description + '</p>';
					product += '</div>';
					product += '</div>';
					product += '</div>';
				}
			} else {
				for(var i = 0; i < data.list.length; i++) {
					product += '<div id="product-' + data.list[i].id + '" class="item col-md-' + layoutClassNamePart + '">';
					product += '<a href="/sklep/produkt?id=' + data.list[i].id + '">';
					product += '<div class="featured-item">';
					if(data.list[i].imageOne) {
						product += '<img src="' + data.list[i].imageOne + '" alt="' + data.list[i].product_name + ' - ' + data.list[i].category + '">';
					} else {
						product += '<img src="/assets/images/products/product-placeholder.jpg" alt="' + data.list[i].product_name + ' - ' + data.list[i].category + '">';
					}
					product += '<h4>' + data.list[i].product_name + '</h4>';
					if(data.list[i].sale_price>0) {
						product += '<h6><span class="greyed-price">' + parseFloat(data.list[i].price).toFixed(2) + ' zł</span> ' + parseFloat(data.list[i].sale_price).toFixed(2) + ' zł</h6>';
					} else {
						product += '<h6>' + parseFloat(data.list[i].price).toFixed(2) + ' zł</h6>';
					}
					product += '</div>';
					product += '</a>';
					product += '</div>';
				}
			}

			div.innerHTML = product;
			// alert(data.additional.amountOfPages);
			// data.additional.amountOfPages
		}
	});
}
function ifNullPutZero(input) {
	if(!$(input).val()) {
		$(input).val(0);
	}
}

function ifLessThanZeroPutZero(input) {
	if($(input).val()<0){
		$(input).val(0);
	}
}

function ifValueOverMaxPutMax(input) {
	if($(input).val()>99999){
		$(input).val(99999);
	}
}

function formatNumber(input) {
	input.value = parseFloat(input.value)
		.toFixed(2)
		.toString()
}

function checkIfProperPriceValue(input) {
	ifNullPutZero(input);
	ifLessThanZeroPutZero(input);
	ifValueOverMaxPutMax(input);
	formatNumber(input);
}

function checkForbiddenCharacters(input) {
	if($(input).val().indexOf('\'')>=0) {
		$(input).val($(input).val().replace('\'', ''));
	}
	if($(input).val().indexOf('\"')>=0) {
		$(input).val($(input).val().replace('\"', ''));
	}
	if($(input).val().indexOf('%')>=0) {
		$(input).val($(input).val().replace('%', ''));
	}
	if($(input).val().indexOf('_')>=0) {
		$(input).val($(input).val().replace('_', ''));
	}
}

function checkIfProperSearchValue (input) {
	checkForbiddenCharacters(input);
}

function changeCategory(category, clickedElementClassName) {
	var chosenCategory = $('#chosenCategory');
	chosenCategory.val(category);

	var categoryButtons = $('.category-button');
	for (var i = 0; i < categoryButtons.length; i++) {
		$(categoryButtons[i]).removeClass('active');
	}
	var clickedElement = $('.' + clickedElementClassName);
	clickedElement.addClass('active');

	if(clickedElementClassName=='category-button-0'){
		$('#PageTitle').text("Sklep");
	} else {
		$('#PageTitle').text("Sklep - " + clickedElement.text());
	}

	sendAjax();
}

function changeListOrder(option){
	$('#ListOrder').val(option);
	sendAjax();
}

function changeStyle(option, optionId) {
	$('#ChosenLayout').val(option);
	var styleButtons = $('.style-button');

	for (var i = 0; i < styleButtons.length; i++) {
		$(styleButtons[i]).removeClass('active');
	}
	$('#' + optionId).addClass('active');

	switch(option){
		case 1:
			$('#perPage').html(
				'<option>4</option>' +
				'<option>8</option>' +
				'<option>12</option>');
			break;
		case 2:
			$('#perPage').html(
				'<option>9</option>' +
				'<option>18</option>' +
				'<option>27</option>');
			break;
		case 3:
			$('#perPage').html(
				'<option>16</option>' +
				'<option>24</option>' +
				'<option>32</option>');
			break;
	}

	sendAjax();
}

function setActive(button) {
	var allButtons = $('.order-button');
	for (var i = 0; i < allButtons.length; i++) {
		$(allButtons[i]).removeClass('active');
	}
	$(button).addClass('active');
}

function verifyQuantity(maxValue) {
	var input = $('#quantity');
	if(input.val() % 1 === 0) {
		if(input.val()<1) { input.val(1); }
		if(input.val()>maxValue) { input.val(maxValue); }
		if(maxValue==0){
			input.prop('disabled', true);
		}
	} else {
		input.val(1);
	}
}