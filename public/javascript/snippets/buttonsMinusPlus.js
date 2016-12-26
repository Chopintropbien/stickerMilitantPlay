//original http://bootsnipp.com/snippets/featured/buttons-minus-and-plus-in-input
$( document ).ready(function() {
  $.fn.bootnumberspiner = function(options) {
  	var spinners = $(this);
    
    $(spinners).each(function (key,spinner) {
        spinner.settings = $.extend({
	    	width:"150px",
      		value:0,
            id:key,
      		min_value:0,
      		max_value:100,
      		name:"quint",
      		minus_icon:"fa fa-minus",
      		plus_icon:"fa fa-plus",
          	onChange:function(){},
          	onCreate:function(){}
	    }, options);
        
        var $spinner = $(spinner);
      
        spinner.settings.value = $spinner.attr('data-value') || spinner.settings.value;
        spinner.settings.id = $spinner.attr('data-id') || spinner.settings.id;
      	spinner.settings.name = $spinner.attr('data-name') || spinner.settings.name;
      
      	$spinner.css("width",spinner.settings.width);
    	$spinner.html(get_content(spinner));
        
        bind_click($spinner,spinner);
        focusin($spinner,spinner);
        focusout($spinner,spinner);
        change($spinner,spinner);
        spinner.settings.onCreate(spinner);
        
    });
    
    
    
    
    function change($spinner,spinner){
        var input_number = $spinner.find('.input-number');
    
        $(input_number).change(function() {
    
          minValue =  parseInt($(this).attr('min'));
          maxValue =  parseInt($(this).attr('max'));
          valueCurrent = parseInt($(this).val());

          
          name = $(this).attr('name');
          if(valueCurrent >= minValue) {
              $spinner.find(".btn-number[data-type='minus'][data-field='"+name+"']").removeAttr('disabled')
          } else {
              $(this).val($(this).data('oldValue') || minValue);
          }
          if(valueCurrent <= maxValue) {
              $spinner.find(".btn-number[data-type='plus'][data-field='"+name+"']").removeAttr('disabled')
          } else {
            $(this).val($(this).data('oldValue') || maxValue);
          }
          
          spinner.settings.onChange(valueCurrent,spinner);
    	});
    }
    
    function focusin($spinner,spinner){
        var input_number = $spinner.find('.input-number');


    	$(input_number).focusin(function(){
         	$(this).data('oldValue', $(this).val());
      	});

    }
    function focusout($spinner,spinner){
        var input_number = $spinner.find('.input-number');


      	$(input_number).focusout(function(){
      	    var currentVal = Number($(this).val());
            if (isNaN(currentVal)) {
                $(this).val($(this).data('oldValue'));
            }
            else{
                var v = Math.max($(this).attr('max'), Math.round(currentVal));
                $(this).val(Math.round(v));
            }
        });
    }

    
    
    
    function bind_click($spinner,spinner){
      var btn_number = $spinner.find('.btn-number');
      
         $(btn_number).click(function(e){
          e.preventDefault();
          
          fieldName = $(this).attr('data-field');
          type      = $(this).attr('data-type');
          var input = $spinner.find("input[name='"+fieldName+"']");
          var currentVal = parseInt(input.val());
        
          if (!isNaN(currentVal)) {
              if(type == 'minus') {
                  
                  if(currentVal > input.attr('min')) {
                      input.val(currentVal - 1).change();
                  } 
                  if(parseInt(input.val()) == input.attr('min')) {
                      $(this).attr('disabled', true);
                  }
      
              } else if(type == 'plus') {
      
                  if(currentVal < input.attr('max')) {
                      input.val(currentVal + 1).change();
                  }
                  if(parseInt(input.val()) == input.attr('max')) {
                      $(this).attr('disabled', true);
                  }
      
              }
          } else {
              input.val(0);
          }
      });
    } 
    
    
    
    function get_content(spinner){
	    var content = "", 
            value = spinner.settings.value, //disabled="disabled";
            minDisabled,
            maxDisabled,
            disabled = 'disabled="disabled"';
		
        if(spinner.settings.min_value > spinner.settings.value  )
        {
          value = spinner.settings.min_value;
        }else if(spinner.settings.max_value < spinner.settings.value){
      	  value = spinner.settings.max_value;	
        }
      
        minDisabled = value <= spinner.settings.min_value  ? disabled  : '';
        maxDisabled = value >= spinner.settings.max_value  ? disabled  : '';  
      
        
      
		content += '<div class="input-group">';
		content += '<span class="input-group-btn">';
		content += '<button type="button" class="btn btn-danger btn-number" data-type="minus" data-field="'+spinner.settings.name+'['+spinner.settings.id+']" '+minDisabled+'>';
		content += '<i class="'+spinner.settings.minus_icon+'"></i>';
		content += '</button>';
		content += '</span>';
		content += '<input type="text" name="'+spinner.settings.name+'['+spinner.settings.id+']" class="form-control input-number" value="'+value+'" min="'+spinner.settings.min_value+'" max="'+spinner.settings.max_value+'">';
		content += '<span class="input-group-btn">';
		content += '<button type="button" class="btn btn-success btn-number" data-type="plus" data-field="'+spinner.settings.name+'['+spinner.settings.id+']" '+maxDisabled+'>';
		content += '<i class="'+spinner.settings.plus_icon+'"></i>';
		content += '</button>';
		content += '</span>';
		content += '</div>';
   
	    return 	content;
    }
  }




$('.xxx').bootnumberspiner({
                            onChange:function(valueCurrent,spinner){
                              //console.log(valueCurrent);
                              //console.log(spinner);
                            },
  							onCreate:function(spinner){
                              //console.log(spinner.settings.value);
                            }	
						  });


});
                         