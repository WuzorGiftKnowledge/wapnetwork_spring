

var addSerialNumber=function(){
    var i=0;
    $('table tr').each(function(index){
        $(this).find('td:nth-child(1)').html(index-1+1);
        
    }) ;
    
};
addSerialNumber();